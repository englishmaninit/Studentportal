/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theultimatestudentportal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jorda
 */
public class Alert {
    
     public static void AddAlert(long currentUserID,long homeworkID , String message , int prioirty){
        
        try{
            Connection connection=DatabaseConnection.connect();
            
            String checkAlert="SELECT alertid FROM alert" + "WHERE studentid = ? AND homework = ? AND message = ? AND readstatus = FALSE";
        
            PreparedStatement checkStatement = connection.prepareStatement(checkAlert);
            checkStatement.setLong(1,currentUserID);
            checkStatement.setLong(2, homeworkID);
            checkStatement.setString(3, message);
            
            ResultSet existingAlert=checkStatement.executeQuery();
            
        if(existingAlert.next() ){
            System.out.println("Alert already exists");
            
        }else{
            String addAlert="INSERT INTO alert" + "(studentid, homeworkid, message, priority, readstatus, datecreated" + "VALUES(?, ?, ?, ?, FALSE, CURRENT_TIMESTAMP)";
            
            PreparedStatement alertStatement = connection.prepareStatement(addAlert);
            alertStatement.setLong(1,currentUserID);
            alertStatement.setLong(2, homeworkID);
            alertStatement.setString(3, message);
            alertStatement.setInt(4, prioirty);
            
            alertStatement.executeUpdate();
            alertStatement.close();
            
            System.out.println("Alert added");
        }
        
        existingAlert.close();
        checkStatement.close();
        connection.close();
        
        }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
    }
    
    public void DisplayAlerts(long currentUserID){
        try{
            Connection connection = DatabaseConnection.connect();
            
            String getAlerts = "String alertid, homeworkid, message, priority, readstatus, datecreated" + "FROM alert" +"WHERE studentid = ? " + "ORDER BY prioirty ASC, datecreated DESC";
            
            PreparedStatement statement=connection.prepareStatement(getAlerts);
            
            statement.setLong(1,currentUserID);
            ResultSet alerts=statement.executeQuery();
            
            while(alerts.next()){
                long alertID =alerts.getLong("alertid");
                String message = alerts.getString("message");
                int priority  = alerts.getInt("priority");
                boolean readStatus=alerts.getBoolean("readstatus");
                
                System.out.println("AlertID: " + alertID);
                
                System.out.println("Prioirity " + priority);
                System.out.println(message);
                
                if(readStatus==false){
                    System.out.println("UNREAD");
                }
                System.out.println("-----------------");
            }
            
            alerts.close();
            statement.close();
            connection.close();
            
        }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
    }
    
    public void MarkAlertAsRead(long currentUserID, long alertID){
        try{
            Connection connection = DatabaseConnection.connect();
            
            String updateAlert="UPDATE alert" + "SET readstatus = TURE" + "WHERE alertid = ? AND studentod = ? ";
            
            PreparedStatement statement=connection.prepareStatement(updateAlert);
            statement.setLong(1,alertID);
            statement.setLong(2,currentUserID);
            
            statement.executeUpdate();
            
            statement.close();
            connection.close();
            
        }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
    }
    
    public void DismissAlert(long currentUserID, long alertID){
        
        try{
            Connection connection = DatabaseConnection.connect();
            
            String deleteAlert= "DELETE alertid FROM alert" + "WHERE alertid = ? AND studentid = ?";
            
            PreparedStatement statement=connection.prepareStatement(deleteAlert);
            
            statement.setLong(1,alertID);
            statement.setLong(2, currentUserID);
            
            int rowsDeleted = statement.executeUpdate();
            
            if(rowsDeleted > 0){
                System.out.println("Alert deleted successfully");
            }else{
                System.out.println("Alert not found");
            }
            
            statement.close();
            connection.close();
            
        }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
    }
}
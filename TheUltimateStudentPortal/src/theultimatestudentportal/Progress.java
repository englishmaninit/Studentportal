/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theultimatestudentportal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author jorda
 */
public class Progress {
    
    public static void DisplayProgress(long currentUserID){
        String findprogress="SELECT subject, topic, percentage, dateCompleted" + "FROM progress" + "WHERE studentid = ? "+ "ORDER BY datecompleted DESC";
        
        try{
            Connection connection = DatabaseConnection.connect();
            
            PreparedStatement statement=connection.prepareStatement(findprogress);
            statement.setLong(1,currentUserID);
            
            ResultSet result = statement.executeQuery();
            
            System.out.println("======Progress=====");
            
            boolean found = false;
            
            while(result.next()){
                found = true;
                
               String subject = result.getString("subject");
               String topic = result.getString("topic");
               double percentage = result.getDouble("percentage");
               LocalDate dateCompleted =result.getDate("datecompleted").toLocalDate();
               
               System.out.println("Subject " + subject);
               System.out.println("Topic " + topic);
               System.out.println("Score" + percentage + "%");
               System.out.println("Date" + dateCompleted);            
            }
            
            if(!found){
                System.out.println("No Progress found");
            }
            result.close();
            statement.close();
            connection.close();
            
        }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }   
    }   
}
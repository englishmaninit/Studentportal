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
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import static theultimatestudentportal.Alert.AddAlert;

/**
 *
 * @author jorda
 */
public class Homework {
    
    public static String AddHomework (long currentUserID, String subject, String description,String homeworkTitle, String date){
        Scanner scanner=new Scanner(System.in);
        
        boolean titleValid = false;
        boolean subjectValid=false;
        boolean dateValid = false;
        long subjectID = 0;
        LocalDate dueDate = null;
      
        
        try{
            Connection connection = DatabaseConnection.connect();
  
            if("".equals(homeworkTitle)){
                return "Title cannot be empty";
            }else{
                titleValid = true;
            }
            
            if("".equals(subject)){
                return "Enter the subject";
                
            }else {
                String findSubject = "SELECT subjectid FROM subject WHERE studentid = ? AND LOWER(subjectname) = Lower(?)";
                
                PreparedStatement subjectStatement=connection.prepareStatement(findSubject);
                
                subjectStatement.setLong(1,currentUserID);
                
                subjectStatement.setString(2,subject);
                
                ResultSet subjectRecord = subjectStatement.executeQuery();
                
                if(subjectRecord.next()){
                    subjectID=subjectRecord.getLong("subjectid");
                    subjectValid=true;
                }else{
                    System.out.println("Subject not found");
                }
                subjectRecord.close();
                subjectStatement.close();
            }
        try {
          dueDate = LocalDate.parse(date);

        if (dueDate.isBefore(LocalDate.now())) {
           return "Please enter a valid date";
        }

        } catch (DateTimeParseException e) {
         return "Please enter a valid date";
        }
           
        if("".equals(description)){
           return "No description entered";
        }
        
        String checkDuplicate="SELECT homeworkid FROM homework WHERE studentid = ? AND duedate = ? AND completed = FALSE";
        
        PreparedStatement duplicateStatement = connection.prepareStatement(checkDuplicate);
        
        duplicateStatement.setLong(1, currentUserID);
        duplicateStatement.setString(2,homeworkTitle);
        duplicateStatement.setDate(3,java.sql.Date.valueOf(dueDate));
        
        ResultSet duplicateHomework=duplicateStatement.executeQuery();
        
        
        if(duplicateHomework.next()){
            return "Homework has already been created";
        }else{
            String addHomework = "INSERT INTO homework" + "(studentid, subjectid, title, description,duedate, completed , datecreated)"+"VALUES(?,?,?,?,? ,FALSE,CURRENT_TIMESTAMP)" +"RETURNING homeworkid";
            
            PreparedStatement homeworkStatement = connection.prepareStatement(addHomework);
            
            homeworkStatement.setLong(1,currentUserID);
            homeworkStatement.setLong(2,subjectID);
            homeworkStatement.setString(3,homeworkTitle);
            homeworkStatement.setString(4,description);
            homeworkStatement.setDate(5,java.sql.Date.valueOf(dueDate));
            
            ResultSet savedHomework=homeworkStatement.executeQuery();
            
            if(savedHomework.next()){
                long homeworkID=savedHomework.getLong("homeworkid");
                
            long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), dueDate);

            if(daysRemaining <=3){
                AddAlert(currentUserID,homeworkID,"Homework deadline is approaching" +homeworkTitle,2);
            }
        }else{
                return "Homework could not be saved";
            }
            savedHomework.close();
            homeworkStatement.close();
        } 
        
        return "Homework added successfully";
        
        }catch(SQLException e){
            return "Database error";
        }
    
    }
    
    public static void GenerateHomeworkAlert(long currentUserID){
        
        try{
            Connection connection = DatabaseConnection.connect();
            
            String getHomework= "SELECT homeworkid, title, duedate FROM homework WHERE stidentid = ? AND completed = FALSE";
            
            PreparedStatement  homeworkStatement=connection.prepareStatement(getHomework);
            
            ResultSet homeworkList = homeworkStatement.executeQuery();
            
            while(homeworkList.next()){
                long homeworkID=homeworkList.getLong("homeworkid");
                String homeworkTitle = homeworkList.getString("title");
                LocalDate dueDate=homeworkList.getDate("duedate").toLocalDate();
                long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(),dueDate);
                
                 boolean alertAlreadyExists= false;
                 
                 String checkAlert="SELECT slertid FROM alert WHERE studentid = ? AND homeworkid = ? AND readstatus = FALSE";
                 
                 PreparedStatement alertStatement =connection.prepareStatement(checkAlert);
                 
                 alertStatement.setLong(1,currentUserID);
                 alertStatement.setLong(2,currentUserID);
                 
                 ResultSet existingAlert = alertStatement.executeQuery();
                 
                 if(existingAlert.next()){
                     alertAlreadyExists=true;
                 }
                 existingAlert.close();
                 alertStatement.close();
                 
                 if(alertAlreadyExists==false){
                     String message="";
                     int prioirty =0;
                     
                     if(daysRemaining<0){
                        message="Overdue Homework: "+homeworkTitle;
                        prioirty =1;
                        
                         AddAlert(currentUserID,homeworkID,message,prioirty);
                         
                     }else if(daysRemaining==0){
                        message= "Homework due today: " + homeworkTitle;
                        prioirty =1;
                        
                        AddAlert(currentUserID,homeworkID,message,prioirty);
                        
                     }else if(daysRemaining==1){
                        message= "Homework due tomorrow: "+homeworkTitle;
                        prioirty =2;
                        
                        AddAlert(currentUserID,homeworkID,message,prioirty);
                 }else if(daysRemaining <=3){
                     message = "Homework due in: " + daysRemaining + "days" + homeworkTitle;
                        prioirty=2;
                        
                       AddAlert(currentUserID,homeworkID,message,prioirty);
                 }else if(daysRemaining <=7){
                      message = "Upcoming homework " + homeworkTitle;
                        prioirty = 3;
                       AddAlert(currentUserID,homeworkID,message,prioirty);
                    }
                }
            }
            homeworkList.close();
            homeworkStatement.close();
            connection.close();    
        }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
    }
    
    public static String DeleteHomework(long currentUserID, long homeworkID){
        
        try{
            Connection connection = DatabaseConnection.connect();
            
            String deleteHomework= "DELETE homeworkid FROM homework WHERE homeworkid = ? AND studentid = ?";
            
            PreparedStatement statement=connection.prepareStatement(deleteHomework);
            
            statement.setLong(1,homeworkID);
            statement.setLong(2, currentUserID);
            
            int rowsDeleted = statement.executeUpdate();
            
            if(rowsDeleted > 0){
                return "Homework deleted successfully";
            }else{
                return "Homework not found";
            }
              
        }catch(SQLException e){
            return "Database error";
        }
    }
    
    public static String UpdateHomework(long currentUserID, long homeworkID){
         
        try{
            Connection connection = DatabaseConnection.connect();
            
            
            
            connection.close();
        }catch(SQLException e){
            return "Database error";
        }
        return "Homework updates successfully";
    }
}
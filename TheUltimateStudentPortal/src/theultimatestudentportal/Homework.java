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
    
    public void AddHomework (long currentUserID){
        Scanner scanner=new Scanner(System.in);
        
        boolean titleValid = false;
        boolean subjectValid=false;
        boolean dateValid = false;
        long subjectID = 0;
        LocalDate dueDate = null;
        String homeworkTitle="";
        
        try{
            Connection connection = DatabaseConnection.connect();
        
        while(titleValid == false){
            System.out.println("Enter homework title");
            homeworkTitle=scanner.nextLine().trim();
  
            if("".equals(homeworkTitle)){
                System.out.println("Title cannot be empty");
            }else{
                titleValid = true;
            }
        }
        
        while(subjectValid == false){
            System.out.println("Enter the subject");
            String subject=scanner.nextLine();
            
            if("".equals(subject)){
                System.out.println("Enter the subject");
                
            }else {
                String findSubject = "SELECT subjectid FROM subject" + "WHERE studentid = ?"+" AND LOWER(subjectname) = Lower(?)";
                
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
        }
        
        while(dateValid == false){
            System.out.println("Enter the due date (YYYY-MM-DD):");
            String enteredDate=scanner.nextLine();
            
            try{
                
            dueDate=LocalDate.parse(enteredDate);
            
            if(dueDate.isBefore(LocalDate.now())){
                System.out.println("Please enter a valid date");   
            }else{
                dateValid = true;
            }
        }catch(DateTimeParseException e){
            System.out.println("Please enter a valid date");
        }
            
        }
        
        System.out.println("Enter homework description");
        String description = scanner.nextLine().trim();
        
        if("".equals(description)){
            System.out.println("No description entered");
        }
        
        String checkDuplicate="SELECT homeworkid FROM homework" + " WHERE studentid = ? AND duedate = ? AND completed = FALSE";
        
        PreparedStatement duplicateStatement = connection.prepareStatement(checkDuplicate);
        
        duplicateStatement.setLong(1, currentUserID);
        duplicateStatement.setString(2,homeworkTitle);
        duplicateStatement.setDate(3,java.sql.Date.valueOf(dueDate));
        
        ResultSet duplicateHomework=duplicateStatement.executeQuery();
        
        
        if(duplicateHomework.next()){
            System.out.println("Homework has already been created");
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
                System.out.println("Homework added successfully");
            
            
            long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(),dueDate);

            if(daysRemaining <=3){
                AddAlert(currentUserID,homeworkID,"Homework deadline is approaching" +homeworkTitle,2);
            }
        }else{
                System.out.println("Homework could not be saved");
            }
            savedHomework.close();
            homeworkStatement.close();
        } 
        
        duplicateHomework.close();
        duplicateStatement.close();
        connection.close();
        }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
        
    }
    
    public static void GenerateHomeworkAlert(long currentUserID){
        
        try{
            Connection connection = DatabaseConnection.connect();
            
            String getHomework= "SELECT homeworkid, title, duedate FROM homework"+ "WHERE stidentid = ? AND completed = FALSE";
            
            PreparedStatement  homeworkStatement=connection.prepareStatement(getHomework);
            
            ResultSet homeworkList = homeworkStatement.executeQuery();
            
            while(homeworkList.next()){
                long homeworkID=homeworkList.getLong("homeworkid");
                String homeworkTitle = homeworkList.getString("title");
                LocalDate dueDate=homeworkList.getDate("duedate").toLocalDate();
                long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(),dueDate);
                
                 boolean alertAlreadyExists= false;
                 
                 String checkAlert="SELECT slertid FROM alert"+"WHERE studentid = ? AND homeworkid = ? AND readstatus = FALSE";
                 
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
    
    public void DisplayHomework(long currentUserID){
        String findHomework="SELECT homeworkid, title, description, duedate, comepleted" + "FROM homework" + "WHERE studentid = ? "+ "ORDER BY duedate";
        
        try{
            Connection connection = DatabaseConnection.connect();
            
            PreparedStatement statement=connection.prepareStatement(findHomework);
            statement.setLong(1,currentUserID);
            
            ResultSet result = statement.executeQuery();
            
            
            
            boolean found = false;
            
            while(result.next()){
                found = true;
                
                long homeworkID = result.getLong("homeworkid");
                String title = result.getString("title");
                String description = result.getString("description");
                LocalDate dueDate = result.getDate("duedate").toLocalDate();
                boolean completed=result.getBoolean("completed");
                
                System.out.println("Homework ID"+homeworkID);
                System.out.println("Title" + title);
                System.out.println("Description" + description);
                System.out.println("Due Date" + dueDate);
                System.out.println("Completed " + completed);
                        
            }
            
            if(!found){
                System.out.println("No homework found");
            }
            result.close();
            statement.close();
            connection.close();
                    
    }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
    }
    
    public void DeleteHomework(long currentUserID, long homeworkID){
        
        try{
            Connection connection = DatabaseConnection.connect();
            
            String deleteHomework= "DELETE homeworkid FROM homework" + "WHERE homeworkid = ? AND studentid = ?";
            
            PreparedStatement statement=connection.prepareStatement(deleteHomework);
            
            statement.setLong(1,homeworkID);
            statement.setLong(2, currentUserID);
            
            int rowsDeleted = statement.executeUpdate();
            
            if(rowsDeleted > 0){
                System.out.println("Homework deleted successfully");
            }else{
                System.out.println("Homework not found");
            }
            
            statement.close();
            connection.close();
            
        }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
    }
    
    public void UpdateHomework(long currentUserID, long homeworkID){
         
        try{
            Connection connection = DatabaseConnection.connect();
            
            
            
            connection.close();
        }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
    }
}
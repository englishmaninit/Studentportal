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
import java.util.Scanner;

/**
 *
 * @author jorda
 */
public class ToDoList {
    
    public void UpdateToDoList(long currentUserID){
         Scanner scanner=new Scanner (System.in);

         try{
            Connection connection = DatabaseConnection.connect();
            
            //DisplayToDoList(currentUserID);
 
         System.out.println("Press the task you would like to update");
         Long selecetdTaskID=scanner.nextLong();
         
         String findTask="SELECT * FROM todolist" + "WHERE taskid = ? AND studentid = ?";
         
         PreparedStatement findStatement = connection.prepareStatement(findTask);
         
         findStatement.setLong(1,selecetdTaskID);
         findStatement.setLong(2,currentUserID);
         
         ResultSet selectedTask=findStatement.executeQuery();
         
         
         if(selectedTask.next()==false){
         System.out.println("Task not  found");
         
          }else{
             System.out.println("Change task title");
                  System.out.println("Change Task description");
                  System.out.println("Change dealine");
                  System.out.println("Mark task as completed");
                  
                  int updateChoice=scanner.nextInt();
                  
                  if(updateChoice==1){
                  boolean validTitle=false;
                  String newTitle = null;
                  
                  while(validTitle == false){
                  System.out.println("Enter the new task title");
                  newTitle=scanner.nextLine().trim();
                  
                  
                  if("".equals(newTitle)){
                  System.out.println("The task title cannot be empty");
                  
                  }else{
                  validTitle=true;
                  }
                  }
                  
                  String updateTitle = "UPDATE todolist" + "SET title = ? " +"WHERE taskid = ? AND studentid = ?";
                  
                  PreparedStatement titleStatement = connection.prepareStatement(updateTitle);
                  
                  titleStatement.setString(1,newTitle);
                  titleStatement.setLong(2,selecetdTaskID);
                  titleStatement.setLong(3,currentUserID);
                  
                  titleStatement.executeUpdate();
                  
                  titleStatement.close();

                  System.out.println("Task title updates successfully");
                  }else if(updateChoice==2){
                  System.out.println("Enter the new description");
                  String newDescription=scanner.nextLine().trim();
                  
                  if("".equals(newDescription)){
                  newDescription="no description entered";
                  
                  }
                  
                  String updateDescription = "UPDATE todolist" + "SET descriptionc = ?" +"WHERE  taskid = ? AND studentid = ?";
                  
                  PreparedStatement descriptionStatement = connection.prepareStatement(updateDescription);
                  
                  descriptionStatement.setString(1,newDescription);
                  descriptionStatement.setLong(2,selecetdTaskID);
                  descriptionStatement.setLong(3,currentUserID);
                  
                  descriptionStatement.executeUpdate();
                  descriptionStatement.close();
                  
                  System.out.println("Tak descritpion updated successfully");
                  
                  }else if (updateChoice ==3){
                    boolean validDate=false;
                    LocalDate newDeadline=null;
                    
                   while(validDate == false){
                  System.out.println("Enter the new deadline");
                  String deadline = scanner.nextLine();
                  
                  try{
                      
                  newDeadline=LocalDate.parse(deadline);
                  
                  if(newDeadline.isBefore(LocalDate.now())){
                  System.out.println("Please enter a valid date");
                  }else{
                  validDate=true;
                  }
                  }catch(DateTimeParseException e){
                      System.out.println("Please enter a valid date");
                  }
                  }
                  
                  String updateDeadLine= "UPDATE todolist" + "SET deadline = ?" + "WHERE tasdkid = ? AND studentid = ?";
                  
                  PreparedStatement deadlineStatement = connection.prepareStatement(updateDeadLine);
                  
                  deadlineStatement.setDate(1,java.sql.Date.valueOf(newDeadline));
                  deadlineStatement.setLong(2,selecetdTaskID);
                  deadlineStatement.setLong(3,currentUserID);
                  
                  deadlineStatement.executeUpdate();
                  deadlineStatement.close();
                  
                  System.out.println("Task deadline updated successfully");
                  }else if(updateChoice ==4){
                  
                      String completeTask ="UPDATE todolist" + "SET completed= TRUE , datecompleted = CURRRENT_TIMESTAMP" + "wHERE taskid = ? AND studentid = ?";
                      
                      PreparedStatement completeStatement = connection.prepareStatement(completeTask);
                      
                      completeStatement.setLong(1,selecetdTaskID);
                      completeStatement.setLong(2,currentUserID);
                      
                      completeStatement.executeUpdate();
                      completeStatement.close();
                      
                  
                  System.out.println("Task marked as completed");
                  }else{
                  System.out.println("Invalid update option");
                  }
             }
         selectedTask.close();
         findStatement.close();
         connection.close();
         }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
         }        
    }
    
    public void AddToDo(long currentUserID){
        
        Scanner scanner =new Scanner(System.in);
        
        String taskName="";
        String description="";
        String prioirity="";
        LocalDate dueDate = null;
        
        boolean taskNameValid = false;
        boolean dateValid = false;
        boolean prioirtyValid = false;
        
        try{
            Connection connection=DatabaseConnection.connect();
            
            while(taskNameValid == false){
                System.out.println("Enter task name");
                taskName=scanner.nextLine().trim();
                
                if("".equals(taskName)){
                    System.out.println("Task name cannot be empty");
                }else{
                    taskNameValid=true;
                }
            }
            
            System.out.println("Enter task description");
            description=scanner.nextLine().trim();
            
            if("".equals(description)){
                System.out.println("No description entered");               
            }
            
            connection.close();
               
        }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }        
    }
    
    public void DisplayToDoList(long currentUserID){
        String findtask="SELECT taskid, title, description, duedate, comepleted" + "FROM todolist" + "WHERE studentid = ? "+ "ORDER BY deadline";
        
        try{
            Connection connection = DatabaseConnection.connect();
            
            PreparedStatement statement=connection.prepareStatement(findtask);
            statement.setLong(1,currentUserID);
            
            ResultSet result = statement.executeQuery();
            
            System.out.println("======To Do List=====");
            
            boolean found = false;
            
            while(result.next()){
                found = true;
                
                long taskID = result.getLong("taskid");
                String title = result.getString("title");
                String description = result.getString("description");
                LocalDate deadline = result.getDate("deadline").toLocalDate();
                boolean completed=result.getBoolean("completed");
                
                System.out.println("Task ID"+ taskID);
                System.out.println("Title" + title);
                System.out.println("Description" + description);
                System.out.println("Deadline" + deadline);
                System.out.println("Completed " + completed);
                        
            }
            
            if(!found){
                System.out.println("No tasks found");
            }
            result.close();
            statement.close();
            connection.close();
            
        }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }   
    }
    
     public void DeleteToDoTask(long currentUserID, long taskID){
        
        try{
            Connection connection = DatabaseConnection.connect();
            
            String deleteHomework= "DELETE taskid FROM todolist" + "WHERE taskid = ? AND studentid = ?";
            
            PreparedStatement statement=connection.prepareStatement(deleteHomework);
            
            statement.setLong(1,taskID);
            statement.setLong(2, currentUserID);
            
            int rowsDeleted = statement.executeUpdate();
            
            if(rowsDeleted > 0){
                System.out.println("Task deleted successfully");
            }else{
                System.out.println("Task not found");
            }
            
            statement.close();
            connection.close();
            
        }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
    }
}
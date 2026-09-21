/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theultimatestudentportal;

/**
 *
 * @author jorda
 */
public class Dashboard {
    
    public void DisplayDashboard(long currentUserID){
         System.out.println(" ------ Student Dashbaord ----- ");
         
         Homework homework = new Homework();
         homework.DisplayHomework(currentUserID);
         
         ToDoList todo = new ToDoList();
         todo.DisplayToDoList(currentUserID);
         
         Alert alert = new Alert();
         alert.DisplayAlerts(currentUserID);
         
         Progress progress = new Progress();
         Progress.DisplayProgress(currentUserID);
    }
}

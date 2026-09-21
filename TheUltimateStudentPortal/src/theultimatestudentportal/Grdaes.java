/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theultimatestudentportal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author jorda
 */
public class Grdaes {
    
    public void AddGrades(long currentUserID){
        
        Scanner scanner = new Scanner(System.in);
        
        try{
           Connection connection = DatabaseConnection.connect();
           
        System.out.println("Enter the subject");
        String subject = scanner.next();
        
        System.out.println("Enter the topic");
        String topic = scanner.next();
        
        System.out.println("Enter the score");
        double score=scanner.nextDouble();
        
        System.out.println("Enter the max score");
        double maxScore=scanner.nextDouble();
        
        if(score<0 || maxScore<=0 || score>maxScore){
            System.out.println("Invalid score");
            return;
        }
        
        double percentage = (score/maxScore)*100;
        
        String addGrade = "INSERT INTO grades" + "VALUES(?, ?, topic, score, maxScore, percentage, datecreated)";
        
        System.out.println("Grade added successfully");
        connection.close();
        
        }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
    }
    
    public void AddTestScores(long currentUserID){
         Scanner scanner = new Scanner(System.in);
        
        try{
           Connection connection = DatabaseConnection.connect();
        System.out.println("Enter the subject");
        String subject = scanner.next();
        
        System.out.println("Enter the testname");
        String testName = scanner.next();
        
        System.out.println("Enter the score");
        double score=scanner.nextDouble();
        
        System.out.println("Enter the max score");
        double maxScore=scanner.nextDouble();
        
        if(score<0 || maxScore<=0 || score>maxScore){
            System.out.println("Invalid score");
            return;
        }
        
        double percentage = (score/maxScore)*100;
        
        String addTestScore="INSERT INTO progress" + "VALUES(?, ?, testname, score, maxscore, percentage, datecreated)";
        
        System.out.println("Test score added successfully");
        
      connection.close();
        
        }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
    }
}

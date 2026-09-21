/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theultimatestudentportal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;

/**
 *
 * @author jorda
 */
public class Grdaes {
    
    public static String AddGrades(long currentUserID, int score , int maxScore, String topic){
        
        Scanner scanner = new Scanner(System.in);
        
        try{
           Connection connection = DatabaseConnection.connect();

        if(score<0 || maxScore<=0 || score>maxScore){
            return "Invalid score";
        }
        
        double percentage = (score/maxScore)*100;
        
        String addGrade = "INSERT INTO grades" + "VALUES(?, ?, topic, score, maxScore, percentage, datecreated)";
        
        PreparedStatement statement = connection.prepareStatement(addGrade);
        statement.setLong(1, currentUserID);
        statement.setString(2, topic);
        statement.setInt(3, score);
        statement.setInt(4, maxScore);
        statement.setDouble(5, percentage);
        statement.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now()));
        
        statement.executeUpdate();
        
        }catch(SQLException e){
            return"Database error";
            
        }
        return "Grade added successfully";
    }
    
    public static String AddTestScores(long currentUserID, int score , int maxScore, String testname){
         Scanner scanner = new Scanner(System.in);
        
        try{
           Connection connection = DatabaseConnection.connect();
       
        if(score<0 || maxScore<=0 || score>maxScore){
            return "Invalid score";
            
        }
        
        double percentage = (score/maxScore)*100;
        
        String addTestScore="INSERT INTO progress" + "VALUES(?, ?, testname, score, maxscore, percentage, datecreated)";
        
         PreparedStatement statement = connection.prepareStatement(addTestScore);
        statement.setLong(1, currentUserID);
        statement.setString(2, testname);
        statement.setInt(3, score);
        statement.setInt(4, maxScore);
        statement.setDouble(5, percentage);
        statement.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now()));
        
        statement.executeUpdate();

        }catch(SQLException e){
            return" Database error";
        }
        
        return "Test score added successfully";
    }
}

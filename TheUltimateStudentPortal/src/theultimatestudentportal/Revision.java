/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theultimatestudentportal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static theultimatestudentportal.Alert.AddAlert;

/**
 *
 * @author jorda
 */
public class Revision {
    
    public void GenerateRevisionRecomnedation(long currentUserID){
 
        double lowestAverage =101;
        String weakestSubject= "";
        String weakestTopic="";
        
        try{
            Connection connection=DatabaseConnection.connect();
            
            String subjectQuery="SELECT DISTINCT subject FROM progress WHERE studentid = ? ";
            
            PreparedStatement subjectStatement =connection.prepareStatement(subjectQuery);
            subjectStatement.setLong(1, currentUserID);
            ResultSet subjectList=subjectStatement.executeQuery();
            
            while(subjectList.next()){
                
                String currentSubject = subjectList.getString("subject");
                
                String averageQuery="SELECT AVG(percentage) AS average" + "FROM progress" + "WHERE studentid= ? ABD subject = ?";
                
                PreparedStatement averageStatement=connection.prepareStatement(averageQuery);
                
                averageStatement.setLong(1,currentUserID);
                averageStatement.setString(2,currentSubject);
                
                ResultSet averageResult =averageStatement.executeQuery();
                
                if(averageResult.next()){
                    double subjectAverage=averageResult.getDouble("average");
                    
                    if(subjectAverage<lowestAverage){
                        lowestAverage= subjectAverage;
                        weakestSubject=currentSubject;
                        
                    }
                }
                averageResult.close();
                averageStatement.close();
            }
            subjectList.close();
            subjectStatement.close();
        
            if(weakestSubject.isEmpty()){
                System.out.println("Complete at least one quiz to recieve a reccomendation");
                connection.close();
                return;
                
            }
        double lowestTopicScore=101;
        
        String topicQuery = "SELECT topic , AVG(percentage) AS average FROM progress" + "WHERE studentid = ? AND subject = ?"+ "GROUP BY topic" + "ORDER BY average ASC" + "LIMIT 1";
        
        PreparedStatement topicStatement = connection.prepareStatement(topicQuery);
        topicStatement.setLong(1,currentUserID);
        topicStatement.setString(2,weakestSubject);
        
        ResultSet topicResult = topicStatement.executeQuery();
        
        if(topicResult.next()){
            weakestTopic=topicResult.getString("topic");
            
            lowestTopicScore=topicResult.getDouble("average");
            
        }
        
        topicResult.close();
        topicStatement.close();
        
        String recomendation;
        int recomendationPrioity;
        
        if(lowestAverage<50){
            recomendation ="Urgent revision needed for " + weakestTopic + "in " + weakestSubject;
            recomendationPrioity = 1;
            
        }else if (lowestAverage<70){
            recomendation="Revise " + weakestTopic + "to improve your understanding of " + weakestSubject;
            recomendationPrioity =2;
        }else{
            recomendation = "Continue practising " + weakestTopic + "in " + weakestSubject;
            recomendationPrioity=3;
        }
        
        String addRecomnedation = "INSERT INTO reccomendation " + "(studentid, subject, topic, averageScore, message, dateCreated)" + "VALUES(?, ?, ?, ?, ?" + "CURRENT_TIMESTAMP)";
        
        PreparedStatement recomendtionStatement = connection.prepareStatement(addRecomnedation);
        recomendtionStatement.setLong(1,currentUserID);
        recomendtionStatement.setString(2,weakestSubject);
        recomendtionStatement.setString(3, weakestTopic);
        recomendtionStatement.setDouble(4,lowestAverage);
        recomendtionStatement.setString(5,recomendation);
        recomendtionStatement.executeUpdate();
        recomendtionStatement.close();
        
        AddAlert(currentUserID , 0 ,recomendation ,recomendationPrioity );
        System.out.println("Revision recomendation" + recomendation);
        connection.close();
        
        }catch(SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
    }
    
    public void AddrevisionNote(){
        
    }
    
    public void CreateFlashcard(){
        
    }
    
    public void UploadFlashcard(){
        
    }
    
    public void UpdateFlashcard(){
        
    }
    
    public void DeleteFlashcard(){
        
    }
    
    public void CreateRevisionPlan(){
        
    }
    
    public void UpdateRevisionPlan(){
        
    }
    
    public void DeleteRevisionPlan(){
        
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theultimatestudentportal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.sql.PreparedStatement;
import java.sql.Connection;

/**
 *
 * @author jorda
 */
public class Images {

    public static String SaveImage(long currentUserID, String noteTitle, long subjectID, String description, byte[] imageData) {

        String fileName = noteTitle + ".jpg";

        Path path = null;

        try {

            Connection connection = DatabaseConnection.connect();

            path = Paths.get("C:\\Users\\jorda\\OneDrive\\Documents\\6th Form\\NEA\\Main project\\Storage\\Notes\\" + fileName);

            boolean fileExists = Files.exists(path);

            if (fileExists) {

                Random random = new Random();

                fileName = noteTitle + random.nextInt(10000) + ".jpg";

                path = Paths.get("C:\\Users\\jorda\\OneDrive\\Documents\\6th Form\\NEA\\Main project\\Storage\\Notes\\" + fileName);
            }

            Files.createDirectories(path.getParent());

            Files.write(path, imageData);

            String imageInsert = "INSERT INTO studentnotes(studentid , subjectid ,notetitle, notecontent,imagelocation,typeofnote,datecreated, dateupdated)" + "VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement images = connection.prepareStatement(imageInsert);

            String filePath = path.toString();

            images.setLong(1, currentUserID);
            images.setLong(2, subjectID);
            images.setString(3, noteTitle);
            images.setString(4, description);
            images.setString(5, filePath);
            images.setString(6, "Image");
            images.setTimestamp(7, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
            images.setTimestamp(8, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));

            images.executeUpdate();

            images.close();
            connection.close();

            return "Note image uploaded successfully";

        } catch (Exception e) {

            if (path != null) {

                try {
                    Files.deleteIfExists(path);
                } catch (IOException error) {
                    System.out.println(error.getMessage());
                }
            }

            System.out.println(e.getMessage());

            return "Failed to upload note image";
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theultimatestudentportal;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Base64;
import java.util.Scanner;

/**
 *
 * @author jorda
 */
public class Authentication {

    public static String Login(String enteredUsername, String enteredPassword) {

        if (enteredUsername == null || enteredUsername.trim().isEmpty()) {
            return "Username can't be empty";
        }
        if (enteredPassword == null || enteredPassword.trim().isEmpty()) {
            return "Password can't be empty";
        }

        String checkUsername = "SELECT * FROM student WHERE Username = ?";

        try {

            Connection connection = DatabaseConnection.connect();

            PreparedStatement statement = connection.prepareStatement(checkUsername);

            statement.setString(1, enteredUsername);

            try (ResultSet userRecord = statement.executeQuery()) {

                if (!userRecord.next()) {
                    return "Username not found";
                }

                long currentUserID = userRecord.getLong("studentid");

                String storedPasswordHash = userRecord.getString("passwordhash");

                String passwordSalt = userRecord.getString("passwordsalt");

                int attempts = userRecord.getInt("failedattempts");

                boolean accountActive = userRecord.getBoolean("accountactive");

                Timestamp blockedUntil = userRecord.getTimestamp("blockeduntil");
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());

                if (!accountActive) {
                    return "This account is not active";
                }

                if (blockedUntil != null && currentTime.before(blockedUntil)) {
                    return "This account is temporarily blocked. Try again later.";
                }

                if (blockedUntil != null && currentTime.after(blockedUntil)) {
                    attempts = 0;
                    String resetBlock = "UPDATE student SET failedattempts = 0, blockeduntil = NULL WHERE studentid = ?";
                    try (PreparedStatement resetStatement = connection.prepareStatement(resetBlock)) {
                        resetStatement.setLong(1, currentUserID);
                        resetStatement.executeUpdate();
                    }
                }

                String hashedPassword = hashPassword(enteredPassword, passwordSalt);

                if (hashedPassword.equals(storedPasswordHash)) {

                    String updateLogin = "UPDATE student SET lastlogin = CURRENT_TIMESTAMP, failedattempts = 0, blockeduntil = NULL WHERE studentid = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateLogin)) {
                        updateStatement.setLong(1, currentUserID);
                        updateStatement.executeUpdate();
                    }
                    return "Login successful";
                } else {
                    attempts++;

                    if (attempts >= 5) {
                        Timestamp newBlockedUntil = new Timestamp(System.currentTimeMillis() + 180000);
                        String blockAccount = "UPDATE student SET failedattempts = ?, blockeduntil = ? WHERE studentid = ?";
                        try (PreparedStatement blockStatement = connection.prepareStatement(blockAccount)) {
                            blockStatement.setInt(1, attempts);
                            blockStatement.setTimestamp(2, newBlockedUntil);
                            blockStatement.setLong(3, currentUserID);
                            blockStatement.executeUpdate();
                        }
                        return "Too many incorrect password attempts. Account blocked for 3 minutes.";
                    } else {
                        String updateAttempts = "UPDATE student SET failedattempts = ? WHERE studentid = ?";
                        try (PreparedStatement attemptStatement = connection.prepareStatement(updateAttempts)) {
                            attemptStatement.setInt(1, attempts);
                            attemptStatement.setLong(2, currentUserID);
                            attemptStatement.executeUpdate();
                        }
                        return "Incorrect Password";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Database error";
        }
    }

    public static String generateSalt() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvwxyz";

        SecureRandom random = new SecureRandom();

        String salt = "";

        for (int i = 0; i < 32; i++) {
            int randomPosition = random.nextInt(characters.length());
            salt = salt + characters.charAt(randomPosition);

        }
        return salt;
    }

    public static String Register(String enteredUsername, String firstName, String surname, String password, String password1, String date) {

        LocalDate dateOfBirth = null;

        boolean registrationSuccessful = false;
        boolean usernameValid = false;
        boolean passwordValid = false;
        boolean dateOfBirthValid = false;
        boolean database = true;

        System.out.println(enteredUsername);
        System.out.println(firstName);
        System.out.println(surname);
        System.out.println(password);
        System.out.println(password1);
        System.out.println(date);

        try {
            Connection connection = DatabaseConnection.connect();
            if (connection == null) {
                return "Could not connect to database";

            }

            System.out.println("Enter a username");

            if ("".equals(enteredUsername)) {
                return "Username cannot be empty";
            } else if (enteredUsername.length() < 5) {
                return "Must conatin at least 5 characters";
            } else if (enteredUsername.length() > 30) {
                return "Must conatin n more than 30 characters";
            } else if (enteredUsername.contains(" ")) {
                return "Username cannot contain spaces";
            } else {

                try {
                    String checkUsername = "SELECT username FROM student" + "WHERE username = ?";
                    PreparedStatement usernameStatement = connection.prepareStatement(checkUsername);

                    usernameStatement.setString(1, enteredUsername);
                    ResultSet existingUser = usernameStatement.executeQuery();
                    existingUser.close();
                    usernameStatement.close();
                    return "Username already exists";
                } catch (SQLException e) {

                    System.out.println("no user");

                }

            }
            if ("".equals(firstName)) {

                return "Firstname cannot be empty";

            }

            if ("".equals(surname)) {
                return "Surname cannot be empty";
            }
            if (dateOfBirthValid == false) {

                try {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    dateOfBirth = LocalDate.parse(date, formatter);

                    LocalDate currentDate = LocalDate.now();

                    if (dateOfBirth.isAfter(currentDate)) {
                        System.out.println("Enter a valid date of birth");
                    } else {

                        int age = Period.between(dateOfBirth, currentDate).getYears();

                        if (age < 11) {
                            System.out.println("You musy be 11 years old to access the website");
                        } else if (age > 20) {
                            System.out.println("Enter a valid student date of birth");
                        } else {
                            dateOfBirthValid = true;
                        }
                    }
                } catch (DateTimeParseException e) {
                    System.out.println(e.getCause());
                    e.printStackTrace();
                    return "Please enter a valid date";
                }

            }

            boolean hasUppercase = false;
            boolean hasLowercase = false;
            boolean hasNumber = false;
            boolean hasSpecialCharacter = false;

            if (password.length() < 8) {
                System.out.println("Password must conatin atleas 8 characters");
            } else if (password.length() > 50) {
                System.out.println("Password too long");
            } else {
                for (int i = 0; i < password.length(); i++) {

                    char currentCharacter = password.charAt(i);

                    if (currentCharacter >= 'A' && currentCharacter <= 'Z') {
                        hasUppercase = true;
                    } else if (currentCharacter >= 'a' && currentCharacter <= 'z') {
                        hasLowercase = true;
                    } else if (currentCharacter >= '0' && currentCharacter <= '9') {
                        hasNumber = true;
                    } else {
                        hasSpecialCharacter = true;
                    }
                }

                if (hasUppercase == false) {
                    return "Password must conatin an uppercase letter";
                } else if (hasLowercase == false) {
                    return "Password must conatin a lowercase letter";
                } else if (hasNumber == false) {
                    return "Password must conatin a number";
                } else if (hasSpecialCharacter == false) {
                    return "Password must conatin a special character";
                } else if (password.equalsIgnoreCase(enteredUsername)) {
                    return "Password cannot be the same as the username";
                } else if (password.toLowerCase().contains(firstName.toLowerCase())) {
                    return "Password cannot contain first name";
                } else if (password.toLowerCase().equals(surname.toLowerCase())) {
                    return "Password cannot conatin surname";
                } else {
                    System.out.println("Confirm password");

                    if (password.equals(password1) == false) {
                        System.out.println("Passwords do not match");
                    } else {
                        passwordValid = true;
                    }
                }
            }

            try {
                String passwordSalt = generateSalt();
                String passwordHash = hashPassword(password, passwordSalt);

                String insertStudent = "INSERT INTO student" + "(username,passwordHash," + "passwordsalt, firstname,surname," + "dateofbirth , datecreated," + "failedattempts ,accountactive)" + "VALUES (?, ?, ?, ?, ?, ?," + "CURRENT_TIMESTAMP,0,TRUE)";

                System.out.println(dateOfBirth);

                PreparedStatement insertStatement = connection.prepareStatement(insertStudent);
                insertStatement.setString(1, enteredUsername);
                insertStatement.setString(2, passwordHash);
                insertStatement.setString(3, passwordSalt);
                insertStatement.setString(4, firstName);
                insertStatement.setString(5, surname);
                insertStatement.setDate(6, Date.valueOf(dateOfBirth));
                int rowsAdded = insertStatement.executeUpdate();

                System.out.println("line 391");

                if (rowsAdded > 0) {
                    registrationSuccessful = true;
                    return "Account created successfully";
                } else {
                    database = false;
                }
                insertStatement.close();
                connection.close();
                if (database) {

                    return "database error";

                }

            } catch (Exception e) {

                e.printStackTrace();
                System.out.println(e.getMessage());
                return "Database error";

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "Database error";
        }
        return "server error";
    }

    public static String hashPassword(String password, String salt) {
        try {
            String passwordtoHash = password + salt;

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            byte[] hashedBytes = messageDigest.digest(passwordtoHash.getBytes(StandardCharsets.UTF_8));
            String hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);
            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("assword hashing error");
            return null;
        }
    }

    public void DeleteAccount(long currentUserID, String password, String confirmation) {
        try {
            Connection connection = DatabaseConnection.connect();
            String validatePassword = "SELECT passwordhash, passwordsalt FROM student WHERE studentid=?";

            PreparedStatement checkPassword = connection.prepareStatement(validatePassword);
            checkPassword.setLong(1, currentUserID);

            ResultSet result = checkPassword.executeQuery();

            if (!result.next()) {
                System.out.println("Acount not found");
                return;
            }

            String storedHash = result.getString("passwordhash");
            String storedSalt = result.getString("passwordsalt");

            String enteredHash = hashPassword(password, storedSalt);

            if (!enteredHash.equals(storedHash)) {
                System.out.println("Incorrect password");
                return;
            }

            if (!confirmation.equalsIgnoreCase("yes")) {
                System.out.println("Account was not deleted");
                return;
            }

            connection.setAutoCommit(false);

            try {
                String[] deleteQueries = {"DELETE FROM homework WHERE studentid = ?", "DELETE FROM subject WHERE studentid = ?", "DELETE FROM student WHERE studentid =?"};

                for (String sql : deleteQueries) {
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setLong(1, currentUserID);
                        statement.executeUpdate();
                    }
                }
                connection.commit();
                System.out.println("Account was deleted");
            } catch (SQLException error) {
                connection.rollback();
                System.out.println("Account could not be deleted");
                System.out.println(error.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
    }

    public void UpdateAccountDetails(long currentUserID, int choice, String value) {

        String changeFirstname = "";
        String changeSurname = "";
        String changeDateOfBirth = "";

        if (choice == 1) {
            changeFirstname = "UPDATE student SET firstname = ? WHERE studentid = ?";
        } else if (choice == 2) {
            changeSurname = "UPDATE student SET surname = ? WHERE studentid =? ";
        } else if (choice == 3) {
            changeDateOfBirth = "UPDATE student SET dateofbirth = ? WHERE studentid = ? ";
        } else {
            System.out.println("Invalid option");
            return;
        }

        try {
            Connection connection = DatabaseConnection.connect();

            PreparedStatement statement;

            if (choice == 1) {
                statement = connection.prepareStatement(changeFirstname);
            } else if (choice == 2) {
                statement = connection.prepareStatement(changeSurname);
            } else {
                statement = connection.prepareStatement(changeDateOfBirth);
            }
            if (choice == 3) {
                statement.setDate(1, java.sql.Date.valueOf(value));

            } else {
                statement.setString(1, value);
            }
            statement.setLong(2, currentUserID);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Account upodated");
            } else {
                System.out.println("Account could not be updated");
            }

        } catch (IllegalArgumentException error) {
            System.out.println("Invalid date .  Use YYYY-MM-DD");

        } catch (SQLException e) {
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
    }

    public void ChangePassword(long currentUserID, String currentPassword, String newPassword) {
        String findPassword = "SELECT passwordhash ,passwordsalt, FROM student WHERE studentid=?";

        try {
            Connection connection = DatabaseConnection.connect();

            PreparedStatement findpassword = connection.prepareStatement(findPassword);

            findpassword.setString(1, newPassword);
            ResultSet result = findpassword.executeQuery();

            if (!result.next()) {
                System.out.println("Account not found");
            }

            String storedHash = result.getString("passwordhash");
            String storedSalt = result.getString("passwordsalt");

            String currentHash = hashPassword(currentPassword, storedSalt);

            if (!currentHash.equals(storedHash)) {
                System.out.println("Incorrect password");
            }

            if (!validatePassword(newPassword)) {
                System.out.println("Password dosent meet rquirements");
            }

            String newSalt = generateSalt();
            String newHash = hashPassword(newPassword, newSalt);

            String updatePassword = "UPDATE student SET passwordhash =? , passowrdsalt = ? WHERE studentid=?";

            try {
                PreparedStatement updatepassword = connection.prepareStatement(updatePassword);

                updatepassword.setString(1, newHash);
                updatepassword.setString(2, newSalt);
                updatepassword.setLong(3, currentUserID);

                int rowUpdated = updatepassword.executeUpdate();

                if (rowUpdated > 0) {
                    System.out.println("Password changed");
                } else {
                    System.out.println("Password not updated");
                }
            } catch (SQLException e) {
                System.out.println("Database error");
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
    }

    public void ChangeUsername(long currentUserID, String newUsername) {

        newUsername = newUsername.trim();

        if (newUsername.length() < 5) {
            System.out.println("Username too short");
            return;
        }

        String findUsername = "SELECT studentid FROM student WHERE username=?";

        String updateUsername = " UPDATE student SET username = ? WHERE studentid = ? ";

        try {
            Connection connection = DatabaseConnection.connect();

            PreparedStatement findusername = connection.prepareStatement(findUsername);

            findusername.setString(1, newUsername);
            ResultSet result = findusername.executeQuery();

            if (result.next()) {
                System.out.println("Username already exists");
            }

            try {
                PreparedStatement updateusername = connection.prepareStatement(updateUsername);
                updateusername.setString(1, newUsername);
                updateusername.setLong(2, currentUserID);

                int rowUpdated = updateusername.executeUpdate();

                if (rowUpdated > 0) {
                    System.out.println("Username changed");
                } else {
                    System.out.println("Username not updated");
                }
            } catch (SQLException e) {
                System.out.println("Database error");
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
    }

    private boolean validatePassword(String newPassword) {

        boolean passwordValid = false;
        String password = "";
        String firstName = "";
        String surname = "";
        String enteredUsername = null;

        Scanner scanner = new Scanner(System.in);

        while (passwordValid == false) {
            boolean hasUppercase = false;
            boolean hasLowercase = false;
            boolean hasNumber = false;
            boolean hasSpecialCharacter = false;

            System.out.println("Enter password");
            password = scanner.nextLine();

            if (password.length() < 8) {
                System.out.println("Password must conatin atleas 8 characters");
            } else if (password.length() > 50) {
                System.out.println("Password too long");
            } else {
                for (int i = 0; i < password.length(); i++) {

                    char currentCharacter = password.charAt(i);

                    if (currentCharacter >= 'A' && currentCharacter <= 'Z') {
                        hasUppercase = true;
                    } else if (currentCharacter >= 'a' && currentCharacter <= 'z') {
                        hasLowercase = true;
                    } else if (currentCharacter >= '0' && currentCharacter <= '9') {
                        hasNumber = true;
                    } else {
                        hasSpecialCharacter = true;
                    }
                }

                if (hasUppercase == false) {
                    System.out.println("Password must conatin an uppercase letter");
                } else if (hasLowercase == false) {
                    System.out.println("Password must conatin a lowercase letter");
                } else if (hasNumber == false) {
                    System.out.println("Password must conatin a number");
                } else if (hasSpecialCharacter == false) {
                    System.out.println("Password must conatin a special character");
                } else if (password.equalsIgnoreCase(enteredUsername)) {
                    System.out.println("Password cannot be the same as the username");
                } else if (password.toLowerCase().contains(firstName.toLowerCase())) {
                    System.out.println("Password cannot contain first name");
                } else if (password.toLowerCase().equals(surname.toLowerCase())) {
                    System.out.println("Password cannot conatin surname");
                } else {
                    System.out.println("Confirm password");
                    String confirmedPassword = scanner.nextLine();

                    if (password.equals(confirmedPassword) == false) {
                        System.out.println("Passwords do not match");
                    } else {
                        passwordValid = true;
                    }
                }
            }
        }
        return false;
    }
}

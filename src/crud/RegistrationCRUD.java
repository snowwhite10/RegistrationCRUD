package crud;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrationCRUD {

    // CREATE Operation
    public static void createRegistration(String name, String email, String dob, String phone) {
        if (!isValidEmail(email)) {
            System.out.println("Invalid email address.");
            return;
        }

        if (!isValidDateOfBirth(dob)) {
            System.out.println("Invalid Date of Birth. Ensure it is in 'YYYY-MM-DD' format and not a future date.");
            return;
        }

        if (!isValidPhoneNumber(phone)) {
            System.out.println("Invalid phone number. It should contain only digits and optional '+' sign.");
            return;
        }

        String sql = "INSERT INTO Registration (Name, Email, DateOfBirth, PhoneNumber) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, dob);
            pstmt.setString(4, phone.isEmpty() ? null : phone); // Allow optional phone number

            int rows = pstmt.executeUpdate();
            System.out.println(rows + " record(s) inserted.");
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                System.out.println("Error: Email already exists in the database.");
            } else {
                System.out.println("Error during CREATE operation: " + e.getMessage());
            }
        }
    }

    // READ Operation
    public static void readRegistration(Integer id) {
        String sql = (id == null) ? "SELECT * FROM Registration" : "SELECT * FROM Registration WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (id != null) {
                pstmt.setInt(1, id);
            }

            ResultSet rs = pstmt.executeQuery();
            if (!rs.isBeforeFirst()) { // Check if the result set is empty
                System.out.println("No records found.");
                return;
            }

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ID"));
                System.out.println("Name: " + rs.getString("Name"));
                System.out.println("Email: " + rs.getString("Email"));
                System.out.println("DateOfBirth: " + rs.getDate("DateOfBirth"));
                System.out.println("PhoneNumber: " + rs.getString("PhoneNumber"));
                System.out.println("CreatedAt: " + rs.getTimestamp("CreatedAt"));
                System.out.println("UpdatedAt: " + rs.getTimestamp("UpdatedAt"));
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error during READ operation: " + e.getMessage());
        }
    }

    // UPDATE Operation
    public static void updateRegistration(int id, String name, String email, String dob, String phone) {
        if (email != null && !isValidEmail(email)) {
            System.out.println("Invalid email address.");
            return;
        }

        if (dob != null && !isValidDateOfBirth(dob)) {
            System.out.println("Invalid Date of Birth. Ensure it is in 'YYYY-MM-DD' format and not a future date.");
            return;
        }

        if (phone != null && !isValidPhoneNumber(phone)) {
            System.out.println("Invalid phone number. It should contain only digits and optional '+' sign.");
            return;
        }

        String sql = "UPDATE Registration SET Name = COALESCE(?, Name), Email = COALESCE(?, Email), " +
                "DateOfBirth = COALESCE(?, DateOfBirth), PhoneNumber = COALESCE(?, PhoneNumber) WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, dob);
            pstmt.setString(4, phone.isEmpty() ? null : phone);
            pstmt.setInt(5, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Record updated successfully.");
            } else {
                System.out.println("No record found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error during UPDATE operation: " + e.getMessage());
        }
    }

    // DELETE Operation
    public static void deleteRegistration(int id) {
        String sql = "DELETE FROM Registration WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("No record found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error during DELETE operation: " + e.getMessage());
        }
    }

    // Helper Method: Validate Email
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }

    // Helper Method: Validate Date of Birth
    private static boolean isValidDateOfBirth(String dob) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dob);
            return date.before(new Date()); // Ensure the date is not in the future
        } catch (ParseException e) {
            return false; // Invalid date format
        }
    }

    // Helper Method: Validate Phone Number
    private static boolean isValidPhoneNumber(String phone) {
        String phoneRegex = "^\\+?\\d{1,15}$"; // Optional '+' followed by up to 15 digits
        return phone == null || phone.isEmpty() || phone.matches(phoneRegex);
    }
}

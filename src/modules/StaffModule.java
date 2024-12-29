package modules;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StaffModule {
    public static void manageStaff() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. View Staff");
        System.out.println("2. Add Staff Member");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                viewStaff();
                break;
            case 2:
                addStaff();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void viewStaff() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Staff";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Staff ID: " + rs.getInt("StaffID"));
                System.out.println("Name: " + rs.getString("Name"));
                System.out.println("Role: " + rs.getString("Role"));
                System.out.println("Contact Number: " + rs.getString("ContactNumber"));
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addStaff() {
        try (Connection conn = DBConnection.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Name:");
            String name = scanner.nextLine();
            System.out.println("Enter Role:");
            String role = scanner.nextLine();
            System.out.println("Enter Contact Number:");
            String contact = scanner.nextLine();

            String query = "INSERT INTO Staff (Name, Role, ContactNumber) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, role);
            pstmt.setString(3, contact);

            pstmt.executeUpdate();
            System.out.println("Staff member added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package modules;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PatientModule {
    public static void registerPatient() {
        try (Connection conn = DBConnection.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Patient Name:");
            String name = scanner.nextLine();
            System.out.println("Enter Patient Age:");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println("Enter Patient Gender:");
            String gender = scanner.nextLine();
            System.out.println("Enter Patient Address:");
            String address = scanner.nextLine();
            System.out.println("Enter Contact Number:");
            String contact = scanner.nextLine();

            String query = "INSERT INTO Patients (Name, Age, Gender, Address, ContactNumber) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, gender);
            pstmt.setString(4, address);
            pstmt.setString(5, contact);

            pstmt.executeUpdate();
            System.out.println("Patient registered successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

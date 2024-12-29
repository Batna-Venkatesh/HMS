package modules;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class EHRModule {
    public static void viewEHR() {
        try (Connection conn = DBConnection.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Patient ID:");
            int patientID = scanner.nextInt();
            System.out.println("helo");
            String query = "SELECT * FROM ehr WHERE PatientID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, patientID);
            pstmt.execute();
            System.out.println("helo4");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Record ID: " + rs.getInt(1));
                System.out.println("Patient ID: " + rs.getInt(2));
                System.out.println("Diagnosis: " + rs.getString(3));
                System.out.println("Treatment Plan: " + rs.getString(4));
                System.out.println("-----------------------"); 
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addEHR() {
        try (Connection conn = DBConnection.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Patient ID:");
            int patientID = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println("Enter Diagnosis:");
            String diagnosis = scanner.nextLine();
            System.out.println("Enter Treatment Plan:");
            String treatmentPlan = scanner.nextLine();

            String query = "INSERT INTO EHR (PatientID, Diagnosis, TreatmentPlan) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, patientID);
            pstmt.setString(2, diagnosis);
            pstmt.setString(3, treatmentPlan);

            pstmt.executeUpdate();
            System.out.println("EHR added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

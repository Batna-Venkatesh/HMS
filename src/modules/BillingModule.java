package modules;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BillingModule {
    public static void generateBill() {
        try (Connection conn = DBConnection.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Patient ID:");
            int patientID = scanner.nextInt();

            System.out.println("Enter Bill Amount:");
            double amount = scanner.nextDouble();

            String query = "INSERT INTO Billing (PatientID, Amount, Paid) VALUES (?, ?, false)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, patientID);
            pstmt.setDouble(2, amount);

            pstmt.executeUpdate();
            System.out.println("Bill generated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewBills() {
        try (Connection conn = DBConnection.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Patient ID:");
            int patientID = scanner.nextInt();

            String query = "SELECT * FROM Billing WHERE PatientID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, patientID);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Bill ID: " + rs.getInt("BillID"));
                System.out.println("Amount: " + rs.getDouble("Amount"));
                System.out.println("Paid: " + (rs.getBoolean("Paid") ? "Yes" : "No"));
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void markAsPaid() {
        try (Connection conn = DBConnection.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Bill ID:");
            int billID = scanner.nextInt();

            String query = "UPDATE Billing SET Paid = true WHERE BillID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, billID);

            pstmt.executeUpdate();
            System.out.println("Bill marked as paid!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

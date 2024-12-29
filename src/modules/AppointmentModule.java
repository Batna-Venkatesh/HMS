package modules;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AppointmentModule {
    public static void scheduleAppointment() {
        try (Connection conn = DBConnection.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Patient ID:");
            int patientID = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println("Enter Doctor Name:");
            String doctorName = scanner.nextLine();
            System.out.println("Enter Appointment Date (YYYY-MM-DD):");
            String date = scanner.nextLine();
            System.out.println("Enter Appointment Time (HH:MM):");
            String time = scanner.nextLine();

            String query = "INSERT INTO Appointments (PatientID, DoctorName, Date, Time) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, patientID);
            pstmt.setString(2, doctorName);
            pstmt.setString(3, date);
            pstmt.setString(4, time);

            pstmt.executeUpdate();
            System.out.println("Appointment scheduled successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

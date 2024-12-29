package modules;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class InventoryModule {
    public static void manageInventory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. View Inventory");
        System.out.println("2. Add Item");
        System.out.println("3. Update Item Quantity");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                viewInventory();
                break;
            case 2:
                addItem();
                break;
            case 3:
                updateItemQuantity();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void viewInventory() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Inventory";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Item ID: " + rs.getInt("ItemID"));
                System.out.println("Item Name: " + rs.getString("ItemName"));
                System.out.println("Quantity: " + rs.getInt("Quantity"));
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addItem() {
        try (Connection conn = DBConnection.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Item Name:");
            String itemName = scanner.nextLine();
            System.out.println("Enter Quantity:");
            int quantity = scanner.nextInt();

            String query = "INSERT INTO Inventory (ItemName, Quantity) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, itemName);
            pstmt.setInt(2, quantity);

            pstmt.executeUpdate();
            System.out.println("Item added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateItemQuantity() {
        try (Connection conn = DBConnection.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Item ID:");
            int itemID = scanner.nextInt();
            System.out.println("Enter New Quantity:");
            int quantity = scanner.nextInt();

            String query = "UPDATE Inventory SET Quantity = ? WHERE ItemID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, itemID);

            pstmt.executeUpdate();
            System.out.println("Item quantity updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

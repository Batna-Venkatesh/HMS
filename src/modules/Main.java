package modules;

import modules.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
    	while(true) 
    	{
	        System.out.println("\nWelcome to the Hospital Management System");
	       
			System.out.println("1. Register Patient");
			System.out.println("2. Schedule Appointment");
			System.out.println("3. View Electronic Health Records (EHR)");
			System.out.println("4.  Billing and Invoicing");
			System.out.println("5. Manage Inventory");
			System.out.println("6. Manage Staff");
			System.out.println("7. Exit");
	        System.out.println("Choose options: ");
	        int n=sc.nextInt();
	        switch(n) {
	        	case 1:
	        		PatientModule.registerPatient();
	        		break;
	        	case 2:
	        		AppointmentModule.scheduleAppointment();
	        		break;
	        	case 3:
	        		EHRModule.viewEHR();
	        		break;
	        	case 4:
	        		BillingModule.generateBill();
	        		break;
	        	case 5:
	        		InventoryModule.manageInventory();
	        		break;
	        	case 6:
	        		StaffModule.manageStaff();
	        		break;
	        	default:
	        		System.out.println("Invalid choosen");
	        		break;
	        }
	        break; 
    	}
    }
}

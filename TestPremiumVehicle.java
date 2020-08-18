import java.util.Scanner;

public class TestPremiumVehicle {

	public static void main(String[] args) throws StatusException, OdometerException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int j = 0, op;
		// char choice;
		// passing ID, description, daily-rate, odometer reading, daily-mileage,
		// service-length, last-service
		PremiumVehicle v = new PremiumVehicle("SAM134", "Lexus 05", 95.00, 18000, 200, 10000, 17500);
		v.print();

		if (v.hire("ST36784") == true)
			System.out.println("\nCar " + v.getID() + " is now hired to " + v.getHirer());
		else
			System.out.println("\nCar " + v.getID() + " could not be hired ");
		v.print();
		DateTime.setAdvance(4, 2, 0);

		int odoReading = 28000;
		double val = v.hireComplete(odoReading);
		if (val > 0.0) {
			System.out.print("\nCar " + v.getID() + " is returned by " + v.getHirer());
			System.out.println(" Charges = " + val + " odo Reading = " + odoReading);
		} else
			System.out.println("\nWARNING: Car " + v.getID() + " could not be returned from hire ");

		v.print();
		DateTime.setAdvance(6, 0, 0);

		// attemption to hire a car which is due for service
		if (v.hire("ST7656") == true)
			System.out.println("\nCar " + v.getID() + " is now hired to " + v.getHirer());
		else
			System.out.println("\nCar " + v.getID() + " could not be hired ");

		DateTime.setAdvance(7, 0, 0);

		// sending car for service
		if (v.service() == true)
			System.out.println("\nCar " + v.getID() + " is now sent to service");
		else
			System.out.println("\nCar " + v.getID() + " could not be serviced ");
		v.print();

		DateTime.setAdvance(8, 0, 0);

		if (v.serviceComplete(28100) == true)
			System.out.println("\nCar " + v.getID() + " is now returned from service");
		else
			System.out.println("\nCar " + v.getID() + " could not be returned from serviced ");
		v.print();
		DateTime.setAdvance(9, 0, 0);

		if (v.hire("ST7656") == true)
			System.out.println("\nCar " + v.getID() + " is now hired to " + v.getHirer());
		else
			System.out.println("\nCar " + v.getID() + " could not be hired ");

		v.print();

		Vehicle vehs[] = new Vehicle[6];
		vehs[0] = new Vehicle("QJT123", "Starlet 99", 35.0, 190000);
		vehs[1] = new PremiumVehicle("TUX132", "BMW 05", 90.0, 12000, 100, 10000, 5000);
		vehs[2] = new Vehicle("PTU121", "Holden 03", 60.0, 165000);
		vehs[3] = new Vehicle("OCD856", "Camry 04", 65.0, 230000);
		vehs[4] = new PremiumVehicle("TEY749", "Jaguar 06", 125.0, 27000, 120, 12000, 20000);
		vehs[5] = new Vehicle("TYR852", "Subaru	02", 60.0, 270000);

		for (int i = 0; i < 6; i++) {
			vehs[i].print();
		}
		do {
			System.out.println(
					"\n\nVehicle Menu \n Display Available Cars  1 \n Hire Vehicle \t\t 2 \n Complete Hire \t\t 3 \n Service Vehicle \t 4 \n Complete Service \t 5 \n Exit \t\t\t 6 \n Your choice: ");
			op = sc.nextInt();

			if (op > 6 && op < 0) {
				System.out.println("Invalid choice");
			} else {
				switch (op) {

				case 1:
					System.out.println("Display Available Cars chosen ");
					for (int i = 0; i < vehs.length; i++) {
						if (vehs[i].getStatus() == 'A' || vehs[i].getStatus() == 'H') {
							vehs[i].print();
						}
					}
					break;

				case 2:
					System.out.println("Hire Vehicle chosen ");
					System.out.println(" Enter Vehicle ID :");
					String veh_id = new String();
					veh_id = sc.next();
					boolean found = false;

					for (j = 0; j < vehs.length; j++) {
						if (vehs[j].getID().compareTo(veh_id) == 0) {
							if (vehs[j].getStatus() == 'A') {
								System.out.println("Enter hirer ID :");
								String hirerID = sc.next();
								vehs[j].hire(hirerID);
							} else {
								System.out.println("\nWARNING: Car " + vehs[j].getID() + " cannot be hired");
							}
							found = true;
						}
					}
					if (j == vehs.length && found == false) {
						System.out.print(" No such vehicles found");
					}

					break;

				case 3:
					System.out.println("Complete Hire Vehicle chosen ");
					System.out.println(" Enter Vehicle ID :");
					String vehicle_id = new String();
					vehicle_id = sc.next();
					boolean found_next = false;
					int k = 0;
					double tCharges;
					for (k = 0; k < vehs.length; k++) {
						if (vehs[k].getID().compareTo(vehicle_id) == 0) {
							if (vehs[k].getStatus() == 'H') {
								System.out.println("Enter odometer reading:");
								int odom = sc.nextInt();
								tCharges = vehs[k].hireComplete(odom);
								System.out.println(" Charges =" + tCharges);

							} else {
								System.out.println(
										"\nWARNING: Car " + vehs[j].getID() + " hire completion cannot be processed");
							}
							found_next = true;
						}

					}
					if (k == vehs.length && found_next == false) {
						System.out.print(" No such vehicles found");
					}
					break;

				case 4:
					System.out.println("Service Vehicle chosen");
					System.out.println(" Enter Vehicle ID :");
					String vehicleService = new String();
					vehicleService = sc.next();
					boolean foundService = false;
					int x = 0;
					for (x = 0; x < vehs.length; x++) {
						if (vehs[x].getID().compareTo(vehicleService) == 0) {
							if (vehs[x].getStatus() == 'A') {
								vehs[x].service();
							} else {
								System.out.println("\nWARNING: Car " + vehs[j].getID() + " could not be serviced");
							}
							foundService = true;
						}
					}
					if (x == vehs.length && foundService == false) {
						System.out.print(" No such vehicles found");
					}
					break;

				case 5:
					System.out.println("Complete Service chosen");
					System.out.println(" Enter Vehicle ID :");
					String vehicleComplete = new String();
					vehicleComplete = sc.next();
					boolean foundComplete = false;
					int y = 0;
					for (y = 0; y < vehs.length; y++) {
						if (vehs[y].getID().compareTo(vehicleComplete) == 0) {
							if (vehs[y].getStatus() == 'S') {
								System.out.println("Enter odometer reading:");
								int odoS = sc.nextInt();
								vehs[y].serviceComplete(odoS);
							} else {
								System.out.println("\nWARNING: Car " + vehs[j].getID()
										+ " service completion cannot be processed");
							}
							foundComplete = true;
						}
					}
					if (y == vehs.length && foundComplete == false) {
						System.out.print(" No such vehicles found");
					}
					break;

				case 6:
					System.out.println("Exit chosen");
					System.exit(0);

				default:
					System.out.println("Invalid choice");
				}

			}

		} while (op != '6');
	}

}

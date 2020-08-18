import java.util.Scanner;

public class TestVehicle {
	public static void main(String args[]) throws StatusException, OdometerException {

		Scanner sc = new Scanner(System.in);
		int op;
		int j = 0;
		char choice;
		Vehicle v = new Vehicle("SAT123", "Toyota Camry", 12.50, 100000);
		v.print();

		// sending the car for service
		if (v.service() == true)
			System.out.println("\nCar " + v.getID() + " is now sent for service ");
		else
			System.out.println("\nWARNING: Car " + v.getID() + " cannot be sent for service ");
		v.print();

		// advancing by 2 days
		DateTime.setAdvance(2, 0, 0);

		// Attempt to hire a car on service

		if (v.hire("User36784") == true)
			System.out.println("\nCar " + v.getID() + " is now hired to " + v.getHirer());
		else
			System.out.println("\nWARNING: Car " + v.getID() + " could not be hired ");

		v.print();

		// Car returning from service
		if (v.serviceComplete(100100) == true)
			System.out.println("\nCar " + v.getID() + " is now returned from service ");
		else
			System.out.println("\nWARNING: Car " + v.getID() + " cannot be sent returned from service ");
		v.print();

		DateTime.setAdvance(4, 0, 0);

		// Attempt to hire the car which is now available for hire

		if (v.hire("User36784") == true)
			System.out.println("\nCar " + v.getID() + " is now hired to " + v.getHirer());
		else
			System.out.println("\nWARNING: Car " + v.getID() + " could not be hired ");

		v.print();

		DateTime.setAdvance(6, 0, 0);

		// Attempt to hire a car already on hire

		if (v.hire("User9999") == true)
			System.out.println("\nCar " + v.getID() + " is now hired to " + v.getHirer());
		else
			System.out.println("\nWARNING: Car " + v.getID() + " could not be hired ");

		v.print();

		// Attempt to return car from hire
		double val = v.hireComplete(121000);
		if (val > 0.0) {
			System.out.print("\nCar " + v.getID() + " is returned by " + v.getHirer());
			System.out.println(" Charges = " + val);
		} else
			System.out.println("\nWARNING:Car " + v.getID() + " could not be returned from hire ");
		v.print();
		DateTime.setAdvance(8, 0, 0);

		// Attempt to hire the car now available

		if (v.hire("User9999") == true)
			System.out.println("\nCar " + v.getID() + " is now hired to " + v.getHirer());
		else
			System.out.println("\nWARNING: Car " + v.getID() + " could not be hired ");

		v.print();

		// step1
		Vehicle vehs[] = new Vehicle[5];
		vehs[0] = new Vehicle("SAM134", "Toyota Camry 02", 45.00, 140000);
		vehs[1] = new Vehicle("QKO156", "Honda Accord 04", 65.0, 125000);
		vehs[2] = new Vehicle("TUV178", "Toyota Starlet 02", 35.00, 180000);
		vehs[3] = new Vehicle("SAG132", "Toyota Avalon 05", 52.0, 190000);
		vehs[4] = new Vehicle("PRE342", "Honda Civic 97", 35.00, 145000);

		// step2:display id and desc
		for (int i = 0; i < vehs.length; i++) {
			// System.out.println("\nID :" + vehs[i].getID() + "\tDescription :" +
			// vehs[i].getDescription());
			vehs[i].toString();
		}

		// step3:daily rate low and high
		System.out.println("\nEnter the lower limit:");
		double lower_limit = sc.nextDouble();

		System.out.println("\nEnter the upper limit:");
		double upper_limit = sc.nextDouble();
		boolean found = false;
		for (j = 0; j < 5; j++) {
			if (lower_limit <= vehs[j].getDailyRate() && upper_limit >= vehs[j].getDailyRate()) {
				System.out.println("\nID :" + vehs[j].getID() + "\tDescription :" + vehs[j].getDescription()
						+ "\tDaily Rate :" + vehs[j].getDailyRate());
				found = true;
			}
		}
		if (found == false) {
			System.out.println("No such vehicles found");
		}
		// step4 & 5:
		do {
			System.out.println("\n Enter Vehicle ID :");
			String veh_id = new String();
			veh_id = sc.next();
			boolean found_next = false;
			for (j = 0; j < vehs.length; j++) {
				if (vehs[j].getID().compareTo(veh_id) == 0) {
					System.out.println(
							" 1\t Hire\n 2\t Complete-Hire\n 3\t Service\n 4\t Complete-Service \n 5\t Exit\nEnter option to be performed :");
					op = sc.nextInt();

					switch (op) {
					case 1:

						if (vehs[j].getStatus() == 'A') {
							System.out.println(" Hire chosen\n Enter hirer ID: ");
							String hirer_id = sc.next();

							vehs[j].hire(hirer_id);
							vehs[j].print();
						} else {
							System.out.println("\nWARNING: Car " + vehs[j].getID() + " cannot be hired");
						}

						break;

					case 2:
						if (vehs[j].getStatus() == 'H') {
							System.out.println(" Complete-Hire chosen\n Enter odometer reading: ");
							int odomH = sc.nextInt();
							vehs[j].hireComplete(odomH);
							vehs[j].print();
						} else {
							System.out.println(
									"\nWARNING: Car " + vehs[j].getID() + " hire completion could not be processed");
						}
						break;

					case 3:
						if (vehs[j].getStatus() == 'A') {
							System.out.println(" Service chosen\n ");
							vehs[j].service();
							vehs[j].print();
						} else {
							System.out.println("\nWARNING: Car " + vehs[j].getID() + " cannot be serviced");
						}
						break;

					case 4:
						if (vehs[j].getStatus() == 'S') {
							System.out.println(" Service-Complete chosen\n Enter odometer reading:");
							int odomS = sc.nextInt();
							vehs[j].serviceComplete(odomS);
							vehs[j].print();
						} else {
							System.out.println(
									"\nWARNING: Car " + vehs[j].getID() + " service completion could not be processed");
						}
						break;

					case 5:
						System.out.println("Exit chosen");
						System.exit(0);

					default:
						System.out.println("Invalid choice");
					}
					found_next = true;
				}

			}

			if (j == vehs.length && found_next == false) {
				System.out.print(" No such vehicles found");
			}

			System.out.println("\n Any more transactions :");
			choice = sc.next().toLowerCase().charAt(0);

		} while (choice != 'n');

		// step 5
		for (

				int i = 0; i < vehs.length; i++) {
			vehs[i].print();
		}
	}

}
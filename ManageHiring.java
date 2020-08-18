
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import java.util.List;

public class ManageHiring implements Serializable {

	public static final int MAX = 3;
	private String testVehicleID, testVehicleDescription, testCustomerID, testCustomerName, testPhoneNo;
	private double testVehicleDailyRate;
	private int testVehicleOdometerR, testVehicleDailyMileage, testVehicleServiceLength, testVehicleLastService;

	Scanner sc = new Scanner(System.in);

	// separate arraylists for each vehicle and customers
	private static ArrayList<Vehicle> veh = new ArrayList<Vehicle>();
	private static ArrayList<PremiumVehicle> pveh = new ArrayList<PremiumVehicle>();
	private static ArrayList<ICustomer> icust = new ArrayList<ICustomer>();
	private static ArrayList<CCustomer> ccust = new ArrayList<CCustomer>();
	private static ArrayList<Report> report = new ArrayList<Report>();

	public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException {
		int choice;
		Scanner scan = new Scanner(System.in);
		ManageHiring mh1 = new ManageHiring();
		char answer;

		loadData(report);

		do {
			System.out.println(
					"Menu\n1\tAdd new Vehicle\n2\tAdd new Customer\n3\tDisplay available vehicles\n4\tHire a Vehicle\n5\tComplete hire of vehicle\n6\tSend a vehicle for service/Return a vehicle from service\n7\tGet Rental Income\n8\tAdvance Time\n9\tExit\nEnter your choice:");
			choice = scan.nextInt();
			switch (choice) {

			case 1:
				System.out.println("Add new Vehicle selected");
				mh1.addVehicle();
				break;

			case 2:
				System.out.println("Add new Customer selected");
				mh1.addCustomer();
				break;

			case 3:
				System.out.println("Display available vehicles selected");
				try {
					mh1.display();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 4:
				System.out.println("Hire a vehicle selected");
				try {
					try {
						mh1.hiring();
					} catch (VehicleNotFoundException | CustomerNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (StatusException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 5:
				System.out.println("Complete hire of vehicle selected");
				try {
					mh1.completion();
				} catch (StatusException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OdometerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (VehicleNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 6:
				System.out.println("Send vehicle for service/Return vehicle from service ");
				System.out
						.println("1\t To send vehicle for service\n2\t To return vehicle from service\nEnter choice: ");
				int ch = scan.nextInt();
				switch (ch) {
				case 1:
					System.out.println("Sending a vehicle for service");
					try {
						mh1.sendToService();
					} catch (StatusException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (VehicleNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case 2:
					System.out.println("Returning a vehicle from service");
					try {
						mh1.returnFromService();
					} catch (StatusException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (OdometerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				default:
					System.out.println("Invalid choice");
				}
				break;

			case 7:
				System.out.println("Get rental income selected");
				mh1.rentalIncome();
				break;

			case 8:
				System.out.println("Advance Time selected");
				try {
					mh1.advanceDays();
				} catch (VehicleNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 9:
				System.out.println("Exit selected");
				System.exit(0);
				break;

			default:
				System.out.println("Invalid choice");

			}
			// saveData();
			System.out.println("Do you wish to continue [y/n]: ");
			answer = mh1.sc.next().toLowerCase().charAt(0);
		} while (answer != 'n');
		saveData();
	}

	private void advanceDays() throws VehicleNotFoundException {

		// TODO Auto-generated method stub

		boolean foundVeh = false;

		System.out.println("Enter Vehicle ID");
		String vID = sc.next();

		System.out.println("Enter number of days to be advanced");
		int num = sc.nextInt();

		System.out.println("Enter number of hours to be advanced");
		int hrs = sc.nextInt();

		System.out.println("Enter number of minutes to be advanced");
		int mins = sc.nextInt();

		DateTime.setAdvance(num, hrs, mins);
		System.out.println("Time advanced by " + num + " days and " + hrs + " hours and " + mins + " minutes.");
//		for (Vehicle v : veh) {
//			// if vehicle is found
//			if (v.getID().compareTo(vID) == 0) {
//				foundVeh = true;
//				
//				System.out.println("Time advanced by " + num + " days and " + hrs + " hours and " + mins + " minutes.");
//				break;
//			}
//		}
//
//		for (PremiumVehicle pv : pveh) {
//			// if vehicle is found
//			if (pv.getID().compareTo(vID) == 0) {
//				foundVeh = true;
//				DateTime.setAdvance(num, hrs, mins);
//				System.out.println("Time advanced by " + num + " days and " + hrs + " hours and " + mins + " minutes.");
//				break;
//			}
//		}
//		if (foundVeh == false) {
//			throw new VehicleNotFoundException("Vehicle not found, please enter correct vehicle ID");
//		}

	}

	private void rentalIncome() throws ParseException {
		String pattern = "dd-MM-yyyy";
		int count = 0;
		System.out.println("enter start date in (dd-mm-yyyy) format:");
		String start = sc.next();
		// converting string to date
		Date startdt = new SimpleDateFormat("dd-MM-yyyy").parse(start);
		System.out.println(startdt);

		System.out.println("enter end date in (dd-mm-yyyy) format:");
		String end = sc.next();
		// converting string to date
		Date enddt = new SimpleDateFormat("dd-MM-yyyy").parse(end);
		System.out.println(enddt);

		for (int i = 0; i < report.size(); i++) {
			// converting hiredate which was in DateTime format first to string then to date
			Date hiredt = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US)
					.parse(report.get(i).gethiredt().toString());

			// to check if hiredate falls between start and end dates enterred by user
			if (startdt.compareTo(hiredt) < 0 && enddt.compareTo(hiredt) > 0) {

				report.get(i).print();
				count++;
			}
		}
		if (count == 0) {
			System.out.println("No vehicle income from " + start + " and " + end);
		}
	}

	private void returnFromService() throws StatusException, OdometerException {
		// TODO Auto-generated method stub
		System.out.println("Enter Vehicle ID");
		String vID = sc.next();

		System.out.println("Enter odometer reading");
		int odom = sc.nextInt();
		boolean foundVeh = false;

		for (Vehicle v : veh) {
			if (v.getID().compareTo(vID) == 0) {
				foundVeh = true;
				v.serviceComplete(odom);
				System.out.println("Vehicle " + v.getID() + " is returned from service.");
				break;
			}
		}
		for (PremiumVehicle pv : pveh) {
			if (pv.getID().compareTo(vID) == 0) {
				foundVeh = true;
				pv.serviceComplete(odom);
				System.out.println("Vehicle " + pv.getID() + " is returned from service.");
				break;
			}
		}
		if (foundVeh = false) {
			System.out.println("Vehicle not sent to service");
		}

	}

	private void sendToService() throws StatusException, VehicleNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Enter Vehicle ID");
		String vID = sc.next();
		boolean foundVeh = false;

		for (Vehicle v : veh) {
			if (v.getID().compareTo(vID) == 0) {
				foundVeh = true;
				v.service();
				System.out.println("Vehicle " + v.getID() + " is sent to service.");
				break;
			}
		}
		for (PremiumVehicle pv : pveh) {
			if (pv.getID().compareTo(vID) == 0) {
				foundVeh = true;
				pv.service();
				System.out.println("Vehicle " + pv.getID() + " is sent to service.");
				break;
			}
		}
		if (foundVeh = false) {

			throw new VehicleNotFoundException("Vehicle not found, please enter correct vehicle ID");

		}

	}

	private void completion()
			throws StatusException, OdometerException, VehicleNotFoundException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		System.out.println("Enter Vehicle ID");
		String vID = sc.next();
		ObjectOutputStream oos = null;
		boolean foundVeh = false;
		double totalCharges, discount_rate = 0;

		for (Vehicle v : veh) {
			if (v.getID().compareTo(vID) == 0) {
				foundVeh = true;
				System.out.println("Enter odometer reading");
				int odom = sc.nextInt();
				totalCharges = v.hireComplete(odom);

				// calculating discount based on customer for vehicle
				for (ICustomer ic : icust) {
					if (ic.custID.compareTo(v.getHirer()) == 0) {
						discount_rate = ic.getDiscount(totalCharges);

						totalCharges -= discount_rate;

					}
				}

				for (CCustomer cc : ccust) {
					if (cc.custID.compareTo(v.getHirer()) == 0) {
						discount_rate = cc.getDiscount(totalCharges);

						totalCharges -= discount_rate;

					}
				}

				System.out.println("Vehicle " + v.getID() + " has completed its hire with charges " + totalCharges);
				report.add(new Report(v.getID(), v.getHirer(), v.getHiredt(), totalCharges));
				break;

			}
		}
		for (PremiumVehicle pv : pveh) {
			if (pv.getID().compareTo(vID) == 0) {
				foundVeh = true;
				System.out.println("Enter odometer reading");
				int odom = sc.nextInt();

				for (Report r : report) {
					if ((r.getVID().compareTo(pv.getID()) == 0)) {
						pv.setHiredt(r.gethiredt());
					}
				}

				totalCharges = pv.hireComplete(odom);

				// calculating discount based on customer for premium vehicle
				for (ICustomer ic : icust) {
					if (ic.custID.compareTo(pv.getHirer()) == 0) {
						discount_rate = ic.getDiscount(totalCharges);

						totalCharges -= discount_rate;

					}
				}

				for (CCustomer cc : ccust) {
					if (cc.custID.compareTo(pv.getHirer()) == 0) {
						discount_rate = cc.getDiscount(totalCharges);

						totalCharges -= discount_rate;

					}
				}

				report.add(new Report(pv.getID(), pv.getHirer(), pv.getHiredt(), totalCharges));
				System.out.println("Vehicle " + pv.getID() + " has completed its hire with charges " + totalCharges);

				break;

			}
		}

		if (foundVeh == false) {

			throw new VehicleNotFoundException("Vehicle not found, please enter correct vehicle ID");
		}
		// writing contents of arrayList to file
		FileOutputStream fos = new FileOutputStream("Report.txt");
		oos = new ObjectOutputStream(fos);
		oos.writeObject(report);
		fos.close();
	}

	private void hiring() throws StatusException, VehicleNotFoundException, CustomerNotFoundException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Enter Vehicle ID to be hired:");
		String vID = sc.next();
		boolean foundVeh = false, foundCust = false, canHire = true;
		char status = 0;

		// finding vehicles in the arraylist
		for (Vehicle v : veh) {

			if (v.getID().compareTo(vID) == 0) {
				foundVeh = true;
				status = v.getStatus();
				break;
			}
		}
		for (PremiumVehicle pv : pveh) {
			if (pv.getID().compareTo(vID) == 0) {
				foundVeh = true;
				status = pv.getStatus();
				break;
			}
		}
		if (foundVeh == true && status == 'A') {

			System.out.println("Enter customer ID");
			String cID = sc.next();

			for (ICustomer ic : icust) {
				if (ic.getCustID().compareTo(cID) == 0) {
					foundCust = true;
					for (Vehicle v1 : veh) {
						// checking whether that particular individual customer has hired any vehicle at
						// that time or not
						if (v1.getHirer() != null && ic.getCustID().compareTo(v1.getHirer()) == 0) {
							canHire = false;
							System.out.println("Customer " + cID + " has already hired a vehicle.");
							break;
						}

					}
					for (Vehicle v1 : veh) {
						if (v1.getID().compareTo(vID) == 0 && canHire) {
							if (v1.hire(cID)) {
								System.out.println(v1.getID() + " is hired by customer " + ic.getCustID());
								break;
							}
						}
					}

					for (PremiumVehicle pv1 : pveh) {
						// checking whether that particular individual customer has hired any premium
						// vehicle at that time or not
						if (pv1.getHirer() != null && ic.getCustID().compareTo(pv1.getHirer()) == 0) {

							canHire = false;
							System.out.println("Customer " + cID + " has already hired a vehicle.");
							break;
						}
					}
					for (PremiumVehicle pv1 : pveh) {
						if (pv1.getID().compareTo(vID) == 0 && canHire) {
							if (pv1.hire(cID)) {
								System.out.println(pv1.getID() + " is hired by customer " + ic.getCustID());
								break;
							}
						}
					}
					break;
				}
			}
			for (CCustomer cc : ccust) {

				if (cc.getCustID().compareTo(cID) == 0) {
					foundCust = true;
					for (Vehicle v1 : veh) {
						if (v1.getID().compareTo(vID) == 0) {
							if (v1.hire(cID)) {

								System.out.println(v1.getID() + " is hired by customer " + cc.getCustID());
								break;
							}
						}

					}
					for (PremiumVehicle pv1 : pveh) {
						if (pv1.getID().compareTo(vID) == 0)

						{
							if (pv1.hire(cID)) {

								System.out.println(pv1.getID() + " is hired by customer " + cc.getCustID());
								break;
							}
						}
					}
				}
			}

			if (foundCust == false) {

				throw new CustomerNotFoundException("Customer not found, please enter correct customer ID");
			}
		} else {
			if (status == 'H' || status == 'S') {
				System.out.println("Vehicle is not available.");
			} else {

				throw new VehicleNotFoundException("Vehicle not found, please enter correct vehicle ID");
			}

		}

	}

	private void display() throws Exception {

		System.out.println("\nEnter the lower limit:");
		double lower_limit = sc.nextDouble();

		System.out.println("\nEnter the upper limit:");
		double upper_limit = sc.nextDouble();
		int foundDR = 0;
		if (upper_limit < lower_limit) {
			throw new Exception("Lower limit greater than Upper Limit,please re-enter ");
		}

		for (Vehicle v : veh) {
			if (lower_limit <= v.getDailyRate() && upper_limit >= v.getDailyRate() && v.getStatus() == 'A') {
				v.print();
				foundDR++;
			}

		}
		for (PremiumVehicle pv : pveh) {
			if (lower_limit <= pv.getDailyRate() && upper_limit >= pv.getDailyRate() && pv.getStatus() == 'A') {
				pv.print();
				foundDR++;
			}

		}
		if (foundDR == 0) {
			System.out.println("No vehicles within the range");
		}

	}

	private void addCustomer() {
		// TODO Auto-generated method stub
		int counter1 = 0;
		System.out.println("Enter Customer ID");
		testCustomerID = sc.next();

		for (ICustomer ic : icust) {
			if (ic.getCustID().compareTo(testCustomerID) == 0) {
				counter1++;
				break;
			}
		}

		for (CCustomer cc : ccust) {
			if (cc.getCustID().compareTo(testCustomerID) == 0) {
				counter1++;
				break;
			}
		}

		if (counter1 > 0) {
			System.out.println("Customer ID already exists, please re-enter unique ID");
		}
		// to check if customer id has length of 6 and starts with C
		else if (testCustomerID.length() == 6 && testCustomerID.charAt(0) == 'C') {
			System.out.println("Enter name");
			testCustomerName = sc.next();
			testCustomerName += sc.nextLine();

			System.out.println("Enter phone number");
			testPhoneNo = sc.next();

			System.out.println(
					"Enter\n1\t For adding Individual Customer\n2\t For adding Corporate Customer \nEnter your choice:");
			int custchoice = sc.nextInt();

			switch (custchoice) {
			case 1:
				System.out.println("Adding Individual Customer");

				System.out.println("Enter past mileage");
				int testpastMileage = sc.nextInt();
				// adding contents to arraylist
				icust.add(new ICustomer(testCustomerID, testCustomerName, testPhoneNo, testpastMileage));

				break;

			case 2:
				System.out.println("Adding Corporate Customer");

				System.out.println("Enter rate of discount");
				int testrateOfDiscount = sc.nextInt();
				// adding contents to arraylist
				ccust.add(new CCustomer(testCustomerID, testCustomerName, testPhoneNo, testrateOfDiscount));

				break;

			default:
				System.out.println("Invalid option");
			}

		} else {
			if (testCustomerID.length() != 6)
				System.out.println("Invalid ID! Enter vehicle ID of length 6");
			else
				System.out.println("Customer ID does not begin with C");
		}
	}

	public void addVehicle() throws IOException {

		int counter = 0;
		System.out.println("Enter Vehicle ID");
		testVehicleID = sc.next();
		// to check if the vehicle ID entered is unique or not
		for (Vehicle i : veh) {
			if (i.getID().compareTo(testVehicleID) == 0) {
				counter++;
				break;
			}
		}

		for (PremiumVehicle i : pveh) {
			if (i.getID().compareTo(testVehicleID) == 0) {
				counter++;
				break;
			}
		}

		if (counter > 0) {
			System.out.println("Vehicle ID already exists, please re-enter unique ID");
		}
		// checking whether vehicle id is of length 6
		else if (testVehicleID.length() == 6) {
			System.out.println("Enter description");
			testVehicleDescription = sc.next();
			testVehicleDescription += sc.nextLine();

			System.out.println("Enter daily rate");
			testVehicleDailyRate = sc.nextDouble();

			System.out.println("Enter odometer reading");
			testVehicleOdometerR = sc.nextInt();

			System.out.println("Enter\n1\t For adding Vehicle\n2\t For adding Premium Vehicle \nEnter your choice:");
			int vehchoice = sc.nextInt();

			switch (vehchoice) {
			case 1:
				System.out.println("Adding Vehicle");
				// adding contents to arraylist
				veh.add(new Vehicle(testVehicleID, testVehicleDescription, testVehicleDailyRate, testVehicleOdometerR));

				break;

			case 2:
				System.out.println("Adding Premium Vehicle");

				System.out.println("Enter daily mileage");
				testVehicleDailyMileage = sc.nextInt();

				System.out.println("Enter service length");
				testVehicleServiceLength = sc.nextInt();

				System.out.println("Enter last service");
				testVehicleLastService = sc.nextInt();
				// adding contents to arraylist
				pveh.add(new PremiumVehicle(testVehicleID, testVehicleDescription, testVehicleDailyRate,
						testVehicleOdometerR, testVehicleDailyMileage, testVehicleServiceLength,
						testVehicleLastService));

				break;

			default:
				System.out.println("Invalid option");
			}

		} else
			System.out.println("Invalid ID! Enter vehicle ID of length 6");

	}

	private static void saveData() throws IOException {

		try {

			// open a text file to save the data to
			PrintWriter pw = new PrintWriter("Vehicle.txt");

			for (Vehicle v : veh) {

				pw.println(v.getClass().getSimpleName());
				// writing out all the details of a vehicle
				pw.println(v.getID());
				pw.println(v.getDescription());
				pw.println(v.getDailyRate());
				pw.println(v.getOdometer());
				pw.println(v.getStatus());
				if (v.getStatus() == 'A') {
					// no hirer id if vehicle is not hired
					pw.println("");
				} else {
					// putting hirer id if the status is hired
					pw.println(v.getHirer());
				}
			}

			for (PremiumVehicle pv : pveh) {
				pw.println(pv.getClass().getSimpleName());
				// writing out all the details of a premium vehicle
				pw.println(pv.getID());
				pw.println(pv.getDescription());
				pw.println(pv.getDailyRate());
				pw.println(pv.getOdometer());

				pw.println(pv.getStatus());
				if (pv.getStatus() == 'A') {
					// no hirer id if vehicle is not hired
					pw.println("");
				} else {
					// putting hirer id if the status is hired
					pw.println(pv.getHirer());
				}
				pw.println(pv.getDailyMileage());
				pw.println(pv.getServiceLength());
				pw.println(pv.getLastService());

			}

			pw.close();

			PrintWriter pwCust = new PrintWriter("Customer.txt");

			for (ICustomer ic : icust) {
				pwCust.println(ic.getClass().getSimpleName());
				// writing out all the details of a individual customer
				pwCust.println(ic.getCustID());
				pwCust.println(ic.getCustName());
				pwCust.println(ic.getPhone());

				pwCust.println(ic.getPastMileage());

			}

			for (CCustomer cc : ccust) {
				pwCust.println(cc.getClass().getSimpleName());
				// writing out all the details of a Corporate customer
				pwCust.println(cc.getCustID());
				pwCust.println(cc.getCustName());
				pwCust.println(cc.getPhone());

				pwCust.println(cc.getROD());

			}

			pwCust.close();

		} catch (FileNotFoundException e) {
			System.out.println("Error - the file cannot be opened for writing!");
		}

	}

	private static void loadData(ArrayList<Report> rep) throws ParseException, IOException, ClassNotFoundException {
		String vehID, vehDes, type, cID, cName, cph, ctype, hirerID, vID, hID, hireDate, hireCDate;
		double vehdailyR, odo, cROD, tCharges;
		int vehOdo, vehDailyM, vehSerLeng, vehLastserv, cpastMileage;
		char status;
		ObjectInputStream in = null;
		DateTime dt = new DateTime();

		try {
			Scanner input = new Scanner(new File("Vehicle.txt"));
			while (input.hasNextLine()) {
				type = input.nextLine();

				vehID = input.nextLine();

				vehDes = input.nextLine();

				vehdailyR = input.nextDouble();

				vehOdo = input.nextInt();

				status = input.next().charAt(0);

				if (status == 'H') {
					hirerID = input.next();
				} else {
					hirerID = "";
					input.nextLine();

				}

				if (type.equals("Vehicle")) {
					veh.add(new Vehicle(vehID, vehDes, vehdailyR, vehOdo));
					for (Vehicle v1 : veh) {
						if (v1.getID().compareTo(vehID) == 0) {
							v1.setStatus(status);
							v1.setHirer(hirerID);
						}
					}

				} else {

					vehDailyM = input.nextInt();
					System.out.println("dailymileage" + vehDailyM);
					vehSerLeng = input.nextInt();

					vehLastserv = input.nextInt();
					pveh.add(new PremiumVehicle(vehID, vehDes, vehdailyR, vehOdo, vehDailyM, vehSerLeng, vehLastserv));
					for (PremiumVehicle pv1 : pveh) {
						if (pv1.getID().compareTo(vehID) == 0) {
							pv1.setStatus(status);
							pv1.setHirer(hirerID);
						}
					}

				}

				if (input.hasNextLine()) {
					input.nextLine();
				}
			}
			input.close();

			Scanner inputCust = new Scanner(new File("Customer.txt"));

			while (inputCust.hasNextLine()) {
				ctype = inputCust.nextLine();
				cID = inputCust.nextLine();
				cName = inputCust.nextLine();
				cph = inputCust.nextLine();

				if (ctype.equals("ICustomer")) {
					cpastMileage = inputCust.nextInt();
					icust.add(new ICustomer(cID, cName, cph, cpastMileage));
				} else {
					cROD = inputCust.nextDouble();
					ccust.add(new CCustomer(cID, cName, cph, cROD));
				}
				if (inputCust.hasNextLine()) {
					inputCust.nextLine();
				}
			}
			inputCust.close();

			FileInputStream fin = new FileInputStream("Report.txt");
			in = new ObjectInputStream(fin);

			rep.addAll((ArrayList<Report>) in.readObject());
			fin.close();

		} catch (FileNotFoundException e) {
			System.out.println("Error - the file cannot be opened for writing!");
		}
	}

}
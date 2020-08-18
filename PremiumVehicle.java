import java.io.Serializable;

public class PremiumVehicle extends Vehicle {

	private int dailyMileage;
	private int serviceLength;
	private int lastService;
	private double totalCharges;
	private double durationCharges;
	private double mileageCharges;
	private double distanceCovered;
	private double freeMilAllowance;

	// constructor
	public PremiumVehicle(String vehicleID, String description, double dailyRate, int odometer, int dailyMileage,
			int serviceLength, int lastService) {
		super(vehicleID, description, dailyRate, odometer);
		this.dailyMileage = dailyMileage;
		this.serviceLength = serviceLength;
		this.lastService = lastService;
	}

	public boolean service() throws StatusException {
		// calling service of super class
		boolean serv = super.service();
		return serv;
	}

	public boolean serviceComplete(int odo) throws StatusException, OdometerException {
		// calling serviceComplete of super class
		boolean result = super.serviceComplete(odo);
		this.lastService = odo;
		return result;

	}

	public double hireComplete(int odo) throws StatusException, OdometerException {
		// calling hireComplete of super class
		durationCharges = super.hireComplete(odo);
		distanceCovered = odo - initialOdometer;
		// num_of_days used from vehicle class
		freeMilAllowance = dailyMileage * num_of_days;
		mileageCharges = distanceCovered - freeMilAllowance;
		if (distanceCovered > freeMilAllowance) {
			// combining mileage based charges with previously calculated charges
			totalCharges = durationCharges + (mileageCharges * 0.10);
		} else {
			totalCharges = durationCharges + mileageCharges;
		}
		return totalCharges;

	}

	public boolean hire(String hirerID) throws StatusException {

		if (lastService + serviceLength > this.getOdometer()) {
			super.hire(hirerID);
			return true;
		} else {
			System.out.println("Vehicle could not be hired as the service is due.");
			return false;
		}

	}

	public void print() {
		super.print();
		System.out.println("Mileage Allowance = " + dailyMileage + "\t Service Length = " + serviceLength
				+ "\t Last Service = " + this.lastService);

	}

//getters
	public char getStatus() {
		// TODO Auto-generated method stub
		char stat = super.getStatus();
		return stat;
	}

	public String getID() {
		String id = super.getID();
		return id;
	}

	public int getServiceLength() {
		return serviceLength;
	}

	public int getLastService() {
		return lastService;
	}

	public int getDailyMileage() {
		return dailyMileage;
	}

	@Override
	public String toString() {
		return "PremiumVehicle [hiredt=" + hiredt + ", getStatus()=" + getStatus() + ", getID()=" + getID()
				+ ", getDescription()=" + getDescription() + ", getDailyRate()=" + getDailyRate() + ", getHirer()="
				+ getHirer() + ", toString()=" + super.toString() + "]";
	}

}

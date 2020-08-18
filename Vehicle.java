import java.io.Serializable;

class Vehicle implements Serializable {

	private String vehicleID;
	private String hirerID = "";
	private String description;
	private char status = 'A';
	private double dailyRate;
	protected int odometerR;
	DateTime hiredt;
	protected double charges = 0;
	protected int num_of_days = 0;

	protected int initialOdometer;

	// constructor
	public Vehicle(String vehicleID, String description, double dailyRate, int odometer) {
		this.vehicleID = vehicleID;
		this.description = description;
		this.dailyRate = dailyRate;
		this.odometerR = odometer;
		status = 'A';
		initialOdometer = this.odometerR;
	}

	// to hire a vehicle
	public boolean hire(String hirerID) throws StatusException // called when a car is hired --- testVehicle
	{
		// if status is other than available throwing excpetion
		if (status == 'H')
			throw new StatusException("The vehicle is not available as it is hired already");
		else if (status == 'S') {
			throw new StatusException("The vehicle is not available as it is in service");

		} else {
			this.hirerID = hirerID;
			hiredt = new DateTime();
			status = 'H';
			return true;
		}

	}

//complete hiring of vehicle
	public double hireComplete(int odo) throws StatusException, OdometerException {

		if (status == 'A')
			throw new StatusException("The vehicle is availabe and not hired, thus hire cannot be completed.");
		else if (status == 'S')
			throw new StatusException("The vehicle is in service and not hired, thus hire cannot be completed.");
		else if (status == 'H' && odo < this.odometerR)
			throw new OdometerException("Previous odometer reading is greater than current one");
		else {

			num_of_days = DateTime.diffDays(new DateTime(), hiredt);
			status = 'A';
			charges = dailyRate * num_of_days;
			this.odometerR = odo;
			return charges;
		}
	}

	// servicing methods
	public boolean service() throws StatusException// called when car is sent to service, status should be A
	{
		if (status == 'S')
			throw new StatusException("Vehicle cannot be serviced as it is already being serviced.");
		else if (status == 'H')
			throw new StatusException("Vehicle cannot be serviced as it is already hired.");
		else {
			status = 'S';
			return true;
		}
	}

	public boolean serviceComplete(int odo) throws StatusException, OdometerException// called when car is back from
																						// service, status should be S
	{
		if (status == 'A')
			throw new StatusException("Service cannot be completed as vehicle is available and not in service.");
		else if (status == 'H')
			throw new StatusException("Service cannot be completed as vehicle is hired and not in service.");
		else if (status == 'S' && odo < this.odometerR)
			throw new OdometerException("Previous odometer reading is greater than current one");
		else {
			status = 'A';
			this.odometerR = odo;
			return true;
		}

	}

	// other functions
	public String getID() {
		// TODO Auto-generated method stub
		return vehicleID;
	}

	public DateTime getHiredt() {
		return this.hiredt;
	}

	public DateTime setHiredt(DateTime hiredt) {
		return this.hiredt = hiredt;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	public double getDailyRate() {
		// TODO Auto-generated method stub
		return dailyRate;
	}

	public int getOdometer() {
		// TODO Auto-generated method stub
		return odometerR;
	}

	public String getHirer() {
		// TODO Auto-generated method stub
		return hirerID;
	}

	public char getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	public char setStatus(char stat) {
		return status = stat;
	}

	public void print() {
		// TODO Auto-generated method stub
		System.out.print("\n****************** Vehicle Details ******************\n" + DateTime.getCurrentTime()
				+ "\nVehicle ID =" + vehicleID + "\tDescription =" + description + "\tStatus=" + status
				+ "\tDaily Rate=" + dailyRate + " \tOdometer Reading=" + odometerR + "\n");
		if (status == 'H') {
			System.out.println("Hirer=" + hirerID + " Date/time of hire=" + hiredt);
		}
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleID=" + vehicleID + ", description=" + description + "]";
	}

	public void setHirer(String hirerID2) {
		// TODO Auto-generated method stub
		this.hirerID = hirerID2;
	}

}

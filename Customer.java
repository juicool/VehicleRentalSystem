
public abstract class Customer {
	protected String custID;
	protected String custName;
	protected String phone;
	protected double discountedAmt;

	// constructor
	public Customer(String id, String name, String phno) {
		custID = id;
		custName = name;
		phone = phno;
	}

//abstract method for getting discount
	public abstract double getDiscount(double amount);

//getters
	public String getCustID() {
		return custID;
	}

	public String getCustName() {
		return custName;
	}

	public String getPhone() {
		return phone;
	}

	// overriding toString
	@Override
	public String toString() {
		return "Customer [custID=" + custID + "]";
	}

}

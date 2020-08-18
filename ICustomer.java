
public class ICustomer extends Customer {

	protected int pastMileage;

	// constructor
	public ICustomer(String id, String name, String phno, int pastMileage) {
		super(id, name, phno);
		this.pastMileage = pastMileage;
		// TODO Auto-generated constructor stub
	}

	// overriding getDiscount method of abstract class according to the
	// specifications for the discount to be applied to individual customer
	@Override
	public double getDiscount(double amount) {
		// TODO Auto-generated method stub
		if (pastMileage > 100000) {
			return (amount *= 0.1);

		} else if (pastMileage > 200000) {
			return (amount *= 0.2);
		} else {
			return 0.0;
		}
	}

	// getters
	@Override
	public String getCustID() {
		// TODO Auto-generated method stub
		return super.getCustID();
	}

	public int getPastMileage() {
		return pastMileage;
	}

	@Override
	public String toString() {
		return "ICustomer [custID=" + custID + ", custName=" + custName + ", phone=" + phone + ", discountedAmt="
				+ discountedAmt + "]";
	}

}

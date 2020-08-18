
public class CCustomer extends Customer {
	protected double rateOfDiscount;

	// constructor
	public CCustomer(String id, String name, String phno, double rateOfDiscount) {
		super(id, name, phno);
		this.rateOfDiscount = rateOfDiscount;
		// TODO Auto-generated constructor stub
	}

//overriding getDiscount method of abstract class according to the specifications for the discount to be applied to corporate customer
	public double getDiscount(double amount) {
		amount *= (rateOfDiscount / 100);
		return amount;
	}

//getter and setter
	public double getROD() {
		return rateOfDiscount;
	}

	public void setRate(double discountRate) {
		this.rateOfDiscount = discountRate;
	}

	@Override
	public String toString() {
		return "CCustomer [custID=" + custID + ", custName=" + custName + ", phone=" + phone + ", discountedAmt="
				+ discountedAmt + "]";
	}

}

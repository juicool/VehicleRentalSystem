import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Report implements Serializable {

	String vID, hID, currDate;
	DateTime hireCompleteDate, hiredate;
	Double totalCharges;

//constructor
	public Report(String vID, String hID, DateTime hiredate, Double totalCharges) {
		this.vID = vID;
		this.hID = hID;
		this.hiredate = hiredate;
		// this.hireCompleteDate = hireCompleteDate;
		this.totalCharges = totalCharges;
	}

//getters
	public String getVID() {
		return vID;
	}

	public String getHID() {
		return hID;
	}

	public DateTime gethiredt() {
		return hiredate;
	}

	public Double getTotalCharges() {
		return totalCharges;
	}

	public void setTotalCharges(Double totalCharges) {
		this.totalCharges = totalCharges;
	}

	public void print() {
		// TODO Auto-generated method stub
		System.out.print("\n****************** Rental History ******************\n");
		System.out.println("Vehicle ID :" + getVID() + "\tHirer ID :" + getHID() + "\tCharges :" + getTotalCharges()
				+ "\tHired date :" + gethiredt());
	}

}

package Com.sanjeet.javabeans;

import java.math.BigDecimal;

public class Employee implements IEmployee
{
	private String FirstName;
	private String LastName;
	private long EmployeeID;
	private long FogNo;
	private String Role;
	
	Employee(String firstName, String lastName, long _ID, long _Fogno, String role){
		this.FirstName = firstName;
		this.LastName = lastName;
		EmployeeID = _ID;
		FogNo = _Fogno;
		Role = role;
	}
	
	public String getFirstName(){
		return FirstName;
	}
	public String getLastName(){
		return LastName;
	}
	public String getname(){
		return FirstName +" " + LastName;
	}
	public long getEmployeeID(){
		return EmployeeID;
	}
	public BigDecimal getbigID(){
		return new BigDecimal(EmployeeID);
	}
	public BigDecimal getbigFobID(){
		return new BigDecimal(FogNo);
	}
	public long getFogNo(){
		return FogNo;
	}
	public String GetParkingRole(){
		return Role;
	}
}

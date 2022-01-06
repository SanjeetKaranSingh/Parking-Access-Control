package Com.sanjeet.javabeans;

import java.util.Date;

public class ParkingEntry {
	private long EmployeeID;
	private Date date;
	private String EmployeeName;
	private String Username;
	public Date getDateOfEntry(){
		return date;
	}
	public long getEmployeeID(){
		return EmployeeID;
	}
	public String getName(){
		return EmployeeName;
	}
	
	public String getUserName(){
		return Username;
	}
	public ParkingEntry(Date date, long id, String username, String Empname){
		this.date = date;
		this.EmployeeID = id;
		this.Username = username;
		this.EmployeeName = Empname;
	}
}

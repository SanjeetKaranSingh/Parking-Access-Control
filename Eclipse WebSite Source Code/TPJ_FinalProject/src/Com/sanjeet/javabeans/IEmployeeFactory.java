package Com.sanjeet.javabeans;

public interface IEmployeeFactory {
	public IEmployee createEmployee(String firstName, String lastName, long _ID, long _Fogno, String role); 
}

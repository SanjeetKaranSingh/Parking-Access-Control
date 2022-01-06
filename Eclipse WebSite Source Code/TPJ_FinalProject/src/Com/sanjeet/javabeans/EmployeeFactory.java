package Com.sanjeet.javabeans;

public class EmployeeFactory implements IEmployeeFactory{
	
	public IEmployee createEmployee(String firstName, String lastName, long _ID, long _Fogno, String role) 
	{
		return new Employee(firstName, lastName,_ID , _Fogno, role);
	}
}

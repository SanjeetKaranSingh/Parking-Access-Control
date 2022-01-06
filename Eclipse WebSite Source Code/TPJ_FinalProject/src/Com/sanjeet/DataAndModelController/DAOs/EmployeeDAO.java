package Com.sanjeet.DataAndModelController.DAOs;

import java.sql.SQLException;

import Com.sanjeet.javabeans.IEmployee;
import Com.sanjeet.javabeans.IEmployeeFactory;

public interface EmployeeDAO {
	public IEmployee ValidateAndGiveEmployee(String username, String password) throws SQLException;
	public java.util.List<IEmployee> getEmployeesList() throws SQLException;
	public void deleteEmployeeWithId(int id) throws SQLException; 
	public void addAnEmployee(IEmployee emp, String Username, String Password) throws SQLException;
}

package Com.sanjeet.DataAndModelController.Data;

import Com.sanjeet.DataAndModelController.DAOs.EmployeeDAO;
import Com.sanjeet.SQLconnection.DataSourceFactory;
import Com.sanjeet.javabeans.EmployeeFactory;
import Com.sanjeet.javabeans.IEmployeeFactory;

public class EmployeeDAOFactory {
	public static EmployeeDAO getEmployeeDAO(){
		return new EmployeeData(new EmployeeFactory(), DataSourceFactory.getDataSource());
	}
}

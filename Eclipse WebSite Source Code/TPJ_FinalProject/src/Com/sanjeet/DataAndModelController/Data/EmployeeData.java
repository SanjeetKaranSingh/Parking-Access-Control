package Com.sanjeet.DataAndModelController.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;



import com.sun.xml.bind.v2.schemagen.xmlschema.List;

import Com.sanjeet.DataAndModelController.DAOs.EmployeeDAO;
import Com.sanjeet.javabeans.EmployeeFactory;
import Com.sanjeet.javabeans.IEmployee;
import Com.sanjeet.javabeans.IEmployeeFactory;

class EmployeeData implements EmployeeDAO
{
	private IEmployeeFactory myObjectsStore;
	private DataSource ds;
	public EmployeeData(IEmployeeFactory factory, DataSource _ds) {
		myObjectsStore = factory;
		setDataSource(_ds);
		// TODO Auto-generated constructor stub
	}
	
	private void setDataSource(DataSource dataSrc){
		ds =  dataSrc;
	}
	
	private DataSource getDs(){
		return ds;
	}
	
	public IEmployee ValidateAndGiveEmployee(String username, String password) throws SQLException
	{
		try(Connection con = getDs().getConnection())
		{
			try (PreparedStatement pstmt = con.prepareStatement("SELECT EmployeeID from EmployeeDT WHERE Username = ? AND Password = ?"))
			{
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				
				try(ResultSet rslt = pstmt.executeQuery())
				{
					
					if(rslt.next())
					{
						long id1 = rslt.getLong("EmployeeID");
						return getEmployeeById(id1);			
					}
					else
					{
						return null;
					}
				}
			}
		}
	}
	
	public IEmployee getEmployeeById(long id) throws SQLException {
		try(Connection con = getDs().getConnection())
		{
			try (PreparedStatement pstmt = con.prepareStatement("SELECT EmployeeID,FirstName, LastName, FogNo, Job from EmployeeDT Where EmployeeID = ?"))
			{
				pstmt.setLong(1, id);
				try(ResultSet rslt = pstmt.executeQuery())
				{
					if(rslt.next())
					{
						String firstName = rslt.getString("FirstName");
						String lastName = rslt.getString("LastName");
						int fogNo = rslt.getInt("FogNo");
						String role = rslt.getString("Job");
						return new EmployeeFactory().createEmployee(firstName, lastName, id, fogNo, role);
					}
					else
					{
						return null;
					}
				}
			}
		}
	}		
	public java.util.List<IEmployee> getEmployeesList() throws SQLException
	{
		java.util.List<IEmployee> list =  new ArrayList<IEmployee>();
		
		try(Connection con = getDs().getConnection())
		{
			try (PreparedStatement pstmt = con.prepareStatement("SELECT EmployeeID,FirstName, LastName, FogNo, Job from EmployeeDT"))
			{
				try(ResultSet rslt = pstmt.executeQuery())
				{
					while(rslt.next())
					{
						String firstName = rslt.getString("FirstName");
						String lastName = rslt.getString("LastName");
						Long fogNo = rslt.getLong("FogNo");
						String role = rslt.getString("Job");
						Long EmployeeId = rslt.getLong("EmployeeID");
						list.add(new EmployeeFactory().createEmployee(firstName, lastName, EmployeeId, fogNo, role));
					}
					return list;
				}
			}
		}

	}

	@Override
	public void deleteEmployeeWithId(int id) throws SQLException {
		// TODO Auto-generated method stub
		try(Connection con = getDs().getConnection())
		{
			try(PreparedStatement pstmt = con.prepareStatement("DELETE FROM EmployeeDT WHERE EmployeeID = ?"))
			{
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
			}
		}
		
	}

	@Override
	public void addAnEmployee(IEmployee emp, String Username, String Password) throws SQLException {
		// TODO Auto-generated method stub
		try(Connection con = getDs().getConnection())
		{
			try(PreparedStatement pstmt = con.prepareStatement("insert into EmployeeDT(Username,FogNo,EmployeeID,Password,Job,FirstName,LastName) VALUES (?, ?, ?, ?, ?, ?, ?)"))
			{
				pstmt.setString(1, Username);
				pstmt.setLong(2, emp.getFogNo());
				pstmt.setLong(3, emp.getEmployeeID());
				pstmt.setString(4, Password);
				pstmt.setString(5, emp.GetParkingRole());
				pstmt.setString(6, emp.getFirstName());
				pstmt.setString(7, emp.getLastName());
				pstmt.executeUpdate();
			}
		}
		
	}
}

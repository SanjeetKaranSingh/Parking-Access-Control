package Com.sanjeet.DataAndModelController.Data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

import javax.sql.DataSource;

import org.omg.CORBA.UserException;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

import Com.sanjeet.DataAndModelController.DAOs.VehicalsEntryDataDAO;
import Com.sanjeet.javabeans.EmployeeFactory;
import Com.sanjeet.javabeans.ParkingEntry;
class VehicalsEntryData implements VehicalsEntryDataDAO
{
	private DataSource ds;
	
	private void setDataSource(DataSource dataSrc){
		ds =  dataSrc;
	}
	public VehicalsEntryData(DataSource datascr){
		setDataSource(datascr);
	}
	private DataSource getDs(){
		return ds;
	}
	public java.util.List<ParkingEntry> getEntryList() throws SQLException{
		java.util.List<ParkingEntry> ParkingEntryList = new ArrayList<ParkingEntry>();
				try(Connection con = getDs().getConnection())
				{
					try (PreparedStatement pstmt = con.prepareStatement("select EntryTable.TimeValue, EntryTable.EmployeeID, EmployeeDT.FirstName, EmployeeDT.LastName, EmployeeDT.Username from dbo.EntryTable inner join EmployeeDT on EmployeeDT.FogNo = EntryTable.EmployeeID order by EntryTable.TimeValue desc"))
					{
						try(ResultSet rslt = pstmt.executeQuery())
						{
							while(rslt.next())
							{
								java.util.Date TimeValue = rslt.getTimestamp("TimeValue");
								long empID = rslt.getLong("EmployeeID");
								String FirstName = rslt.getString("FirstName");
								String LastName = rslt.getString("LastName");
								String username = rslt.getString("Username");
								ParkingEntryList.add(new ParkingEntry(TimeValue,empID,FirstName+LastName,username));
							}
							return ParkingEntryList;
						}
					}
				}
	}
	}

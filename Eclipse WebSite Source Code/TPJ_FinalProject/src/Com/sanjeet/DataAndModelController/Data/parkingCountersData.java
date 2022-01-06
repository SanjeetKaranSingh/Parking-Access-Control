package Com.sanjeet.DataAndModelController.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Com.sanjeet.DataAndModelController.DAOs.ParkingCounterDAO;
import Com.sanjeet.javabeans.ParkingCounters;

import javax.sql.DataSource;

public class parkingCountersData implements ParkingCounterDAO {
	private DataSource ds;
	public parkingCountersData(DataSource _ds){
		ds = _ds;
	}
	
	/* (non-Javadoc)
	 * @see Com.sanjeet.DataObjects.ParkingCounterDAO#getNumberOfVehicalsAndParkingLimit()
	 */
	@Override
	public ParkingCounters getNumberOfVehicalsAndParkingLimit() throws SQLException
	{
		try (Connection conn = getDs().getConnection()){
			try (Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)){
				try (ResultSet rslt = stmt.executeQuery("select VehicalCounter from InstructionFlags")){
					if(rslt.next())
					{
						int VehicalCounter = rslt.getInt(1);
						try (ResultSet rslt2 = stmt.executeQuery("select ParkingLimit from InstructionFlags")){
							if(rslt2.next())
							{
								int parkingLimit = rslt2.getInt(1);
								return new ParkingCounters(VehicalCounter, parkingLimit);
							}
						}
					}
				}				
			}
		}
		return null;
	}
	
	
	private DataSource getDs()
	{
		return ds;
	}

	private void setDs(DataSource ds)
	{
		this.ds = ds;
	}

	@Override
	public void setParkingLimit(int newCount) throws SQLException {
		// TODO Auto-generated method stub
		try (Connection conn = getDs().getConnection()){
				try (PreparedStatement rslt = conn.prepareStatement("update rasberry.dbo.InstructionFlags set ParkingLimit = ?")){
					rslt.setInt(1, newCount);
					
					rslt.executeUpdate();
				}				
			
		}		
	}

}

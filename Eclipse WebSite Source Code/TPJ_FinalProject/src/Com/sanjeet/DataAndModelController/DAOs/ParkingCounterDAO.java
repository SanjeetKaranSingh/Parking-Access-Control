package Com.sanjeet.DataAndModelController.DAOs;

import java.sql.SQLException;

import Com.sanjeet.javabeans.ParkingCounters;

public interface ParkingCounterDAO {

	public abstract ParkingCounters getNumberOfVehicalsAndParkingLimit()
			throws SQLException;
	public abstract void setParkingLimit(int newCount) throws SQLException;
}
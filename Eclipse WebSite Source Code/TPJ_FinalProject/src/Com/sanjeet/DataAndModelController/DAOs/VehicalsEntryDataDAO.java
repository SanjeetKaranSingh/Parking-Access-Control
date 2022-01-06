package Com.sanjeet.DataAndModelController.DAOs;

import java.sql.SQLException;

import Com.sanjeet.javabeans.ParkingEntry;

public interface VehicalsEntryDataDAO {
	public java.util.List<ParkingEntry> getEntryList() throws SQLException;
}

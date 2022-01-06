package Com.sanjeet.DataAndModelController.Data;
import Com.sanjeet.DataAndModelController.DAOs.ParkingCounterDAO;
import Com.sanjeet.SQLconnection.DataSourceFactory;

import javax.sql.DataSource;

public class ParkingDAOFactory {
	public static ParkingCounterDAO getParkingCounter(){
		return new parkingCountersData(DataSourceFactory.getDataSource());
	}
}

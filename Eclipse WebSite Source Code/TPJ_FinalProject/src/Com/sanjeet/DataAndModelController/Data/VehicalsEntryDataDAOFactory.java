package Com.sanjeet.DataAndModelController.Data;

import Com.sanjeet.DataAndModelController.DAOs.VehicalsEntryDataDAO;
import Com.sanjeet.SQLconnection.DataSourceFactory;

public class VehicalsEntryDataDAOFactory {
	public VehicalsEntryDataDAO getEntryDataObject(){
		return new VehicalsEntryData(DataSourceFactory.getDataSource());
	}
}

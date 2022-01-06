package Com.sanjeet.SQLconnection;

import javax.sql.DataSource;

public class DataSourceFactory {
	private static DataSource datasource;
	public static void setDataSource(DataSource ds){
		datasource = ds;
	}
	public static DataSource getDataSource(){
		return datasource;
	}

}

package Com.sanjeet.SQLconnection;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;


/**
 * Application Lifecycle Listener implementation class SQLconnectionList
 *
 */
@WebListener
public class SQLconnectionList implements ServletContextListener {
	@Resource(name = "rasberryDS")
	private DataSource _ds;
    /**
     * Default constructor. 
     */
    public SQLconnectionList() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	DataSourceFactory.setDataSource(_ds);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    	DataSourceFactory.setDataSource(null);
        // TODO Auto-generated method stub
    }
	
}

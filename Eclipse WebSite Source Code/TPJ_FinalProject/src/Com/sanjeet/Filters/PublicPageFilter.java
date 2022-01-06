package Com.sanjeet.Filters;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import Com.sanjeet.DataAndModelController.DAOs.ParkingCounterDAO;
import Com.sanjeet.DataAndModelController.Data.ParkingDAOFactory;

/**
 * Servlet Filter implementation class PublicPageFilter
 */

public class PublicPageFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PublicPageFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		try
		{
			ParkingCounterDAO ParkingCounterdao = ParkingDAOFactory.getParkingCounter(); 	
			request.setAttribute("ParkingSpaceAvailable", ParkingCounterdao.getNumberOfVehicalsAndParkingLimit().getParkingLimit() - ParkingCounterdao.getNumberOfVehicalsAndParkingLimit().getVehicalCounter());
		}
		catch (SQLException sqle)
		{
			throw new ServletException(sqle);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

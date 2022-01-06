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

import Com.sanjeet.DataAndModelController.DAOs.EmployeeDAO;
import Com.sanjeet.DataAndModelController.DAOs.ParkingCounterDAO;
import Com.sanjeet.DataAndModelController.Data.EmployeeDAOFactory;
import Com.sanjeet.DataAndModelController.Data.ParkingDAOFactory;

/**
 * Servlet Filter implementation class parkingLimitsDataDisplayFiter
 */
@WebFilter("/parkingshow.jspx")
public class parkingLimitsDataDisplayFiter implements Filter {

    /**
     * Default constructor. 
     */
    public parkingLimitsDataDisplayFiter() {
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
		ParkingCounterDAO ParkingCounterdao = ParkingDAOFactory.getParkingCounter();
		try {
			request.setAttribute("ParkingInstructObj", ParkingCounterdao.getNumberOfVehicalsAndParkingLimit());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

package Com.sanjeet.Filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.ws.runtime.dev.Session;

import sun.net.www.http.HttpClient;
import Com.sanjeet.DataAndModelController.DAOs.EmployeeDAO;
import Com.sanjeet.DataAndModelController.DAOs.ParkingCounterDAO;
import Com.sanjeet.DataAndModelController.Data.EmployeeDAOFactory;
import Com.sanjeet.DataAndModelController.Data.ParkingDAOFactory;
import Com.sanjeet.javabeans.EmployeeFactory;
import Com.sanjeet.javabeans.IEmployee;
import Com.sanjeet.javabeans.IEmployeeFactory;
import Com.sanjeet.javabeans.ParkingCounters;
import Com.sanjeet.remoteControlAPIs.apiRequest;

/**
 * Servlet Filter implementation class AdministratorHomePage
 */
public class AdministratorHomePage implements Filter {

    /**
     * Default constructor. 
     */
    public AdministratorHomePage() {
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
		
		HttpServletRequest req = (HttpServletRequest)request;
		ParkingCounterDAO ParkingCounterdao = ParkingDAOFactory.getParkingCounter();
		EmployeeDAO empdao = EmployeeDAOFactory.getEmployeeDAO();
		ServletContext contextRoot = req.getServletContext();
		
		ParkingCounters parkingCounter;
		try 
		{
			
			if ("POST".equals(req.getMethod()))
			{
				if("submitParkingLimit".equals(request.getParameter("submitParkingLimit"))){
					int newLimit;
					try
					{
						newLimit = Integer.parseInt(request.getParameter("parkingLimit"));
					}
					catch(NumberFormatException ex){
						newLimit = 0;
					}
					ParkingCounterdao.setParkingLimit(newLimit);
				}
				else{
					if("Delete".equals(request.getParameter("cancelbutton"))){
						try{
							empdao.deleteEmployeeWithId(Integer.parseInt(request.getParameter("employeeID")));
						}
						catch(NumberFormatException ex){
							String a = "sds";
							// this is something that needs to be implemented as it will always catch this exception with long input
							// not a safe way of doing it
						}
					}
					else{
						if("Add Employee".equals(request.getParameter("AddEmployee"))){
							String Username = request.getParameter("Username");
							Long FogNo = Long.parseLong(request.getParameter("FogNo")); 
							// Note for my self :- Add exception handling
							Long EmployeeID = Long.parseLong(request.getParameter("EmployeeID"));
							String Password = request.getParameter("Password");
							String job = request.getParameter("Job");
							String FirstName = request.getParameter("FirstName");
							String LastName = request.getParameter("LastName");
							empdao.addAnEmployee((new EmployeeFactory()).createEmployee(FirstName, LastName, EmployeeID, FogNo, job), Username, Password);
						}
						
						if("Turn Off night Mode".equals(request.getParameter("IsNightModeOnButton"))){
							apiRequest.shared.TurnOffNightMode();
							contextRoot.setAttribute("IsNightModeOnButton", false);
//							request.setAttribute("IsNightModeOnButton", false);
						}
						else{
							if("Turn On night Mode".equals(request.getParameter("IsNightModeOnButton"))){
								if(contextRoot.getAttribute("IsNightModeOnButton") != "true"){
								apiRequest.shared.TurnOnNightMode();
								contextRoot.setAttribute("IsNightModeOnButton", true);
								}
//								request.setAttribute("IsNightModeOnButton", true);
							}
							else{
							if("Turn Off Emergency Mode".equals(request.getParameter("IsEmergencyOn"))){
								apiRequest.shared.TurnOffEmergyPlan();
								contextRoot.setAttribute("IsEmergencyOn", false);
								
		//						request.setAttribute("IsEmergencyOn", false);
							}
							else{
								if("Turn On Emergency Mode".equals(request.getParameter("IsEmergencyOn"))){
									if(contextRoot.getAttribute("IsEmergencyOn") != "true"){
									apiRequest.shared.TurnOnEmergyPlan();
									
									contextRoot.setAttribute("IsEmergencyOn", true);
									}
								}
							}
							}
						}
					}
				}
			}
			
			parkingCounter = ParkingCounterdao.getNumberOfVehicalsAndParkingLimit();
			request.setAttribute("EmployeeList", empdao.getEmployeesList());
			request.setAttribute("shouldAddNewEmployee", request.getParameter("shouldAddNewEmployee"));

					// TODO Auto-generated method stub
			request.setAttribute("shouldResetParkingLimit", request.getParameter("shouldResetParkingLimit"));
			request.setAttribute("ParkingInstructObj", parkingCounter);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block			

			e.printStackTrace();
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

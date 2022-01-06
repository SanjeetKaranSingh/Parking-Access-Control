package Com.sanjeet.Filters;
import Com.sanjeet.javabeans.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Com.sanjeet.DataAndModelController.DAOs.ParkingCounterDAO;
import Com.sanjeet.DataAndModelController.Data.EmployeeDAOFactory;
import Com.sanjeet.DataAndModelController.Data.ParkingDAOFactory;
import Com.sanjeet.javabeans.IEmployee;


/**
 * Servlet Filter implementation class Authentication
 */
public class Authentication implements Filter {

    /**
     * Default constructor. 
     */
    public Authentication() {
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
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest request = (HttpServletRequest)req;
		HttpSession session = request.getSession();
		HttpServletResponse response = (HttpServletResponse)resp;
		
		try
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if(session.getAttribute("EmployeeObj") == null)
			{
					IEmployee employee = EmployeeDAOFactory.getEmployeeDAO().ValidateAndGiveEmployee(username, password);
					if (employee != null)
					{		
						if ("POST".equals(request.getMethod()) && username != null && password != null && !("".equals(username)) && !("".equals(password)))
						{
							
							session.setAttribute("EmployeeObj", employee);
							if(employee.GetParkingRole().equals("Admin"))
							{
								response.sendRedirect(request.getContextPath() + "/AdministratorPage.jspx"); // redirect to context root folder							
								return;
							}
							else 
							{
								response.sendRedirect(request.getContextPath() + "/GeneralEmployee.jspx"); // redirect to context root folder
								return;							
							}
						}
						
					}
					else
					{
						if(username != null && password != null){
						request.setAttribute("username", username);
						request.setAttribute("unsuccessfulLogin", Boolean.TRUE);
					}
					}
				}
				else
				{
						IEmployee employee = (IEmployee) session.getAttribute("EmployeeObj");
						
						if(employee.GetParkingRole().equals("Admin"))
						{
							response.sendRedirect(request.getContextPath() + "/AdministratorPage.jspx"); // redirect to context root folder							
							return;
						}
						else 
						{
							response.sendRedirect(request.getContextPath() + "/GeneralEmployee.jspx"); // redirect to context root folder
							return;						
						}
				}		
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

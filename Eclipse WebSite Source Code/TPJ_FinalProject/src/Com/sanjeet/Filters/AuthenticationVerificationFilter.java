package Com.sanjeet.Filters;

import java.io.IOException;

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

import Com.sanjeet.javabeans.IEmployee;

/**
 * Servlet Filter implementation class AuthenticationVerificationFilter
 */
public class AuthenticationVerificationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationVerificationFilter() {
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
		
		if((!request.getRequestURI().equals(request.getContextPath() + "/Employeelogin.jspx")) && (!request.getRequestURI().equals(request.getContextPath() + "/publicWeb.jspx")) && (!request.getRequestURI().equals(request.getContextPath() + "/")))
		{
			if(session.getAttribute("EmployeeObj") == null){
				response.sendRedirect(request.getContextPath() + "/Employeelogin.jspx"); // redirect to context root folder
				return;
			}
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

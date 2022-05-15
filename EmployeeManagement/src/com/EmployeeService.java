package com;

import com.Employee;
import java.io.IOException; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EmployeeService")
public class EmployeeService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Employee EmployeeObj = new Employee();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeService() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = EmployeeObj.insertEmployee(request.getParameter("employeeNIC"),      
				request.getParameter("employeeName"),
				request.getParameter("employeeType"),
				request.getParameter("employeePhoneNo")); 
				response.getWriter().write(output);
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method

		Map paras = getParasMap(request); 
		 
		 String output = EmployeeObj.updateEmployee(paras.get("hidEmployeeIDSave").toString(),     
		    		paras.get("employeeNIC").toString(),     
		    		paras.get("employeeName").toString(), 
		    		paras.get("employeeType").toString(),
		    		paras.get("employeePhoneNo").toString()); 
		 
		 			response.getWriter().write(output);
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request); 
		 
		 String output = EmployeeObj.deleteEmployee(paras.get("employeeID").toString());  
		 
		 response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
		{
		 Map<String, String> map = new HashMap<String, String>();
		try
		 { 
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		 String queryString = scanner.hasNext() ?
		 scanner.useDelimiter("\\A").next() : "";
		 scanner.close();
		 String[] params = queryString.split("&");
		 for (String param : params)
		 { 
		
		String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		}

}

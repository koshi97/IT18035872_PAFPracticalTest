package com;

import com.Appointment;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AppointmentsAPI
 */
@WebServlet("/AppointmentsAPI")

public class AppointmentsAPI extends HttpServlet{
	
private static final long serialVersionUID = 1L;
	
	Appointment appObj = new Appointment();
       
/**
   * @see HttpServlet#HttpServlet()
 */
    public AppointmentsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

    
/**
 	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	
/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* doGet(request, response); */
		
		
		String output = appObj.insertAppointment(request.getParameter("appNo"),
				request.getParameter("appType"),
				request.getParameter("appDate"),
				request.getParameter("appDescription"));
		
				response.getWriter().write(output);	
	}
	
	
/**
 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		Map paras = getParasMap(request);
		String output = appObj.updateAppointment(paras.get("hidAppIDSave").toString(),
		paras.get("appNo").toString(),
		paras.get("appType").toString(),
		paras.get("appDate").toString(),
		paras.get("appDescription").toString());
		response.getWriter().write(output);
		}

	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		Map paras = getParasMap(request);
		String output = appObj.deleteAppointment(paras.get("appID").toString());
		response.getWriter().write(output);
		
	}
	
	
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

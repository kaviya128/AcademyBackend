package com.academy.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entitysampleDAO.StudentDao;

import entitySample.Student;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    private StudentDao studentDao;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		studentDao = new StudentDao();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getParameter("action");
		try {
		if(action == null) {
			
				listStudent(request,response);
			
		} else {
			if(action.equalsIgnoreCase("insertStudent")) {
				
					insertStudent(request,response);
				
			} else if (action.equalsIgnoreCase("updateStudent")) {
				
				updateStudent(request,response);
				
			} else if (action.equalsIgnoreCase("deleteStudent")) {
				
				deleteStudent(request,response);
			} 
			
		}
		
		} catch(SQLException e) {
			e.printStackTrace();
			
		} catch(IOException e) {
			e.printStackTrace();
			
		} catch(ServletException e) {
			e.printStackTrace();
		}
	}
	
	private void listStudent(HttpServletRequest request,HttpServletResponse response)
	throws SQLException, IOException, ServletException {
		List<Student> listStudent = studentDao.getAllStudent();
		request.setAttribute("listStudent",listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("studentindex.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void insertStudent(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String name = request.getParameter("name");
		int rollNo = Integer.parseInt(request.getParameter("rollNo"));
		Student newStudent = new Student(name,rollNo);
		studentDao.saveStudent(newStudent);
		response.sendRedirect("list");
	}
	
	
	private void updateStudent(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int rollNo = Integer.parseInt(request.getParameter("rollNo"));
		Student student = new Student(id,name,rollNo);
		studentDao.updateStudent(student);
		response.sendRedirect("list");
	}
	
	
	private void deleteStudent(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		studentDao.deleteStudent(id);
		response.sendRedirect("list");
	}
	
	
}

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

import com.entitysampleDAO.TeachersDao;


import entitySample.Teachers;

/**
 * Servlet implementation class TeachersServlet
 */
@WebServlet("/TeachersServlet")
public class TeachersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TeachersDao teachersDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public TeachersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		teachersDao = new TeachersDao();
	}
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
String action = request.getServletPath();
		
		try {
			switch (action) {
			case "/new":
				showNewForm(request,response);
				break;
			case "/insert":
				insertTeacher(request,response);
				break;
				
			case "/delete":
				deleteTeacher(request,response);
				break;
			case "/edit":
				showEditForm(request,response);
				break;
			case "/update":
				updateTeacher(request,response);
				break;
			default:
				listTeacher(request,response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listTeacher(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
				List<Teachers> listTeacher = teachersDao.getAllTeacher();
				request.setAttribute("listTeacher",listTeacher);
				RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-list.jsp");
				dispatcher.forward(request, response);
			}
	
	private void showNewForm(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int teacherId = Integer.parseInt(request.getParameter("teacherId"));
		Teachers existingTeacher = teachersDao.getTeachers(teacherId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-form.jsp");
		request.setAttribute("teacher",existingTeacher);
		dispatcher.forward(request, response);
		
	}
	
	private void insertTeacher(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String name = request.getParameter("name");
		int experience = Integer.parseInt(request.getParameter("experience"));
		Teachers newteacher = new Teachers(name,experience);
		teachersDao.saveTeachers(newteacher);
		response.sendRedirect("list");
	}
	
	private void updateTeacher(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int teacherId = Integer.parseInt(request.getParameter("teacherId"));
		String name = request.getParameter("name");
		int experience= Integer.parseInt(request.getParameter("experience"));
	    Teachers teacher = new Teachers(teacherId,name,experience);
		teachersDao.updateTeachers(teacher);
		response.sendRedirect("list");
	}
	
	private void deleteTeacher(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int teacherId = Integer.parseInt(request.getParameter("teacherId"));
		teachersDao.deleteTeachers(teacherId);
		response.sendRedirect("list");
	}
	
	}

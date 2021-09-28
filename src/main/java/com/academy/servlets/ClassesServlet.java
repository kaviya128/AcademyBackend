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

import com.entitysampleDAO.ClassesDao;

import entitySample.Classes;


/**
 * Servlet implementation class ClassesServlet
 */
@WebServlet("/ClassesServlet")
public class ClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClassesDao classesDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		classesDao = new ClassesDao();
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
				insertClasses(request,response);
				break;
				
			case "/delete":
				deleteClasses(request,response);
				break;
			case "/edit":
				showEditForm(request,response);
				break;
			case "/update":
				updateClasses(request,response);
				break;
			default:
				listClasses(request,response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	private void listClasses(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
				List<Classes> listClasses = classesDao.getAllClasses();
				request.setAttribute("listClasses",listClasses);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Classes-list.jsp");
				dispatcher.forward(request, response);
			}
	private void showNewForm(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("classes-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int classId = Integer.parseInt(request.getParameter("classId"));
		Classes existingClasses = classesDao.getClasses(classId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("classes-form.jsp");
		request.setAttribute("classe",existingClasses);
		dispatcher.forward(request, response);
		
	}
	
	private void insertClasses(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String name = request.getParameter("name");
		String section = request.getParameter("section");
		Classes newClasses = new Classes(name,section);
		classesDao.saveClasses(newClasses);
		response.sendRedirect("list");
	}
	
	private void updateClasses(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int classId = Integer.parseInt(request.getParameter("classId"));
		String name = request.getParameter("name");
		String section = request.getParameter("section");
		
		Classes classe = new Classes(classId,name,section);
		classesDao.updateClasses(classe);
		response.sendRedirect("list");
	}
	
	private void deleteClasses(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int classId = Integer.parseInt(request.getParameter("classId"));
		classesDao.deleteClasses(classId);
		response.sendRedirect("list");
	}
			
	
	
	}

	


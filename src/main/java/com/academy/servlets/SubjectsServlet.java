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

import com.entitysampleDAO.SubjectsDao;
import entitySample.Subjects;

/**
 * Servlet implementation class SubjectsServlet
 */
@WebServlet("/SubjectsServlet")
public class SubjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SubjectsDao subjectsDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		subjectsDao = new SubjectsDao();
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
				insertSubject(request,response);
				break;
				
			case "/delete":
				deleteSubject(request,response);
				break;
			case "/edit":
				showEditForm(request,response);
				break;
			case "/update":
				updateSubject(request,response);
				break;
			default:
				listSubject(request,response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
 
	private void listSubject(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
				List<Subjects> listSubject = subjectsDao.getAllSubjects();
				request.setAttribute("listSubject",listSubject);
				RequestDispatcher dispatcher = request.getRequestDispatcher("subject-list.jsp");
				dispatcher.forward(request, response);
			}
	
	private void showNewForm(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("subject-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		Subjects existingSubject = subjectsDao.getSubjects(subjectId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("subject-form.jsp");
		request.setAttribute("subject",existingSubject);
		dispatcher.forward(request, response);
		
	}
	
	private void insertSubject(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String name = request.getParameter("name");
		Subjects newSubject = new Subjects(name);
		subjectsDao.saveSubjects(newSubject);
		response.sendRedirect("list");
	}
	
	private void updateSubject(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		String name = request.getParameter("name");
		
		Subjects subject = new Subjects(subjectId,name);
		subjectsDao.updateSubjects(subject);
		response.sendRedirect("list");
	}
	
	private void deleteSubject(HttpServletRequest request,HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		subjectsDao.deleteSubjects(subjectId);
		response.sendRedirect("list");
	}

	}

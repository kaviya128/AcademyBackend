package com.entitysampleDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernateutil.HibernateUtil;

import entitySample.Student;

public class StudentDao {
	
	public void saveStudent(Student student) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			
			session.save(student);
			transaction.commit();
			
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	public void updateStudent(Student student) {
		
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		
		transaction = session.beginTransaction();
		
		session.update(student);
		transaction.commit();
		
	} catch(Exception e) {
		if(transaction != null) {
			transaction.rollback();
		}
		e.printStackTrace();
	}
		
	}

	
	public void deleteStudent(int id) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			
			Student student = session.get(Student.class,id);
			if(student!=null) {
				session.delete(student);
				System.out.println("student is deleted");
			}
			
			transaction.commit();
		} catch(Exception e ) {
			if(transaction != null) {
				transaction.rollback();
			}
			
			e.printStackTrace();
		}
	}
	
	public Student getStudent(int id) {
		Transaction transaction = null;
		Student student = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			
			student = session.get(Student.class, id);
			
			transaction.commit();
		} catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return student;
	}
	
	@SuppressWarnings("unchecked")
	public List<Student> getAllStudent(){
		
		Transaction transaction = null;
		List<Student> listOfStudent = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			
			listOfStudent = session.createQuery("from student").getResultList();
			transaction.commit();
		} catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfStudent;
		
	}
}

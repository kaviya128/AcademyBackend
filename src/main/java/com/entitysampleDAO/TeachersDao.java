package com.entitysampleDAO;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernateutil.HibernateUtil;

import entitySample.Teachers;



public class TeachersDao {
	
	public void saveTeachers(Teachers teacher) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			
			session.save(teacher);
			transaction.commit();
			
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	public void updateTeachers(Teachers teacher) {
		
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		
		transaction = session.beginTransaction();
		
		session.update(teacher);
		transaction.commit();
		
	} catch(Exception e) {
		if(transaction != null) {
			transaction.rollback();
		}
		e.printStackTrace();
	}
		
	}

	
	public void deleteTeachers(int teacherId) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			
			Teachers teacher = session.get(Teachers.class,teacherId);
			if(teacher!=null) {
				session.delete(teacher);
				System.out.println("teacher is deleted");
			}
			
			transaction.commit();
		} catch(Exception e ) {
			if(transaction != null) {
				transaction.rollback();
			}
			
			e.printStackTrace();
		}
	}
	
	public Teachers getTeachers(int teacherId) {
		Transaction transaction = null;
		Teachers teacher = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			
			teacher = session.get(Teachers.class, teacherId);
			
			transaction.commit();
		} catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return teacher;
	}
	@SuppressWarnings("unchecked")
	public List<Teachers> getAllTeacher(){
		
		Transaction transaction = null;
		List<Teachers> listOfTeachers = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			
			listOfTeachers = session.createQuery("from teachers").getResultList();
			transaction.commit();
		} catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfTeachers;
		
	}

}

package com.entitysampleDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernateutil.HibernateUtil;

import entitySample.Classes;



public class ClassesDao {

	public void saveClasses(Classes classe) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			
			session.save(classe);
			transaction.commit();
			
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	public void updateClasses(Classes classe) {
		
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		
		transaction = session.beginTransaction();
		
		session.update(classe);
		transaction.commit();
		
	} catch(Exception e) {
		if(transaction != null) {
			transaction.rollback();
		}
		e.printStackTrace();
	}
		
	}

	
	public void deleteClasses(int classId) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			
			Classes classe = session.get(Classes.class,classId);
			if(classe!=null) {
				session.delete(classe);
				System.out.println("class is deleted");
			}
			
			transaction.commit();
		} catch(Exception e ) {
			if(transaction != null) {
				transaction.rollback();
			}
			
			e.printStackTrace();
		}
	}
	
	public Classes getClasses(int classId) {
		Transaction transaction = null;
		Classes classe = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			
			classe = session.get(Classes.class, classId);
			
			transaction.commit();
		} catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return classe;
	}
	@SuppressWarnings("unchecked")
	public List<Classes> getAllClasses(){
		
		Transaction transaction = null;
		List<Classes> listOfClasses = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			
			listOfClasses = session.createQuery("from Classes").getResultList();
			transaction.commit();
		} catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfClasses;
		
	}
}

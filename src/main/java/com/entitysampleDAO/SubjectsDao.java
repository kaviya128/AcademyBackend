package com.entitysampleDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernateutil.HibernateUtil;


import entitySample.Subjects;

public class SubjectsDao {
	
	public void saveSubjects(Subjects subject) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			
			session.save(subject);
			transaction.commit();
			
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	public void updateSubjects(Subjects subject) {
		
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		
		transaction = session.beginTransaction();
		
		session.update(subject);
		transaction.commit();
		
	} catch(Exception e) {
		if(transaction != null) {
			transaction.rollback();
		}
		e.printStackTrace();
	}
		
	}

	
	public void deleteSubjects(int subjectId) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			
			Subjects subject = session.get(Subjects.class, subjectId);
			if(subject!=null) {
				session.delete(subject);
				System.out.println("subject is deleted");
			}
			
			transaction.commit();
		} catch(Exception e ) {
			if(transaction != null) {
				transaction.rollback();
			}
			
			e.printStackTrace();
		}
	}
	
	public Subjects getSubjects(int subjectId) {
		Transaction transaction = null;
		Subjects subject = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			
			subject = session.get(Subjects.class, subjectId);
			
			transaction.commit();
		} catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return subject;
	}
	@SuppressWarnings("unchecked")
	public List<Subjects> getAllSubjects(){
		
		Transaction transaction = null;
		List<Subjects> listOfSubject = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			
			listOfSubject = session.createQuery("from subjects").getResultList();
			transaction.commit();
		} catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfSubject;
		
	}

}

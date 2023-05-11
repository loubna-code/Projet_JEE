package DAO;

import java.sql.*;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.mindrot.jbcrypt.BCrypt;

import Beans.Employe;






public class DAOEmploye {

	
	public  static List<Employe> getAll() throws SQLException
	{
		List<Employe> employes= null;
		Transaction transaction = null;
		
		try {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// get an Employe object
			
			employes = session.createQuery("from Employe").getResultList();
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return employes;

	}
	
	public static Employe getUnique(String cin) throws SQLException
	{
		Transaction transaction = null;
		Employe c = null;
		try {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// get an Employe object
			c = session.get(Employe.class, cin);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return c;
		

	}
	
	public static void ajouter(Employe c) throws SQLException
	 {
		Transaction transaction = null;
		try {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();
			 c.setPassword(BCrypt.hashpw(c.getPassword(), BCrypt.gensalt()));
			// save the Employe object
			session.save(c);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} 
		
	 }

	 public static void modifier (Employe e1) throws SQLException
	 {	
			Transaction transaction = null;
			try {
				SessionFactory sessionFactory = new Configuration()
		    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
		        Session session = sessionFactory.openSession();
				// start a transaction
				transaction = session.beginTransaction();
				// save the Employe object
				 e1.setPassword(BCrypt.hashpw(e1.getPassword(), BCrypt.gensalt()));
				session.saveOrUpdate(e1);
				// commit transaction
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
	 }
	
	public static boolean supprimer(String cin1) throws SQLException {
		Transaction transaction = null;
		try  {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a Employe object
			Employe c = session.get(Employe.class, cin1);
			if (c != null) {
				session.delete(c);
				System.out.println(" Employe is deleted");
			}

			// commit transaction
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return false;    
                
	}
	public static boolean validate(String cin,String email, String password) {

		Transaction transaction = null;
		Employe user = null;
		try  {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// get an Employe object
			user = (Employe) session.createQuery("FROM Employe U WHERE U.cin = :cin").setParameter("cin", cin).uniqueResult();
			
			if(user != null && user.getEmail().equals(email) &&user.getPassword().equals(password)) {
				return true;
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//Citoyen c=new Citoyen("WA212","khalid","oussama","aimane@gmail.com","Casablanca","074323456");
		//DAOCitoyen.ajouter(c);
		/*List<Citoyen> citoyens= new ArrayList<Citoyen>();
		citoyens=DAOCitoyen.getAll();
        for(Citoyen c1:citoyens)
        	System.out.println(c1);
          */
		
		//Citoyen c2=getUnique("WA2121");
		//System.out.println(c2);
		//supprimer("WA212");
		
		//Citoyen c3=new Citoyen("WQ212","khalid","oussama","oussama@gmail.com","Agadir","074323456");
		//modifier("WA21",c3);
	}

}

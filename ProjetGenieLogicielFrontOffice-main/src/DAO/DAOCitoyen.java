package DAO;

import Beans.Citoyen;
import java.sql.*;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.mindrot.jbcrypt.BCrypt;



public class DAOCitoyen {

	
	public  static List<Citoyen> getAll() throws SQLException
	{
		List<Citoyen> citoyens= null;
		Transaction transaction = null;
		
		try {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// get an Citoyen object
			
			citoyens = session.createQuery("from Citoyen").getResultList();
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return citoyens;

	}
	
	public static Citoyen getUnique(String cin) throws SQLException
	{
		Transaction transaction = null;
		Citoyen c = null;
		try {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// get an Citoyen object
			c = session.get(Citoyen.class, cin);
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
	
	public static boolean ajouter(Citoyen c) throws SQLException
	 {
		Transaction transaction = null;
		try {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();
			 c.setPassword(BCrypt.hashpw(c.getPassword(), BCrypt.gensalt()));
			// save the Citoyen object
			session.save(c);
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

	 public static void modifier (Citoyen e1) throws SQLException
	 {	
			Transaction transaction = null;
			try {
				SessionFactory sessionFactory = new Configuration()
		    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
		        Session session = sessionFactory.openSession();
				// start a transaction
				transaction = session.beginTransaction();
				// save the Citoyen object
				 e1.setPassword(BCrypt.hashpw(e1.getPassword(), BCrypt.gensalt()));
				session.update(e1);
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

			// Delete a Citoyen object
			Citoyen c = session.get(Citoyen.class, cin1);
			//System.out.println(c);
			if (c!= null) {
				session.delete(c);
				// commit transaction
				transaction.commit();
				
				System.out.println(" citoyen is deleted");
				return true;
			}
			else {
				System.out.println("citoyen n'existe pas");

			}

			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return false;    
                
	}
	public  static boolean validate(String cin,String email, String password) {

		Transaction transaction = null;
		Citoyen user = null;
		try  {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// get an Citoyen object
			user = (Citoyen) session.createQuery("FROM Citoyen U WHERE U.cin = :cin").setParameter("cin", cin).uniqueResult();
			

			if(user != null ) {
				if(user.getEmail().equals(email))
				{
					if(BCrypt.checkpw(password, user.getPassword()))
					{
						return true;
					}
					else
					{
						System.out.println("mot de passe incorrect");
						
					}
				}
				else
				{
					System.out.println("email incorrect");
					
				}
			}
			else
			{
				System.out.println("CIN incorrect");
				
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
		//Citoyen c=new Citoyen("WA212","khalid","oussama","aimane@gmail.com","Casablanca","074323456","azerty");
		//DAOCitoyen.ajouter(c);
		/*List<Citoyen> citoyens= new ArrayList<Citoyen>();
		citoyens=DAOCitoyen.getAll();
        for(Citoyen c1:citoyens)
        	System.out.println(c1);
          */
		
		//Citoyen c2=getUnique("WA212");
		//System.out.println(c2);
		 supprimer("WA212");
		 //System.out.println(validate("WA212","aimane@gmail.com","azerty"));
		//Citoyen c3=new Citoyen("WQ212","aimane","oussama","oussama@gmail.com","Agadir","074323456","qwerty");
		//modifier(c3);
	}

}

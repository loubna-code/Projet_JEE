package DAO;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.mindrot.jbcrypt.BCrypt;

import Beans.Chef_division;
import Beans.Citoyen;





public class DAOChef_division {

	
	public  static List<Chef_division> getAll() throws SQLException
	{
		List<Chef_division> chefs= null;
		Transaction transaction = null;
		
		try {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// get an Chef_division object
			
			chefs = session.createQuery("from Chef_division").getResultList();
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return chefs;

	}
	
	public static Chef_division getUnique(String cin) throws SQLException

	{
		Transaction transaction = null;
		Chef_division c = null;
		try {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// get an Chef_division object
			c = session.get(Chef_division.class, cin);
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
	
	public static void ajouter(Chef_division c) throws SQLException

	 {
		Transaction transaction = null;
		try {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();
			 c.setPassword(BCrypt.hashpw(c.getPassword(), BCrypt.gensalt()));
			// save the Chef_division object
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
	public static Chef_division getChefByRole(String role) throws SQLException

	 {
		Chef_division chefDivision=null;
		Transaction transaction = null;
		try {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();
			//Problème de sécuritée
			String query = "FROM Chef_division E WHERE E.role ='"+role+"'";
			chefDivision = (Chef_division) session.createQuery(query).getSingleResult();
			// commit transaction
			transaction.commit();
			return chefDivision;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} 
		return chefDivision;
	 }

	 public static void modifier (Chef_division e1) throws SQLException
	 {	
			Transaction transaction = null;
			try {
				SessionFactory sessionFactory = new Configuration()
		    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
		        Session session = sessionFactory.openSession();
				// start a transaction
				transaction = session.beginTransaction();
				// save the Chef_division object
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
	        Session session = sessionFactory.openSession();;
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a Chef_division object
			Chef_division c = session.get(Chef_division.class, cin1);
			if (c != null) {
				session.delete(c);
				System.out.println(" citoyen is deleted");
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
		Chef_division user = null;
		try  {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();

			// get an Chef_division object
user = (Chef_division) session.createQuery("FROM Chef_division U WHERE U.cin = :cin").setParameter("cin", cin).uniqueResult();
			
			if(user != null ) {
				if(user.getEmail().equals(email))
				{
					if(password.equals(password))
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

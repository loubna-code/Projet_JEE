package DAO;

import java.sql.*;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.mindrot.jbcrypt.BCrypt;

import Beans.Citoyen;
import Beans.ProcedureAdministrateur;
import reponse.Reponse;






public class DAOProcedure {

	
	public  static List<ProcedureAdministrateur> getAll() throws SQLException
	{
		List<ProcedureAdministrateur> procedures= null;
		Transaction transaction = null;
		
		try {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// get an ProcedureAdministrateur object
			
			procedures = session.createQuery("from ProcedureAdministrateur").getResultList();
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return procedures;

	}
		

	
	public static ProcedureAdministrateur getUniqueProcedure(String nom) throws SQLException
	{
		Transaction transaction = null;
		ProcedureAdministrateur c = null;
		try {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// get an Demande object
			c = session.get(ProcedureAdministrateur.class, nom);
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
	

	 
	public static boolean supprimer(String nom) throws SQLException {
		Transaction transaction = null;
		try  {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a Procedure object
			ProcedureAdministrateur c = session.get(ProcedureAdministrateur.class, nom);
			//System.out.println(c);
			if (c!= null) {
				session.delete(c);
				// commit transaction
				transaction.commit();
				
				System.out.println(" procedure is deleted");
				return true;
			}
			else {
				System.out.println("procedure n'existe pas");

			}


			
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
		//Citoyen a=new Citoyen("WA212","khalid","oussama","aimane@gmail.com","Casablanca","074323456","azerty");

			  // Demande c=new Demande("declarer","qwerty","aswqd",a);
		      // ajouter(c);
		/*List<Demande> demandes= new ArrayList<Demande>();
		demandes=getAll();
	   for(Demande c1:demandes)
	   	System.out.println(c1);
	     */
		
		//Demande c2=getUnique(4);
		//System.out.println(c2);
		//Scanner sc=new Scanner(System.in);
		//System.out.println("donner id du demande à supprimer");
		//int id = sc.nextInt();
		  //supprimer(id);
		       
			
		}
}
	
	
	


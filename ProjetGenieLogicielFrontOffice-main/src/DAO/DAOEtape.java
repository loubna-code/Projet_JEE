package DAO;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Beans.Etape;
import reponse.Reponse;

public class DAOEtape {

	public  static List<Etape> getAllEtapeDuProcedure(String cin1) throws SQLException
	{
		List<Etape> etapes= null;
		Transaction transaction = null;
		
		try {
			SessionFactory sessionFactory = new Configuration()
	    		    .configure("DAO/hibernate.cfg.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// get an Demande object
			String query = "SELECT E FROM Etape E ,Demande D WHERE D.procedureNom.nom_procedure=E.nomProcedures.nom_procedure  and E.employe.cin='"+cin1+"'";

			etapes = session.createQuery(query).getResultList();
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return etapes;

	}
	
}

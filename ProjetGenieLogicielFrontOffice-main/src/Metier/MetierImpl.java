package Metier;
import java.sql.*;


import java.util.*;

import Beans.Chef_division;
import Beans.Citoyen;
import Beans.Demande;
import Beans.Employe;
import Beans.Etape;
import Beans.ProcedureAdministrateur;
import DAO.DAOChef_division;
import DAO.DAOCitoyen;
import DAO.DAODemande;
import DAO.DAOEmploye;
import DAO.DAOEtape;
import DAO.DAOProcedure;
public class MetierImpl implements ImetierCatalogue {

	public boolean signup( Citoyen c) throws SQLException {
		// TODO Auto-generated method stub
		if(DAOCitoyen.ajouter(c))
		return true;
		else
		return false;
		
	}
	public boolean signin( String cin,String email, String password) throws SQLException {
		if(DAOCitoyen.validate(cin, email, password))
			return true;
			else
			return false;
	}
//	@Override
	public Citoyen getUnique(String cin) throws SQLException {
	// TODO Auto-generated method stub
		return DAOCitoyen.getUnique(cin);
}
	//@Override
	public boolean creerDemande(Demande d) throws SQLException {
		// TODO Auto-generated method stub
		if(DAODemande.ajouter(d))
			return true;
		else
		return false;
		
	}
	//@Override
	public List<Demande> getAllDemande(String cin) throws SQLException {
		// TODO Auto-generated method stub
		
		List<Demande> demandes= DAODemande.getAlldemandes(cin);
		return demandes;
	}
	//@Override
	public void modifierDemande(Demande d) throws SQLException {
		// TODO Auto-generated method stub
		DAODemande.modifier(d);
		
	}
	//@Override
	public Demande getDemandeById(int id) throws SQLException {
		// TODO Auto-generated method stub
		Demande d=DAODemande.getUnique(id);
		return d;
	}
	//@Override
	public boolean supprimerDemande(int id) throws SQLException {
		if(DAODemande.supprimer(id))
			return true;
		else
			return false;
	}
	//@Override
	public boolean signin_chef(String cin, String email, String password) throws SQLException {
		if(DAOChef_division.validate(cin, email, password))
			return true;
			else
			return false;
	}
	//@Override
	public Chef_division getUniqueChef(String cin) throws SQLException {
		// TODO Auto-generated method stub
		return DAOChef_division.getUnique(cin);
	}
	//@Override
	public Chef_division getChefDivisionByRole(String role) throws SQLException {
		// TODO Auto-generated method stub
		return DAOChef_division.getChefByRole(role);
	}
	//@Override
	public List<Demande> getAllDemandeChef(String cin) throws SQLException {
		List<Demande> demandes= DAODemande.getAllDemandeChef(cin);
		return demandes;
	}
	//@Override
	public void modifierEtatGenererJeton(Demande d) throws SQLException {
		// TODO Auto-generated method stub

		      Citoyen c = d.getCitoyen();
		      Double j=(Double) Math.random()*9999;
		      String  jeton = c.getCin()+Double.toString(j);
		      jeton=jeton.replace(".", "");
		      DAODemande.modifierEtatGenererJeton(d, jeton);
		     	
	}
	//@Override
	public void modifierEtat(Demande d) throws SQLException {
		// TODO Auto-generated method stub
	      DAODemande.modifierEtat(d);

	}
	//@Override
	public List<Demande> getAllDemandeArchive(String cin) throws SQLException {
		// TODO Auto-generated method stub
		List<Demande> demandes= DAODemande.getAllDemandeChefAccepte(cin);
		return demandes;
	}
	//@Override
	public void modifierTermination(Demande d) throws SQLException {
		// TODO Auto-generated method stub
		DAODemande.modifierTermination(d);
		
	}
	//@Override
	public boolean signin_employe(String cin, String email, String password) throws SQLException {
		// TODO Auto-generated method stub
		if(DAOEmploye.validate(cin, email, password))
			return true;
			else
			return false;
	}
	//@Override
	public Employe getUniqueEmploye(String cin) throws SQLException {
		// TODO Auto-generated method stub
		return DAOEmploye.getUnique(cin);
		}
	//@Override
	public ProcedureAdministrateur getUniqueProcedure(String nom) throws SQLException {
		// TODO Auto-generated method stub
		return DAOProcedure.getUniqueProcedure(nom);
				
	}
	//@Override
	public List<Demande> getAllDemandeEmploye(String cin) throws SQLException {
		// TODO Auto-generated method stub
		List<Demande> demandes= DAODemande.getAllDemandeEmploye(cin);
		return demandes;
	}
	//@Override
	public List<Etape> getAllEtapeDuProcedure(String cin) throws SQLException {
		// TODO Auto-generated method stub
		List<Etape> etapes= DAOEtape.getAllEtapeDuProcedure(cin);
		return etapes;	
		}
	
	

	
	
}
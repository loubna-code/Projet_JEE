package Metier;
import java.sql.SQLException;
import java.util.*;

import Beans.Chef_division;
import Beans.Citoyen;
import Beans.Demande;
import Beans.Employe;
import Beans.Etape;
import Beans.ProcedureAdministrateur;
public interface ImetierCatalogue {
public boolean signup( Citoyen c) throws SQLException;
public boolean signin( String cin,String email, String password) throws SQLException;
public Citoyen getUnique(String cin) throws SQLException;
public boolean creerDemande(Demande d) throws SQLException;
public List<Demande> getAllDemande(String cin) throws SQLException;
public void modifierDemande(Demande d) throws SQLException;
public Demande getDemandeById(int id) throws SQLException;
public boolean supprimerDemande(int id) throws SQLException;
public boolean signin_chef( String cin,String email, String password) throws SQLException;
public Chef_division getUniqueChef(String cin) throws SQLException;
public Chef_division getChefDivisionByRole(String role) throws SQLException;
public List<Demande> getAllDemandeChef(String cin) throws SQLException;
public void modifierEtatGenererJeton(Demande d) throws SQLException;
public void modifierEtat(Demande d) throws SQLException;
public List<Demande> getAllDemandeArchive(String cin) throws SQLException;
public void modifierTermination(Demande d) throws SQLException;
public boolean signin_employe( String cin,String email, String password) throws SQLException;
public Employe getUniqueEmploye(String cin) throws SQLException;
public ProcedureAdministrateur getUniqueProcedure(String nom) throws SQLException;
public List<Demande> getAllDemandeEmploye(String cin) throws SQLException;
public List<Etape> getAllEtapeDuProcedure(String cin) throws SQLException;




}
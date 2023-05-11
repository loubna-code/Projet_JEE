package Web;
import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Beans.Chef_division;
import Beans.Citoyen;
import Beans.Demande;
import Beans.Employe;
import Beans.Etape;
import Beans.ProcedureAdministrateur;
import DAO.DAODemande;
import Metier.ImetierCatalogue;
import Metier.MetierImpl;
import reponse.Reponse;

@WebServlet("/")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 40,      // 40 MB
        maxRequestSize = 1024 * 1024 * 40 ,  // 40 MB
        fileSizeThreshold = 1024 * 1024 * 40 // 40 MB
)
public class ControleurServlet extends HttpServlet {
	
private ImetierCatalogue metier;
public static final int TAILLE_TAMPON = 10240;
public static final String CHEMIN_FICHIERS = "C:/Users/AIMANEJAMAL-EDDYN/Desktop/ProjetGenieLogiciel/Projet_GenieLogiciel_Front/src/main/webapp/documents_upload";
@Override
public void init() throws ServletException {
metier=new MetierImpl();
}
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
	doGet(request, response);

}
protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	String action = request.getServletPath();
	try {
		switch (action) {
		case "/signup":
			signup(request, response);
			break;
		case "/signin":
			signin(request, response);
			break;
		case "/creerDemande":
			creerDemande(request, response);
			break;
		case "/logout":
			logout(request, response);
			break;
				
		  case "/listerDemandeByCin":
			listerDemandeByCin(request, response);
			break;
		 case "/modifierDemande":
			 modifierDemande(request, response);
			break;
		 case "/modifier":
			 modifier(request, response);
			break;
		 case "/delete":
			 supprimerDemande(request, response);
			break;
		 case "/recevoirDemande":
				recevoirDemande(request, response);
				break;
		 case "/signin_chef":
			 signin_chef(request, response);
			break;
		 case "/Accepter":
			 accepter(request, response);
			break;
		 case "/Refuser":
			 refuser(request, response);
			break;
		 case "/archiverDemande":
			 archiverDemande(request, response);
			break;
		 case "/Rejeter":
			 rejeterDemande(request, response);
			 break;
		 case "/Terminer":
			 terminerDemande(request, response);
			 break;
		 case "/signin_employe":
			 signin_employe(request, response);
			break;
		 case "/recevoirDemandeEmploye":
			 recevoirDemandeEmploye(request, response);
				break;
		 case "/logoutChef":
			 logoutChef(request, response);
				break;
				
		 case "/logoute":
			 logoutEmploye(request, response);
				break;
		}
	} catch (SQLException ex) {
		throw new ServletException(ex);
	}
}

private void signup(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	String cin=request.getParameter("cin");
	String nom=request.getParameter("nom");
	String prenom=request.getParameter("prenom");
	String email=request.getParameter("email");
	String adresse=request.getParameter("adresse");
	String phone=request.getParameter("phone");
	String password=request.getParameter("passwd");
	
	Citoyen c =new Citoyen(cin,nom,prenom,email,adresse,phone,password);
	try {
		if(metier.signup(c))
		{
			HttpSession session = request.getSession();
			session.setAttribute( "nom", c.getNom());
			session.setAttribute( "cin", c.getCin());
		request.getRequestDispatcher("Acceuil.jsp").forward(request,response);
		}
		else
		{
			String err="ce cin deja existe vous pouvez faire  sign in";
			request.setAttribute("message", err);
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}


	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


private void signin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	String cin=request.getParameter("cin");
	String email=request.getParameter("email");
	String password=request.getParameter("passwd");
	
	try {
		if(metier.signin(cin, email, password))
		{
			Citoyen c=metier.getUnique(cin);
			HttpSession session = request.getSession();
			session.setAttribute( "nom", c.getNom());
			session.setAttribute( "cin", c.getCin());
		  request.getRequestDispatcher("/Acceuil.jsp").forward(request,response);
		}
		else
		{
			String err="vous avez insérer des informations incorrects";
			request.setAttribute("message2", err);
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}


	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private void creerDemande(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	
	String description=request.getParameter("description");
	String choix=request.getParameter("choix2");
	ProcedureAdministrateur nomProcedure=null;
	nomProcedure=metier.getUniqueProcedure(choix);
	//recupérer le chef de division par role
	
    // On récupère le champ du fichier
    Part part = request.getPart("files");
       
    // On vérifie qu'on a bien reçu un fichier
    String nomFichier = getNomFichier(part);
  

    // Si on a bien un fichier
    if (nomFichier != null && !nomFichier.isEmpty()) {
    	 String nomChamp = part.getName();
    	 nomFichier= nomFichier.replaceAll("\"","");
    	 HttpSession session = request.getSession();
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyyHH_mm_ss");
         String d = session.getAttribute("cin")+dtf.format(LocalDateTime.now());
      
        //  System.out.println(d);
         nomFichier=d+nomFichier;

   
        // On écrit définitivement le fichier sur le disque
        ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);

    }
    String url ="./documents_upload"+"/"+nomFichier;
    
	
	try {
		HttpSession session = request.getSession();
		String cin = (String) session.getAttribute("cin");
		Citoyen c=metier.getUnique(cin);
		Demande d=new Demande(description,url,c,nomProcedure);
		if(metier.creerDemande(d))
		{
			String message="votre demande est bien creer";
			request.setAttribute("message2", message);
			request.getRequestDispatcher("Deposer.jsp").forward(request,response);
		}
		


	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
private void logout(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	
		request.getSession().removeAttribute("cin");
		request.getSession().removeAttribute("nom");
		response.sendRedirect("login.jsp");
	}

private void logoutChef(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	
	request.getSession().removeAttribute("cin");
	request.getSession().removeAttribute("nom");
	response.sendRedirect("loginChef.jsp");
}

private void logoutEmploye(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	
	request.getSession().removeAttribute("cin");
	request.getSession().removeAttribute("nom");
	response.sendRedirect("loginEmploye.jsp");
}

private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
    BufferedInputStream entree = null;
    BufferedOutputStream sortie = null;
    try {
        entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
        sortie = new BufferedOutputStream(new FileOutputStream(new File(CHEMIN_FICHIERS+"/"+nomFichier)), TAILLE_TAMPON);
        byte[] tampon = new byte[TAILLE_TAMPON];
        int longueur;
        while ((longueur = entree.read(tampon)) > 0) {
            sortie.write(tampon, 0, longueur);
        }
    } finally {
    	  try {
               sortie.close();
           } catch (IOException ignore) {
           }
           try {
               entree.close();
           } catch (IOException ignore) {
           }
    }
}


private static String getNomFichier( Part part ) {
    for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
        if ( contentDisposition.trim().startsWith( "filename" ) ) {
            return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim();
        }
    }
    return null;
}   
 
private void listerDemandeByCin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	HttpSession session = request.getSession();
	String cin=(String) session.getAttribute("cin");
	System.out.println(cin);
	List<Demande> demandes=metier.getAllDemande(cin);
	
	request.setAttribute("listDemandes", demandes);

	request.getRequestDispatcher("/listerDemandes.jsp").forward(request,response);
}


private void recevoirDemande(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	HttpSession session = request.getSession();
	String cin=(String) session.getAttribute("cin");
	List<Demande> demandes=metier.getAllDemandeChef(cin);
	request.setAttribute("listDemandes", demandes);

	request.getRequestDispatcher("/ListeDemandeChef.jsp").forward(request,response);
}

private void recevoirDemandeEmploye(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	HttpSession session = request.getSession();
	String cin=(String) session.getAttribute("cin");
	List<Demande> demandes=metier.getAllDemandeEmploye(cin);
	List<Etape> etapes=metier.getAllEtapeDuProcedure(cin);

	request.setAttribute("listDemandes", demandes);
	request.setAttribute("listEtapes", etapes);

	request.getRequestDispatcher("/ListeDemandeEmploye.jsp").forward(request,response);
}


private void modifierDemande(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	

	int id = Integer.parseInt(request.getParameter("id"));
	Demande d=DAODemande.getUnique(id);
	
	String description=request.getParameter("description");
	String choix=request.getParameter("choix2");
	ProcedureAdministrateur nomProcedure=null;
	nomProcedure=metier.getUniqueProcedure(choix);
    // On récupère le champ du fichier
    Part part = request.getPart("files");
       
    // On vérifie qu'on a bien reçu un fichier
    String nomFichier = getNomFichier(part);
    nomFichier= nomFichier.replaceAll("\"","");
    // Si on a bien un fichier
    if (nomFichier != null && !nomFichier.isEmpty()) {
    	String f=d.getUrl_document();
    	File file = new File(f);
    	file.delete();
    	 String nomChamp = part.getName();
    	 nomFichier= nomFichier.replaceAll("\"","");
    	 HttpSession session = request.getSession();
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyyHH_mm_ss");
         String d1 = session.getAttribute("cin")+dtf.format(LocalDateTime.now());
      
        //  System.out.println(d);
         nomFichier=d1+nomFichier;
        // On écrit définitivement le fichier sur le disque
        ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);

    }
    String url =CHEMIN_FICHIERS+"/"+nomFichier;

	
	try {
		HttpSession session = request.getSession();
		String cin = (String) session.getAttribute("cin");
		Citoyen c=metier.getUnique(cin);
		Demande d1=new Demande(description,url,c,nomProcedure);
         d1.setId(id);
         metier.modifierDemande(d1);
     	String ms="votre demande est bien modifier";
		request.setAttribute("message", ms); 
        request.getRequestDispatcher("/listerDemandeByCin").forward(request,response);
		
		


	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}


private void modifier(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	
	int id = Integer.parseInt(request.getParameter("id"));
	Demande d= metier.getDemandeById(id);
	request.setAttribute("Demande", d);
	request.getRequestDispatcher("ModifierDemande.jsp").forward(request,response);
	}


private void supprimerDemande(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	
	int id = Integer.parseInt(request.getParameter("id"));
	Demande d= metier.getDemandeById(id);
	if(d.getEtat()== Reponse.EnCours ||d.getEtat()== Reponse.Refus)
	{
		metier.supprimerDemande(id);
		String ms="votre demande est bien supprimer";
		request.setAttribute("message", ms);
		request.getRequestDispatcher("/listerDemandeByCin").forward(request,response);

	}
	else
	{
		String err="vous n'avez pas le droit de supprimer ce demande";
		request.setAttribute("message", err);
		request.getRequestDispatcher("/listerDemandeByCin").forward(request,response);
	}
	}

private void rejeterDemande(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	
	int id = Integer.parseInt(request.getParameter("id"));
	Demande d= metier.getDemandeById(id);
	
    if(	metier.supprimerDemande(id)) {
		String ms=" la demande est bien rejeter";
		request.setAttribute("message", ms);
		request.getRequestDispatcher("/archiverDemande").forward(request,response);

	}
	else
	{
		String err="erreur: la demande n'est pas rejeté";
		request.setAttribute("message", err);
		request.getRequestDispatcher("/archiverDemande").forward(request,response);
	}
	}
private void terminerDemande(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	
	int id = Integer.parseInt(request.getParameter("id"));
	Demande d= metier.getDemandeById(id);
	
        metier.modifierTermination(d);
		String ms=" la demande est bien terminé";
		request.setAttribute("message", ms);
		request.getRequestDispatcher("/archiverDemande").forward(request,response);

	
	}

private void signin_chef(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	String cin=request.getParameter("cin");
	String email=request.getParameter("email");
	String password=request.getParameter("passwd");
	
	try {
		if(metier.signin_chef(cin, email, password))
		{
			Chef_division c=metier.getUniqueChef(cin);
			HttpSession session = request.getSession();
			session.setAttribute( "nom", c.getNom());
			session.setAttribute( "cin", c.getCin());
		  request.getRequestDispatcher("/AcceuilChef.jsp").forward(request,response);
		}
		else
		{
			String err="vous avez insérer des informations incorrects";
			request.setAttribute("message2", err);
			request.getRequestDispatcher("loginChef.jsp").forward(request,response);
		}


	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private void signin_employe(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	String cin=request.getParameter("cin");
	String email=request.getParameter("email");
	String password=request.getParameter("passwd");
	
	try {
		if(metier.signin_employe(cin, email, password))
		{
			Employe c=metier.getUniqueEmploye(cin);
			HttpSession session = request.getSession();
			session.setAttribute( "nom", c.getNom());
			session.setAttribute( "cin", c.getCin());
		  request.getRequestDispatcher("/AceuilEmploye.jsp").forward(request,response);
		}
		else
		{
			String err="vous avez insérer des informations incorrects";
			request.setAttribute("message2", err);
			request.getRequestDispatcher("loginEmploye.jsp").forward(request,response);
		}


	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


private void accepter(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	
	int id = Integer.parseInt(request.getParameter("id"));
	Demande d= metier.getDemandeById(id);
	metier.modifierEtatGenererJeton(d);
	String ms="demande bien accepte";
	request.setAttribute("message", ms);
	request.getRequestDispatcher("/recevoirDemande").forward(request,response);
}

private void refuser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	
	int id = Integer.parseInt(request.getParameter("id"));
	Demande d= metier.getDemandeById(id);
	metier.modifierEtat(d);
	String ms="demande bien refuser";
	request.setAttribute("message", ms);
	request.getRequestDispatcher("/recevoirDemande").forward(request,response);
}

private void archiverDemande(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	HttpSession session = request.getSession();
	String cin=(String) session.getAttribute("cin");
	List<Demande> demandes=metier.getAllDemandeArchive(cin);
	
	request.setAttribute("listDemandes", demandes);

	request.getRequestDispatcher("/ListerDemandesArchive.jsp").forward(request,response);
}

}




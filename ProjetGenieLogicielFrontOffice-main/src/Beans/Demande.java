package Beans;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import reponse.Reponse;
@Entity 
@Table(name= "Demande") 
public class Demande implements Serializable{
	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="description")
	private String description;
	@Column(name="etat" )
	private Reponse etat= reponse.Reponse.EnCours;
	@Column(name="url_document")
	private  String url_document;
	@Column(name="jeton")
	private String jeton="pas encore générer";
	@Column(name="termination")
	private boolean termination=false;
	@ManyToOne
	private Citoyen citoyen;
	@ManyToOne
	private ProcedureAdministrateur procedureNom; 
	
	
	public Demande() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Demande(String description, String url_document, Citoyen citoyen, ProcedureAdministrateur procedureNom) {
		super();
		this.description = description;
		this.url_document = url_document;
		this.citoyen = citoyen;
		this.procedureNom = procedureNom;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Reponse getEtat() {
		return etat;
	}


	public void setEtat(Reponse etat) {
		this.etat = etat;
	}


	public String getUrl_document() {
		return url_document;
	}


	public void setUrl_document(String url_document) {
		this.url_document = url_document;
	}


	public String getJeton() {
		return jeton;
	}


	public void setJeton(String jeton) {
		this.jeton = jeton;
	}


	public Citoyen getCitoyen() {
		return citoyen;
	}


	public void setCitoyen(Citoyen citoyen) {
		this.citoyen = citoyen;
	}


	public boolean isTermination() {
		return termination;
	}


	public void setTermination(boolean termination) {
		this.termination = termination;
	}

	


	public ProcedureAdministrateur getProcedureNom() {
		return procedureNom;
	}


	public void setProcedureNom(ProcedureAdministrateur procedureNom) {
		this.procedureNom = procedureNom;
	}


	@Override
	public String toString() {
		return "Demande [id=" + id + ", description=" + description + ", etat=" + etat + ", url_document="
				+ url_document + ", jeton=" + jeton + ", termination=" + termination + ", citoyen=" + citoyen
				+ ", procedureNom=" + procedureNom + "]";
	}



	

	
	
}







	
	
	

	



	

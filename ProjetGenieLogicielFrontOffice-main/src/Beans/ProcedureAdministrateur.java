package Beans;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import reponse.Reponse;

@Entity 
@Table(name= "ProcedureAdministrateur") 

public class ProcedureAdministrateur {

	@Id   
	private String nom_procedure;
	
	@ManyToOne
	private Chef_division chef;
	
	@OneToMany(mappedBy = "nomProcedures",fetch=FetchType.LAZY)
	private Collection<Etape> etapes;
	
	@OneToMany(mappedBy = "procedureNom",fetch=FetchType.LAZY)
	private Collection<Demande> demandes;
	
	public ProcedureAdministrateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProcedureAdministrateur(String nom_procedure, Chef_division chef, Collection<Etape> etapes,
			Collection<Demande> demandes) {
		super();
		this.nom_procedure = nom_procedure;
		this.chef = chef;
		this.etapes = etapes;
		this.demandes = demandes;
	}

	public String getNom_procedure() {
		return nom_procedure;
	}

	public void setNom_procedure(String nom_procedure) {
		this.nom_procedure = nom_procedure;
	}

	public Chef_division getChef() {
		return chef;
	}

	public void setChef(Chef_division chef) {
		this.chef = chef;
	}

	public Collection<Etape> getEtapes() {
		return etapes;
	}

	public void setEtapes(Collection<Etape> etapes) {
		this.etapes = etapes;
	}

	public Collection<Demande> getDemandes() {
		return demandes;
	}

	public void setDemandes(Collection<Demande> demandes) {
		this.demandes = demandes;
	}

	@Override
	public String toString() {
		return "ProcedureAdministrateur [nom_procedure=" + nom_procedure + ", chef=" + chef + ", etapes=" + etapes
				+ ", demandes=" + demandes + "]";
	}
	
	
	
	
}

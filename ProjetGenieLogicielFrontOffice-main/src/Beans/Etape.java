package Beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name= "Etape") 
public class Etape {

	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="nom_etape")
	private String nom_etape;
	@ManyToOne
	private Employe employe;
	@ManyToOne
	private ProcedureAdministrateur nomProcedures;
	public Etape() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Etape(int id, String nom_etape, Employe employe, ProcedureAdministrateur nom_procedure) {
		super();
		this.id = id;
		this.nom_etape = nom_etape;
		this.employe = employe;
		this.nomProcedures = nom_procedure;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom_etape() {
		return nom_etape;
	}
	public void setNom_etape(String nom_etape) {
		this.nom_etape = nom_etape;
	}
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	public ProcedureAdministrateur getNom_procedure() {
		return nomProcedures;
	}
	public void setNom_procedure(ProcedureAdministrateur nom_procedure) {
		this.nomProcedures = nom_procedure;
	}
	@Override
	public String toString() {
		return "Etape [id=" + id + ", nom_etape=" + nom_etape + ", employe=" + employe + ", nom_procedure="
				+ nomProcedures + "]";
	}

	
	
	
	
}

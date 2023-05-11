package Beans;

import java.io.Serializable;
import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity 
@Table(name= "Employe") 
public class Employe implements Serializable {
	@Id
	private String cin;
	   
	@Column(name="nom")
	private String nom;
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="email")
	private String email;  
	@Column(name="adresse")
	private String adress;
	@Column(name="phone")
	private String phone;
	@Column(name="password")
	private String password;
	@OneToMany(mappedBy = "employe" , fetch=FetchType.LAZY)
	private Collection<Etape> etapes;
	
	public Employe() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Employe(String cin, String nom, String prenom, String email, String adress, String phone, String password) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adress = adress;
		this.phone = phone;
		this.password = password;
	}


	public String getCin() {
		return cin;
	}


	public void setCin(String cin) {
		this.cin = cin;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Employe [cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", adress=" + adress
				+ ", phone=" + phone + ", password=" + password + "]";
	}


	
	
}
package com.recettes.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "membre")
public class Membre {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
     
    @Column(name = "nom", nullable = false)
    private String nom;
    
    @Column(name = "pseudo", nullable = false)
    private String pseudo;
     
    @Column(name = "mdp", nullable = false)
    private String mdp;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dateInscription", nullable = false)
    private Date dateInscription;
    
    @OneToMany(mappedBy = "membre")
    private List<Recette> recettesMembre;
    
    public Membre() {}
    
    public Membre(Integer id, String nom, String pseudo, String mdp, String email, Date dateInscription) {
		this.id = id;
		this.nom = nom;
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.email = email;
		this.dateInscription = dateInscription;
		this.recettesMembre = new ArrayList<Recette>();
	}
    
    public Membre(String nom, String pseudo, String mdp, String email, Date dateInscription) {
		this.nom = nom;
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.email = email;
		this.dateInscription = dateInscription;
		this.recettesMembre = new ArrayList<Recette>();
	}


	public Integer getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getPseudo() {
		return pseudo;
	}

	public String getMdp() {
		return mdp;
	}

	public String getEmail() {
		return email;
	}

	public Date getDateInscription() {
		return dateInscription;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	
    public void addRecettesMembre(Recette recette){
    	recettesMembre.add(recette);
    	recette.setMembre(this);
    }
    
    public void removeRecettesMembre(Recette recette) {    
    	recettesMembre.remove(recette);    
    	recette.setMembre(null);
    }

	public List<Recette> getRecettes() {
		return recettesMembre;
	}

	public void setRecettes(List<Recette> recettes) {
		this.recettesMembre = recettes;
	}

	@Override
	public String toString() {
		return "Membre [nom=" + nom + ", pseudo=" + pseudo + ", mdp=" + mdp + ", email=" + email + ", dateInscription="
				+ dateInscription + "]";
	}
	
}

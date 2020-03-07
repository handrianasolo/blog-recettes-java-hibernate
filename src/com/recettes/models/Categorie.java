package com.recettes.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "categorie")
public class Categorie {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
	
    @Column(name = "nom", nullable = false)
    private String nom;
    
    @OneToMany(mappedBy = "categorie")
    private List<Recette> recettesCategorie;
    
    public Categorie() {}
    
    public Categorie(Integer id, String nom) {
    	this.id = id;
    	this.nom = nom;
    	this.recettesCategorie = new ArrayList<Recette>();
    }
    
    public Categorie(String nom) {
    	this.nom = nom;
    	this.recettesCategorie = new ArrayList<Recette>();
    }

	public Integer getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
    public List<Recette> getRecettesCategorie() {
		return recettesCategorie;
	}

	public void setRecettesCategorie(List<Recette> recettesCategorie) {
		this.recettesCategorie = recettesCategorie;
	}

	public void addRecettesCategorie(Recette recette){
    	recettesCategorie.add(recette);
    	recette.setCategorie(this);
    }
    
    public void removeRecettesCategorie(Recette recette) {    
    	recettesCategorie.remove(recette);    
    	recette.setCategorie(null);
    }

	@Override
	public String toString() {
		return "Categorie [id=" + id + ", nom=" + nom + "]";
	}
	
}


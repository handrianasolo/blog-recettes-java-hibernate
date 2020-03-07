package com.recettes.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "tag")
public class Tag {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	    
	@Column(name = "nom", nullable = false)
	private String nom;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Recette> recettes = new ArrayList<Recette>();
	   
	public Tag(String nom) {
		this.nom = nom;
	}
   
	public Tag() {
		super();
	}
   
	public void setId(Integer id) {
		this.id = id;
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

	public List<Recette> getRecettes() {
		return recettes;
	}

	public void setRecettes(List<Recette> recettes) {
		this.recettes = recettes;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", nom=" + nom + "]";
	}
	
	
   
}
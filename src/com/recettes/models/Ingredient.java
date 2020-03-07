package com.recettes.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ingredient")
public class Ingredient {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Column(name = "nom", nullable = false)
    private String nom;
    
    @Column(name = "quantite", nullable = false)
    private Double quantite;
	
    @Column(name = "unit", nullable = false)
    private String unit;
    
    @ManyToOne
    private Recette recette;

    public Ingredient() {}
    
	public Ingredient(Integer id, String nom, Double quantite, String unit) {
		this.id = id;
		this.nom = nom;
		this.quantite = quantite;
		this.unit = unit;
	}

	public Ingredient(String nom, Double quantite, String unit, Recette recette) {
		super();
		this.nom = nom;
		this.quantite = quantite;
		this.unit = unit;
		this.recette = recette;
	}

	public Ingredient(String nom, Double quantite, String unit) {
		this.nom = nom;
		this.quantite = quantite;
		this.unit = unit;
	}

	public Integer getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public Double getQuantite() {
		return quantite;
	}

	public String getUnit() {
		return unit;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette  recette) {
		this.recette = recette;
	}

	@Override
	public String toString() {
		return "Ingredient [nom=" + nom + ", quantite=" + quantite + ", unit=" + unit + ", recette=" + recette + "]";
	}
	
}

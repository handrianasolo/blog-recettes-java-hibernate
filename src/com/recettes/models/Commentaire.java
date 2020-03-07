package com.recettes.models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "commentaire")
public class Commentaire {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Column(name = "auteur", nullable = false)
    private String auteur;
    
    @Column(name = "contenu", nullable = false)
    private String contenu;
    
    @Column(name = "note", nullable = false)
    private Integer note;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dateCreation", nullable = false)
    private Date dateCreation;
    
    @ManyToOne
    private Recette recette;
    
    public Commentaire() {}
    
	public Commentaire(Integer id, String auteur, String contenu, Integer note, Date dateCreation) {
	
		this.id = id;
		this.auteur = auteur;
		this.contenu = contenu;
		this.note = note;
		this.dateCreation = dateCreation;
	}

	public Commentaire(String auteur, String contenu, Integer note, Date dateCreation) {
	
		this.auteur = auteur;
		this.contenu = contenu;
		this.note = note;
		this.dateCreation = dateCreation;
	}
	

	public Commentaire(String auteur, String contenu, Integer note, Date dateCreation, Recette recette) {
	
		this.auteur = auteur;
		this.contenu = contenu;
		this.note = note;
		this.dateCreation = dateCreation;
		this.recette = recette;
	}

	public Integer getId() {
		return id;
	}

	public String getAuteur() {
		return auteur;
	}

	public String getContenu() {
		return contenu;
	}

	public Integer getNote() {
		return note;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}

	@Override
	public String toString() {
		return "Commentaire [auteur=" + auteur + ", contenu=" + contenu + ", note=" + note + ", dateCreation="
				+ dateCreation + ", recette=" + recette.getId() + "]";
	}
  
}

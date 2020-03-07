package com.recettes.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "recette")
public class Recette {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Column(name = "titre", nullable = false)
    private String titre;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "photo", nullable = false)
    private String photo;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dateCreation", nullable = false)
    private Date dateCreation;
    
    @Transient
    private Double moyenneNote;
    
    @OneToMany(mappedBy="recette", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Commentaire> commentaires;
    
    @OneToMany(mappedBy="recette", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Ingredient> ingredients;
    
    @ManyToOne
    private Categorie categorie ;
    
    @ManyToOne
    private Membre membre;
    
    @ManyToMany(mappedBy = "recettes", fetch = FetchType.EAGER)
    private List<Tag> tags = new ArrayList<Tag>();
    
    
    public void addCommentaire(Commentaire commentaire){
    	commentaires.add(commentaire);
    	commentaire.setRecette(this);
    }

    public void removeCommentaire(Commentaire commentaire){
    	commentaires.remove(commentaire);
    }
    
    public void addIngredient(Ingredient ingredient){
    	ingredients.add(ingredient);
    	ingredient.setRecette(this);
    }

    public void removeIngredient(Ingredient ingredient){
    	ingredients.remove(ingredient);
    }

	public Recette() {}
    
	public Recette(Integer id, String titre, String description, String photo, Date dateCreation) {
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.photo = photo;
		this.dateCreation = dateCreation;
		this.commentaires = new ArrayList<Commentaire>();
		this.ingredients = new ArrayList<Ingredient>();
	}

	public Recette(String titre, String description, String photo, Date dateCreation) {

		this.titre = titre;
		this.description = description;
		this.photo = photo;
		this.dateCreation = dateCreation;
		this.commentaires = new ArrayList<Commentaire>();
		this.ingredients = new ArrayList<Ingredient>();
	}

	public Recette(String titre, String description, String photo, Date dateCreation, Categorie categorie,
			Membre membre) {
		this.titre = titre;
		this.description = description;
		this.photo = photo;
		this.dateCreation = dateCreation;
		this.categorie = categorie;
		this.membre = membre;
		this.commentaires = new ArrayList<Commentaire>();
		this.ingredients = new ArrayList<Ingredient>();
	}

	public Integer getId() {
		return id;
	}
	
	public String getTitre() {
		return titre;
	}

	public String getDescription() {
		return description;
	}

	public String getPhoto() {
		return photo;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	
	public Double getMoyenneNote() {
		return moyenneNote;
	}

	public void setMoyenneNote(Double moyenneNote) {
		this.moyenneNote = moyenneNote;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Recette [id=" + id + ", titre=" + titre + ", description=" + description + ", photo=" + photo
				+ ", dateCreation=" + dateCreation + ", categorie=" + categorie.getId() + ", membre=" + membre.getId() + "]";
	}

}

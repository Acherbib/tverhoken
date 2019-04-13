package fr.norsys.tpTdd.modeles;

import java.util.Date;

/**
 * Classe qui represente une video que le magasin a a sa disposition.
 */
public class Video {

    public static final String DESSINS_ANIME = "Dessin animé";
    public static final String ACTION = "Action";
    public static final String THRILLER = "Thriller";
    public static final String COMEDIE = "Comédie";
    public static final String FANTASTIQUE = "Fantastique";
    public static final String EAU_DE_ROSE = "Eau de rose";
    
    
    private String code;
    private String nom;
    private Integer dureeFilm;
    private String genre;
    private Boolean isDisponible;
    private Date dateDebutLocation;
    private Date dateRetour;
    private Integer nbJourLocationAutorises;
    
    /**
     * Constructor. Initialise les attributs de la classe.
     * @param code le code de la video.
     * @param nom le nom/titre de la video.
     * @param dureeFilm la duree de la video.
     * @param genre le genre de la video.
     * @param isDisponible la disponibilite de la video.
     * @param dateDebutLocation la derniere date de debut de location de la video.
     * @param dateRetour la derniere date de retour de la video.
     * @param nbJourLocationAutorise le nombre de jour de location maximum autorise de la video.
     */
    public Video(String code, String nom, Integer dureeFilm, String genre,
	    Boolean isDisponible, Date dateDebutLocation, Date dateRetour, Integer nbJourLocationAutorise) {
	this.code = code;
	this.nom = nom;
	this.dureeFilm = dureeFilm;
	this.genre = genre;
	this.isDisponible = isDisponible;
	this.dateDebutLocation = dateDebutLocation;
	this.dateRetour = dateRetour;
	this.nbJourLocationAutorises = nbJourLocationAutorise;
    }
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }
    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }
    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /**
     * @return the dureeFilm
     */
    public Integer getDureeFilm() {
        return dureeFilm;
    }
    /**
     * @param dureeFilm the dureeFilm to set
     */
    public void setDureeFilm(Integer dureeFilm) {
        this.dureeFilm = dureeFilm;
    }
    /**
     * @return the isDisponible
     */
    public Boolean getIsDisponible() {
        return isDisponible;
    }
    /**
     * @param isDisponible the isDisponible to set
     */
    public void setIsDisponible(Boolean isDisponible) {
        this.isDisponible = isDisponible;
    }
    /**
     * @return the dateDebutLocation
     */
    public Date getDateDebutLocation() {
        return dateDebutLocation;
    }
    /**
     * @param dateDebutLocation the dateDebutLocation to set
     */
    public void setDateDebutLocation(Date dateDebutLocation) {
        this.dateDebutLocation = dateDebutLocation;
    }
    /**
     * @return the dateRetour
     */
    public Date getDateRetour() {
        return dateRetour;
    }
    /**
     * @param dateRetour the dateRetour to set
     */
    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }
    /**
     * @return the nbJourLocationAutorises
     */
    public Integer getNbJourLocationAutorises() {
        return nbJourLocationAutorises;
    }
    /**
     * @param nbJourLocationAutorises the nbJourLocationAutorises to set
     */
    public void setNbJourLocationAutorises(Integer nbJourLocationAutorises) {
        this.nbJourLocationAutorises = nbJourLocationAutorises;
    }
    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
	this.genre = genre;
    }
    /**
     * @return the genre
     */
    public String getGenre() {
	return genre;
    }
}

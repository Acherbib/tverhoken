package fr.norsys.tpTdd.modeles;

import java.util.Date;

/**
 * Classe representant une personne. Dans le SI, une personne est caracterisee
 * par un login/password. Vient ensuite les informations sur cette personne
 * comme son nom, prenom, etc.
 */
public class Personne {

    public static final String TYPE_GERANT = "GERANT";
    public static final String TYPE_CLIENT = "CLIENT";
    
    private String login;
    private String password;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String type;
    
    /**
     * Constructor. Initialise les attribut de la classe.
     * @param login
     * @param password
     * @param nom
     * @param prenom
     * @param dateNaissance
     * @param type
     */
    public Personne(String login, String password, String nom,
	    String prenom, Date dateNaissance, String type) {
	this.login = login;
	this.password = password;
	this.nom = nom;
	this.prenom = prenom;
	this.dateNaissance = dateNaissance;
	this.type = type;
    }
    
    /**
     * Constructor by default.
     */
    public Personne() {
    }
    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }
    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }
    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    /**
     * @return the dateNaissance
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }
    /**
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
	this.type = type;
    }
    /**
     * @return the type
     */
    public String getType() {
	return type;
    }
}

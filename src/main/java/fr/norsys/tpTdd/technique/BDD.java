package fr.norsys.tpTdd.technique;

import java.util.HashMap;
import java.util.Map;

import fr.norsys.tpTdd.modeles.Personne;
import fr.norsys.tpTdd.modeles.Video;

/**
 * Classe simulant une base de donnees.
 */
public class BDD {

    public static final String TABLE_PERSONNE = "Personne";
    public static final String TABLE_VIDEO = "Video";
    /*
     * Une mappe qui fait office de BDD. la clef est le nom de la table, la
     * valeur retournee est une mappe qui va contenir le contenu de la table
     * selectionnee. On accede a un element de la table grace a une clef qui
     * reference l'objet que l'on souhaite avoir.
     */
    private Map<String, Map<String, ?>> baseDeDonnees;

    /**
     * Constructor. Initialise la BDD avec un contenu par defaut.
     */
    public BDD(Personne admin) {
	// on initialise la BDD
	this.baseDeDonnees = new HashMap<String, Map<String, ?>>();
	// On ajoute un objet Personne qui sera la personne autorisee a se
	// connecter a l'application.
	Map<String, Personne> tablePersonne = new HashMap<String, Personne>();
	tablePersonne.put(admin.getLogin(), admin);
	this.baseDeDonnees.put(TABLE_PERSONNE, tablePersonne);
	// On construit la table qui va contenir les videos disponibles
	Map<String, Video> tableVideosDisponibles = new HashMap<String, Video>();
	// On construit des videos que l'on souhaite avoir en BDD par defaut
	Video livreDeLaJungle = new Video("0001001", "Le livre de la jungle",
		97, Video.DESSINS_ANIME, true, null, null, 3);
	Video petitDinosaure = new Video("0001002", "Le petit dinosaure", 90,
		Video.DESSINS_ANIME, true, null, null, 3);
	Video jackieChanInTheBronx = new Video("0002001",
		"Jackie Chan dans le Bronx", 90, Video.ACTION, true, null,
		null, 5);
	Video titanic = new Video("0003001", "Titanic", 155, Video.EAU_DE_ROSE,
		true, null, null, 2);
	Video harryPotter = new Video("0004001",
		"Harry Potter à l'école des sorciers", 106, Video.FANTASTIQUE,
		true, null, null, 3);
	Video laCiteDeLaPeur = new Video("0005001", "La cité de la peur", 90,
		Video.COMEDIE, true, null, null, 4);
	// On ajoute les videos dans la table
	tableVideosDisponibles.put(livreDeLaJungle.getCode(), livreDeLaJungle);
	tableVideosDisponibles.put(petitDinosaure.getCode(), petitDinosaure);
	tableVideosDisponibles.put(jackieChanInTheBronx.getCode(),
		jackieChanInTheBronx);
	tableVideosDisponibles.put(titanic.getCode(), titanic);
	tableVideosDisponibles.put(harryPotter.getCode(), harryPotter);
	tableVideosDisponibles.put(laCiteDeLaPeur.getCode(), laCiteDeLaPeur);
	this.baseDeDonnees.put(TABLE_VIDEO, tableVideosDisponibles);
    }

    /**
     * @return the baseDeDonnees
     */
    public Map<String, Map<String, ?>> getBaseDeDonnees() {
	return baseDeDonnees;
    }
}

package fr.norsys.tpTdd.serveur.servicemetier;

import java.util.List;

import fr.norsys.tpTdd.modeles.Personne;
import fr.norsys.tpTdd.modeles.Reponse;
import fr.norsys.tpTdd.modeles.Video;
import fr.norsys.tpTdd.technique.MetierException;
import fr.norsys.tpTdd.technique.TechniqueException;

public interface MainService {

    /**
     * @param titre
     *            le titre du film.
     * @param genre
     *            le genre du film.
     * @return la liste des videos correspondant aux critères.
     */
    List<Video> selectVideo(String titre, String genre);

    /**
     * @param video
     *            La video a inserer.
     * @return Un booleen indiquant l'etat de l'operation.
     * @throws MetierException
     *             Exception lancee lorsque l'operation n'a pas pus avoir lieu
     *             du au fait de la detection d'erreur.
     * @throws TechniqueException
     *             Exception lancee lorsqu'une erreur innatendue intervient.
     */
    Boolean insertVideo(Video video) throws TechniqueException, MetierException;

    /**
     * @param video
     *            La video a inserer.
     * @return Un booleen indiquant l'etat de l'operation.
     * @throws MetierException
     *             Exception lancee lorsque l'operation n'a pas pus avoir lieu
     *             du au fait de la detection d'erreur.
     * @throws TechniqueException
     *             Exception lancee lorsqu'une erreur innatendue intervient.
     */
    Boolean updateVideo(Video video) throws TechniqueException, MetierException;

    /**
     * @param video
     *            La video a supprimer.
     * @return Un booleen indiquant l'etat de l'operation.
     * @throws MetierException
     *             Exception lancee lorsque l'operation n'a pas pus avoir lieu
     *             du au fait de la detection d'erreur.
     * @throws TechniqueException
     *             Exception lancee lorsqu'une erreur innatendue intervient.
     */
    Boolean deleteVideo(Video video) throws TechniqueException, MetierException;

    /**
     * @param type
     *            Le type de personne.
     * @param login
     *            le login de la personne recherchee.
     * @return la liste des personnes correspondant aux criteres de recherches.
     */
    List<Personne> selectPersonne(String type, String login);

    /**
     * @param personne
     *            La personne a inserer.
     * @return Un booleen indiquant l'etat de l'operation.
     * @throws MetierException
     *             Exception lancee lorsque l'operation n'a pas pus avoir lieu
     *             du au fait de la detection d'erreur.
     * @throws TechniqueException
     *             Exception lancee lorsqu'une erreur innatendue intervient.
     */
    Boolean insertPersonne(Personne personne) throws TechniqueException,
	    MetierException;

    /**
     * @param personne
     *            La personne a mettre a jour.
     * @return Un booleen indiquant l'etat de l'operation.
     * @throws MetierException
     *             Exception lancee lorsque l'operation n'a pas pus avoir lieu
     *             du au fait de la detection d'erreur.
     * @throws TechniqueException
     *             Exception lancee lorsqu'une erreur innatendue intervient.
     */
    Boolean updatePersonne(Personne personne) throws TechniqueException,
	    MetierException;

    /**
     * @param personne
     *            La personne a supprimer.
     * @return Un booleen indiquant l'etat de l'operation.
     * @throws MetierException
     *             Exception lancee lorsque l'operation n'a pas pus avoir lieu
     *             du au fait de la detection d'erreur.
     * @throws TechniqueException
     *             Exception lancee lorsqu'une erreur innatendue intervient.
     */
    Boolean deletePersonne(Personne personne) throws TechniqueException,
	    MetierException;

    /**
     * Valide l'authentification a l'application.
     * 
     * @param login
     *            le login de la personne.
     * @param password
     *            le mot de passe de la personne.
     * @return un booleen indiquant si l'authentification c'est bien passee.
     */
    Boolean getAuthentification(String login, String password);

    /**
     * @return Tous les clients disponibles en BDD.
     */
    List<Personne> selectListeClients();

    /**
     * @param genre
     *            Le genre de videos que l'on recherche.
     * @return la liste des videos qui sont disponibles.
     */
    List<Video> selectVideoDisponibles(String genre);

    /**
     * Effectue l'algorithme lorsqu'un client rend une video. Va indiquer si la
     * video est rendue en retard ou non.
     * 
     * @param video
     *            La video que le client rend.
     * @return un objet {@link Reponse} qui va contenir un message indiquant si
     *         la video a etait rendu a temps ou non.
     * @throws MetierException
     *             Exception lancee lorsque l'operation n'a pas pus avoir lieu
     *             du au fait de la detection d'erreur.
     * @throws TechniqueException
     *             Exception lancee lorsqu'une erreur innatendue intervient.
     */
    Reponse rendreVideo(Video video) throws TechniqueException, MetierException;

}

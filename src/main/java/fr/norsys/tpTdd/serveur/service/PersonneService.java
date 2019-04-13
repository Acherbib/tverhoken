package fr.norsys.tpTdd.serveur.service;

import java.util.List;

import fr.norsys.tpTdd.modeles.Personne;
import fr.norsys.tpTdd.technique.MetierException;
import fr.norsys.tpTdd.technique.TechniqueException;

public interface PersonneService {

    /**
     * @param type
     *            Le type de personne.
     * @param login
     *            Le login de la personne.
     * @return une liste de personnes.
     */
    List<Personne> selectPersonne(String type, String login);

    /**
     * Service d'insertion d'une nouvelle personne. Va controler la validiter
     * des donnees avant d'appeler la couche DAO.
     * 
     * @param personne
     *            La personne a inserer.
     * @return un booleen indiquant si l'operation a reussie.
     * @throws TechniqueException
     *             Exception lancee lorsqu'une erreur innatendue intervient.
     * @throws MetierException
     *             Exception lancee lorsque l'on detecte une erreur avant
     *             d'appeler la couche DAO.
     */
    Boolean insertPersonne(Personne personne) throws TechniqueException,
	    MetierException;

    /**
     * Methode de mise a jour d'une personne. Va controler la validiter des
     * donnees avant d'appeler la couche DAO.
     * 
     * @param personne
     *            La personne a mettre a jour.
     * @return Un booleen indiquant si l'operation a reussie ou non.
     * @throws TechniqueException
     *             Exception lancee lorsqu'une erreur innatendue intervient.
     * @throws MetierException
     *             Exception lancee lorsque l'on detecte une erreur avant
     *             d'appeler la couche DAO.
     */
    Boolean updatePersonne(Personne personne) throws TechniqueException,
	    MetierException;

    /**
     * Methode de suppression d'une personne. Va controler la validiter des
     * donnees avant d'appeler la couche DAO.
     * 
     * @param personne
     *            La personne a supprimer.
     * @return Un booleen indiquant si l'operation a reussie ou non.
     * @throws TechniqueException
     *             Exception lancee lorsqu'une erreur innatendue intervient.
     * @throws MetierException
     *             Exception lancee lorsque l'on detecte une erreur avant
     *             d'appeler la couche DAO.
     */
    Boolean deletePersonne(Personne personne) throws TechniqueException,
	    MetierException;

}

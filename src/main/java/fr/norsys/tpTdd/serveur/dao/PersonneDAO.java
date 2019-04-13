package fr.norsys.tpTdd.serveur.dao;

import java.util.List;

import fr.norsys.tpTdd.modeles.Personne;
import fr.norsys.tpTdd.technique.TechniqueException;

public interface PersonneDAO {

    /**
     * @param type
     *            le type de personne.
     * @param login
     *            le login de la personne.
     * @return une liste de personne.
     */
    List<Personne> selectPersonne(String type, String login);

    /**
     * @param newPersonne
     *            la nouvelle personne a inserer.
     * @return un booleen indiquant si l'insertion a reussie ou non.
     * @throws TechniqueException
     *             Exception lancee lorsque une erreur technique intervient.
     */
    Boolean insertPersonne(Personne newPersonne) throws TechniqueException;

    /**
     * @param newPersonne
     *            la personne a mettre a jour.
     * @return un booleen indiquant si la mise a jour a ete effectuee.
     * @throws TechniqueException
     *             Exception lancee lorsque une erreur technique intervient.
     */
    Boolean updatePersonne(Personne newPersonne) throws TechniqueException;

    /**
     * @param newPersonne
     *            la personne a supprimer.
     * @return un booleen indiquant si l'operation s'est bien deroulee ou non.
     * @throws TechniqueException
     *             Exception lancee lorsque une erreur technique intervient.
     */
    Boolean deletePersonne(Personne newPersonne) throws TechniqueException;

}

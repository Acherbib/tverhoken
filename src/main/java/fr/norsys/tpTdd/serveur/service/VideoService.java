package fr.norsys.tpTdd.serveur.service;

import java.util.List;

import fr.norsys.tpTdd.modeles.Video;
import fr.norsys.tpTdd.technique.MetierException;
import fr.norsys.tpTdd.technique.TechniqueException;

public interface VideoService {

    /**
     * @param nom
     *            le nom/titre de la video recherchee.
     * @param genre
     *            le genre de la video recherchee.
     * @return la liste des videos correspondant au criteres de recherches.
     */
    List<Video> selectVideo(String nom, String genre);

    /**
     * Methode d'insertion d'une nouvelle Video en BDD. Va controler la validite
     * des donnees avant d'appeler la couche DAO.
     * 
     * @param video
     *            La video a inserer.
     * @return Un booleen indiquant si l'operation a reussie ou non. Si
     *         l'operation a echouee, c'est que la video que l'on cherche a
     *         inserer existe deja.
     * @throws TechniqueException
     *             Exception lancee lorsqu'une erreur innatendue intervient.
     * @throws MetierException
     *             Exception lancee lorsque l'on detecte une erreur avant
     *             d'appeler la couche DAO.
     */
    Boolean insertVideo(Video video) throws TechniqueException, MetierException;

    /**
     * Methode de mise a jour d'une video en BDD. Va controler la validite des
     * donnees avant d'appeler la couche DAO.
     * 
     * @param video
     *            La video a mettre a jour.
     * @return Un booleen indiquant si l'operation a reussie ou non. Si
     *         l'operation a echouee, c'est que la video que l'on cherche a
     *         mettre a jour n'existe pas.
     * @throws TechniqueException
     *             Exception lancee lorsqu'une erreur innatendue intervient.
     * @throws MetierException
     *             Exception lancee lorsque l'on detecte une erreur avant
     *             d'appeler la couche DAO.
     */
    Boolean updateVideo(Video video) throws TechniqueException, MetierException;

    /**
     * Methode de suppression d'une video. Va controler la validite des donnees
     * avant d'appeler la couche DAO.
     * 
     * @param video
     *            La video a supprimer.
     * @return Un booleen indiquant si l'operation a reussie ou non. Si
     *         l'operation a echouee, c'est que la video que l'on cherche a
     *         supprimer n'existe pas.
     * @throws TechniqueException
     *             Exception lancee lorsqu'une erreur innatendue intervient.
     * @throws MetierException
     *             Exception lancee lorsque l'on detecte une erreur avant
     *             d'appeler la couche DAO.
     */
    Boolean deleteVideo(Video video) throws TechniqueException, MetierException;

}

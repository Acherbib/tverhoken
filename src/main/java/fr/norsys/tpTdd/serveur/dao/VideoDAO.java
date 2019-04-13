package fr.norsys.tpTdd.serveur.dao;

import java.util.List;

import fr.norsys.tpTdd.modeles.Video;
import fr.norsys.tpTdd.technique.TechniqueException;

public interface VideoDAO {

    /**
     * @param titre
     *            Le titre de la video.
     * @param genre
     *            Le genre de la video.
     * @return une liste de videos.
     */
    List<Video> selectVideos(String titre, String genre);

    /**
     * @param newVideo
     *            La nouvelle video a inserer.
     * @return Un booleen indiquant si l'operation a reussie ou non.
     * @throws TechniqueException
     *             Exception levee lorsqu'une erreur innattendue survient.
     */
    Boolean insert(Video newVideo) throws TechniqueException;

    /**
     * @param newVideo
     *            La video a mettre a jour.
     * @return Un booleen indiquant si l'operation a reussie ou non.
     * @throws TechniqueException
     *             Exception levee lorsqu'une erreur innattendue survient.
     */
    Boolean updateVideo(Video newVideo) throws TechniqueException;

    /**
     * @param newVideo
     *            La video que l'on cherche a supprimer.
     * @return Un booleen indiquant si l'operation a reussie ou non.
     * @throws TechniqueException
     *             Exception levee lorsqu'une erreur innattendue survient.
     */
    Boolean deleteVideo(Video newVideo) throws TechniqueException;

}

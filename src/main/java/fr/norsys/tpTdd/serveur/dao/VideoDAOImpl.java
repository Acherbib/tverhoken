package fr.norsys.tpTdd.serveur.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.norsys.tpTdd.modeles.Video;
import fr.norsys.tpTdd.technique.BDD;
import fr.norsys.tpTdd.technique.TechniqueException;

public class VideoDAOImpl implements VideoDAO {

    private BDD baseDeDonnees;

    @Override
    public List<Video> selectVideos(String titre, String genre) {
	Map<String, ?> tableVideo = this.baseDeDonnees.getBaseDeDonnees().get(
		BDD.TABLE_VIDEO);
	List<Video> result = new ArrayList<Video>();
	for (Object video : tableVideo.values()) {
	    if (titre != null && genre != null) {
		if (titre.equals(((Video) video).getNom())
			&& genre.equals(((Video) video).getGenre())) {
		    result.add((Video) video);
		}
	    } else if (titre != null) {
		if (titre.equals(((Video) video).getNom())) {
		    result.add((Video) video);
		}
	    } else if (genre != null) {
		if (genre.equals(((Video) video).getGenre())) {
		    result.add((Video) video);
		}
	    } else {
		result.add((Video) video);
	    }
	}
	return result;
    }

    /**
     * @param baseDeDonnees
     *            the baseDeDonnees to set
     */
    public void setBaseDeDonnees(BDD baseDeDonnees) {
	this.baseDeDonnees = baseDeDonnees;
    }

    @Override
    public Boolean insert(Video newVideo) throws TechniqueException {
	Boolean success = false;
	if (newVideo.getCode() == null) {
	    throw new TechniqueException();
	} else {
	    Map<String, Video> tableVideo = (Map<String, Video>) this.baseDeDonnees
		    .getBaseDeDonnees().get(BDD.TABLE_VIDEO);
	    tableVideo.put(newVideo.getCode(), newVideo);
	    success = true;
	}
	return success;
    }

    @Override
    public Boolean updateVideo(Video newVideo) throws TechniqueException {
	Boolean success = false;
	if (newVideo.getCode() == null) {
	    throw new TechniqueException();
	} else {
	    Map<String, Video> tableVideo = (Map<String, Video>) this.baseDeDonnees
		    .getBaseDeDonnees().get(BDD.TABLE_VIDEO);
	    tableVideo.get(newVideo.getCode()).setDateDebutLocation(
		    newVideo.getDateDebutLocation());
	    tableVideo.get(newVideo.getCode()).setDateRetour(
		    newVideo.getDateRetour());
	    tableVideo.get(newVideo.getCode()).setDureeFilm(
		    newVideo.getDureeFilm());
	    tableVideo.get(newVideo.getCode()).setGenre(newVideo.getGenre());
	    tableVideo.get(newVideo.getCode()).setIsDisponible(
		    newVideo.getIsDisponible());
	    tableVideo.get(newVideo.getCode()).setNbJourLocationAutorises(
		    newVideo.getNbJourLocationAutorises());
	    tableVideo.get(newVideo.getCode()).setNom(newVideo.getNom());
	    success = true;
	}
	return success;
    }

    @Override
    public Boolean deleteVideo(Video newVideo) throws TechniqueException {
	Boolean success = false;
	if (newVideo.getCode() == null) {
	    throw new TechniqueException();
	} else {
	    Map<String, Video> tableVideo = (Map<String, Video>) this.baseDeDonnees
		    .getBaseDeDonnees().get(BDD.TABLE_VIDEO);
	    tableVideo.remove(newVideo.getCode());
	    success = true;
	}
	return success;
    }

}

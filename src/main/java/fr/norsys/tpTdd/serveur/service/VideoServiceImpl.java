package fr.norsys.tpTdd.serveur.service;

import java.util.List;

import fr.norsys.tpTdd.modeles.Video;
import fr.norsys.tpTdd.serveur.dao.VideoDAO;
import fr.norsys.tpTdd.technique.MetierException;
import fr.norsys.tpTdd.technique.TechniqueException;

public class VideoServiceImpl implements VideoService {

    private VideoDAO videoDao;

    /**
     * @param videoDao
     *            the videoDao to set
     */
    public void setVideoDao(VideoDAO videoDao) {
	this.videoDao = videoDao;
    }

    @Override
    public List<Video> selectVideo(String nom, String genre) {
	return this.videoDao.selectVideos(nom, genre);
    }

    @Override
    public Boolean insertVideo(Video video) throws TechniqueException,
	    MetierException {
	if (video.getCode() == null || video.getCode().isEmpty()) {
	    throw new MetierException(
		    "Le code d'une vidéo ne peut pas être 'null' ou vide.");
	} else if (video.getNom() == null || video.getNom().isEmpty()) {
	    throw new MetierException(
		    "Le nom/titre d'une vidéo ne peut pas être 'null' ou vide.");
	} else if (video.getDureeFilm() == null) {
	    throw new MetierException(
		    "La durée d'une vidéo ne peut pas être 'null'.");
	} else if (video.getGenre() == null || video.getGenre().isEmpty()) {
	    throw new MetierException(
		    "Le genre d'une vidéo ne peut pas être 'null' ou vide.");
	} else if (video.getIsDisponible() == null) {
	    throw new MetierException(
		    "La disponibilité d'une vidéo ne peut pas être 'null'.");
	} else if (video.getNbJourLocationAutorises() == null) {
	    throw new MetierException(
		    "Le nombre de jour maximum de location autorisé pour une vidéo ne peut pas être 'null'.");
	} else {
	    List<Video> test = this.videoDao.selectVideos(video.getNom(),
		    video.getGenre());
	    if (test != null && !(test.isEmpty())) {
		return false;
	    } else {
		return this.videoDao.insert(video);
	    }
	}
    }

    @Override
    public Boolean updateVideo(Video video) throws TechniqueException,
	    MetierException {
	if (video.getCode() == null || video.getCode().isEmpty()) {
	    throw new MetierException(
		    "Le code d'une vidéo ne peut pas être 'null' ou vide.");
	} else if (video.getNom() == null || video.getNom().isEmpty()) {
	    throw new MetierException(
		    "Le nom/titre d'une vidéo ne peut pas être 'null' ou vide.");
	} else if (video.getDureeFilm() == null) {
	    throw new MetierException(
		    "La durée d'une vidéo ne peut pas être 'null'.");
	} else if (video.getGenre() == null || video.getGenre().isEmpty()) {
	    throw new MetierException(
		    "Le genre d'une vidéo ne peut pas être 'null' ou vide.");
	} else if (video.getIsDisponible() == null) {
	    throw new MetierException(
		    "La disponibilité d'une vidéo ne peut pas être 'null'.");
	} else if (video.getNbJourLocationAutorises() == null) {
	    throw new MetierException(
		    "Le nombre de jour maximum de location autorisé pour une vidéo ne peut pas être 'null'.");
	} else {
	    List<Video> test = this.videoDao.selectVideos(video.getNom(),
		    video.getGenre());
	    if (test != null && !(test.isEmpty())) {
		return this.videoDao.updateVideo(video);
	    } else {
		return false;
	    }
	}
    }

    @Override
    public Boolean deleteVideo(Video video) throws TechniqueException, MetierException {
	if (video.getNom() == null || video.getNom().isEmpty()) {
	    throw new MetierException(
		    "Le nom/titre d'une vidéo ne peut pas être 'null' ou vide.");
	} else if (video.getGenre() == null || video.getGenre().isEmpty()) {
	    throw new MetierException(
		    "Le genre d'une vidéo ne peut pas être 'null' ou vide.");
	} else {
	    List<Video> test = this.videoDao.selectVideos(video.getNom(),
		    video.getGenre());
	    if (test != null && !(test.isEmpty())) {
		video.setCode(test.get(0).getCode());
		return this.videoDao.deleteVideo(video);
	    } else {
		return false;
	    }
	}
    }
}

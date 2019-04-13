package fr.norsys.tpTdd.serveur.servicemetier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.norsys.tpTdd.modeles.Personne;
import fr.norsys.tpTdd.modeles.Reponse;
import fr.norsys.tpTdd.modeles.Video;
import fr.norsys.tpTdd.serveur.service.PersonneService;
import fr.norsys.tpTdd.serveur.service.VideoService;
import fr.norsys.tpTdd.technique.MetierException;
import fr.norsys.tpTdd.technique.TechniqueException;

public class MainServiceImpl implements MainService {

	private PersonneService personneService;
	private VideoService videoService;

	/**
	 * @param personneService
	 *            the personneService to set
	 */
	public void setPersonneService(PersonneService personneService) {
		this.personneService = personneService;
	}

	/**
	 * @param videoService
	 *            the videoService to set
	 */
	public void setVideoService(VideoService videoService) {
		this.videoService = videoService;
	}

	@Override
	public List<Video> selectVideo(String titre, String genre) {
		return this.videoService.selectVideo(titre, genre);
	}

	@Override
	public Boolean insertVideo(Video video) throws TechniqueException,
			MetierException {
		return this.videoService.insertVideo(video);
	}

	@Override
	public Boolean updateVideo(Video video) throws TechniqueException,
			MetierException {
		return this.videoService.updateVideo(video);
	}

	@Override
	public Boolean deleteVideo(Video video) throws TechniqueException,
			MetierException {
		return this.videoService.deleteVideo(video);
	}

	@Override
	public List<Personne> selectPersonne(String type, String login) {
		return this.personneService.selectPersonne(type, login);
	}

	@Override
	public Boolean insertPersonne(Personne personne) throws TechniqueException,
			MetierException {
		return this.personneService.insertPersonne(personne);
	}

	@Override
	public Boolean updatePersonne(Personne personne) throws TechniqueException,
			MetierException {
		return this.personneService.updatePersonne(personne);
	}

	@Override
	public Boolean deletePersonne(Personne personne) throws TechniqueException,
			MetierException {
		return this.personneService.deletePersonne(personne);
	}

	@Override
	public Boolean getAuthentification(String login, String password) {
		List<Personne> test = this.personneService.selectPersonne(
				Personne.TYPE_GERANT, login);
		if (test != null && !(test.isEmpty())) {
			if (test.get(0).getLogin().equals(login)
					&& test.get(0).getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public List<Personne> selectListeClients() {
		return this.personneService.selectPersonne(Personne.TYPE_CLIENT, null);
	}

	@Override
	public List<Video> selectVideoDisponibles(String genre) {
		List<Video> result = this.videoService.selectVideo(null, genre);
		List<Video> videosDispos = new ArrayList<Video>();
		for (Video video : result) {
			if (video.getIsDisponible())
				videosDispos.add(video);
		}
		return videosDispos;
	}

	@Override
	public Reponse rendreVideo(Video video) throws TechniqueException,
			MetierException {
		Reponse reponse = new Reponse();
		if (video.getDateDebutLocation() != null) {
			Calendar calDebutLocation = Calendar.getInstance();
			calDebutLocation.setTime(video.getDateDebutLocation());
			int nbJourVideoLouee = (Calendar.getInstance().get(
					Calendar.DAY_OF_YEAR) - calDebutLocation
					.get(Calendar.DAY_OF_YEAR));
			video.setDateRetour(Calendar.getInstance().getTime());
			if (video.getNbJourLocationAutorises() > nbJourVideoLouee) {
				this.videoService.updateVideo(video);
				reponse.setMessage(Reponse.MESSAGE_OK);
			} else {
				this.videoService.updateVideo(video);
				reponse.setMessage(Reponse.MESSAGE_KO_RETARD_RENDU_VIDEO
						+ (nbJourVideoLouee - video
								.getNbJourLocationAutorises()));
			}
		} else {
			throw new MetierException(
					"Vous ne pouvez pas rendre une vidéo dont la date de début de location n'ap as était renseignée.");
		}
		return reponse;
	}
}

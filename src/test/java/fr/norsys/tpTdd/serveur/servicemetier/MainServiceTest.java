package fr.norsys.tpTdd.serveur.servicemetier;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.norsys.tpTdd.modeles.Personne;
import fr.norsys.tpTdd.modeles.Reponse;
import fr.norsys.tpTdd.modeles.Video;
import fr.norsys.tpTdd.serveur.service.PersonneService;
import fr.norsys.tpTdd.serveur.service.VideoService;

public class MainServiceTest {

    private MainService service;

    @Mock
    private PersonneService personneService;
    @Mock
    private VideoService videoService;

    @Before
    public void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);

	this.service = new MainServiceImpl();
	((MainServiceImpl) service).setPersonneService(personneService);
	((MainServiceImpl) service).setVideoService(videoService);
    }

    @Test
    public void selectVideo() throws Exception {
	List<Video> liste = new ArrayList<Video>();
	liste.add(new Video("123456", "TOTO", 90, Video.ACTION, true, null,
		null, 3));
	// On definit le comportement attendu
	when(videoService.selectVideo(Mockito.anyString(), Mockito.anyString()))
		.thenReturn(liste);
	List<Video> result = this.service.selectVideo(null, null);
	// On verifie l'appel au service
	verify(videoService, atLeastOnce()).selectVideo(Mockito.anyString(),
		Mockito.anyString());
	// On verifie le contenu
	assertNotNull(result);
	assertFalse(result.isEmpty());
	for (Video video : result) {
	    assertNotNull(video);
	}
    }

    @Test
    public void insertVideo() throws Exception {
	// On definit le comportement attendu
	when(videoService.insertVideo(Mockito.any(Video.class))).thenReturn(
		true);
	Boolean result = this.service.insertVideo(new Video("123456", "TOTO",
		90, Video.ACTION, true, null,
		null, 3));
	// On verifie l'appel au service
	verify(videoService, atLeastOnce()).insertVideo(
		Mockito.any(Video.class));
	// On verifie le contenu
	assertNotNull(result);
	assertTrue(result);
    }

    @Test
    public void updateVideo() throws Exception {
	// On definit le comportement attendu
	when(videoService.updateVideo(Mockito.any(Video.class))).thenReturn(
		true);
	Boolean result = this.service.updateVideo(new Video("123456", "TOTO",
		90, Video.ACTION, true, null,
		null, 3));
	// On verfie l'appel au service
	verify(videoService, atLeastOnce()).updateVideo(
		Mockito.any(Video.class));
	// On verifie le contenu
	assertNotNull(result);
	assertTrue(result);
    }

    @Test
    public void deleteVideo() throws Exception {
	// On definit le comportement attendu
	when(videoService.deleteVideo(Mockito.any(Video.class))).thenReturn(
		true);
	Boolean result = this.service.deleteVideo(new Video("123456", "TOTO",
		90, Video.ACTION, true, null,
		null, 3));
	// On verfie l'appel au service
	verify(videoService, atLeastOnce()).deleteVideo(
		Mockito.any(Video.class));
	// On verifie le contenu
	assertNotNull(result);
	assertTrue(result);
    }

    @Test
    public void selectPersonne() throws Exception {
	List<Personne> liste = new ArrayList<Personne>();
	liste.add(new Personne("ttoto", "123456", "TOTO", "toto", null,
		Personne.TYPE_GERANT));
	// On definit le comportement attendu
	when(
		personneService.selectPersonne(Mockito.anyString(),
			Mockito.anyString()))
		.thenReturn(liste);
	List<Personne> result = this.service.selectPersonne(null, null);
	// On verifie l'appel au service
	verify(personneService, atLeastOnce()).selectPersonne(
		Mockito.anyString(),
		Mockito.anyString());
	// On verifie le contenu
	assertNotNull(result);
	assertFalse(result.isEmpty());
	for (Personne personne : result) {
	    assertNotNull(personne);
	}
    }

    @Test
    public void insertPersonne() throws Exception {
	// On definit le comportement attendu
	when(personneService.insertPersonne(Mockito.any(Personne.class)))
		.thenReturn(
			true);
	Boolean result = this.service.insertPersonne(new Personne("ttoto",
		"123456", "TOTO", "toto", null, Personne.TYPE_GERANT));
	// On verifie l'appel au service
	verify(personneService, atLeastOnce()).insertPersonne(
		Mockito.any(Personne.class));
	// On verifie le contenu
	assertNotNull(result);
	assertTrue(result);
    }

    @Test
    public void updatePersonne() throws Exception {
	// On definit le comportement attendu
	when(personneService.updatePersonne(Mockito.any(Personne.class)))
		.thenReturn(
			true);
	Boolean result = this.service.updatePersonne(new Personne("ttoto",
		"123456", "TOTO", "toto", null, Personne.TYPE_GERANT));
	// On verifie l'appel au service
	verify(personneService, atLeastOnce()).updatePersonne(
		Mockito.any(Personne.class));
	// On verifie le contenu
	assertNotNull(result);
	assertTrue(result);
    }

    @Test
    public void deletePersonne() throws Exception {
	// On definit le comportement attendu
	when(personneService.deletePersonne(Mockito.any(Personne.class)))
		.thenReturn(
			true);
	Boolean result = this.service.deletePersonne(new Personne("ttoto",
		"123456", "TOTO", "toto", null, Personne.TYPE_GERANT));
	// On verifie l'appel au service
	verify(personneService, atLeastOnce()).deletePersonne(
		Mockito.any(Personne.class));
	// On verifie le contenu
	assertNotNull(result);
	assertTrue(result);
    }

    @Test
    public void authentification() throws Exception {
	List<Personne> liste = new ArrayList<Personne>();
	liste.add(new Personne("ttoto",
			"123456", "TOTO", "toto", null, Personne.TYPE_GERANT));
	// On definit le comportement attendu
	when(personneService.selectPersonne(Personne.TYPE_GERANT, "ttoto"))
		.thenReturn(liste);
	Boolean result = this.service.getAuthentification("ttoto", "123456");
	// On verifie l'appel au service
	verify(personneService, atLeastOnce()).selectPersonne(
		Personne.TYPE_GERANT, "ttoto");
	// On verfie le contenu
	assertNotNull(result);
	assertTrue(result);
    }

    @Test
    public void authentificationFailed() throws Exception {
	// On definit le comportement attendu
	when(personneService.selectPersonne(Personne.TYPE_GERANT, "ttiti"))
		.thenReturn(new ArrayList<Personne>());
	Boolean result = this.service.getAuthentification("ttiti", "123456");
	// On verifie l'appel au service
	verify(personneService, atLeastOnce()).selectPersonne(
		Personne.TYPE_GERANT, "ttiti");
	// On verfie le contenu
	assertNotNull(result);
	assertFalse(result);
    }

    @Test
    public void authentificationFailed2() throws Exception {
	List<Personne> liste = new ArrayList<Personne>();
	liste.add(new Personne("ttoto",
			"123456", "TOTO", "toto", null, Personne.TYPE_GERANT));
	// On definit le comportement attendu
	when(personneService.selectPersonne(Personne.TYPE_GERANT, "ttoto"))
		.thenReturn(liste);
	Boolean result = this.service.getAuthentification("ttoto", "123457");
	// On verifie l'appel au service
	verify(personneService, atLeastOnce()).selectPersonne(
		Personne.TYPE_GERANT, "ttoto");
	// On verfie le contenu
	assertNotNull(result);
	assertFalse(result);
    }

    @Test
    public void listerClients() throws Exception {
	List<Personne> liste = new ArrayList<Personne>();
	liste.add(new Personne("ttiti",
			"123456", "TITI", "titi", null, Personne.TYPE_CLIENT));
	liste.add(new Personne("ttata",
		"123456", "TATA", "tata", null, Personne.TYPE_CLIENT));
	// On definit le comportement attendu
	when(personneService.selectPersonne(Personne.TYPE_CLIENT, null))
		.thenReturn(liste);
	List<Personne> result = this.service.selectListeClients();
	// On verifie l'appel au service
	verify(personneService, atLeastOnce()).selectPersonne(
		Personne.TYPE_CLIENT, null);
	// On verifie le contenu
	assertNotNull(result);
	assertFalse(result.isEmpty());
	for (Personne personne : result) {
	    assertNotNull(personne);
	    assertEquals(Personne.TYPE_CLIENT, personne.getType());
	}
    }

    @Test
    public void listerVideoDisponibles() throws Exception {
	List<Video> liste = new ArrayList<Video>();
	liste.add(new Video("123456", "TOTO",
		90, Video.ACTION, true, null,
		null, 3));
	liste.add(new Video("123456", "TOTO",
		90, Video.DESSINS_ANIME, true, null,
		null, 3));
	liste.add(new Video("123456", "TOTO",
		90, Video.ACTION, false, null,
		null, 3));
	liste.add(new Video("123456", "TOTO",
		90, Video.COMEDIE, true, null,
		null, 3));
	// On defininit le comportement attendu
	when(videoService.selectVideo(null, null)).thenReturn(liste);
	List<Video> result = this.service.selectVideoDisponibles(null);
	// On verifie l'appel au service
	verify(videoService, atLeastOnce()).selectVideo(null, null);
	// On verifie le contenu
	assertNotNull(result);
	assertFalse(result.isEmpty());
	for (Video video : result) {
	    assertNotNull(video);
	    assertTrue(video.getIsDisponible());
	}
    }

    @Test
    public void listerVideoDisponiblesParGenre() throws Exception {
	List<Video> liste = new ArrayList<Video>();
	liste.add(new Video("123456", "TOTO",
			90, Video.ACTION, true, null,
			null, 3));
	liste.add(new Video("123456", "TOTO",
			90, Video.DESSINS_ANIME, true, null,
			null, 3));
	liste.add(new Video("123456", "TOTO",
			90, Video.ACTION, false, null,
			null, 3));
	liste.add(new Video("123456", "TOTO",
			90, Video.COMEDIE, true, null,
			null, 3));
	// On defininit le comportement attendu
	when(videoService.selectVideo(null, Video.ACTION)).thenReturn(liste);
	List<Video> result = this.service.selectVideoDisponibles(Video.ACTION);
	// On verifie l'appel au service
	verify(videoService, atLeastOnce()).selectVideo(null, Video.ACTION);
	// On verifie le contenu
	assertNotNull(result);
	assertFalse(result.isEmpty());
	for (Video video : result) {
	    assertNotNull(video);
	    assertTrue(video.getIsDisponible());
	}
    }

    @Test
    public void rendreVideoOK() throws Exception {
	Calendar calDebutLocation = Calendar.getInstance();
	calDebutLocation.roll(Calendar.DAY_OF_YEAR, -2);
	Calendar calFinLocation = Calendar.getInstance();
	// On definit le comportement attendu
	when(
		videoService.updateVideo(new Video("0001003", "TOTO", 90,
			Video.ACTION, false, calDebutLocation.getTime(),
			calFinLocation.getTime(), 3))).thenReturn(
		true);
	Reponse result = service.rendreVideo(new Video("0001003", "TOTO", 90,
		Video.ACTION, false, calDebutLocation.getTime(), null, 3));
	// On verifie l'appel au service
	ArgumentCaptor<Video> captor = ArgumentCaptor.forClass(Video.class);
	verify(videoService, atLeastOnce()).updateVideo(captor.capture());
	assertNotNull(captor.getValue().getDateRetour());
	// On verifie le contenu
	assertNotNull(result);
	assertEquals(Reponse.MESSAGE_OK, result.getMessage());
    }

    @Test
    public void rendreVideoKO() throws Exception {
	Calendar calDebutLocation = Calendar.getInstance();
	calDebutLocation.roll(Calendar.DAY_OF_YEAR, -5);
	Calendar calFinLocation = Calendar.getInstance();
	// On definit le comportement attendu
	when(
		videoService.updateVideo(new Video("0001003", "TOTO", 90,
			Video.ACTION, false, calDebutLocation.getTime(),
			calFinLocation.getTime(), 3))).thenReturn(
		true);
	Reponse result = service.rendreVideo(new Video("0001003", "TOTO", 90,
		Video.ACTION, false, calDebutLocation.getTime(), null, 3));
	// On verifie l'appel au service
	ArgumentCaptor<Video> captor = ArgumentCaptor.forClass(Video.class);
	verify(videoService, atLeastOnce()).updateVideo(captor.capture());
	assertNotNull(captor.getValue().getDateRetour());
	// On verifie le contenu
	assertNotNull(result);
	assertEquals(Reponse.MESSAGE_KO_RETARD_RENDU_VIDEO+2, result.getMessage());
    }
}

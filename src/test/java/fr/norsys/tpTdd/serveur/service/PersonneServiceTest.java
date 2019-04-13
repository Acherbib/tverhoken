package fr.norsys.tpTdd.serveur.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.norsys.tpTdd.modeles.Personne;
import fr.norsys.tpTdd.serveur.dao.PersonneDAO;
import fr.norsys.tpTdd.technique.MetierException;

public class PersonneServiceTest {

    private PersonneService service;
    @Mock
    private PersonneDAO personneDao;

    @Before
    public void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);

	this.service = new PersonneServiceImpl();

	((PersonneServiceImpl) this.service).setPersonneDao(personneDao);
    }

    @Test
    public void selectListePersonne() throws Exception {
	List<Personne> liste = new ArrayList<Personne>();
	liste.add(new Personne("ttoto", "123456", "TOTO", "Toto", null,
		Personne.TYPE_GERANT));
	// On indique le comportment souhaite pour personneDao
	when(
		personneDao.selectPersonne(Mockito.anyString(),
			Mockito.anyString())).thenReturn(liste);
	List<Personne> result = this.service.selectPersonne(null, null);
	// on verifie l'appel au service
	verify(personneDao, atLeastOnce()).selectPersonne(Mockito.anyString(),
		Mockito.anyString());
	// On verifie le contenu
	assertNotNull(result);
	assertFalse(result.isEmpty());
	for (Personne personne : result) {
	    assertNotNull(personne);
	}
    }

    @Test
    public void selectListePersonne2() throws Exception {
	List<Personne> liste = new ArrayList<Personne>();
	liste.add(new Personne("ttoto", "123456", "TOTO", "Toto", null,
		Personne.TYPE_GERANT));
	// On decrit le comportement que l'on attend du service.
	when(
		personneDao.selectPersonne(Personne.TYPE_CLIENT,
			null)).thenReturn(
		new ArrayList<Personne>());
	when(
		personneDao.selectPersonne(Personne.TYPE_GERANT,
			null)).thenReturn(
		liste);
	List<Personne> result = this.service
		.selectPersonne(Personne.TYPE_CLIENT, null);
	// on verifie l'appel au service
	verify(personneDao, atLeastOnce()).selectPersonne(Personne.TYPE_CLIENT,
		null);
	// On verifie le contenu
	assertNotNull(result);
	assertTrue(result.isEmpty());
	// On rappel le service mais pour un autre type de personne
	result = this.service.selectPersonne(Personne.TYPE_GERANT, null);
	// on verifie l'appel au service
	verify(personneDao, atLeastOnce()).selectPersonne(Personne.TYPE_GERANT,
		null);
	// On verifie le contenu
	assertNotNull(result);
	assertFalse(result.isEmpty());
    }

    @Test
    public void selectListePersonne3() throws Exception {
	List<Personne> liste = new ArrayList<Personne>();
	liste.add(new Personne("ttoto", "123456", "TOTO", "Toto", null,
		Personne.TYPE_GERANT));
	// On decrit le comportement que l'on attend du service.
	when(
		personneDao.selectPersonne(Personne.TYPE_CLIENT,
			"ttoto")).thenReturn(
		new ArrayList<Personne>());
	when(
		personneDao.selectPersonne(Personne.TYPE_GERANT,
			"ttoto")).thenReturn(
		liste);
	List<Personne> result = this.service
		.selectPersonne(Personne.TYPE_CLIENT, "ttoto");
	// on verifie l'appel au service
	verify(personneDao, atLeastOnce()).selectPersonne(Personne.TYPE_CLIENT,
		"ttoto");
	// On verifie le contenu
	assertNotNull(result);
	assertTrue(result.isEmpty());
	// On rappel le service mais pour un autre type de personne
	result = this.service.selectPersonne(Personne.TYPE_GERANT, "ttoto");
	// on verifie l'appel au service
	verify(personneDao, atLeastOnce()).selectPersonne(Personne.TYPE_GERANT,
		"ttoto");
	// On verifie le contenu
	assertNotNull(result);
	assertFalse(result.isEmpty());
    }

    @Test
    public void insertPersonne() throws Exception {
	// On decrit le comportement que l'on attend du service.
	when(personneDao.insertPersonne(Mockito.any(Personne.class)))
		.thenReturn(true);
	Boolean result = this.service.insertPersonne(new Personne("ttoto",
		"123456", "TOTO", "Toto", null, Personne.TYPE_GERANT));
	// On verifie l'appel au service
	verify(personneDao).insertPersonne(Mockito.any(Personne.class));
	verify(personneDao, atLeastOnce()).selectPersonne(Personne.TYPE_GERANT,
		"ttoto");
	// On verifie le contenu
	assertNotNull(result);
	assertTrue(result);
    }

    @Test(expected = MetierException.class)
    public void insertPersonneCheckValideData1() throws Exception {
	Calendar cal = Calendar.getInstance();
	cal.set(1989, 06, 07);
	Personne personne = new Personne(null, "123456", "TOTO", "Toto",
		cal.getTime(), Personne.TYPE_GERANT);
	this.service.insertPersonne(personne);
    }

    @Test(expected = MetierException.class)
    public void insertPersonneCheckValideData2() throws Exception {
	Calendar cal = Calendar.getInstance();
	cal.set(1989, 06, 07);
	Personne personne = new Personne("ttoto", null, "TOTO", "Toto",
		cal.getTime(), Personne.TYPE_GERANT);
	this.service.insertPersonne(personne);
    }

    @Test(expected = MetierException.class)
    public void insertPersonneCheckValideData3() throws Exception {
	Calendar cal = Calendar.getInstance();
	cal.set(1989, 06, 07);
	Personne personne = new Personne("ttoto", "123456", null, "Toto",
		cal.getTime(), Personne.TYPE_GERANT);
	this.service.insertPersonne(personne);
    }

    @Test(expected = MetierException.class)
    public void insertPersonneCheckValideData4() throws Exception {
	Calendar cal = Calendar.getInstance();
	cal.set(1989, 06, 07);
	Personne personne = new Personne("ttoto", "123456", "TOTO", null,
		cal.getTime(), Personne.TYPE_GERANT);
	this.service.insertPersonne(personne);
    }

    @Test(expected = MetierException.class)
    public void insertPersonneCheckValideData5() throws Exception {
	Calendar cal = Calendar.getInstance();
	cal.set(1989, 06, 07);
	Personne personne = new Personne("ttoto", "123456", "TOTO", "Toto",
		cal.getTime(), null);
	this.service.insertPersonne(personne);
    }

    @Test
    public void insert2XTheSamePersonne() throws Exception {
	Personne personne = new Personne("ttoto",
		"123456", "TOTO", "Toto", null, Personne.TYPE_GERANT);
	// On decrit le comportement que l'on attend du service.
	when(personneDao.insertPersonne(personne))
		.thenReturn(true);
	Boolean result = this.service.insertPersonne(personne);
	// On verifie l'appel au service
	verify(personneDao, atLeastOnce()).insertPersonne(
		Mockito.any(Personne.class));
	verify(personneDao, atLeastOnce()).selectPersonne(personne.getType(),
		personne.getLogin());
	// On verifie le contenu
	assertNotNull(result);
	assertTrue(result);
	// on defini que la personne que l'on vient d'ajouter est presente en
	// BDD
	List<Personne> liste = new ArrayList<Personne>();
	liste.add(personne);
	when(
		personneDao.selectPersonne(personne.getType(),
			personne.getLogin())).thenReturn(liste);
	// on essaye de reinserer le personne
	result = this.service.insertPersonne(personne);
	// On verifie l'appel au service
	verify(personneDao, atLeastOnce()).selectPersonne(personne.getType(),
		personne.getLogin());
	// On verifie le contenu
	assertNotNull(result);
	assertFalse(result);
    }

    @Test
    public void updatePersonne() throws Exception {
	Personne personne = new Personne("ttoto",
		"123457", "TOTO", "Toto", null, Personne.TYPE_GERANT);
	// On decrit le comportment que l'on attend du service
	when(personneDao.updatePersonne(personne)).thenReturn(true);
	List<Personne> liste = new ArrayList<Personne>();
	liste.add(new Personne("ttoto",
		"123456", "TOTO", "Toto", null, Personne.TYPE_GERANT));
	when(
		personneDao.selectPersonne(personne.getType(),
			personne.getLogin())).thenReturn(liste);
	Boolean result = this.service.updatePersonne(personne);
	// On verifie l'appel au service
	verify(personneDao, atLeastOnce()).updatePersonne(personne);
	verify(personneDao, atLeastOnce()).selectPersonne(personne.getType(),
		personne.getLogin());
	// On verfie le contenu
	assertNotNull(result);
	assertTrue(result);
    }

    @Test(expected = MetierException.class)
    public void updatePersonneCheckDataValide1() throws Exception {
	this.service.updatePersonne(new Personne(null,
		"123456", "TOTO", "Toto", null, Personne.TYPE_GERANT));
    }

    @Test(expected = MetierException.class)
    public void updatePersonneCheckDataValide2() throws Exception {
	this.service.updatePersonne(new Personne("ttoto",
		null, "TOTO", "Toto", null, Personne.TYPE_GERANT));
    }

    @Test(expected = MetierException.class)
    public void updatePersonneCheckDataValide3() throws Exception {
	this.service.updatePersonne(new Personne("ttoto",
		"123456", null, "Toto", null, Personne.TYPE_GERANT));
    }

    @Test(expected = MetierException.class)
    public void updatePersonneCheckDataValide4() throws Exception {
	this.service.updatePersonne(new Personne("ttoto",
		"123456", "TOTO", null, null, Personne.TYPE_GERANT));
    }

    @Test(expected = MetierException.class)
    public void updatePersonneCheckDataValide5() throws Exception {
	this.service.updatePersonne(new Personne("ttoto",
		"123456", "TOTO", "Toto", null, null));
    }

    @Test
    public void deletePersonne() throws Exception {
	Personne personne = new Personne("ttoto",
		"123457", "TOTO", "Toto", null, Personne.TYPE_GERANT);
	// On definit le comportment attendu
	when(personneDao.deletePersonne(personne)).thenReturn(true);
	List<Personne> liste = new ArrayList<Personne>();
	liste.add(new Personne("ttoto",
		"123457", "TOTO", "Toto", null, Personne.TYPE_GERANT));
	when(
		personneDao.selectPersonne(personne.getType(),
			personne.getLogin())).thenReturn(liste);
	Boolean result = this.service.deletePersonne(personne);
	// On verifie l'appel au service
	verify(personneDao, atLeastOnce()).deletePersonne(personne);
	verify(personneDao, atLeastOnce()).selectPersonne(personne.getType(),
		personne.getLogin());
	// On verifie le contenu
	assertNotNull(result);
	assertTrue(result);
    }

    @Test(expected = MetierException.class)
    public void deletePersonneCheckDataValide1() throws Exception {
	this.service.deletePersonne(new Personne(null,
		"123457", "TOTO", "Toto", null, Personne.TYPE_GERANT));
    }

    @Test(expected = MetierException.class)
    public void deletePersonneCheckDataValide2() throws Exception {
	this.service.deletePersonne(new Personne("ttoto",
		"123457", "TOTO", "Toto", null, null));
    }

    @Test
    public void delete2XSamePersonne() throws Exception {
	Personne personne = new Personne("ttoto",
		"123457", "TOTO", "Toto", null, Personne.TYPE_GERANT);
	// On definit le comportment attendu
	when(personneDao.deletePersonne(personne)).thenReturn(true);
	List<Personne> liste = new ArrayList<Personne>();
	liste.add(new Personne("ttoto",
		"123456", "TOTO", "Toto", null, Personne.TYPE_GERANT));
	when(
		personneDao.selectPersonne(personne.getType(),
			personne.getLogin())).thenReturn(liste);
	Boolean result = this.service.deletePersonne(personne);
	// On verifie l'appel au service
	verify(personneDao, atLeastOnce()).deletePersonne(personne);
	verify(personneDao, atLeastOnce()).selectPersonne(personne.getType(),
		personne.getLogin());
	// On verifie le contenu
	assertNotNull(result);
	assertTrue(result);
	// On defini le comportement que l'on attend du service
	when(
		personneDao.selectPersonne(personne.getType(),
			personne.getLogin())).thenReturn(
		new ArrayList<Personne>());
	// On essai de supprimer une 2eme fois la même personne (cad une
	// personne qui n'existe pas)
	result = this.service.deletePersonne(personne);
	assertNotNull(result);
	assertFalse(result);
    }
}

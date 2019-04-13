package fr.norsys.tpTdd.serveur.dao;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.norsys.tpTdd.modeles.Personne;
import fr.norsys.tpTdd.technique.BDD;

public class PersonneDaoTest {

	private BDD base = this.initBDD();
	private PersonneDAO service;

	@Before
	public void setUp() throws Exception {
		this.service = new PersonneDAOImpl();
		((PersonneDAOImpl) this.service).setBaseDeDonnees(base);
	}

	@Test
	public void selectAllPersonne() throws Exception {
		List<Personne> result = service.selectPersonne(null, null);
		assertNotNull(result);
		assertFalse(result.isEmpty());
		for (Personne personne : result) {
			assertNotNull(personne);
		}
	}

	@Test
	public void selectPersonneTypeGerant() throws Exception {
		List<Personne> result = service.selectPersonne(Personne.TYPE_GERANT,
				null);
		assertNotNull(result);
		assertFalse(result.isEmpty());
		for (Personne personne : result) {
			assertNotNull(personne);
			assertTrue(Personne.TYPE_GERANT.equals(personne.getType()));
		}
	}

	@Test
	public void selectPersonneTypeClient() throws Exception {
		List<Personne> result = service.selectPersonne(Personne.TYPE_CLIENT,
				null);
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	public void selectPersonneWithLogin() throws Exception {
		List<Personne> result = service.selectPersonne(null, "ttoto");
		assertNotNull(result);
		assertFalse(result.isEmpty());
		for (Personne personne : result) {
			assertNotNull(personne);
			assertTrue("ttoto".equals(personne.getLogin()));
		}
	}

	@Test
	public void selectPersonneWithLogin2() throws Exception {
		List<Personne> result = service.selectPersonne(null, "ttata");
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	public void selectPersonneWithTypeAndLogin() throws Exception {
		List<Personne> result = service.selectPersonne(Personne.TYPE_GERANT,
				"ttoto");
		assertNotNull(result);
		assertFalse(result.isEmpty());
		for (Personne personne : result) {
			assertNotNull(personne);
			assertTrue("ttoto".equals(personne.getLogin()));
			assertTrue(Personne.TYPE_GERANT.equals(personne.getType()));
		}
	}

	@Test
	public void selectPersonneWithTypeAndLogin2() throws Exception {
		List<Personne> result = service.selectPersonne(Personne.TYPE_CLIENT,
				"ttoto");
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	public void insertPersonne() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.set(1970, 06, 07);
		Personne newPersonne = new Personne("ttata", "123456", "TATA", "Tata",
				cal.getTime(), Personne.TYPE_CLIENT);
		Boolean result = service.insertPersonne(newPersonne);
		assertNotNull(result);
		assertTrue(result);
		List<Personne> liste = service.selectPersonne(Personne.TYPE_CLIENT,
				"ttata");
		assertNotNull(liste);
		assertFalse(liste.isEmpty());
	}

	@Test
	public void insertPersonneAndUpdate() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.set(1970, 06, 07);
		Personne newPersonne = new Personne("ttiti", "123456", "TITI", "Titi",
				cal.getTime(), Personne.TYPE_CLIENT);
		Boolean result = service.insertPersonne(newPersonne);
		assertNotNull(result);
		assertTrue(result);
		List<Personne> liste = service.selectPersonne(Personne.TYPE_CLIENT,
				"ttiti");
		assertNotNull(liste);
		assertFalse(liste.isEmpty());
		cal.set(1980, 04, 27);
		newPersonne.setDateNaissance(cal.getTime());
		result = service.updatePersonne(newPersonne);
		assertNotNull(result);
		assertTrue(result);
		liste = service.selectPersonne(Personne.TYPE_CLIENT, "ttiti");
		assertNotNull(liste);
		assertFalse(liste.isEmpty());
		assertTrue(cal.getTime().equals(liste.get(0).getDateNaissance()));
	}

	@Test
	public void insertAndDeletePersonne() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.set(1970, 06, 07);
		Personne newPersonne = new Personne("ttutu", "123456", "TUTU", "Tutu",
				cal.getTime(), Personne.TYPE_CLIENT);
		Boolean result = service.insertPersonne(newPersonne);
		assertNotNull(result);
		assertTrue(result);
		List<Personne> liste = service.selectPersonne(Personne.TYPE_CLIENT,
				"ttutu");
		assertNotNull(liste);
		assertFalse(liste.isEmpty());
		result = service.deletePersonne(newPersonne);
		assertNotNull(result);
		assertTrue(result);
		liste = service.selectPersonne(Personne.TYPE_CLIENT, "ttutu");
		assertNotNull(liste);
		assertTrue(liste.isEmpty());
	}

	private BDD initBDD() {
		Calendar cal = Calendar.getInstance();
		cal.set(1986, 12, 12);
		Personne personne = new Personne("ttoto", "123456", "TOTO", "Toto",
				cal.getTime(), Personne.TYPE_GERANT);
		return new BDD(personne);
	}
}

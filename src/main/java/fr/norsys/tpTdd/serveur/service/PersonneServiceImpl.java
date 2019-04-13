package fr.norsys.tpTdd.serveur.service;

import java.util.List;

import fr.norsys.tpTdd.modeles.Personne;
import fr.norsys.tpTdd.serveur.dao.PersonneDAO;
import fr.norsys.tpTdd.technique.MetierException;
import fr.norsys.tpTdd.technique.TechniqueException;

public class PersonneServiceImpl implements PersonneService {

    private PersonneDAO personneDao;

    /**
     * @param personneDao
     *            the personneDao to set
     */
    public void setPersonneDao(PersonneDAO personneDao) {
	this.personneDao = personneDao;
    }

    @Override
    public List<Personne> selectPersonne(String type, String login) {
	return this.personneDao.selectPersonne(type, login);
    }

    @Override
    public Boolean insertPersonne(Personne personne) throws TechniqueException,
	    MetierException {
	if (personne.getLogin() == null || personne.getLogin().isEmpty()) {
	    throw new MetierException(
		    "Le login d'une personne ne peut pas être null ou vide.");
	} else if (personne.getPassword() == null
		|| personne.getPassword().isEmpty()) {
	    throw new MetierException(
		    "Le password d'une personne ne peut pas être null ou vide.");
	} else if (personne.getNom() == null || personne.getNom().isEmpty()) {
	    throw new MetierException(
		    "Le nom d'une personne ne peut pas être null ou vide.");
	} else if (personne.getPrenom() == null
		|| personne.getPrenom().isEmpty()) {
	    throw new MetierException(
		    "Le prénom d'une personne ne peut pas être null ou vide.");
	} else if (personne.getType() == null || personne.getType().isEmpty()) {
	    throw new MetierException(
		    "Le type d'une personne ne peut pas être null ou vide.");
	} else {
	    List<Personne> test = this.personneDao.selectPersonne(
		    personne.getType(), personne.getLogin());
	    if (test != null && !(test.isEmpty())) {
		return false;
	    } else {
		return this.personneDao.insertPersonne(personne);
	    }
	}
    }

    @Override
    public Boolean updatePersonne(Personne personne) throws TechniqueException,
	    MetierException {
	if (personne.getLogin() == null || personne.getLogin().isEmpty()) {
	    throw new MetierException(
		    "Le login d'une personne ne peut pas être null ou vide.");
	} else if (personne.getPassword() == null
		|| personne.getPassword().isEmpty()) {
	    throw new MetierException(
		    "Le password d'une personne ne peut pas être null ou vide.");
	} else if (personne.getNom() == null || personne.getNom().isEmpty()) {
	    throw new MetierException(
		    "Le nom d'une personne ne peut pas être null ou vide.");
	} else if (personne.getPrenom() == null
		|| personne.getPrenom().isEmpty()) {
	    throw new MetierException(
		    "Le prénom d'une personne ne peut pas être null ou vide.");
	} else if (personne.getType() == null || personne.getType().isEmpty()) {
	    throw new MetierException(
		    "Le type d'une personne ne peut pas être null ou vide.");
	} else {
	    List<Personne> test = this.personneDao.selectPersonne(
		    personne.getType(), personne.getLogin());
	    if (test != null && !(test.isEmpty())) {
		return this.personneDao.updatePersonne(personne);
	    } else {
		return false;
	    }
	}
    }

    @Override
    public Boolean deletePersonne(Personne personne) throws TechniqueException,
	    MetierException {
	if (personne.getType() == null || personne.getType().isEmpty()) {
	    throw new MetierException(
		    "Le type d'une personne ne peut pas être null ou vide.");
	} else if (personne.getLogin() == null || personne.getLogin().isEmpty()) {
	    throw new MetierException(
		    "Le login d'une personne ne peut pas être null ou vide.");
	} else {
	    List<Personne> test = this.personneDao.selectPersonne(
		    personne.getType(), personne.getLogin());
	    if (test != null && !(test.isEmpty())) {
		return this.personneDao.deletePersonne(personne);
	    } else {
		return false;
	    }
	}
    }
}

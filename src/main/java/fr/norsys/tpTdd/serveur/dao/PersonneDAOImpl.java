package fr.norsys.tpTdd.serveur.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.norsys.tpTdd.modeles.Personne;
import fr.norsys.tpTdd.technique.BDD;
import fr.norsys.tpTdd.technique.TechniqueException;

public class PersonneDAOImpl implements PersonneDAO {

	private BDD baseDeDonnees;

	@Override
	public List<Personne> selectPersonne(String type, String login) {
		Map<String, ?> tablePersonne = (this.baseDeDonnees.getBaseDeDonnees()
				.get(BDD.TABLE_PERSONNE));
		List<Personne> result = new ArrayList<Personne>();
		for (Object personne : tablePersonne.values()) {
			if (type != null && login != null) {
				if (type.equals(((Personne) personne).getType())
						&& login.equals(((Personne) personne).getLogin())) {
					result.add((Personne) personne);
				}
			} else if (type != null) {
				if (type.equals(((Personne) personne).getType())) {
					result.add((Personne) personne);
				}
			} else if (login != null) {
				if (login.equals(((Personne) personne).getLogin())) {
					result.add((Personne) personne);
				}
			} else {
				result.add((Personne) personne);
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
	public Boolean insertPersonne(Personne newPersonne)
			throws TechniqueException {
		Boolean success = false;
		if (newPersonne.getLogin() == null) {
			throw new TechniqueException();
		} else {
			Map<String, Personne> tablePersonne = (Map<String, Personne>) this.baseDeDonnees
					.getBaseDeDonnees().get(BDD.TABLE_PERSONNE);
			tablePersonne.put(newPersonne.getLogin(), newPersonne);
			success = true;
		}
		return success;
	}

	@Override
	public Boolean updatePersonne(Personne newPersonne)
			throws TechniqueException {
		Boolean success = false;
		if (newPersonne.getLogin() == null) {
			throw new TechniqueException();
		} else {
			Map<String, Personne> tablePersonne = (Map<String, Personne>) this.baseDeDonnees
					.getBaseDeDonnees().get(BDD.TABLE_PERSONNE);
			tablePersonne.get(newPersonne.getLogin()).setNom(
					newPersonne.getNom());
			tablePersonne.get(newPersonne.getLogin()).setPassword(
					newPersonne.getPassword());
			tablePersonne.get(newPersonne.getLogin()).setPrenom(
					newPersonne.getPrenom());
			tablePersonne.get(newPersonne.getLogin()).setType(
					newPersonne.getType());
			tablePersonne.get(newPersonne.getLogin()).setDateNaissance(
					newPersonne.getDateNaissance());
			success = true;
		}
		return success;
	}

	@Override
	public Boolean deletePersonne(Personne newPersonne)
			throws TechniqueException {
		Boolean success = false;
		if (newPersonne.getLogin() == null) {
			throw new TechniqueException();
		} else {
			Map<String, Personne> tablePersonne = (Map<String, Personne>) this.baseDeDonnees
					.getBaseDeDonnees().get(BDD.TABLE_PERSONNE);
			tablePersonne.remove(newPersonne.getLogin());
			success = true;
		}
		return success;
	}

}

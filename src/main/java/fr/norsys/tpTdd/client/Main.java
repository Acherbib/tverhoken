package fr.norsys.tpTdd.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.norsys.tpTdd.modeles.Personne;
import fr.norsys.tpTdd.modeles.Reponse;
import fr.norsys.tpTdd.modeles.Video;
import fr.norsys.tpTdd.serveur.dao.PersonneDAO;
import fr.norsys.tpTdd.serveur.dao.PersonneDAOImpl;
import fr.norsys.tpTdd.serveur.dao.VideoDAO;
import fr.norsys.tpTdd.serveur.dao.VideoDAOImpl;
import fr.norsys.tpTdd.serveur.service.PersonneService;
import fr.norsys.tpTdd.serveur.service.PersonneServiceImpl;
import fr.norsys.tpTdd.serveur.service.VideoService;
import fr.norsys.tpTdd.serveur.service.VideoServiceImpl;
import fr.norsys.tpTdd.serveur.servicemetier.MainService;
import fr.norsys.tpTdd.serveur.servicemetier.MainServiceImpl;
import fr.norsys.tpTdd.technique.BDD;
import fr.norsys.tpTdd.technique.MetierException;
import fr.norsys.tpTdd.technique.TechniqueException;

public class Main {

	private static String login;
	private static String password;
	private static BDD base;
	private static MainService service;

	public static void main(String[] args) {
		init();
		initAffichageConnection();
		if (login != null & password != null) {
			Boolean authentification = false;
			while (!authentification) {
				authentification = service.getAuthentification(login, password);
				if (!authentification) {
					affichageErreurConnection();
					initAffichageConnection();
				}
			}
			if (authentification) {
				performMenuPrincipal();
			}
		}

	}

	private static void performMenuPrincipal() {
		Integer chx = null;
		while (chx == null || !(chx > 0 && chx < 7)) {
			affichageMenu();
			String choix = lireSaisie();
			try {
				chx = Integer.parseInt(choix);
			} catch (NumberFormatException e) {
				System.out.println("Veuillez saisir un chiffre svp.");
			}
		}
		traitementChoixMenu(chx);
	}

	private static void traitementChoixMenu(Integer chx) {
		switch (chx) {
		case 1:
			traitementGestionTablePersonne();
		case 2:
			traitementGestionTableVideo();
		case 3:
			traitementListerClients();
		case 4:
			traitementListerVideosDisponibles();
		case 5:
			traitementRendreVideo();
		case 6:
			System.exit(0);
		}

	}

	private static void traitementGestionTableVideo() {
		Integer chx = null;
		while (chx == null || !(chx > 0 && chx < 6)) {
			System.out
					.println("\n/********** MENU GESTION TABLE VIDEO **********/\n");
			System.out
					.println("Veuillez sélectionner une des options suivantes :");
			System.out.println("1- Lister le contenu de la table video");
			System.out.println("2- Insérer un nouvelle video");
			System.out.println("3- mettre à jour une video");
			System.out.println("4- Supprimer une video");
			System.out.println("5- Revenir au menu général");
			System.out.println("\nChoix : ");
			String choix = lireSaisie();
			try {
				chx = Integer.parseInt(choix);
			} catch (NumberFormatException e) {
				System.out.println("Veuillez saisir un chiffre svp.");
			}
		}
		traitementChoixGestionTableVideo(chx);
	}

	private static void traitementChoixGestionTableVideo(Integer chx) {
		switch (chx) {
		case 1:
			traitementAffichageListeVideos();
			break;
		case 2:
			traitementInsertionNewVideo();
			break;
		case 3:
			traitementMiseAjourVideo();
			break;
		case 4:
			traitementSuppressionVideo();
			break;
		case 5:
			performMenuPrincipal();
			break;
		}
	}

	private static void traitementSuppressionVideo() {
		System.out.println("/********** SUPPRESSION VIDEO **********/");
		System.out
				.print("\nVeuillez saisir le nom/titre de la nouvelle vidéo : ");
		String nom = lireSaisie();
		System.out.println("Veuillez sélectionner le genre de la vidéo :");
		System.out.println("1- " + Video.ACTION);
		System.out.println("2- " + Video.COMEDIE);
		System.out.println("3- " + Video.DESSINS_ANIME);
		System.out.println("4- " + Video.EAU_DE_ROSE);
		System.out.println("5- " + Video.FANTASTIQUE);
		System.out.println("6- " + Video.THRILLER);
		System.out.print("Choix : ");
		String choix = lireSaisie();
		Integer chx = null;
		try {
			chx = Integer.parseInt(choix);
		} catch (NumberFormatException e) {
			System.out.println("Veuillez saisir un chiffre svp.");
			traitementInsertionNewVideo();
		}
		if (chx > 0 && chx < 7) {
			String type = null;
			switch (chx) {
			case 1:
				type = Video.ACTION;
				break;
			case 2:
				type = Video.COMEDIE;
				break;
			case 3:
				type = Video.DESSINS_ANIME;
				break;
			case 4:
				type = Video.EAU_DE_ROSE;
				break;
			case 5:
				type = Video.FANTASTIQUE;
				break;
			case 6:
				type = Video.THRILLER;
				break;
			default:
				break;
			}
			System.out
					.println("Etes-vous sûr de vouloir supprimer cette personne ?");
			System.out.println("1- Oui\n2- Non");
			System.out.print("choix : ");
			choix = lireSaisie();
			chx = null;
			try {
				chx = Integer.parseInt(choix);
			} catch (NumberFormatException e1) {
				System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
				System.out.println("Veuillez saisir un chiffre.");
				traitementSuppressionVideo();
			}
			if (chx < 0 && chx > 2) {
				System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
				System.out
						.println("Veuillez saisir un chiffre compris entre 1 et 2.");
				traitementSuppressionVideo();
			} else {
				if (chx == 1) {
					Boolean result = false;
					try {
						result = service.deleteVideo(new Video(null, nom, null,
								type, null, null, null, null));
					} catch (TechniqueException e) {
						System.out.println("\n/!!!!!!!!!! ERRROR !!!!!!!!!!/");
						System.out
								.println("Une erreur technique est survenue.");
						lireSaisie();
						traitementGestionTableVideo();
					} catch (MetierException e) {
						System.out.println(e.getMessage());
						lireSaisie();
						traitementGestionTableVideo();
					}
					if (result) {
						System.out
								.println("\n/********** SUCCESS **********/\nLa vidéo a était supprimée.");
						lireSaisie();
						traitementGestionTableVideo();
					}
				} else {
					traitementGestionTableVideo();
				}
			}
		} else {
			System.out.println("Veuillez saisir un chiffre entre 1 et 6.");
			traitementSuppressionVideo();
		}
	}

	private static void traitementMiseAjourVideo() {
		System.out.println("/********** MISE A JOUR VIDEO **********/");
		System.out.print("Veuillez saisir le code de la vidéo : ");
		String code = lireSaisie();
		System.out.print("\nVeuillez saisir le nom/titre de la vidéo : ");
		String nom = lireSaisie();
		System.out.print("\nVeuillez saisir la durée de la vidéo : ");
		String duree = lireSaisie();
		Integer dureeFilm = null;
		try {
			dureeFilm = Integer.parseInt(duree);
		} catch (NumberFormatException e1) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out.println("Veuillez saisir un chiffre.");
			traitementMiseAjourVideo();
		}
		System.out
				.print("\nVeuillez saisir le nombre de jours maximum de location de la vidéo : ");
		String nbJourMaxLocation = lireSaisie();
		Integer nbJourMax = null;
		try {
			nbJourMax = Integer.parseInt(nbJourMaxLocation);
		} catch (NumberFormatException e1) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out.println("Veuillez saisir un chiffre.");
			traitementMiseAjourVideo();
		}
		System.out
				.println("Est ce que la vidéo est disponible ?\n1- Oui\n2- Non");
		System.out.println("choix : ");
		String choix = lireSaisie();
		Integer chx = null;
		Boolean isDisponible = false;
		try {
			chx = Integer.parseInt(choix);
		} catch (NumberFormatException e1) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out.println("Veuillez saisir un chiffre.");
			traitementMiseAjourVideo();
		}
		if (chx < 0 && chx > 2) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out
					.println("Veuillez saisir un chiffre compris entre 1 et 2.");
			traitementMiseAjourVideo();
		} else {
			if (chx == 1) {
				isDisponible = true;
			}
		}
		System.out
				.print("\nVeuillez saisir la date de début de location(format jj-mm-aaaa) de la vidéo (facultatif) : ");
		String dateDebut = lireSaisie();
		System.out
				.print("\nVeuillez saisir la date de retour (format jj-mm-aaaa) de la vidéo (facultatif) : ");
		String dateRetour = lireSaisie();
		System.out.println("Veuillez sélectionner le genre de la vidéo :");
		System.out.println("1- " + Video.ACTION);
		System.out.println("2- " + Video.COMEDIE);
		System.out.println("3- " + Video.DESSINS_ANIME);
		System.out.println("4- " + Video.EAU_DE_ROSE);
		System.out.println("5- " + Video.FANTASTIQUE);
		System.out.println("6- " + Video.THRILLER);
		System.out.print("Choix : ");
		choix = lireSaisie();
		chx = null;
		try {
			chx = Integer.parseInt(choix);
		} catch (NumberFormatException e) {
			System.out.println("Veuillez saisir un chiffre svp.");
			traitementMiseAjourVideo();
		}
		if (chx > 0 && chx < 7) {
			String type = null;
			switch (chx) {
			case 1:
				type = Video.ACTION;
				break;
			case 2:
				type = Video.COMEDIE;
				break;
			case 3:
				type = Video.DESSINS_ANIME;
				break;
			case 4:
				type = Video.EAU_DE_ROSE;
				break;
			case 5:
				type = Video.FANTASTIQUE;
				break;
			case 6:
				type = Video.THRILLER;
				break;
			default:
				break;
			}
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date dateDeb = null;
			Date dateRet = null;
			if (dateDebut != null && !(dateDebut.isEmpty())) {
				try {
					dateDeb = format.parse(dateDebut);
				} catch (ParseException e2) {
					System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
					System.out
							.println("Veuillez saisir une date dans le format demandé : jj-mm-aaaa");
					traitementMiseAjourVideo();
				}
			}
			if (dateRetour != null && !(dateRetour.isEmpty())) {
				try {
					dateRet = format.parse(dateRetour);
				} catch (ParseException e2) {
					System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
					System.out
							.println("Veuillez saisir une date dans le format demandé : jj-mm-aaaa");
					traitementMiseAjourVideo();
				}
			}
			Boolean result = false;
			try {
				result = service.updateVideo(new Video(code, nom, dureeFilm,
						type, isDisponible, dateDeb, dateRet, nbJourMax));
			} catch (TechniqueException e) {
				System.out.println("\n/!!!!!!!!!! ERRROR !!!!!!!!!!/");
				System.out.println("Une erreur technique est survenue.");
				lireSaisie();
				traitementGestionTableVideo();
			} catch (MetierException e) {
				System.out.println(e.getMessage());
				lireSaisie();
				traitementGestionTableVideo();
			}
			if (result) {
				System.out
						.println("\n/********** SUCCESS **********/\nLa vidéo a était enregistrée.");
				lireSaisie();
				traitementGestionTableVideo();
			}
		} else {
			System.out.println("Veuillez saisir un chiffre entre 1 et 6.");
			traitementMiseAjourVideo();
		}
	}

	private static void traitementInsertionNewVideo() {
		System.out.println("/********** INSERTION NOUVELLE VIDEO **********/");
		System.out.print("Veuillez saisir le code de la nouvelle vidéo : ");
		String code = lireSaisie();
		System.out
				.print("\nVeuillez saisir le nom/titre de la nouvelle vidéo : ");
		String nom = lireSaisie();
		System.out
				.print("\nVeuillez saisir la durée de la nouvelle vidéo : ");
		String duree = lireSaisie();
		Integer dureeFilm = null;
		try {
			dureeFilm = Integer.parseInt(duree);
		} catch (NumberFormatException e1) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out.println("Veuillez saisir un chiffre.");
			traitementInsertionNewVideo();
		}
		System.out
				.print("\nVeuillez saisir le nombre de jours maximum de location de la nouvelle vidéo : ");
		String nbJourMaxLocation = lireSaisie();
		Integer nbJourMax = null;
		try {
			nbJourMax = Integer.parseInt(nbJourMaxLocation);
		} catch (NumberFormatException e1) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out.println("Veuillez saisir un chiffre.");
			traitementInsertionNewVideo();
		}
		System.out
				.println("Est ce que la vidéo est disponible ?\n1- Oui\n2- Non");
		System.out.println("choix : ");
		String choix = lireSaisie();
		Integer chx = null;
		Boolean isDisponible = false;
		try {
			chx = Integer.parseInt(choix);
		} catch (NumberFormatException e1) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out.println("Veuillez saisir un chiffre.");
			traitementInsertionNewVideo();
		}
		if (chx < 0 && chx > 2) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out
					.println("Veuillez saisir un chiffre compris entre 1 et 2.");
			traitementInsertionNewVideo();
		} else {
			if (chx == 1) {
				isDisponible = true;
			}
		}
		System.out
				.print("\nVeuillez saisir la date de début de location(format jj-mm-aaaa) de la nouvelle vidéo (facultatif) : ");
		String dateDebut = lireSaisie();
		System.out
				.print("\nVeuillez saisir la date de retour (format jj-mm-aaaa) de la nouvelle vidéo (facultatif) : ");
		String dateRetour = lireSaisie();
		System.out.println("Veuillez sélectionner le genre de la vidéo :");
		System.out.println("1- " + Video.ACTION);
		System.out.println("2- " + Video.COMEDIE);
		System.out.println("3- " + Video.DESSINS_ANIME);
		System.out.println("4- " + Video.EAU_DE_ROSE);
		System.out.println("5- " + Video.FANTASTIQUE);
		System.out.println("6- " + Video.THRILLER);
		System.out.print("Choix : ");
		choix = lireSaisie();
		chx = null;
		try {
			chx = Integer.parseInt(choix);
		} catch (NumberFormatException e) {
			System.out.println("Veuillez saisir un chiffre svp.");
			traitementInsertionNewVideo();
		}
		if (chx > 0 && chx < 7) {
			String type = null;
			switch (chx) {
			case 1:
				type = Video.ACTION;
				break;
			case 2:
				type = Video.COMEDIE;
				break;
			case 3:
				type = Video.DESSINS_ANIME;
				break;
			case 4:
				type = Video.EAU_DE_ROSE;
				break;
			case 5:
				type = Video.FANTASTIQUE;
				break;
			case 6:
				type = Video.THRILLER;
				break;
			default:
				break;
			}
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date dateDeb = null;
			Date dateRet = null;
			if (dateDebut != null && !(dateDebut.isEmpty())) {
				try {
					dateDeb = format.parse(dateDebut);
				} catch (ParseException e2) {
					System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
					System.out
							.println("Veuillez saisir une date dans le format demandé : jj-mm-aaaa");
					traitementInsertionNewVideo();
				}
			}
			if (dateRetour != null && !(dateRetour.isEmpty())) {
				try {
					dateRet = format.parse(dateRetour);
				} catch (ParseException e2) {
					System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
					System.out
							.println("Veuillez saisir une date dans le format demandé : jj-mm-aaaa");
					traitementInsertionNewVideo();
				}
			}
			Boolean result = false;
			try {
				result = service.insertVideo(new Video(code, nom, dureeFilm,
						type, isDisponible, dateDeb, dateRet, nbJourMax));
			} catch (TechniqueException e) {
				System.out.println("\n/!!!!!!!!!! ERRROR !!!!!!!!!!/");
				System.out.println("Une erreur technique est survenue.");
				lireSaisie();
				traitementGestionTableVideo();
			} catch (MetierException e) {
				System.out.println(e.getMessage());
				lireSaisie();
				traitementGestionTableVideo();
			}
			if (result) {
				System.out
						.println("\n/********** SUCCESS **********/\nLa vidéo a était enregistrée.");
				lireSaisie();
				traitementGestionTableVideo();
			}
		} else {
			System.out.println("Veuillez saisir un chiffre entre 1 et 6.");
			traitementGestionTableVideo();
		}
	}

	private static void traitementAffichageListeVideos() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		List<Video> result = service.selectVideo(null, null);
		System.out.println("Liste des videos :");
		if (result != null && !(result.isEmpty())) {
			for (Video video : result) {
				String ligne = "- " + video.getCode() + " ; "
						+ video.getGenre() + " ; " + video.getNom() + " ; ";
				ligne += "durée : " + video.getDureeFilm();
				ligne += " ; Nb jours location maxi : "
						+ video.getNbJourLocationAutorises();
				if (video.getDateDebutLocation() != null) {
					ligne += " ; Dernière date début location : "
							+ format.format(video.getDateDebutLocation());
				}
				if (video.getDateRetour() != null) {
					ligne += " ; Dernière date de retour : "
							+ format.format(video.getDateRetour());
				}
				ligne += " ; Disponible : " + video.getIsDisponible();
				System.out.println(ligne);
			}
			lireSaisie();
			traitementGestionTableVideo();
		} else {
			System.out.println("La liste des vidéos vide.");
			lireSaisie();
			traitementGestionTableVideo();
		}
	}

	private static void traitementListerClients() {
		System.out.println("\n/********** LISTE DES CLIENTS **********/");
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		List<Personne> result = service.selectListeClients();
		if (result != null && !(result.isEmpty())) {
			for (Personne personne : result) {
				System.out.println("- " + personne.getLogin() + " ; "
						+ personne.getNom() + " ; " + personne.getPrenom()
						+ " ; " + format.format(personne.getDateNaissance()));
			}
		} else {
			System.out.println("La liste des client est vide.");
		}
		lireSaisie();
		performMenuPrincipal();
	}

	private static void traitementListerVideosDisponibles() {
		System.out
				.println("\n/********** LISTE DES VIDEOS DISPONIBLES **********/");
		System.out.println("Revenir au menu :");
		System.out.println("1- Oui\n2- Non");
		String choix = lireSaisie();
		Integer chx = null;
		try {
			chx = Integer.parseInt(choix);
		} catch (NumberFormatException e) {
			System.out.println("Veuillez saisir un chiffre svp.");
			traitementListerVideosDisponibles();
		}
		if (chx > 0 && chx < 3) {
			if (chx == 1) {
				performMenuPrincipal();
			} else {
				System.out
						.println("Voulez vous rechercher un genre en particulier ?");
				System.out.println("1- Oui\n2- Non");
				System.out.print("choix : ");
				choix = lireSaisie();
				chx = null;
				try {
					chx = Integer.parseInt(choix);
				} catch (NumberFormatException e) {
					System.out.println("Veuillez saisir un chiffre svp.");
					traitementListerVideosDisponibles();
				}
				if (chx > 0 && chx < 3) {
					if (chx == 1) {
						System.out
								.println("Veuillez sélectionner le genre de la vidéo :");
						System.out.println("1- " + Video.ACTION);
						System.out.println("2- " + Video.COMEDIE);
						System.out.println("3- " + Video.DESSINS_ANIME);
						System.out.println("4- " + Video.EAU_DE_ROSE);
						System.out.println("5- " + Video.FANTASTIQUE);
						System.out.println("6- " + Video.THRILLER);
						System.out.print("Choix : ");
						choix = lireSaisie();
						chx = null;
						try {
							chx = Integer.parseInt(choix);
						} catch (NumberFormatException e) {
							System.out
									.println("Veuillez saisir un chiffre svp.");
							traitementListerVideosDisponibles();
						}
						if (chx > 0 && chx < 7) {
							String type = null;
							switch (chx) {
							case 1:
								type = Video.ACTION;
								break;
							case 2:
								type = Video.COMEDIE;
								break;
							case 3:
								type = Video.DESSINS_ANIME;
								break;
							case 4:
								type = Video.EAU_DE_ROSE;
								break;
							case 5:
								type = Video.FANTASTIQUE;
								break;
							case 6:
								type = Video.THRILLER;
								break;
							default:
								break;
							}
							List<Video> dispos = service
									.selectVideoDisponibles(type);
							if (dispos != null && !(dispos.isEmpty())) {
								for (Video video : dispos) {
									System.out
											.println("- "
													+ video.getCode()
													+ " ; "
													+ video.getGenre()
													+ " ; "
													+ video.getNom()
													+ " ; durée : "
													+ video.getDureeFilm()
													+ " ; Nb jour Max location autorisé : "
													+ video.getNbJourLocationAutorises());
								}
								lireSaisie();
								performMenuPrincipal();
							} else {
								System.out
										.println("Aucune vidéo n'est disponible.");
								lireSaisie();
								performMenuPrincipal();
							}
						}
					} else {
						List<Video> dispos = service
								.selectVideoDisponibles(null);
						if (dispos != null && !(dispos.isEmpty())) {
							for (Video video : dispos) {
								System.out
										.println("- "
												+ video.getCode()
												+ " ; "
												+ video.getGenre()
												+ " ; "
												+ video.getNom()
												+ " ; durée : "
												+ video.getDureeFilm()
												+ " ; Nb jour Max location autorisé : "
												+ video.getNbJourLocationAutorises());
							}
							lireSaisie();
							performMenuPrincipal();
						} else {
							System.out
									.println("Aucune vidéo n'est disponible.");
							lireSaisie();
							performMenuPrincipal();
						}
					}
				} else {
					System.out
							.println("Veuillez saisir un chiffre entre 1 et 2.");
					traitementListerVideosDisponibles();
				}
			}
		} else {
			System.out.println("Veuillez saisir un chiffre entre 1 et 2.");
			traitementListerVideosDisponibles();
		}
	}

	/* ********** RENDRE UNE VIDEO ********** */
	private static void traitementRendreVideo() {
		System.out.println("\n/********** RENDRE UNE VIDEO **********/");
		System.out.println("Revenir au menu :");
		System.out.println("1- Oui\n2- Non");
		String choix = lireSaisie();
		Integer chx = null;
		try {
			chx = Integer.parseInt(choix);
		} catch (NumberFormatException e) {
			System.out.println("Veuillez saisir un chiffre svp.");
			traitementRendreVideo();
		}
		if (chx > 0 && chx < 3) {
			if (chx == 1) {
				performMenuPrincipal();
			} else {
				System.out.print("Veuillez saisir le titre de la vidéo : ");
				String titre = lireSaisie();
				System.out
						.println("Veuillez sélectionner le genre de la vidéo :");
				System.out.println("1- " + Video.ACTION);
				System.out.println("2- " + Video.COMEDIE);
				System.out.println("3- " + Video.DESSINS_ANIME);
				System.out.println("4- " + Video.EAU_DE_ROSE);
				System.out.println("5- " + Video.FANTASTIQUE);
				System.out.println("6- " + Video.THRILLER);
				System.out.print("Choix : ");
				choix = lireSaisie();
				chx = null;
				try {
					chx = Integer.parseInt(choix);
				} catch (NumberFormatException e) {
					System.out.println("Veuillez saisir un chiffre svp.");
					traitementRendreVideo();
				}
				if (chx > 0 && chx < 7) {
					String type = null;
					switch (chx) {
					case 1:
						type = Video.ACTION;
						break;
					case 2:
						type = Video.COMEDIE;
						break;
					case 3:
						type = Video.DESSINS_ANIME;
						break;
					case 4:
						type = Video.EAU_DE_ROSE;
						break;
					case 5:
						type = Video.FANTASTIQUE;
						break;
					case 6:
						type = Video.THRILLER;
						break;
					default:
						break;
					}
					List<Video> videos = service.selectVideo(titre, type);
					if (videos != null && !(videos.isEmpty())) {
						Reponse reponse = null;
						try {
							reponse = service.rendreVideo(videos.get(0));
						} catch (TechniqueException e) {
							System.out
									.println("\n/!!!!!!!!!! ERRROR !!!!!!!!!!/");
							System.out
									.println("Une erreur technique est survenue.");
							lireSaisie();
							performMenuPrincipal();
						} catch (MetierException e) {
							System.out.println(e.getMessage());
							lireSaisie();
							performMenuPrincipal();
						}
						System.out.println(reponse.getMessage());
						lireSaisie();
						performMenuPrincipal();
					} else {
						System.out
								.println("La video que vous essayez de rendre n'existe pas.");
						lireSaisie();
						performMenuPrincipal();
					}

				} else {
					traitementRendreVideo();
				}
			}
		} else {
			System.out.println("Veuillez saisir un chiffre entre 1 et 2.");
			traitementRendreVideo();
		}
	}

	/* ********** GESTION TABLE PERSONNE ********** */
	private static void traitementGestionTablePersonne() {
		Integer chx = null;
		while (chx == null || !(chx > 0 && chx < 6)) {
			System.out
					.println("\n/********** MENU GESTION TABLE PERSONNE **********/\n");
			System.out
					.println("Veuillez sélectionner une des options suivantes :");
			System.out.println("1- Lister le contenu de la table personne");
			System.out.println("2- Insérer un nouvelle personne");
			System.out.println("3- mettre à jour une personne");
			System.out.println("4- Supprimer une personne");
			System.out.println("5- Revenir au menu général");
			System.out.println("\nChoix : ");
			String choix = lireSaisie();
			try {
				chx = Integer.parseInt(choix);
			} catch (NumberFormatException e) {
				System.out.println("Veuillez saisir un chiffre svp.");
			}
		}
		traitementChoixGestionTablePersonne(chx);

	}

	private static void traitementChoixGestionTablePersonne(Integer chx) {
		switch (chx) {
		case 1:
			traitementAffichageListePersonnes();
			break;
		case 2:
			traitementInsertionNewPersonne();
			break;
		case 3:
			traitementMiseAjourPersonne();
			break;
		case 4:
			traitementSuppressionPersonne();
			break;
		case 5:
			performMenuPrincipal();
			break;
		}
	}

	private static void traitementSuppressionPersonne() {
		System.out
				.println("/********** SUPPRESSION D'UNE PERSONNE **********/");
		System.out
				.println("Veuillez saisir le login de la personne à supprimer : ");
		String login = lireSaisie();
		System.out.println("\nVeuillez choisir le type de la personne : ");
		System.out.println("1- GERANT");
		System.out.println("2- CLIENT");
		String type = lireSaisie();
		Integer chx = null;
		try {
			chx = Integer.parseInt(type);
		} catch (NumberFormatException e1) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out.println("Veuillez saisir un chiffre.");
			traitementSuppressionPersonne();
		}
		if (chx < 0 && chx > 2) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out
					.println("Veuillez saisir un chiffre compris entre 1 et 2.");
			traitementSuppressionPersonne();
		} else {
			if (chx == 1) {
				type = Personne.TYPE_GERANT;
			} else {
				type = Personne.TYPE_CLIENT;
			}
		}
		System.out
				.println("Etes-vous sûr de vouloir supprimer cette personne ?");
		System.out.println("1- Oui\n2- Non");
		System.out.print("choix : ");
		String choix = lireSaisie();
		chx = null;
		try {
			chx = Integer.parseInt(choix);
		} catch (NumberFormatException e1) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out.println("Veuillez saisir un chiffre.");
			traitementSuppressionPersonne();
		}
		if (chx < 0 && chx > 2) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out
					.println("Veuillez saisir un chiffre compris entre 1 et 2.");
			traitementSuppressionPersonne();
		} else {
			if (chx == 1) {
				Boolean result = false;
				try {
					result = service.deletePersonne(new Personne(login, null,
							null, null, null, type));
				} catch (TechniqueException e) {
					System.out.println("\n/!!!!!!!!!! ERRROR !!!!!!!!!!/");
					System.out.println("Une erreur technique est survenue.");
					lireSaisie();
					traitementGestionTablePersonne();
				} catch (MetierException e) {
					System.out.println(e.getMessage());
					lireSaisie();
					traitementGestionTablePersonne();
				}
				if (result) {
					System.out
							.println("\n/********** SUCCESS **********/\nLa personne a était supprimée.");
					lireSaisie();
					traitementGestionTablePersonne();
				}
			} else {
				traitementGestionTablePersonne();
			}
		}
	}

	private static void traitementMiseAjourPersonne() {
		System.out
				.println("/********** MISE A JOUR D'UNE PERSONNE **********/");
		System.out.print("Veuillez saisir le login de la personne : ");
		String login = lireSaisie();
		System.out.print("\nVeuillez saisir le mot de passe de la personne : ");
		String password = lireSaisie();
		System.out.print("\nVeuillez saisir le nom de la personne : ");
		String nom = lireSaisie();
		System.out.print("\nVeuillez saisir le prenom de la personne : ");
		String prenom = lireSaisie();
		System.out
				.print("\nVeuillez saisir la date de naissance(format jj-mm-aaaa) de la personne : ");
		String date = lireSaisie();
		System.out.println("\nVeuillez choisir le type de la personne : ");
		System.out.println("1- GERANT");
		System.out.println("2- CLIENT");
		String type = lireSaisie();
		Integer chx = null;
		try {
			chx = Integer.parseInt(type);
		} catch (NumberFormatException e1) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out.println("Veuillez saisir un chiffre.");
			traitementMiseAjourPersonne();
		}
		if (chx < 0 && chx > 2) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out
					.println("Veuillez saisir un chiffre compris entre 1 et 2.");
			traitementMiseAjourPersonne();
		} else {
			if (chx == 1) {
				type = Personne.TYPE_GERANT;
			} else {
				type = Personne.TYPE_CLIENT;
			}
		}
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dateNaissance = null;
		try {
			dateNaissance = format.parse(date);
		} catch (ParseException e2) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out
					.println("Veuillez saisir une date dans le format demandé : jj-mm-aaaa");
			traitementMiseAjourPersonne();
		}
		Boolean result = false;
		try {
			result = service.updatePersonne(new Personne(login, password, nom,
					prenom, dateNaissance, type));
		} catch (TechniqueException e) {
			System.out.println("\n/!!!!!!!!!! ERRROR !!!!!!!!!!/");
			System.out.println("Une erreur technique est survenue.");
			lireSaisie();
			traitementGestionTablePersonne();
		} catch (MetierException e) {
			System.out.println(e.getMessage());
			lireSaisie();
			traitementGestionTablePersonne();
		}
		if (result) {
			System.out
					.println("\n/********** SUCCESS **********/\nLa personne a était enregistrée.");
			lireSaisie();
			traitementGestionTablePersonne();
		}
	}

	private static void traitementAffichageListePersonnes() {
		List<Personne> liste = service.selectPersonne(null, null);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println("Liste des personnes :");
		for (Personne personne : liste) {
			System.out.println("- " + personne.getType() + " ; "
					+ personne.getLogin() + " ; " + personne.getNom() + " ; "
					+ personne.getPrenom() + " ; "
					+ format.format(personne.getDateNaissance()));
		}
		lireSaisie();
		traitementGestionTablePersonne();
	}

	private static void traitementInsertionNewPersonne() {
		System.out
				.println("/********** INSERTION NOUVELLE PERSONNE **********/");
		System.out.print("Veuillez saisir le login de la nouvelle personne : ");
		String login = lireSaisie();
		System.out
				.print("\nVeuillez saisir le mot de passe de la nouvelle personne : ");
		String password = lireSaisie();
		System.out.print("\nVeuillez saisir le nom de la nouvelle personne : ");
		String nom = lireSaisie();
		System.out
				.print("\nVeuillez saisir le prenom de la nouvelle personne : ");
		String prenom = lireSaisie();
		System.out
				.print("\nVeuillez saisir la date de naissance(format jj-mm-aaaa) de la nouvelle personne : ");
		String date = lireSaisie();
		System.out
				.println("\nVeuillez choisir le type de la nouvelle personne : ");
		System.out.println("1- GERANT");
		System.out.println("2- CLIENT");
		String type = lireSaisie();
		Integer chx = null;
		try {
			chx = Integer.parseInt(type);
		} catch (NumberFormatException e1) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out.println("Veuillez saisir un chiffre.");
			traitementInsertionNewPersonne();
		}
		if (chx < 0 && chx > 2) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out
					.println("Veuillez saisir un chiffre compris entre 1 et 2.");
			traitementInsertionNewPersonne();
		} else {
			if (chx == 1) {
				type = Personne.TYPE_GERANT;
			} else {
				type = Personne.TYPE_CLIENT;
			}
		}
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date dateNaissance = null;
		try {
			dateNaissance = format.parse(date);
		} catch (ParseException e2) {
			System.out.println("\n/!!!!!!!!!! ERROR !!!!!!!!!!/");
			System.out
					.println("Veuillez saisir une date dans le format demandé : jj-mm-aaaa");
			traitementInsertionNewPersonne();
		}
		Boolean result = false;
		try {
			result = service.insertPersonne(new Personne(login, password, nom,
					prenom, dateNaissance, type));
		} catch (TechniqueException e) {
			System.out.println("\n/!!!!!!!!!! ERRROR !!!!!!!!!!/");
			System.out.println("Une erreur technique est survenue.");
			lireSaisie();
			traitementGestionTablePersonne();
		} catch (MetierException e) {
			System.out.println(e.getMessage());
			lireSaisie();
			traitementGestionTablePersonne();
		}
		if (result) {
			System.out
					.println("\n/********** SUCCESS **********/\nLa personne a était enregistrée.");
			lireSaisie();
			traitementGestionTablePersonne();
		}
	}

	/* ********** MENU PRINCPAL ********** */
	private static void affichageMenu() {
		Personne logged = (service.selectPersonne(Personne.TYPE_GERANT, login))
				.get(0);
		System.out.println("Bienvenue " + logged.getNom() + " "
				+ logged.getPrenom());
		System.out.println("\n\n/********** MENU **********/\n");
		System.out.println("Veuillez selectionner une operation :");
		System.out.println("1- Gestion de la table 'Personne'");
		System.out.println("2- Gestion de la table 'Video'");
		System.out.println("3- Voir la liste des clients");
		System.out.println("4- Voir la liste des vidéos disponibles");
		System.out.println("5- Rendre une vidéo");
		System.out.println("6- quitter l'application");
		System.out.println("\nChoix : ");
	}

	private static void affichageErreurConnection() {
		System.out
				.println("\n\n\n/!!!!!!!!!! ERROR CONNECTION !!!!!!!!!!/\n\n");
		System.out
				.println("Votre login/password n'est peut être pas correcte. Veuillez réessayer.\nSi cela ne fonctionne toujuors pas, vous n'êtes peut être pas enregistrer dans la base de données.");
	}

	private static String lireSaisie() {
		Reader reader = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(reader);
		try {
			return input.readLine();
		} catch (IOException e) {
			System.out.println("\n\n\n/!!!!!!!!!! ERROR !!!!!!!!!!/\n\n\n");
			System.out.println(e.getMessage());
			return null;
		}
	}

	private static void initAffichageConnection() {
		System.out.println("\n\n\n/********** MENU MAIN **********/\n\n\n");
		System.out
				.println("Veuillez vous identifier afin de vous connecter à l'application.\n\n\n");
		System.out.println("Login : ");
		login = lireSaisie();
		System.out.println("password : ");
		password = lireSaisie();

	}

	private static void init() {
		Calendar dateNaiss = Calendar.getInstance();
		dateNaiss.set(1990, 05, 18);
		base = new BDD(new Personne("tverhoken", "123456", "VERHOKEN",
				"Thomas", dateNaiss.getTime(), Personne.TYPE_GERANT));
		// Initialisation du service personne
		PersonneService personneService = new PersonneServiceImpl();
		PersonneDAO personneDao = new PersonneDAOImpl();
		((PersonneDAOImpl) personneDao).setBaseDeDonnees(base);
		((PersonneServiceImpl) personneService).setPersonneDao(personneDao);
		// Initialisation du service Video
		VideoService videoService = new VideoServiceImpl();
		VideoDAO videoDao = new VideoDAOImpl();
		((VideoDAOImpl) videoDao).setBaseDeDonnees(base);
		((VideoServiceImpl) videoService).setVideoDao(videoDao);
		// Initailisation du service principale
		service = new MainServiceImpl();
		((MainServiceImpl) service).setPersonneService(personneService);
		((MainServiceImpl) service).setVideoService(videoService);

	}
}

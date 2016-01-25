package gestionXML;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import observer.Observable;
import observer.Observer;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import vue.Fenetre;
import vue.PageConnection;

public class GestionXML implements IGestionXML, Observable {

	private org.jdom2.Document document;
	private Element racine;
	private String fichier = "Data.xml";
	public ArrayList<Observer> listObserver = new ArrayList<Observer>();

	public GestionXML() {
		new PageConnection(this);
	}

	public Element ouvrir(String fichier) {
		SAXBuilder sxb = new SAXBuilder();

		try {
			document = sxb.build(new File(fichier));
		} catch (Exception e) {
		}
		racine = document.getRootElement();

		return racine;
	}

	public void enregistreFichier(String fichier) throws Exception {
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		sortie.output(document, new FileOutputStream(fichier));
	}

	public void creerCompte(String pseudoCreation, String passwordCreation) {

		racine = ouvrir(fichier);
		Element utilisateur = new Element("utilisateur");
		racine.addContent(utilisateur);

		Element pseudo = new Element("pseudo");
		Element password = new Element("password");
		Element listMessage = new Element("liste_messages");
		Element listSuivie = new Element("liste_suivie");
		Element listAime = new Element("liste_aime");

		pseudo.setText(pseudoCreation);
		password.setText(passwordCreation);

		utilisateur.addContent(pseudo);
		utilisateur.addContent(password);
		utilisateur.addContent(listMessage);
		utilisateur.addContent(listSuivie);
		utilisateur.addContent(listAime);

		try {
			enregistreFichier(fichier);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Compte " + pseudoCreation + " créé");

		Fenetre f = new Fenetre(this, pseudoCreation);
		this.addObserver(f);
	}

	public void supprimerCompte(String pseudoSuppr) {
		racine = ouvrir(fichier);
		List<Element> listUtilisateurs = racine.getChildren("utilisateur");
		Iterator<Element> i = listUtilisateurs.iterator();
		// On parcours la liste grâce à un iterator
		while (i.hasNext()) {
			Element courant = (Element) i.next();
			if (courant.getChildText("pseudo") != null) {
				if (courant.getChildText("pseudo").toString()
						.equals(pseudoSuppr)
						&& courant.getChildText("pseudo") != "") {
					courant.removeContent();
				}

			}
		}

		try {
			enregistreFichier(fichier);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean existeDeja(String pseudo) {

		boolean existe = false;
		racine = ouvrir(fichier);
		List<Element> listUtilisateurs = racine.getChildren("utilisateur");
		Iterator<Element> i = listUtilisateurs.iterator();
		// On parcours la liste grâce à un iterator
		while (i.hasNext()) {
			Element courant = (Element) i.next();
			if (courant.getChildText("pseudo") != null) {
				if (courant.getChildText("pseudo").toString().equals(pseudo)
						&& courant.getChildText("pseudo") != "") {
					existe = true;
				}
			}
		}
		return existe;
	}

	public boolean connecterCompte(String pseudo, String password) {
		racine = ouvrir(fichier);
		boolean connection = false;
		List<Element> listUtilisateurs = racine.getChildren("utilisateur");
		Iterator<Element> i = listUtilisateurs.iterator(); // On parcours la liste grâce
													// à un iterator
		while (i.hasNext()) {
			Element courant = (Element) i.next();
			if (courant.getChildText("pseudo") != null) {
				if (courant.getChildText("pseudo").toString().equals(pseudo)
						&& courant.getChildText("password").toString()
								.equals(password)) {
					System.out.println(pseudo + " : connection réussie");
					connection = true;
				}
			}
		}
		if (!connection)
			System.out.println("Connection échouée");
		else {
			Fenetre f = new Fenetre(this, pseudo);
			this.addObserver(f);
			System.out.println("Le model observe bien " + pseudo);
		}

		return connection;
	}

	public void suivrePersonne(String pseudoUtilisateur, String pseudoSuivi) {

		racine = ouvrir(fichier);
		List<Element> listUtilisateurs = racine.getChildren("utilisateur");
		Iterator<Element> i = listUtilisateurs.iterator(); // On parcours la liste grâce
													// à un iterator
		while (i.hasNext()) {
			Element courant = (Element) i.next();
			if (courant.getChildText("pseudo") != null
					&& courant.getChildText("pseudo").toString()
							.equals(pseudoUtilisateur)) {

				Element listSuivie = courant.getChild("liste_suivie");
				Element personneSuivie = new Element("pseudo");
				personneSuivie.setText(pseudoSuivi);
				listSuivie.addContent(personneSuivie);

				System.out.println(pseudoUtilisateur + " suit désormai "
						+ pseudoSuivi);
			}
		}

		try {
			enregistreFichier(fichier);
		} catch (Exception e) {
			e.printStackTrace();
		}

		notifyObserverLike();

	}

	public void aimerMessage(int id, String pseudo) {

		racine = ouvrir(fichier);
		List<Element> listUtilisateurs = racine.getChildren("utilisateur");
		Iterator<Element> i = listUtilisateurs.iterator(); // On parcours la liste grâce
													// à un iterator
		while (i.hasNext()) {
			Element courant = (Element) i.next();
			String pseudoCourant = courant.getChildText("pseudo");
			System.out.println(pseudo);
			if (pseudoCourant != null) {
				if (pseudoCourant.equals(pseudo)) {
					Element listeAime = courant.getChild("liste_aime");
					Element idMessage = new Element("id");
					idMessage.setText(Integer.toString(id));
					listeAime.addContent(idMessage);
				}
				Element listMessages = courant.getChild("liste_messages");
				try {
					List<Element> messages = listMessages.getChildren();

					Iterator<Element> j = messages.iterator();

					while (j.hasNext()) {

						Element courantM = (Element) j.next();
						if (courantM.getChildText("id") != null) {
							if (Integer.parseInt(courantM.getChildText("id")) == id) {
								String nbr_likeStr = courantM
										.getChildText("nbr_like");
								int nbr_like = Integer.parseInt(nbr_likeStr);
								nbr_like += 1;
								nbr_likeStr = Integer.toString(nbr_like);
								Element likes = courantM.getChild("nbr_like");
								likes.setText(nbr_likeStr);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		try {
			enregistreFichier(fichier);
		} catch (Exception e) {
			e.printStackTrace();
		}

		notifyObserverLike();
	}

	public void supprimerMessage(int id) {

		racine = ouvrir(fichier);
		List<Element> listUtilisateurs = racine.getChildren("utilisateur");
		Iterator<Element> i = listUtilisateurs.iterator(); // On parcours la liste grâce
													// à un iterator
		while (i.hasNext()) {
			Element courant = (Element) i.next();
			Element listMessages = courant.getChild("liste_messages");
			if (listMessages != null) {
				List<Element> messages = listMessages.getChildren();

				Iterator<Element> j = messages.iterator();

				while (j.hasNext()) {

					Element courantM = (Element) j.next();
					try {
						if (courantM.getChildText("id") != null) {
							Integer.parseInt(courantM.getChildText("id"));
							if (Integer.parseInt(courantM.getChildText("id")) == id) {
								courantM.removeContent();
								System.out
										.println("Le message a bien été supprimé");

							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}
		try {
			enregistreFichier(fichier);
		} catch (Exception e) {
			e.printStackTrace();
		}

		notifyObserverLike();
	}

	public ArrayList<String> getListSuivie(String pseudo) {

		racine = ouvrir(fichier);
		ArrayList<String> listSuivie = new ArrayList<String>();
		List<Element> listUtilisateurs = racine.getChildren("utilisateur");
		Iterator<Element> i = listUtilisateurs.iterator();

		while (i.hasNext()) {

			Element courant = (Element) i.next();
			String pseudoCourant = courant.getChildText("pseudo");
			if (pseudoCourant != null) {
				if (pseudoCourant.equals(pseudo)) {
					Element personnesSuivies = courant.getChild("liste_suivie");
					try {
						List<Element> l = personnesSuivies.getChildren();
						for (int k = 0; k < l.size(); k++) {
							listSuivie.add(((Element) l.get(k)).getText());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		return listSuivie;
	}

	public ArrayList<String> getListUtilisateurs() {

		racine = ouvrir(fichier);
		ArrayList<String> listUtilisateurs = new ArrayList<String>();
		List<Element> listUtilisateursEl = racine.getChildren("utilisateur");
		Iterator<Element> i = listUtilisateursEl.iterator();

		while (i.hasNext()) {
			Element courant = (Element) i.next();
			if (courant.getChildText("pseudo") != null)
				listUtilisateurs.add(courant.getChild("pseudo").getText());
		}

		return listUtilisateurs;

	}

	public ArrayList<Message> getListMessages(String pseudo) {

		racine = ouvrir(fichier);
		ArrayList<Message> listMessages = new ArrayList<Message>();
		List<Element> listUtilisateurs = racine.getChildren("utilisateur");
		Iterator<Element> i = listUtilisateurs.iterator();

		while (i.hasNext()) {

			Element courant = (Element) i.next();
			String pseudoCourant = courant.getChildText("pseudo");
			if (pseudoCourant != null) {
				if (pseudoCourant.equals(pseudo)) {
					try {
						Element e = courant.getChild("liste_messages");
						List<Element> listMessage = e.getChildren("message");
						Iterator<Element> j = listMessage.iterator();

						while (j.hasNext()) {

							Element messageCourant = (Element) j.next();

							if (messageCourant.getChildText("id") != null) {
								String id = messageCourant.getChildText("id");
								String texte = messageCourant
										.getChildText("texte");
								String date = messageCourant
										.getChildText("date");
								String nbr_like = messageCourant
										.getChildText("nbr_like");
								// System.out.println(date);
								int idInt = Integer.parseInt(id);
								int nbr_likeInt = Integer.parseInt(nbr_like);

								Message message = new Message(texte, pseudo);
								message.setDate(date);
								message.setId(idInt);
								message.setLikes(nbr_likeInt);
								listMessages.add(message);
							}

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		return listMessages;
	}

	public void posterMessage(Message message) {

		int idMax = getIdMax();
		racine = ouvrir(fichier);
		List<Element> listUtilisateurs = racine.getChildren("utilisateur");
		Iterator<Element> i = listUtilisateurs.iterator();

		while (i.hasNext()) {

			Element courant = (Element) i.next();
			String pseudoCourant = courant.getChildText("pseudo");
			if (pseudoCourant != null) {
				if (pseudoCourant.equals(message.getPosteur())) {
					try {
						Element l = courant.getChild("liste_messages");

						Element m = new Element("message");

						Element id = new Element("id");
						Element texte = new Element("texte");
						Element date = new Element("date");
						Element nbr_likes = new Element("nbr_like");

						id.setText(Integer.toString(idMax + 1));
						texte.setText(message.getTexte());
						date.setText(message.getDate());
						nbr_likes.setText(Integer.toString(message.getLike()));

						m.addContent(id);
						m.addContent(texte);
						m.addContent(date);
						m.addContent(nbr_likes);

						l.addContent(m);

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}
		try {
			enregistreFichier(fichier);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erreur d'enregistrement");
		}

		notifyObserverLike();
	}

	public int getIdMax() {
		racine = ouvrir(fichier);
		int idMax = 0;

		List<Element> listUtilisateurs = racine.getChildren("utilisateur");
		Iterator<Element> i = listUtilisateurs.iterator();

		while (i.hasNext()) {

			Element courant = (Element) i.next();

			try {
				Element e = courant.getChild("liste_messages");
				if (e != null) {
					List<Element> listMessage = e.getChildren("message");
					Iterator<Element> j = listMessage.iterator();

					while (j.hasNext()) {
						Element messageCourant = (Element) j.next();
						if (messageCourant.getChildText("id") != null) {
							int id = Integer.parseInt(messageCourant
									.getChildText("id"));
							if (id > idMax)
								idMax = id;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		System.out.println(idMax);
		return idMax;
	}

	public void nePlusSuivre(String personne1, String personne2) {

		racine = ouvrir(fichier);
		List<Element> listUtilisateurs = racine.getChildren("utilisateur");
		Iterator<Element> i = listUtilisateurs.iterator(); // On parcours la liste grâce
													// à un iterator
		while (i.hasNext()) {
			Element courant = (Element) i.next();
			if (personne1.equals(courant.getChildText("pseudo"))) {
				Element listSuivie = courant.getChild("liste_suivie");
				if (listSuivie != null) {
					List<Element> suivies = listSuivie.getChildren();
					Iterator<Element> j = suivies.iterator();

					while (j.hasNext()) {
						Element personne = (Element) j.next();
						try {
							if (personne.getText() != null) {
								if (personne.getText().equals(personne2)) {

									personne.removeContent();
									System.out.println(personne1
											+ " ne suit plus " + personne2);
								}

							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}
			}
		}
		try {
			enregistreFichier(fichier);
		} catch (Exception e) {
			e.printStackTrace();
		}

		notifyObserverLike();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Message> messagesPersonnesSuivies(String pseudo) {

		racine = ouvrir(fichier);
		ArrayList<Message> m = new ArrayList<Message>();
		ArrayList<String> l = new ArrayList<String>();
		l = getListSuivie(pseudo);
		l.add(pseudo);
		for (int i = 0; i < l.size(); i++) {
			String personneSuivie = l.get(i);
			ArrayList<Message> messages = getListMessages(personneSuivie);
			for (int k = 0; k < messages.size(); k++)
				m.add(messages.get(k));
		}

		Collections.sort(m);
		return m;
	}

	public ArrayList<String> getListNonSuivie(String pseudo) {
		ArrayList<String> lSuivie = getListSuivie(pseudo);
		ArrayList<String> lUtilisateurs = getListUtilisateurs();
		ArrayList<String> lNonSuivie = new ArrayList<String>();

		Iterator<String> i = lUtilisateurs.iterator();

		while (i.hasNext()) {
			String courant = (String) i.next();
			if (!lSuivie.contains(courant) && !courant.equals(pseudo)) {
				lNonSuivie.add(courant);
				System.out.println(courant);
			}
		}

		return (lNonSuivie);
	}

	public boolean dejaAime(String pseudo, int id) {

		boolean dejaAime = false;

		racine = ouvrir(fichier);
		List<Element> listUtilisateurs = racine.getChildren("utilisateur");
		Iterator<Element> i = listUtilisateurs.iterator(); // On parcours la liste grâce
													// à un iterator
		while (i.hasNext()) {
			Element courant = (Element) i.next();
			if (pseudo.equals(courant.getChildText("pseudo"))) {
				Element listAime = courant.getChild("liste_aime");
				if (listAime != null) {
					List<Element> idMessages = listAime.getChildren();
					Iterator<Element> j = idMessages.iterator();

					while (j.hasNext()) {
						Element idMessage = (Element) j.next();
						try {
							if (idMessage.getText() != null) {
								if (idMessage.getText().equals(
										Integer.toString(id))) {
									dejaAime = true;
								}

							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}
			}
		}
		try {
			enregistreFichier(fichier);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(dejaAime);
		return dejaAime;
	}

	public void nePlusAimerMessage(int id, String pseudo) {

		racine = ouvrir(fichier);
		List<Element> listUtilisateurs = racine.getChildren("utilisateur");
		Iterator<Element> i = listUtilisateurs.iterator(); // On parcours la liste grâce
													// à un iterator
		while (i.hasNext()) {
			Element courant = (Element) i.next();
			String pseudoCourant = courant.getChildText("pseudo");
			if (pseudoCourant != null) {
				if (pseudoCourant.equals(pseudo)) {
					Element listeAime = courant.getChild("liste_aime");
					List<Element> listeId = listeAime.getChildren();
					Iterator<Element> k = listeId.iterator();
					while (k.hasNext()) {
						Element idCourant = (Element) k.next();
						if (idCourant.getText() != ""
								&& Integer.toString(id).equals(
										idCourant.getText())) {
							idCourant.removeContent();
						}
					}
				}
				Element listMessages = courant.getChild("liste_messages");
				try {
					List<Element> messages = listMessages.getChildren();

					Iterator<Element> j = messages.iterator();

					while (j.hasNext()) {

						Element courantM = (Element) j.next();
						if (courantM.getChildText("id") != null) {
							if (Integer.parseInt(courantM.getChildText("id")) == id) {
								String nbr_likeStr = courantM
										.getChildText("nbr_like");
								int nbr_like = Integer.parseInt(nbr_likeStr);
								nbr_like -= 1;
								nbr_likeStr = Integer.toString(nbr_like);
								Element likes = courantM.getChild("nbr_like");
								likes.setText(nbr_likeStr);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		try {
			enregistreFichier(fichier);
		} catch (Exception e) {
			e.printStackTrace();
		}

		notifyObserverLike();
	}

	// implémentation du pattern observer

	// ajouter un observeur au model
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	// demander à un observer de s'actualiser
	public void notifyObserverLike() {
		for (Observer obs : listObserver)
			obs.update();
	}

	// enlever tous les observers
	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}

}

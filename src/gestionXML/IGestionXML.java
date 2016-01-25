package gestionXML;

import java.util.ArrayList;

import org.jdom2.Element;

public interface IGestionXML{

	// ouvrir le fichier XML (pas utile pour vous)
	public Element ouvrir(String fichier);

	// enregistrer le JDOM dans le fichier XML (pas utile pour vous)
	public void enregistreFichier(String fichier) throws Exception;

	// créer un nouveau compte
	public void creerCompte(String pseudoCreation, String passwordCreation);

	// supprimer un compte existant
	public void supprimerCompte(String pseudoSuppr);

	// connecter un compte existant, renvoie true si le compte existe et a le
	// bon password, false sinon
	public boolean connecterCompte(String pseudo, String password);
	
	//vérifier si un pseudo existe déja dans la base de données XML, renvoie true si le pseudo existe
	public boolean existeDeja(String pseudo);

	// faire que pseudoUtilisateur suive pseudoSuivi
	public void suivrePersonne(String pseudoUtilisateur, String pseudoSuivi);

	// incrémenterle nombre de like d'un message
	public void aimerMessage(int id,String pseudo);
	
	//ne plus aimer un message
		public void nePlusAimerMessage(int id,String pseudo);

	// supprimer un message, ne marche que si le message a été posté par
	// l'utilisateur "pseudo"
	public void supprimerMessage(int id);

	// obtenir la liste des personnes que suit l'utilisateur "pseudo"
	public ArrayList<String> getListSuivie(String pseudo);

	// obtenir la list des utilisateurs de l'application
	public ArrayList<String> getListUtilisateurs();

	// Obtenir la liste des messages qu'a posté l'utilisateur pseudo (pas utile
	// pour vous)
	public ArrayList<Message> getListMessages(String pseudo);

	// ajoute un message au fichier XML. Pour créer un message, mettre par
	// exemple "posterMessage(new Message("Daho","Salut ça va ?"))
	public void posterMessage(Message message);

	// (pas utile pour vous)
	public int getIdMax();

	// faire personne1 ne suive plus personne2
	public void nePlusSuivre(String personne1, String personne2);

	// rendre la liste des messages de toutes les peronnes que l'utilisateur
	// suit
	public ArrayList<Message> messagesPersonnesSuivies(String pseudo);
	
	//voir la liste des personnes non suivies
	public ArrayList<String> getListNonSuivie(String pseudo); 

	//savoir si une personne a déja aimé un message
	public boolean dejaAime(String pseudo, int id);

}

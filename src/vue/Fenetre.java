package vue;

import gestionXML.GestionXML;
import gestionXML.Message;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import observer.Observer;

public class Fenetre extends JFrame implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	// Zone de post
	private ImagePanel zone_post = new ImagePanel(new ImageIcon(
			"bleuFonce.jpg").getImage());
	private JLabel label_post = new JLabel();
	private JTextField messageDefaut = new JTextField();
	private JButton poster = new JButton("Poster");

	// Zone d'affichage
	private ZoneAffichage zone_affichage = new ZoneAffichage();
	private JScrollPane zone_affichage_scroll = new JScrollPane(zone_affichage);

	// objets issus de la gestion xml
	private GestionXML g;
	private String pseudo;
	private ArrayList<String> listSuivie = new ArrayList<String>();
	private ArrayList<String> listNonSuivie = new ArrayList<String>();
	private ArrayList<Message> listMessages = new ArrayList<Message>();

	// Zone pour l'option "ne plus suivre"
	private ImagePanel ZoneSuivie = new ImagePanel(new ImageIcon(
			"grisClair.jpg").getImage());
	JButton[] BoutonsNePlusSuivre;

	// Zone pour l'option "suivre"
	private ImagePanel ZoneNonSuivie = new ImagePanel(new ImageIcon(
			"grisClair.jpg").getImage());
	JButton[] BoutonsSuivre;

	// Zone pour se déconnecter ou supprimer son compte
	private JPanel Zdeconnexion = new JPanel();
	private JButton deconnexion = new JButton("Déconnexion");
	private JButton suppression = new JButton("Supprimer mon compte");

	public Fenetre(GestionXML g, String pseudo) {
		this.g = g;
		this.pseudo = pseudo;
		this.listSuivie = g.getListSuivie(pseudo);
		this.listNonSuivie = g.getListNonSuivie(pseudo);
		this.listMessages = g.messagesPersonnesSuivies(pseudo);

		this.setTitle(pseudo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// permet de mettre l'objet au centre
		this.setLocationRelativeTo(null); 
		//mettre l'image en plein écran, s'adapte à la taille de l'écran
		this.setSize((int)getToolkit().getScreenSize().getWidth(), ((int)getToolkit().getScreenSize().getHeight() - 40));
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		afficherComposants();
		update();
		ecouteBoutons(this);

		this.setVisible(true);
		
	}

	public void afficherComposants() {

		// Positionnement des Jpanel dans le Jpanel contenu dans la JFrame
		this.getContentPane().add(zone_post, BorderLayout.NORTH);
		this.getContentPane().add(zone_affichage_scroll, BorderLayout.CENTER);
		this.getContentPane().add(ZoneNonSuivie, BorderLayout.EAST);
		this.getContentPane().add(ZoneSuivie, BorderLayout.WEST);

		zone_affichage_scroll.setHorizontalScrollBarPolicy(31);
		zone_affichage_scroll.setVerticalScrollBarPolicy(22);

		// Style de la zone de post
		zone_post.setPreferredSize(new Dimension(1250,100));
		Font police = new Font("Arial", Font.BOLD, 14);
		messageDefaut.setFont(police);
		messageDefaut.setPreferredSize(new Dimension(500, 30));

		// Création de la zone de post	
		JPanel vide1 = new JPanel();
		vide1.setPreferredSize(new Dimension(350, 30));
		vide1.setBackground(new Color(0, 0, 0, 0));
		zone_post.add(vide1, BorderLayout.EAST);

		zone_post.add(label_post);
		zone_post.add(messageDefaut);
		zone_post.add(poster);
		zone_post.setBackground(Color.BLUE);//Color(59,89,151));

		// création de la zone de suppression
		Zdeconnexion.setPreferredSize(new Dimension(170,80));
		deconnexion.setPreferredSize(new Dimension(170,30));
		suppression.setPreferredSize(new Dimension(170,30));
		Zdeconnexion.add(deconnexion);
		Zdeconnexion.add(suppression);
		Zdeconnexion.setBackground(new Color(0, 0, 0, 0));
		JPanel vide2 = new JPanel();
		vide2.setPreferredSize(new Dimension(this.getWidth()-1166,50));
		vide2.setBackground(new Color(0, 0, 0, 0));
		zone_post.add(vide2);

		zone_post.add(Zdeconnexion, BorderLayout.EAST);
	}

	public void update() {		
		zone_affichage.removeAll();
		ZoneSuivie.removeAll();
		ZoneNonSuivie.removeAll();
		this.getContentPane().repaint();
		
		
		this.listMessages = g.messagesPersonnesSuivies(pseudo);
		this.listSuivie = g.getListSuivie(pseudo);
		this.listNonSuivie = g.getListNonSuivie(pseudo);
		// Afficher les messages
		if (!listMessages.isEmpty())
			afficherMessages(listMessages);

		// Afficher les personnes suivies
		afficherListSuivie(listSuivie);
		// Afficher la liste d'utilisateurs
		afficherListNonSuivie(listNonSuivie);
	}

	public void afficherMessages(ArrayList<Message> listMessages) {

		int length = listMessages.size();// taille du tableau = nombre de
											// messages
		// Afficher selon l'ordre de publication du plus rÃˆcent au moins
		// rÃˆcent
		zone_affichage.add(Box.createVerticalStrut(10));
		if (length == 1) {
			ZoneMessage zm = new ZoneMessage(listMessages.get(0), g,
					pseudo);
			zm.setAlignmentX(Component.CENTER_ALIGNMENT);
			zone_affichage.add(zm, BorderLayout.AFTER_LINE_ENDS);
			zone_affichage.add(Box.createVerticalStrut(10));	
		} else {
			
			for (int inc = length - 1; inc >= 1; inc--) {
				ZoneMessage zm = new ZoneMessage(listMessages.get(inc), g,
						pseudo);
				zm.setAlignmentX(Component.CENTER_ALIGNMENT);
				zone_affichage.add(zm, BorderLayout.AFTER_LINE_ENDS);
				zone_affichage.add(Box.createVerticalStrut(10));			
			}
			ZoneMessage zm = new ZoneMessage(listMessages.get(0), g,
					pseudo);
			zm.setAlignmentX(Component.CENTER_ALIGNMENT);
			zone_affichage.add(zm, BorderLayout.AFTER_LINE_ENDS);
			zone_affichage.add(Box.createVerticalStrut(10));	
		}
		zone_affichage_scroll.setViewportView(zone_affichage);
		zone_affichage.validate();
		
		zone_affichage_scroll.validate();
	}

	public void afficherListSuivie(ArrayList<String> listSuivie) {
		
		JLabel nePlusSuivre = new JLabel("Ne plus suivre");
		nePlusSuivre.setPreferredSize(new Dimension(110, 30));
		nePlusSuivre.setFont(new Font("Arial", Font.BOLD, 14));
		ZoneSuivie.add(nePlusSuivre);

		BoutonsNePlusSuivre = new JButton[listSuivie.size()];
		ZoneSuivie.setPreferredSize(new Dimension(150, 800));

		Iterator<String> i = listSuivie.iterator();
		int k = 0;

		while (i.hasNext()) {
			String pseudoSuivi = (String) i.next();
			if (pseudoSuivi != "") {
				BoutonsNePlusSuivre[k] = new JButton(pseudoSuivi);
				BoutonsNePlusSuivre[k].setPreferredSize(new Dimension(100, 20));
				BoutonsNePlusSuivre[k].setName("" + pseudoSuivi);
				BoutonsNePlusSuivre[k].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton b = (JButton) e.getSource();
						g.nePlusSuivre(pseudo, b.getText());
					}
				});
				BoutonsNePlusSuivre[k].setFocusPainted(false);
				BoutonsNePlusSuivre[k].setMargin(new Insets(0, 0, 0, 0));
				BoutonsNePlusSuivre[k].setContentAreaFilled(false);
				BoutonsNePlusSuivre[k].setBorderPainted(false);
				BoutonsNePlusSuivre[k].setOpaque(false);
				BoutonsNePlusSuivre[k].setFont(new Font("Arial", Font.PLAIN, 14));
				ZoneSuivie.add(BoutonsNePlusSuivre[k]);
				k++;
			}
		}
		ZoneSuivie.validate();

	}

	public void afficherListNonSuivie(ArrayList<String> listNonSuivie) {

		JLabel Suivre = new JLabel("Suivre");
		Suivre.setPreferredSize(new Dimension(50, 30));
		Suivre.setFont(new Font("Arial", Font.BOLD, 14));
		ZoneNonSuivie.add(Suivre);

		BoutonsSuivre = new JButton[listNonSuivie.size()];
		// ZoneNonSuivie.setBackground(Color.red);
		ZoneNonSuivie.setPreferredSize(new Dimension(150, 600));

		Iterator<String> i = listNonSuivie.iterator();
		int k = 0;

		while (i.hasNext()) {
			String pseudoNonSuivi = (String) i.next();
			if (pseudoNonSuivi != "") {
				BoutonsSuivre[k] = new JButton(pseudoNonSuivi);
				BoutonsSuivre[k].setPreferredSize(new Dimension(100, 20));
				BoutonsSuivre[k].setName("" + pseudoNonSuivi);
				BoutonsSuivre[k].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton b = (JButton) e.getSource();
						g.suivrePersonne(pseudo, b.getText());
					}
				});
				BoutonsSuivre[k].setFocusPainted(false);
				BoutonsSuivre[k].setMargin(new Insets(0, 0, 0, 0));
				BoutonsSuivre[k].setContentAreaFilled(false);
				BoutonsSuivre[k].setBorderPainted(false);
				BoutonsSuivre[k].setOpaque(false);
				BoutonsSuivre[k].setFont(new Font("Arial", Font.PLAIN, 14));
				ZoneNonSuivie.add(BoutonsSuivre[k]);
				k++;
			}
		}
		ZoneNonSuivie.validate();
	}

	public void ecouteBoutons(Fenetre f) {

		// Ecoute du bouton poster
		poster.addActionListener(new TraiteBoutonPoster(g, pseudo,
				messageDefaut));

		// Ecoute de la touche entrer
		messageDefaut.addActionListener(new TraiteBoutonPoster(g, pseudo,
				messageDefaut));

		suppression
				.addActionListener(new TraiteBoutonSuppression(f, g, pseudo));

		deconnexion.addActionListener(new TraiteBoutonDeconnexion(f));

	}

	// suivre une personne
	public class TraiteBoutonSuivrePersonne implements ActionListener {
		String pseudoUtilisateur;
		String pseudoSuivi;
		private GestionXML g;

		TraiteBoutonSuivrePersonne(String pseudoUtilisateur,
				String pseudoSuivi, GestionXML g) {
			this.pseudoUtilisateur = pseudoUtilisateur;
			this.pseudoSuivi = pseudoSuivi;
			this.g = g;
		}

		public void actionPerformed(ActionEvent e) {
			// on envoie les données à la gestion xml pour qu'elle relie le
			// suiveur et le suivi
			g.suivrePersonne(pseudoUtilisateur, pseudoSuivi);
		}
	}

	// poster un message
	public class TraiteBoutonPoster implements ActionListener {
		private JTextField textField;
		private String pseudo;
		private GestionXML g;

		public TraiteBoutonPoster(GestionXML g, String pseudo,
				JTextField messageDefaut) {
			this.textField = messageDefaut;
			this.pseudo = pseudo;
			this.g = g;
		}

		public void actionPerformed(ActionEvent e) {
			// quand on clique sur le bouton "poster"
			// On limite la taille du texte
			if (textField.getText().length() < 75) {
				g.posterMessage(new Message(textField.getText(), pseudo));
				textField.setText("");
				textField.setBackground(Color.WHITE);
			}

			else {
				textField.setBackground(Color.RED);
				textField.setText("Votre message est trop long");
			}
		}
	}

	// se déconnecter
	public class TraiteBoutonDeconnexion implements ActionListener {
		private Fenetre f;

		TraiteBoutonDeconnexion(Fenetre f) {
			this.f = f;
		}

		public void actionPerformed(ActionEvent e) {
			f.setVisible(false);
		}
	}

	// supprimer son compte
	public class TraiteBoutonSuppression implements ActionListener {
		private Fenetre f;
		private GestionXML g;
		private String pseudo;

		public TraiteBoutonSuppression(Fenetre f, GestionXML g, String pseudo) {
			this.f = f;
			this.g = g;
			this.pseudo = pseudo;
		}

		public void actionPerformed(ActionEvent e) {
			g.supprimerCompte(pseudo);
			f.setVisible(false);
		}
	}
}

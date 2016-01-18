package vue;

import gestionXML.GestionXML;
import gestionXML.Message;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ZoneMessage extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton Like = new JButton(); 
	private JButton Supprimer = new JButton("Supprimer");

	public ZoneMessage(Message m, GestionXML g, String pseudo) {

		JPanel Pmessage = new JPanel();
		JPanel Pinfos = new JPanel();
		JPanel Paction = new JPanel();

		// Organisation du message
		JLabel message = new JLabel("<html><font color=rgb(59,81,151)><b>"
				+ m.getPosteur() + "</b></font> " + m.getTexte() + "</html>");
		Pmessage.setLayout(new BoxLayout(Pmessage, BoxLayout.LINE_AXIS));
		Pmessage.add(message);
		JLabel infos = new JLabel("<html><font color=rgb(145,145,145)>" + "Le "
				+ m.getDate() + "</font><font color=rgb(59,81,151)> "
				+ Integer.toString(m.getLike()) + " like</font></html>");
		infos.setFont(new Font("Arial", Font.PLAIN, 14));
		message.setFont(new Font("Arial", Font.PLAIN, 14));
		Pinfos.setLayout(new BoxLayout(Pinfos, BoxLayout.LINE_AXIS));
		Pinfos.add(infos);

		if (!g.dejaAime(pseudo, m.getId())) {
			Like.setText("J'aime");
			Like.addActionListener(new TraiteBoutonJaime(m.getId(), pseudo, g));
		} else {
			Like.setText("Je n'aime plus");
			Like.addActionListener(new TraiteBoutonJaimePlus(m.getId(), pseudo,
					g));
		}
		Like.setFocusPainted(false);
		Like.setMargin(new Insets(0, 0, 0, 0));
		Like.setContentAreaFilled(false);
		Like.setBorderPainted(false);
		Like.setOpaque(false);

		Supprimer.setFocusPainted(false);
		Supprimer.setMargin(new Insets(0, 0, 0, 0));
		Supprimer.setContentAreaFilled(false);
		Supprimer.setBorderPainted(false);
		Supprimer.setOpaque(false);

		Paction.setLayout(new BoxLayout(Paction, BoxLayout.LINE_AXIS));
		JPanel Pvide = new JPanel();
		Pvide.setPreferredSize(new Dimension(10, 5));
		Paction.add(Like);
		Paction.add(Pvide);
		if (pseudo.equals(m.getPosteur())) {
			Paction.add(Supprimer);
			Supprimer
					.addActionListener(new TraiteBoutonSupprimer(m.getId(), g));
		}

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(Pmessage);
		this.add(Pinfos);
		this.add(Paction);
		Pmessage.setAlignmentX(Component.LEFT_ALIGNMENT);
		Pinfos.setAlignmentX(Component.LEFT_ALIGNMENT);
		Paction.setAlignmentX(Component.LEFT_ALIGNMENT);

		// Style du message
		this.setPreferredSize(new Dimension(600, 70));
		this.setMaximumSize(this.getPreferredSize());
		this.setBorder(BorderFactory.createLineBorder(Color.gray));

	}

	// supprimer un message
	public class TraiteBoutonSupprimer implements ActionListener {

		private int idMessage;
		private GestionXML g;

		TraiteBoutonSupprimer(int id_message, GestionXML g) {
			this.idMessage = id_message;
			this.g = g;
		}

		public void actionPerformed(ActionEvent e) {
			// on incrémente les "jaime" en passant par la gestion xml
			g.supprimerMessage(idMessage);
		}
	}

	// aimer un message
	public class TraiteBoutonJaime implements ActionListener {

		private int idMessage;
		private GestionXML g;
		private String pseudo;

		TraiteBoutonJaime(int id_message, String pseudo, GestionXML g) {
			this.idMessage = id_message;
			this.g = g;
			this.pseudo = pseudo;
		}

		public void actionPerformed(ActionEvent e) {
			// on incrémente les "jaime" en passant par la gestion xml
			g.aimerMessage(idMessage, pseudo);
		}
	}

	// Ne plus aimer un message
	public class TraiteBoutonJaimePlus implements ActionListener {

		private int idMessage;
		private GestionXML g;
		private String pseudo;

		TraiteBoutonJaimePlus(int id_message, String pseudo, GestionXML g) {
			this.idMessage = id_message;
			this.g = g;
			this.pseudo = pseudo;
		}

		public void actionPerformed(ActionEvent e) {
			// on incrémente les "jaime" en passant par la gestion xml
			g.nePlusAimerMessage(idMessage, pseudo);
		}
	}

}

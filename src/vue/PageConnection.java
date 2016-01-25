package vue;

import gestionXML.GestionXML;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PageConnection {
	private JFrame f = new JFrame("ECL Twitter Page Connection");
	private ImagePanel p = new ImagePanel(new ImageIcon(
			"ecl.jpg").getImage());
	
	private JTextField nomConnection = new JTextField();
	private JTextField pseudoConnection = new JPasswordField();
	private JTextField nomCreation = new JTextField();
	private JTextField pseudoCreation = new JPasswordField();

	private JLabel nomConnectionLabel = new JLabel("NOM");
	private JLabel pseudoConnectionLabel = new JLabel("PASSWORD");
	private JLabel nomCreationLabel = new JLabel("NOM");
	private JLabel pseudoCreationLabel = new JLabel("PASSWORD");
	private JLabel creer = new JLabel("Créer un nouveau compte");
	private JLabel login = new JLabel("Login");
	private JLabel infoConnection = new JLabel("");
	private JLabel infoCreation = new JLabel("");

	private JButton creerButton = new JButton("Crée un nouveau compte");
	private JButton loginButton = new JButton("Login");
	

	public PageConnection(GestionXML g) {

		JPanel dx = new JPanel();
		JPanel sx = new JPanel();

		JPanel dxc = new JPanel();
		JPanel dxcn = new JPanel();
		JPanel dxcnn = new JPanel();
		JPanel dxcns = new JPanel();
		JPanel dxcs = new JPanel();
		JPanel dxcsn = new JPanel();
		JPanel dxcss = new JPanel();

		JPanel sxc = new JPanel();
		JPanel sxcn = new JPanel();
		JPanel sxcnn = new JPanel();
		JPanel sxcns = new JPanel();
		JPanel sxcs = new JPanel();
		JPanel sxcsn = new JPanel();
		JPanel sxcss = new JPanel();

		// jpanel deplace
		JPanel dxsur = new JPanel();
		JPanel sxsur = new JPanel();
		JPanel dxsx = new JPanel();
		JPanel sxsx = new JPanel();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(900, 600);
		f.setContentPane(p);

		// definition des polices
		Font police = new Font("Arial", Font.BOLD, 14);
		Font policebig = new Font("Arial", Font.BOLD, 16);
		Font policebigbig = new Font("Arial", Font.BOLD, 19);
		nomConnection.setFont(police);
		pseudoConnection.setFont(police);
		nomCreation.setFont(police);
		pseudoCreation.setFont(police);
		infoCreation.setFont(police);
		infoCreation.setForeground(Color.RED);
		infoConnection.setFont(police);
		infoConnection.setForeground(Color.RED);
		nomConnectionLabel.setFont(policebig);
		pseudoConnectionLabel.setFont(policebig);
		nomCreationLabel.setFont(policebig);
		pseudoCreationLabel.setFont(policebig);
		creerButton.setFont(police);
		loginButton.setFont(police);
		login.setFont(policebigbig);
		creer.setFont(policebigbig);
		login.setForeground(new Color(0, 0, 255));
		creer.setForeground(new Color(0, 0, 255));
		f.setFont(police);

		// definition des tailles
		nomConnectionLabel.setPreferredSize(new Dimension(250, 30));
		nomConnection.setPreferredSize(new Dimension(250, 30));
		pseudoConnection.setPreferredSize(new Dimension(250, 30));
		login.setPreferredSize(new Dimension(250, 30));
		pseudoConnectionLabel.setPreferredSize(new Dimension(250, 30));

		nomCreationLabel.setPreferredSize(new Dimension(250, 30));
		nomCreation.setPreferredSize(new Dimension(250, 30));
		pseudoCreation.setPreferredSize(new Dimension(250, 30));
		creer.setPreferredSize(new Dimension(250, 30));
		pseudoCreationLabel.setPreferredSize(new Dimension(250, 30));

		dx.setPreferredSize(new Dimension(430, 600));
		sx.setPreferredSize(new Dimension(430, 600));

		dxc.setPreferredSize(new Dimension(350, 400));
		dxsur.setPreferredSize(new Dimension(350, 100));
		dxcn.setPreferredSize(new Dimension(350, 175));
		dxcs.setPreferredSize(new Dimension(350, 200));

		dxcnn.setPreferredSize(new Dimension(350, 50));
		dxcns.setPreferredSize(new Dimension(350, 90));
		dxcsn.setPreferredSize(new Dimension(350, 90));
		dxcss.setPreferredSize(new Dimension(350, 50));

		sxc.setPreferredSize(new Dimension(350, 400));
		sxsur.setPreferredSize(new Dimension(350, 100));
		sxcn.setPreferredSize(new Dimension(350, 175));
		sxcs.setPreferredSize(new Dimension(350, 200));

		sxcnn.setPreferredSize(new Dimension(350, 50));
		sxcns.setPreferredSize(new Dimension(350, 90));
		sxcsn.setPreferredSize(new Dimension(350, 90));
		sxcss.setPreferredSize(new Dimension(350, 50));
		
		sxcnn.setBackground(new Color(0, 0, 0, 0));
		sxcns.setBackground(new Color(0, 0, 0, 0));
		sxcsn.setBackground(new Color(0, 0, 0, 0));
		sxcss.setBackground(new Color(0, 0, 0, 0));
		sxc.setBackground(new Color(0, 0, 0, 0));
		sxsur.setBackground(new Color(0, 0, 0, 0));
		sxcn.setBackground(new Color(0, 0, 0, 0));
		sxcs.setBackground(new Color(0, 0, 0, 0));
		sx.setBackground(new Color(0, 0, 0, 0));
		
		dxcnn.setBackground(new Color(0, 0, 0, 0));
		dxcns.setBackground(new Color(0, 0, 0, 0));
		dxcsn.setBackground(new Color(0, 0, 0, 0));
		dxcss.setBackground(new Color(0, 0, 0, 0));
		dxc.setBackground(new Color(0, 0, 0, 0));
		dxsur.setBackground(new Color(0, 0, 0, 0));
		dxcn.setBackground(new Color(0, 0, 0, 0));
		dxcs.setBackground(new Color(0, 0, 0, 0));
		dx.setBackground(new Color(0, 0, 0, 0));

		dxsx.setPreferredSize(new Dimension(170, 10));
		sxsx.setPreferredSize(new Dimension(30, 10));

		// definition des positionement jpanel

		p.setLayout(new BorderLayout());
		p.add(sx, BorderLayout.WEST);
		p.add(dx, BorderLayout.EAST);

		dx.add(dxsur, BorderLayout.NORTH);
		dx.add(dxc, BorderLayout.SOUTH);
		dxc.add(dxcn, BorderLayout.NORTH);
		dxc.add(dxcs, BorderLayout.SOUTH);

		dxcn.add(dxcnn, BorderLayout.NORTH);
		dxcn.add(dxcns, BorderLayout.SOUTH);
		dxcs.add(dxcsn, BorderLayout.NORTH);
		dxcs.add(dxcss, BorderLayout.SOUTH);

		sx.add(sxsur, BorderLayout.NORTH);
		sx.add(sxc, BorderLayout.SOUTH);
		sxc.add(sxcn, BorderLayout.NORTH);
		sxc.add(sxcs, BorderLayout.SOUTH);

		sxcn.add(sxcnn, BorderLayout.NORTH);
		sxcn.add(sxcns, BorderLayout.SOUTH);
		sxcs.add(sxcsn, BorderLayout.NORTH);
		sxcs.add(sxcss, BorderLayout.SOUTH);

		// definition des positionement truck dx

		dxcnn.add(login, BorderLayout.WEST);

		dxcns.add(nomConnectionLabel, BorderLayout.NORTH);
		dxcns.add(nomConnection, BorderLayout.SOUTH);

		dxcsn.add(pseudoConnectionLabel, BorderLayout.NORTH);
		dxcsn.add(pseudoConnection, BorderLayout.SOUTH);

		dxcss.add(dxsx, BorderLayout.WEST);
		dxcss.add(loginButton, BorderLayout.EAST);

		// definition des positionement truck sx

		sxcnn.add(creer, BorderLayout.NORTH);

		sxcns.add(nomCreationLabel, BorderLayout.NORTH);
		sxcns.add(nomCreation, BorderLayout.SOUTH);

		sxcsn.add(pseudoCreationLabel, BorderLayout.NORTH);
		sxcsn.add(pseudoCreation, BorderLayout.SOUTH);

		sxcss.add(sxsx, BorderLayout.WEST);
		sxcss.add(creerButton, BorderLayout.EAST);

		// de button
		loginButton.addActionListener(new Login(nomConnection,
				pseudoConnection, g));
		creerButton
				.addActionListener(new Créer(nomCreation, pseudoCreation, g));
	
		sxcs.add(infoCreation);
		dxcs.add(infoConnection);
		
		f.setVisible(true);
	}

	public class Créer implements ActionListener {
		JTextField nomCreation;
		JTextField passwordCreation;

		String nomCreationS;
		String passwordCreationS;

		GestionXML g;

		public Créer(JTextField nomCreation, JTextField passwordCreation,
				GestionXML g) {
			this.nomCreation = nomCreation;
			this.passwordCreation = passwordCreation;
			this.g = g;
		}

		public void actionPerformed(ActionEvent e) {

			nomCreationS = nomCreation.getText();
			passwordCreationS = passwordCreation.getText();
			p.repaint();

			if(g.existeDeja(nomCreationS)){
				infoCreation.setText("Ce pseudo n'est pas disponible");
				passwordCreation.setText("");
			}
		
			
			else if (nomCreationS.length() >= 3 && passwordCreationS.length() >= 3) {
				System.out.println(nomCreationS);
				g.creerCompte(nomCreationS, passwordCreationS);
				nomCreation.setText("");
				passwordCreation.setText("");
				infoCreation.setText("");
			}	
			
			else{
				infoCreation.setText("Pseudo ou password de moins de 3 caractères");
				pseudoCreation.setText("");
				passwordCreation.setText("");
			}
		}
	}


	public class Login implements ActionListener {
		JTextField nomConnection;
		JTextField passwordConnection;

		String nomConnectionS;
		String pseudoConnectionS;

		GestionXML g;
		

		public Login(JTextField nomConnection, JTextField passwordConnection,
				GestionXML g) {
			this.nomConnection = nomConnection;
			this.passwordConnection = passwordConnection;
			this.g = g;
		}

		public void actionPerformed(ActionEvent e) {

			nomConnectionS = nomConnection.getText();
			pseudoConnectionS = passwordConnection.getText();
			
			p.repaint();
						
			if (nomConnectionS != "" && pseudoConnectionS != "") {
				boolean connection = g.connecterCompte(nomConnectionS,
						pseudoConnectionS);
				if (!connection) {
					passwordConnection.setText("");
					infoConnection.setText("Login ou mot de passe incorrect");
				} else {
					pseudoConnection.setText("");
					passwordConnection.setText("");
					infoConnection.setText("");
					passwordConnection.setBackground(Color.WHITE);
				}
			}

		}

	}
}

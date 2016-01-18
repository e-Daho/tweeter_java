package vue;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
 
public class ImagePanel extends JPanel {
 
    private static final long serialVersionUID = 1L;
 
    /**
     * @param chemin d'accès vers l'image
     */
 
    private Image img;
     
    public ImagePanel(Image img){
        this.img = img;
    }
     
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
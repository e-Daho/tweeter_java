package vue;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

public class ZoneAffichage extends JPanel implements Scrollable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ZoneAffichage(){
	 this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	
	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return getPreferredSize();
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect,
			   int orientation, int direction) {
		return (orientation == SwingConstants.VERTICAL) 
			      ? visibleRect.height
			      : visibleRect.width;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		if (getParent() instanceof JViewport) {
		      return 
		       (((JViewport) getParent()).getHeight() > getPreferredSize().height);
		}
		   return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		 if (getParent() instanceof JViewport) {
		      return 
		       (((JViewport) getParent()).getWidth() > getPreferredSize().width);
		   }
		   return false;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect,
			   int orientation, int direction) {
		   return 10;
	}

}

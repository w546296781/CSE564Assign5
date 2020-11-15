import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class Canvas extends JPanel implements Observer{

    private Graphics2D g2;
	/**
	 * Create the panel.
	 */
	public Canvas() {
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		clear();
		drawPoint();
		drawLine();
	}
	

	
    public void clear() {
    	g2 = (Graphics2D) this.getGraphics();
        g2.setPaint(Color.white);
        g2.fillRect(0, 0, getSize().width, getSize().height);
    }
    
	public void drawPoint() {
		Repository repo = Repository.getInstance();
		List<int[]> data = repo.getData();
		g2 = (Graphics2D) this.getGraphics();
        g2.setPaint(Color.blue);
		for(int i = 0; i < data.size(); i++) {
			g2.fillOval(data.get(i)[0] - 3, data.get(i)[1] - 3, 6, 6);
		}
	}
    
    public void drawLine() {
    	
    }

}

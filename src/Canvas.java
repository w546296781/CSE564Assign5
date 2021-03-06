import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
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
		Repository repo = Repository.getInstance();
		if(repo.getIsNew() == true)
			clear();
		if(repo.getIsRun() == false)
			drawPoint();
		drawLine();
	}
	
    public void clear() {
    	g2 = (Graphics2D) this.getGraphics();
        g2.setPaint(Color.white);
        g2.fillRect(0, 0, getSize().width, getSize().height);
        drawPoint();
    }
    
	public void drawPoint() {
		Repository repo = Repository.getInstance();
		List<int[]> data = repo.getData();
		g2 = (Graphics2D) this.getGraphics();
		g2.setPaint(Color.blue);
		for(int[] i:data) {
			g2.fillOval(i[0] - 3, i[1] - 3, 6, 6);
		}
	}
    
    public void drawLine() {
    	Repository repo = Repository.getInstance();
		List<int[]> data = repo.getData();
    	HashMap<Integer,List> result = repo.getResult();
    	if(result.size() < 3)
    		return;
    	g2 = (Graphics2D) this.getGraphics();
        g2.setPaint(Color.green);
        List line = result.get(3);
        draw(g2, data, line);
        g2.setPaint(Color.pink);
        line = result.get(2);
        draw(g2, data, line);
        g2.setPaint(Color.red);
        line = result.get(1);
        draw(g2, data, line);
    }
    
    public void draw(Graphics2D g2, List<int[]> data, List line) {
    	for(int i = 0; i < line.size() - 1; i++) {
    		int x1 = data.get((int)line.get(i) - 1)[0];
    		int y1 = data.get((int)line.get(i) - 1)[1];
    		int x2 = data.get((int)line.get(i + 1) - 1)[0];
    		int y2 = data.get((int)line.get(i + 1) - 1)[1];
    		g2.drawLine(x1, y1, x2, y2);
    	}
    }
}

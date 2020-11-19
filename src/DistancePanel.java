import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DistancePanel extends JPanel implements Observer{
	private JLabel redlabel, pinklabel, greenlabel;

	public DistancePanel() {
		JLabel lblNewLabel = new JLabel("Shortest distance\uFF1A");
		lblNewLabel.setBounds(10, 15, 120, 15);
		this.add(lblNewLabel);
		
		JPanel redblock = new JPanel();
		redblock.setBackground(Color.RED);
		redblock.setBounds(10, 45, 18, 17);
		this.add(redblock);
		
		JPanel pinkblock = new JPanel();
		pinkblock.setBackground(Color.PINK);
		pinkblock.setBounds(188, 45, 18, 17);
		this.add(pinkblock);
		
		JPanel greenblock = new JPanel();
		greenblock.setBackground(Color.GREEN);
		greenblock.setBounds(353, 45, 18, 17);
		this.add(greenblock);
		
		redlabel = new JLabel("0");
		redlabel.setBounds(38, 47, 80, 15);
		this.add(redlabel);
		
		pinklabel = new JLabel("0");
		pinklabel.setBounds(216, 47, 80, 15);
		this.add(pinklabel);
		
		greenlabel = new JLabel("0");
		greenlabel.setBounds(381, 47, 80, 15);
		this.add(greenlabel);
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		redlabel.setText("0");
		pinklabel.setText("0");
		greenlabel.setText("0");
		Repository repo = Repository.getInstance();
		HashMap<Integer,Double> distance = repo.getDistance();
		List<Integer> shortestIndex = repo.getShortestIndex();
		for(int i = 0; i < shortestIndex.size(); i++) {
			if(i == 0) {
				redlabel.setText("" + distance.get(shortestIndex.get(i)));
			}
			if(i == 1) {
				pinklabel.setText("" + distance.get(shortestIndex.get(i)));
			}
			if(i == 2) {
				greenlabel.setText("" + distance.get(shortestIndex.get(i)));
			}
		}
	}

}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;

public class Reporter implements MouseListener{
	private JFileChooser jfilechooser = new JFileChooser("."); 

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		Repository.getInstance().addPoint(x, y);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public ActionListener openItemListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a = jfilechooser.showOpenDialog(null); 
				if(a == JFileChooser.APPROVE_OPTION){
					String filePath = jfilechooser.getSelectedFile().getPath();
					System.out.println(filePath);
				}
			}
		};
	}
	
	public ActionListener saveItemListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a = jfilechooser.showOpenDialog(null); 
				if(a == JFileChooser.APPROVE_OPTION){
					String filePath = jfilechooser.getSelectedFile().getPath();
					System.out.println(filePath);
				}
			}
		};
	}
	
	public ActionListener newItemListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Repository.getInstance().clearData();
			}
		};
	}
		
	public ActionListener runItemListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		};
	}
			
	public ActionListener stopItemListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						
			}
		};
	}
	
	public ActionListener authosrsItemListener(Main mainFrame, JMenu fatherMenu) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	            JDialog dialog = new JDialog(mainFrame, "Authors");
	            JLabel label = new JLabel("Authors: Xinkai Wang, Shih-Yu Chang");
	            dialog.setLocationRelativeTo(fatherMenu);
	            dialog.add(label);
	            dialog.setSize(300,150);
	            dialog.setVisible(true);
			}
		};
	}

}

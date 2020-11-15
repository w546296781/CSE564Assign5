import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class Reporter implements MouseListener{
	private JFileChooser jfilechooser = new JFileChooser("."); 

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		System.out.println("x=" + x + "y=" + y);
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

}

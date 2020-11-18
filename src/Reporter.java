import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;

public class Reporter implements MouseListener{
	private JFileChooser jfilechooser = new JFileChooser("."); 
	private boolean isRun;
	private Pauser pauser;
	private Thread control;

	public Reporter() {
		isRun = false;
	}
	
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
					Repository.getInstance().openFile(filePath);
					//System.out.println(filePath);
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
					Repository.getInstance().saveFile(filePath);
					//System.out.println(filePath);
				}
			}
		};
	}
	
	public ActionListener newItemListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pauser.pause();
				isRun = false;
				Repository.getInstance().clearData();
			}
		};
	}
		
	public ActionListener runItemListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isRun == false) {
					isRun = true;
					pauser = new Pauser();
					int ti = 1;
					int threadHold = (int)Math.ceil(Repository.getInstance().getData().size()/10.0);
					List<Integer> templ;
					for(int i = 0; i < 10; i++) {
						templ = new ArrayList<Integer>();
						int tempIndex = 0;
						while(ti <= Repository.getInstance().getData().size()) {
							templ.add(ti);
							ti++;
							tempIndex++;
							if(tempIndex == threadHold) {
								break;
							}
						}
						Thread temp= new Thread(new TspShortest(templ,pauser));
				        temp.start();
					}
					control = new Thread(new RepositoryControl(pauser));
					control.start();
				}
				else {
					pauser.resume();
				}
			}
		};
	}
			
	public ActionListener stopItemListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isRun = false;
				pauser.pause();
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

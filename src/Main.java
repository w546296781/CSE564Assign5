import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JMenuItem;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 48, 500, 500);
		contentPane.add(panel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(4, 0, 500, 40);
		contentPane.add(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		fileMenu.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(fileMenu);
		fileMenu.setPreferredSize(new Dimension(100, 40));
		
		JMenuItem openItem = new JMenuItem("Open");
		fileMenu.add(openItem);
		
		JMenuItem saveItem = new JMenuItem("Save");
		fileMenu.add(saveItem);
		
		JMenu projectMenu = new JMenu("Project");
		projectMenu.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		menuBar.add(projectMenu);
		projectMenu.setPreferredSize(new Dimension(100, 40));
		
		JMenu aboutMenu = new JMenu("About");
		aboutMenu.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		menuBar.add(aboutMenu);
		aboutMenu.setPreferredSize(new Dimension(100, 40));
	}
}

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
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
		setBounds(100, 100, 525, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DistancePanel distancePanel = new DistancePanel();
		distancePanel.setBackground(Color.WHITE);
		distancePanel.setBounds(5, 40, 500, 81);
		contentPane.add(distancePanel);
		distancePanel.setLayout(null);
		
		Canvas canvas = new Canvas();
		Reporter reporter = new Reporter();
		canvas.setBounds(5, 122, 500, 500);
		canvas.setBackground(Color.WHITE);
		canvas.addMouseListener(reporter);
		contentPane.add(canvas);
		
		createMenu(reporter);
		
		Repository.getInstance().addObserver(canvas);
		Repository.getInstance().addObserver(distancePanel);
	}
	
	private void createMenu(Reporter reporter) {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(4, 0, 500, 40);
		contentPane.add(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		fileMenu.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(fileMenu);
		fileMenu.setPreferredSize(new Dimension(100, 40));
		
		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(reporter.openItemListener());
		fileMenu.add(openItem);
		
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(reporter.saveItemListener());
		fileMenu.add(saveItem);
		
		JMenu projectMenu = new JMenu("Project");
		projectMenu.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		menuBar.add(projectMenu);
		projectMenu.setPreferredSize(new Dimension(100, 40));
		
		JMenuItem newItem = new JMenuItem("New");
		newItem.addActionListener(reporter.newItemListener());
		projectMenu.add(newItem);
		
		JMenuItem runItem = new JMenuItem("Run");
		runItem.addActionListener(reporter.runItemListener());
		projectMenu.add(runItem);
		
		JMenuItem stopItem = new JMenuItem("Stop");
		stopItem.addActionListener(reporter.stopItemListener());
		projectMenu.add(stopItem);
		
		JMenu aboutMenu = new JMenu("About");
		aboutMenu.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		menuBar.add(aboutMenu);
		aboutMenu.setPreferredSize(new Dimension(100, 40));
		
		JMenuItem authosrsItem = new JMenuItem("Authors");
		authosrsItem.addActionListener(reporter.authosrsItemListener(this, aboutMenu));
		aboutMenu.add(authosrsItem);
	}
}

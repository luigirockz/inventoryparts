import java.awt.Color;
import javax.swing.*;


public class View extends JFrame {
	private Model model;
	private JPanel partsFrame; 
	private JButton add, delete, details;
	private JList<Part> parts;
	private DefaultListModel<Part> dfm = new DefaultListModel<Part>();
	private int frameWidth = 310, frameHeight = 430;
	private JScrollPane partsPanel;
	public View(Model model) {
		super("Parts Inventory");
		this.model = model;
		this.setSize(frameWidth, frameHeight);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		partsFrame = new JPanel();
		setContentPane(partsFrame);
		partsFrame.setLayout(null);
		//Three Buttons
		add = new JButton("Add Part");
		add.setBounds(5, 310, 90, 30);
		partsFrame.add(add);
		delete = new JButton("Delete Part");
		delete.setBounds(96, 310, 100, 30);
		partsFrame.add(delete);
		details = new JButton("Details");
		details.setBounds(197, 310, 90, 30);
		partsFrame.add(details);
		partsPanel = new JScrollPane();
		partsPanel.setBounds(0, 0, partsFrame.getWidth(), partsFrame.getHeight()-100);


		for (Part prt : model.getParts()) {// Add Part to Jlist
			dfm.addElement(prt);
		}
		parts = new JList<Part>(dfm);
		parts.setBackground(Color.WHITE);
		parts.setBounds(0, 0, partsPanel.getWidth(), partsPanel.getHeight());
		parts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		partsPanel.setViewportView(parts);

		partsFrame.add(partsPanel);
		repaint();
	}
	
	public void register(Controller controller) {// registers the controller
		add.addActionListener(controller);
		delete.addActionListener(controller);
		details.addActionListener(controller);
		parts.addListSelectionListener(controller);
	}
	public void update() {// Refreshes the View when new part is added
		dfm.removeAllElements();
		for (Part p : model.getParts()) {
			dfm.addElement(p);
		}
		parts.setModel(dfm);
		parts.setBounds(0, 0, partsPanel.getWidth(), partsPanel.getHeight());
		partsPanel.setViewportView(parts);
	}

	public void disableView() {// Turns of View
		details.setEnabled(false);
	}
	public void enableView() {//Turns on View
		details.setEnabled(true);
	}
}
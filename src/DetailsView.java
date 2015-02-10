import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;

public class DetailsView extends JFrame {
	private JPanel partFrame;
	private JButton  pSave, cancel, edit, save;
	private JLabel partName, partNumb, partVendor, partQuantity;
	private JTextField nameField, numbField, vendorField, quantityField;
	public DetailsView(String title) {
		
		super(title);
		this.setSize(350, 250);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		partFrame = new JPanel();
		setContentPane(partFrame);
		partFrame.setLayout(null);
		
		partName = new JLabel("Name");
		partName.setBounds(10, 15, 150, 30);
		partFrame.add(partName);
		
		partNumb = new JLabel("Part Number");
		partNumb.setBounds(10, 45, 150, 30);
		partFrame.add(partNumb);
		
		partVendor = new JLabel("Vendor");
		partVendor.setBounds(10, 75, 150, 30);
		partFrame.add(partVendor);
		
		partQuantity = new JLabel("Quantity");
		partQuantity.setBounds(10, 105, 150, 30);
		partFrame.add(partQuantity);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(225, 150, 75, 25);
		partFrame.add(cancel);
		
		pSave = new JButton("Save Part");
		pSave.setBounds(135, 150, 90, 25);
		partFrame.add(pSave);
		
		edit = new JButton("Edit");
		edit.setBounds(155, 150, 70, 25);
		partFrame.add(edit);
		
		save = new JButton("Save");
		save.setBounds(155, 150, 70, 25);
		partFrame.add(save);
		
		nameField = new JTextField();
		nameField.setBounds(100, 20, 200, 20);
		nameField.setDisabledTextColor(Color.GRAY);
		partFrame.add(nameField);
		
		numbField = new JTextField();
		numbField.setBounds(100, 50, 200, 20);
		numbField.setDisabledTextColor(Color.GRAY);
		partFrame.add(numbField);
		
		vendorField = new JTextField();
		vendorField.setBounds(100, 80, 200, 20);
		vendorField.setDisabledTextColor(Color.GRAY);
		partFrame.add(vendorField);
		
		quantityField = new JTextField();
		quantityField.setBounds(100, 110, 200, 20);
		quantityField.setDisabledTextColor(Color.GRAY);
		partFrame.add(quantityField);
	}
	public void register(Controller controller) {
		pSave.addActionListener(controller);
		cancel.addActionListener(controller);
		edit.addActionListener(controller);
		save.addActionListener(controller);
	}
	public String getName() {
		return nameField.getText();
	}
	public String getNumber() {
		return numbField.getText();
	}
	public String getVendor() {
		return vendorField.getText();
	}
	public Integer getQuantity() throws NumberFormatException {
		Integer i = 0;
		try {
			i = Integer.parseInt(quantityField.getText().trim());
			return i;
		}
		catch (NumberFormatException nfe) {
			throw new NumberFormatException("Quantity can only be an integer.");
		}
	}
	public void invalidView(String error) {
		JOptionPane.showMessageDialog(null, error, "Invalid", JOptionPane.ERROR_MESSAGE);
		
	}
	public void setName(String name) {
		nameField.setText(name);
	}
	public void setNumber(String number) {
		numbField.setText(number);
	}
	public void setVendor(String vendor) {
		vendorField.setText(vendor);
	}
	public void setQuantity(long quantity) {
		quantityField.setText(String.valueOf(quantity));
	}
	public void hideEditButton() {
		edit.setVisible(false);
	}
	public void hideSaveButton() {
		save.setVisible(false);
	}
	public void disableEdit() {
		pSave.setVisible(false);
		save.setVisible(false);
		nameField.setEnabled(false);
		numbField.setEnabled(false);
		vendorField.setEnabled(false);
		quantityField.setEnabled(false);
	}
	public void enableEdit() {
		save.setVisible(true);
		pSave.setVisible(false);
		nameField.setEnabled(true);
		numbField.setEnabled(true);
		vendorField.setEnabled(true);
		quantityField.setEnabled(true);
	}
}
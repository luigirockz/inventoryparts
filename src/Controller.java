import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class Controller implements ActionListener, ListSelectionListener {
	private Model model;
	private View view;
	private boolean dViewOpen;
	private DetailsView dView;
	private List<Part> partsList = null;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		dViewOpen = false;
	}

	public void actionPerformed(ActionEvent e) throws NumberFormatException {
		String command = e.getActionCommand();
		e.paramString();

		if(command.equals("Add Part")){
			if (dViewOpen) {
				dView.dispose();
			}
			dView = new DetailsView("Add New Part");
			dView.register(this);
			dView.hideSaveButton();
			dView.hideEditButton();
			dViewOpen = true;
		}else if(command.equals("Delete Part")){
			if (partsList != null) {
				if (dViewOpen) {
					dView.dispose();
					dViewOpen = false;
				}
				for (Part p : partsList) {
					model.deletePart(p);
				}

				view.update();
				view.repaint();
			}
		}else if(command.equals("Details")){
			if (dViewOpen) {
				dView.dispose();
			}
			if (partsList != null) {

				view.disableView();
				for (Part p : partsList) {
					dView = new DetailsView("Details");
					dView.register(this);
					dView.disableEdit();
					dView.setName(p.getPartName());
					dView.setNumber(p.getPartNumber());
					dView.setVendor(p.getVendor());
					dView.setQuantity(p.getQuantity());
				}
				view.update();
				view.repaint();
				dViewOpen = true;
			}
		}else if(command.equals("Edit")){
			dView.enableEdit();
			dView.hideEditButton();
			dView.repaint();
		}else if(command.equals("Save")){
			if (partsList != null) {
				for (Part p : partsList) {
					try {
						Part prt = new Part(dView.getName(), dView.getNumber(), dView.getVendor(), dView.getQuantity());
						model.editPart(p, prt);
						dView.dispose();
						view.update();
						view.repaint();

					}  catch (IOException io) {
						dView.invalidView(io.getMessage());
					}
					catch (NumberFormatException n) {
						dView.invalidView(n.getMessage());

					} catch (Exception ex) {
						dView.invalidView(ex.getMessage());
					}
				}
			}
		}else if(command.equals("Save Part")){
			try {
				Part prt = new Part( dView.getName(), dView.getNumber(), dView.getVendor(), dView.getQuantity());
				model.addPart(prt);
				dView.dispose();
				view.update();
				view.repaint();
			}
			catch (NumberFormatException n) {
				dView.invalidView(n.getMessage());
			}
			catch (IOException ioex) {
				dView.invalidView(ioex.getMessage());
			}
			catch (Exception ex) {
				dView.invalidView(ex.getMessage());
			}
		}else if(command.equals("Cancel")){
			dView.dispose();
		}

	}


	@SuppressWarnings("unchecked")
	public void valueChanged(ListSelectionEvent e) {
		JList<Part> j;
		if (e.getSource() instanceof JList<?>) {
			j = (JList<Part>) e.getSource();
			if (j.getSelectedValue() != null) {
				partsList = j.getSelectedValuesList();
				view.enableView();
			}
		}
	}
}
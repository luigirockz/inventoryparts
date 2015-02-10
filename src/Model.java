import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class Model {
	private List<Part> parts;
	public Model() {
		parts = new ArrayList<Part>();
	}
	public void addPart(Part p) throws Exception {
		try {
			addPart( p.getPartName(), p.getPartNumber(), p.getVendor(), p.getQuantity());
		}
		catch (IOException e) {
			throw new IOException(e.getMessage());
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public void addPart( String partName, String partNumber, long quantity) throws Exception {
		try {
			addPart( partName, partNumber, "" , quantity);
		}
		catch (IOException e) {
			throw new IOException(e.getMessage());
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public void addPart( String partName, String partNumber, String vendor, long quantity) throws Exception, IOException {
		if (quantity <= 0) {
			throw new IOException("Parts can not have less than 1");
		}
		try {
			Part p = new Part( partName, partNumber, vendor, quantity);
			if (findPartName(p.getPartName()) != null) {
				throw new Exception("Part's Name needs to be unique");
			}
			parts.add(p);
		}
		catch (IOException e) {
			throw new IOException(e.getMessage());
		}
	}
	public void deletePart(Part p) {
		parts.remove(p); 
	}
	public void deletePart(String partName) {
		Part p = findPartName(partName);
		if (p != null) {
			parts.remove(p); 
		}
	}
	public void editPart(Part partOld, Part partNew) throws Exception {
		int index = parts.indexOf(partOld);
		if (!partOld.getPartName().equals(partNew.getPartName()) && findPartName(partNew.getPartName()) != null) {
			throw new Exception("Part's Name needs to be unique.");
		} else {
			parts.set(index, partNew);
		}
	}
	public void editPart(Part partOld, int newQuantity, String newName, String newPartNumber, String newVendor) throws Exception {
		int index = parts.indexOf(partOld);
		
		if (!partOld.getPartName().equals(newName) && findPartName(newName) != null) {

			throw new Exception("Part's Name needs to be unique.");
		} else {
			Part newPart = new Part(newName, newPartNumber, newVendor,  newQuantity);
			parts.set(index, newPart);
		}
	}
	public Part findPartName(String partName) {
		if (partName.length() > Part.getMaxPartNameLength()) {
			partName = partName.substring(0, Part.getMaxPartNameLength()); 
		}
		for (Part part : parts) { 
			if (part.getPartName().equals(partName)) {
				return part;
			}
		}
		return null;
	}
	public Part findPartNumber(String partNumber) {
		if (partNumber.length() > Part.getMaxPartNumberLength()) {
			partNumber = partNumber.substring(0, Part.getMaxPartNumberLength());
		}
		for (Part part : parts) {
			if (part.getPartNumber().equals(partNumber)) {
				return part;
			}
		}
		return null;
	}
	
	public int getSize() {
		return parts.size();
	}
	public List<Part> getParts() {
		return parts;
	}
	
}
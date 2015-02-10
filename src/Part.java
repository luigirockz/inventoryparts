
import java.io.IOException;
import java.util.Comparator;
public class Part  {

	private String partName = "";
	private String partNumber = "";
	private String vendor = "";
	private long quantity = 0;
	final private static int MAX_PNAME_LENGTH = 255;
	final private static int MAX_PNUMB_LENGTH = 20;
	final private static int MAX_PVENDOR_LENGTH = 255;
	public Part(String partName, String partNum, long quantity) throws IOException {
		try {
			setQuantity(quantity);
			setPartName(partName);
			setPartNumber(partNum);
		}
		catch (IOException e) {
			throw new IOException(e.getMessage());
		}
	}
	public Part( String partName, String partNum, String vendor, long quantity) throws IOException {
		this(partName, partNum, quantity);
		setVendor(vendor);
	}
	public Integer getQuantity() {
		return (int) this.quantity;
	}
	public String getPartName() {
		return this.partName;
	}
	public String getPartNumber() {
		return this.partNumber;
	}
	public String getVendor() {
		return this.vendor;
	}

	public String toString() {

		return getPartName(); 
	}


	private void setPartName(String partName) throws IOException {
		if (partName.length() > MAX_PNAME_LENGTH) {
			throw new IOException("Part name is too long (" + MAX_PNAME_LENGTH + " characters max).");
		}
		else if (partName.trim().length() == 0) {
			throw new IOException("Part name is required.");
		}
		else {
			this.partName = partName.trim();
		}
	}
	private void setPartNumber(String partNumber) throws IOException {
		if (partNumber.length() > MAX_PNUMB_LENGTH) {
			throw new IOException("Part number is too long (" + MAX_PNUMB_LENGTH + " integers max).");
		}
		else if (partNumber.trim().length() == 0) {
			throw new IOException("Part number is required.");
		}
		else {
			this.partNumber = partNumber.trim();
		}
	}
	private void setVendor(String vendor) throws IOException {
		if (vendor.length() > MAX_PVENDOR_LENGTH) {
			throw new IOException("Vendor name is too long (" + MAX_PVENDOR_LENGTH + " characters max).");
		}
		else {
			this.vendor = vendor.trim();
		}
	}
	private void setQuantity(long quantity) throws IOException {
		if (quantity < 0) {
			throw new IOException("Cannot Be Below 0");
		}
		else {
			this.quantity = quantity;
		}
	}
	public static int getMaxPartNameLength() {
		return MAX_PNAME_LENGTH;
	}
	public static int getMaxPartNumberLength() {
		return MAX_PNUMB_LENGTH;
	}
	public static int getMaxVendorLength() {
		return MAX_PVENDOR_LENGTH;
	}

}
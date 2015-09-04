package lordmastodon.miscal.util;

public class VersionNumber {
	
	public enum EnumVersionPriority {
		HIGH(0), MEDIUM(1), LOW(2);
		
		private int arrayNumber;
		
		private EnumVersionPriority(int arrayNumber) {
			this.arrayNumber = arrayNumber;
		}
		
		public int arrayNumber() {
			return arrayNumber;
		}
	}
	
	public enum EnumVersionType {
		BUG_FIX, NEW_CONTENT, OVERHAUL
	}
	
	private boolean available;
	private EnumVersionPriority priority;
	private EnumVersionType type;
	private int versionNumber;
	
	public VersionNumber(EnumVersionPriority priority, EnumVersionType versionType) {
		this.priority = priority;
		this.type = versionType;
	}
	
	public boolean available() {
		return available;
	}
	
	public int versionNumber() {
		return versionNumber;
	}
	
	public void setVersionNumber(int newVersionNumber) {
		versionNumber = newVersionNumber;
	}
	
	public void setAvailable(boolean newAvailable) {
		available = newAvailable;
	}
	
	public EnumVersionPriority priority() {
		return priority;
	}
	
	public EnumVersionType type() {
		return type;
	}

}

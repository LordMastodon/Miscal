package lordmastodon.miscal.util;

import java.util.Arrays;
import java.util.List;

import lordmastodon.miscal.util.VersionNumber.EnumVersionPriority;
import lordmastodon.miscal.util.VersionNumber.EnumVersionType;

public class Version {
	
	List<VersionNumber> versionNumbers = Arrays.asList(new VersionNumber(EnumVersionPriority.HIGH, EnumVersionType.OVERHAUL), new VersionNumber(EnumVersionPriority.MEDIUM, EnumVersionType.NEW_CONTENT), new VersionNumber(EnumVersionPriority.LOW, EnumVersionType.BUG_FIX));
	
	public Version() {
		
	}
	
	public void setVersionNumbers(int[] versionNums) {
		for (int i = 0; i < versionNumbers.size(); i++) {
			versionNumbers.get(i).setVersionNumber(versionNums[i]);
		}
	}
	
	public List<VersionNumber> versionNumbers() {
		return versionNumbers;
	}
	
	
	public boolean hasPriority(EnumVersionPriority priority) {		
		if (versionNumbers.get(priority.arrayNumber()).available()) {
			return true;
		} else {
			return false;
		}
	}
	
	public VersionNumber getVersionNumber(int i) {
		return versionNumbers.get(i);
	}
	
}
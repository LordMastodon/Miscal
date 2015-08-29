package lordmastodon.miscal.util;

public enum LightationOnOff {
	ON (true),
	OFF (false);
	
	private boolean onOff = false;
	
	private LightationOnOff(boolean onOff) { 
		this.onOff = onOff;
	}
	
	public boolean getOnOff() {
		return this.onOff;
	}
}

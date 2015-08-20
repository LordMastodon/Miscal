package lordmastodon.robotsculptures.proxy;

import lordmastodon.robotsculptures.block.RSBlocks;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		RSBlocks.registerRenders();
	}
	
}

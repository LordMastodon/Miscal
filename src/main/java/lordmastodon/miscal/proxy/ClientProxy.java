package lordmastodon.miscal.proxy;

import lordmastodon.miscal.block.MiscalBlocks;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		MiscalBlocks.registerRenders();
	}
	
}

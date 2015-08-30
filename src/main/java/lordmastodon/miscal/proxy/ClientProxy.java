package lordmastodon.miscal.proxy;

import lordmastodon.miscal.block.MiscalBlocks;
import lordmastodon.miscal.item.MiscalItems;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		MiscalBlocks.registerRenders();
		//MiscalItems.registerRenders();
	}
	
}

package lordmastodon.miscal.creativetab;

import lordmastodon.miscal.block.MiscalBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MiscalTab extends CreativeTabs {

	public MiscalTab(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(MiscalBlocks.ken_sculpture);
	}

}

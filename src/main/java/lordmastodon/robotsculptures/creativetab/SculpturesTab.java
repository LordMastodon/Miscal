package lordmastodon.robotsculptures.creativetab;

import lordmastodon.robotsculptures.block.RSBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SculpturesTab extends CreativeTabs {

	public SculpturesTab(String label) {
		super(label);
		
		this.setBackgroundImageName("sculptures.png");
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(RSBlocks.ken_sculpture);
	}

}

package lordmastodon.miscal.item;

import lordmastodon.miscal.Miscal;
import lordmastodon.miscal.enums.EnumGuiIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ConnectedTextureItem extends Item {
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World world, EntityPlayer playerIn) {
		playerIn.openGui(Miscal.instance, EnumGuiIds.CONNECTED_TEXTURE_ITEM_GUI.ordinal(), world, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
		
		return itemStackIn;
	}

}

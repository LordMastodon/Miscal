package lordmastodon.miscal.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ConnectedTextureItem extends Item {
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World world, EntityPlayer playerIn) {
		//playerIn.openGui(mod, modGuiId, world, playerIn.posX, playerIn.posY, playerIn.posZ);
		
		return itemStackIn;
	}

}

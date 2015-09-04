package lordmastodon.miscal.item;

import lordmastodon.miscal.Miscal;
import lordmastodon.miscal.constants.ItemConstants;
import lordmastodon.miscal.constants.ModConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MiscalItems {
	
	public static Item connected_texture_item;
	
	public static void init() {
		connected_texture_item = new ConnectedTextureItem().setUnlocalizedName(ItemConstants.CONNECTED_TEXTURE_ITEM_NAME).setCreativeTab(Miscal.modTab);
	}
	
	public static void register() {
		GameRegistry.registerItem(connected_texture_item, ItemConstants.CONNECTED_TEXTURE_ITEM_NAME);
	}
	
	public static void registerRenders() {
		registerRender(connected_texture_item);
	}
	
	public static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(ModConstants.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}

}

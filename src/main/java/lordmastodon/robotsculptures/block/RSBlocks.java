package lordmastodon.robotsculptures.block;

import lordmastodon.robotsculptures.RobotSculptures;
import lordmastodon.robotsculptures.blocks.KenSculptureBlock;
import lordmastodon.robotsculptures.constants.BlockConstants;
import lordmastodon.robotsculptures.constants.ModConstants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RSBlocks {
	
	public static Block ken_sculpture;
	
	public static void init() {
		ken_sculpture = new KenSculptureBlock(Material.iron).setUnlocalizedName(BlockConstants.KEN_SCULPTURE_UNLOCALIZED_NAME).setCreativeTab(RobotSculptures.modTab);
	}
	
	public static void register() {
		GameRegistry.registerBlock(ken_sculpture, BlockConstants.KEN_SCULPTURE_UNLOCALIZED_NAME);
	}
	
	public static void registerRenders() {
		registerRender(ken_sculpture);
	}
	
	public static void registerRender(Block block) {
		Item item = Item.getItemFromBlock(block);
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(ModConstants.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}

}

package lordmastodon.miscal.block;

import lordmastodon.miscal.Miscal;
import lordmastodon.miscal.blocks.KenSculptureBlock;
import lordmastodon.miscal.blocks.LightationBlock;
import lordmastodon.miscal.constants.BlockConstants;
import lordmastodon.miscal.constants.ModConstants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MiscalBlocks {

	public static Block ken_sculpture;
	public static Block lightation;
	
	public static void init() {
		ken_sculpture = new KenSculptureBlock(Material.iron).setUnlocalizedName(BlockConstants.KEN_SCULPTURE_UNLOCALIZED_NAME).setCreativeTab(Miscal.modTab);
		lightation = new LightationBlock().setUnlocalizedName(BlockConstants.LIGHTATION_UNLOCALIZED_NAME).setCreativeTab(Miscal.modTab);
	}
	
	public static void register() {
		GameRegistry.registerBlock(ken_sculpture, BlockConstants.KEN_SCULPTURE_UNLOCALIZED_NAME);
		GameRegistry.registerBlock(lightation, BlockConstants.LIGHTATION_UNLOCALIZED_NAME);
	}
	
	public static void registerRenders() {
		registerRender(ken_sculpture);
		registerRender(lightation);
	}
	
	public static void registerRender(Block block) {
		Item item = Item.getItemFromBlock(block);
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(ModConstants.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}

}

package lordmastodon.robotsculptures.block;

import java.util.Arrays;
import java.util.List;

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

	private static List<Block> blocks = Arrays.asList(
			ken_sculpture
	);
	
	public static Block getBlockByUnlocalizedNameForRecipe(String unlocalizedName) {
		boolean blockExists = false;
		int blockNumber = 0;
		
		for (int i = 0; i < blocks.size(); i++) {
			if (blocks.get(i).getUnlocalizedName().substring(5).equals(unlocalizedName)) {
				blockExists = true;
				blockNumber = i;
			}
		}
		
		if (blockExists) {
			return blocks.get(blockNumber);
		} else {
			throw new IllegalArgumentException("You've provided an incorrect unlocalized name for your recipe.");
		}
	}
	
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

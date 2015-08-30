package lordmastodon.miscal.crafting;

import lordmastodon.miscal.block.MiscalBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MiscalCraftingRecipes {
	
	public static void register() {
		//Ken Sculpture Recipe
		GameRegistry.addRecipe(new ItemStack(MiscalBlocks.ken_sculpture), 
				" I ",
				"III",
				"I I",
				'I', Items.iron_ingot);
		
		//Lightation Recipe
		GameRegistry.addRecipe(new ItemStack(MiscalBlocks.lightation),
				"G",
				'G', Blocks.glowstone);
	}

}

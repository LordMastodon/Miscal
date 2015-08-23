package lordmastodon.robotsculptures.crafting;

import lordmastodon.robotsculptures.block.RSBlocks;
import lordmastodon.robotsculptures.handler.ConfigurationHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RSCraftingRecipes {
	
	public static void register() {
		//Ken Sculpture Recipe
		GameRegistry.addShapedRecipe(new ItemStack(RSBlocks.ken_sculpture), 
				"ABC",
				"DEF",
				"GHI",
				'A', ConfigurationHandler.kenSculptureRecipeItems.get(0),
				'B', ConfigurationHandler.kenSculptureRecipeItems.get(1),
				'C', ConfigurationHandler.kenSculptureRecipeItems.get(2),
				'D', ConfigurationHandler.kenSculptureRecipeItems.get(3),
				'E', ConfigurationHandler.kenSculptureRecipeItems.get(4),
				'F', ConfigurationHandler.kenSculptureRecipeItems.get(5),
				'G', ConfigurationHandler.kenSculptureRecipeItems.get(6),
				'H', ConfigurationHandler.kenSculptureRecipeItems.get(7),
				'I', ConfigurationHandler.kenSculptureRecipeItems.get(8));
	}

}

package lordmastodon.robotsculptures.handler;

import java.util.ArrayList;
import java.util.List;

import lordmastodon.robotsculptures.block.RSBlocks;
import lordmastodon.robotsculptures.client.gui.RSGuiConfig;
import lordmastodon.robotsculptures.constants.ModConstants;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ConfigurationHandler {

	public static boolean kenSculptureExplosions = true;
	public static boolean kenSculptureFuse = true;
	public static int kenSculptureFuseLength = 60;
	public static float kenSculptureExplosionStrength = 3;
	
	public static List<Item> kenSculptureRecipeItems = new ArrayList<Item>(9);
	
	public static void init () {
		loadConfiguration();
	}
	
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(ModConstants.MOD_ID)) {
			loadConfiguration();
		}
	}
	
	private static void loadConfiguration() { 
		//KEN_SCULPTURE_CATEGORY
		kenSculptureExplosions = (Boolean) RSGuiConfig.kenSculptureExplosions.get();
		kenSculptureFuse = (Boolean) RSGuiConfig.kenSculptureFuse.get();
		kenSculptureFuseLength = (Integer) RSGuiConfig.kenSculptureFuseLength.get();
		kenSculptureExplosionStrength = (Float) RSGuiConfig.kenSculptureExplosionStrength.get();
		
		//KEN_SCULPTURE_RECIPE_CATEGORY
		for(int i = 0; i < kenSculptureRecipeItems.size(); i++) {
			Object[] list = RSGuiConfig.kenSculptureRecipeIngredients.getList();
			
			String listString = (String) list[i].toString();
			String modid;
			
			if (listString.indexOf(":") != -1) {
				modid = listString.substring(0, listString.indexOf(":"));
			} else {
				throw new IllegalArgumentException("You haven't provided a modid for your item.");
			}
			
			kenSculptureRecipeItems.set(i, GameRegistry.findItem(modid, list[i].toString()));
		}
	}
	
}

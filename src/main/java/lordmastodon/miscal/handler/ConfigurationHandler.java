package lordmastodon.miscal.handler;

import lordmastodon.miscal.client.gui.MiscalGuiConfig;
import lordmastodon.miscal.constants.ModConstants;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {

	public static boolean kenSculptureExplosions = true;
	public static boolean kenSculptureFuse = true;
	public static int kenSculptureFuseLength = 60;
	public static double kenSculptureExplosionStrength = 3;
	
	public static void init () {
		loadConfiguration();
	}
	
	public static void loadConfiguration() { 
		System.out.println("Loaded Configuration");
		
		//KEN_SCULPTURE_CATEGORY
		kenSculptureExplosions = (Boolean) MiscalGuiConfig.kenSculptureExplosions.get();
		kenSculptureFuse = (Boolean) MiscalGuiConfig.kenSculptureFuse.get();
		kenSculptureFuseLength = (Integer) MiscalGuiConfig.kenSculptureFuseLength.get();
		kenSculptureExplosionStrength = (Double) MiscalGuiConfig.kenSculptureExplosionStrength.get();
	}
	
}

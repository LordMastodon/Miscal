package lordmastodon.robotsculptures.handler;

import java.io.File;

import lordmastodon.robotsculptures.constants.ModConstants;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {
	
	public static Configuration config;

	public static boolean kenSculptureExplosions = true;
	public static boolean kenSculptureFuse = true;
	public static int kenSculptureFuseLength = 60;
	public static int kenSculptureExplosionStrength = 3;
	
	public static final String KEN_SCULPTURE_CATEGORY = "ken_sculpture";
	
	public static void init (File configFile) {
		if(config == null) {
			config = new Configuration(configFile);
			
			loadConfiguration();
		}
	}
	
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(ModConstants.MOD_ID)) {
			loadConfiguration();
		}
	}
	
	private static void loadConfiguration() {
		kenSculptureExplosions = config.get(KEN_SCULPTURE_CATEGORY, "kenSculptureExplosions", true, "Should the Sculpture of Ken the Mighty explode?").getBoolean(true);
		kenSculptureFuse = config.get(KEN_SCULPTURE_CATEGORY, "kenSculptureFuse", true, "Should the Sculpture of Ken the Mighty have a fuse?").getBoolean(true);
		kenSculptureFuseLength = config.get(KEN_SCULPTURE_CATEGORY, "kenSculptureFuseLength", 60, "How long should the fuse timer be before the Sculpture of Ken the Mighty explodes (in game ticks, one second = 20 ticks)?").getInt(60);
		kenSculptureExplosionStrength = config.get(KEN_SCULPTURE_CATEGORY, "kenSculptureExplosionStrength", 3, "How strong should the explosion be (creeper = 3)?").getInt(3);
		
		if (config.hasChanged()) {
			config.save();
		}
	}
	
}

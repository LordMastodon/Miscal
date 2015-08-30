package lordmastodon.miscal.handler;

import java.io.File;

import lordmastodon.miscal.client.gui.MiscalGuiConfig;
import lordmastodon.miscal.constants.ModConstants;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {
	
	public static Configuration configuration;

	public static final String KEN_SCULPTURE = "kenSculpture";
	
	public static boolean kenSculptureExplosions = true;
	public static boolean kenSculptureFuse = true;
	public static int kenSculptureFuseLength = 60;
	public static double kenSculptureExplosionStrength = 3.0D;
	
	public static void init(File configFile) {
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }
	
	public static void loadConfiguration() {
		//KEN_SCULPTURE_CATEGORY
		kenSculptureExplosions = configuration.get("kenSculptureExplosions", KEN_SCULPTURE, "Whether or not the Sculpture of Ken the Mighty should explode.", "confval.kensculpture.explosions").getBoolean(true);
		kenSculptureFuse = configuration.get("kenSculptureFuse", KEN_SCULPTURE, "Whether or not the Sculpture of Ken the Mighty should have a fuse.", "confval.kensculpture.fuse").getBoolean(true);
		kenSculptureFuseLength = configuration.get("kenSculptureFuseLength", KEN_SCULPTURE, "The length of the fuse (every increment of 20 adds another second).", "confvalue.kensculpture.fuselength").getInt(40);
		kenSculptureExplosionStrength = configuration.get("kenSculptureExplosionStrength", KEN_SCULPTURE, "The strength of the explosion (3.0 is a creeper's explosion).", "confvalue.kensculpture.explosionstrength").getDouble(3.0D);
		
		if (configuration.hasChanged()) {
			configuration.save();
		}
	}
	
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(ModConstants.MOD_ID)) {
			ConfigurationHandler.loadConfiguration();
		}
	}
	
}

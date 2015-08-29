package lordmastodon.miscal.handler;

import lordmastodon.miscal.constants.ModConstants;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FMLCommonHandlerEventHandler {

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(ModConstants.MOD_ID)) {
			ConfigurationHandler.loadConfiguration();
		}
	}
	
}

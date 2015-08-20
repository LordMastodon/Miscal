package lordmastodon.robotsculptures.client.gui;

import lordmastodon.robotsculptures.constants.ModConstants;
import lordmastodon.robotsculptures.handler.ConfigurationHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;

public class RSGuiConfig extends GuiConfig {

	public RSGuiConfig(GuiScreen screen) {
		super(screen,
				new ConfigElement(ConfigurationHandler.config.getCategory(ConfigurationHandler.KEN_SCULPTURE_CATEGORY)).getChildElements(),
				ModConstants.MOD_ID,
				false,
				false,
				"Robot Sculptures Configuration");
	}
	
}

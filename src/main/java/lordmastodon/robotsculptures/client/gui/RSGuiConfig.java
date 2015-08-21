package lordmastodon.robotsculptures.client.gui;

import java.util.ArrayList;
import java.util.List;

import lordmastodon.robotsculptures.constants.ModConstants;
import lordmastodon.robotsculptures.handler.ConfigurationHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CategoryEntry;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;

public class RSGuiConfig extends GuiConfig {

	public RSGuiConfig(GuiScreen screen) {
		super(screen,
				getConfigElements(),
				ModConstants.MOD_ID,
				false,
				false,
				"Robot Sculptures Configuration");
	}
	
	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
	
		list.add(new DummyCategoryElement("kenSculptureCfg", "configcategory.kensculpture.name", KenSculptureConfig.class));
		
		return list;
	}
	
	public static class KenSculptureConfig extends CategoryEntry {
		public KenSculptureConfig(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
			super(owningScreen, owningEntryList, prop);
		}
		
		@Override
		protected GuiScreen buildChildScreen() {
			return new GuiConfig(this.owningScreen,
					new ConfigElement(ConfigurationHandler.config.getCategory(ConfigurationHandler.KEN_SCULPTURE_CATEGORY)).getChildElements(),
					this.owningScreen.modID, 
					this.owningScreen.allRequireWorldRestart,
					this.owningScreen.allRequireMcRestart,
					"Ken Sculpture Sub-Configuration");
		}
	}
	
}

package lordmastodon.miscal.client.gui;

import java.util.ArrayList;
import java.util.List;

import lordmastodon.miscal.constants.ModConstants;
import lordmastodon.miscal.handler.ConfigurationHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CategoryEntry;
import net.minecraftforge.fml.client.config.IConfigElement;

public class MiscalGuiConfig extends GuiConfig {

	public MiscalGuiConfig(GuiScreen screen) {
		super(screen,
				getConfigElements(),
				ModConstants.MOD_ID,
				false,
				false,
				"Miscal Configuration");
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
					new ConfigElement(ConfigurationHandler.configuration.getCategory(ConfigurationHandler.KEN_SCULPTURE)).getChildElements(),
					this.owningScreen.modID, 
					this.owningScreen.allRequireWorldRestart,
					this.owningScreen.allRequireMcRestart,
					"Ken Sculpture Configuration");
		}
	}
}	
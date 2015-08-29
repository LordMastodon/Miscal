package lordmastodon.miscal.client.gui;

import java.util.ArrayList;
import java.util.List;

import lordmastodon.miscal.constants.ModConstants;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.ConfigGuiType;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyListElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CategoryEntry;
import net.minecraftforge.fml.client.config.IConfigElement;

public class MiscalGuiConfig extends GuiConfig {
	
	public static DummyConfigElement kenSculptureExplosionStrength = new DummyConfigElement("kenSculptureExplosionStrength", 3.0, ConfigGuiType.DOUBLE, "configvalue.kensculpture.explosionstrength.name");
	public static DummyConfigElement kenSculptureExplosions = new DummyConfigElement("kenSculptureExplosions", true, ConfigGuiType.BOOLEAN, "configvalue.kensculpture.explosions.name");
	public static DummyConfigElement kenSculptureFuse = new DummyConfigElement("kenSculptureFuse", true, ConfigGuiType.BOOLEAN, "configvalue.kensculpture.fuse.name");
	public static DummyConfigElement kenSculptureFuseLength = new DummyConfigElement("kenSculptureFuseLength", 60, ConfigGuiType.INTEGER, "configvalue.kensculpture.fuselength.name");
	
	public MiscalGuiConfig(GuiScreen screen) {
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
	
	private static List<IConfigElement> getKenSculptureConfig() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		
		list.add(kenSculptureExplosionStrength);
		list.add(kenSculptureExplosions);
		list.add(kenSculptureFuse);
		list.add(kenSculptureFuseLength);
	
		return list;
	}
	
	public static class KenSculptureConfig extends CategoryEntry {
		public KenSculptureConfig(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
			super(owningScreen, owningEntryList, prop);
		}
		
		@Override
		protected GuiScreen buildChildScreen() {
			return new GuiConfig(this.owningScreen,
					getKenSculptureConfig(),
					this.owningScreen.modID, 
					this.owningScreen.allRequireWorldRestart,
					this.owningScreen.allRequireMcRestart,
					"Ken Sculpture Configuration");
		}
	}
}	
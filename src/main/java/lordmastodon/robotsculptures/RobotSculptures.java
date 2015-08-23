package lordmastodon.robotsculptures;

import lordmastodon.robotsculptures.block.RSBlocks;
import lordmastodon.robotsculptures.constants.ModConstants;
import lordmastodon.robotsculptures.crafting.RSCraftingRecipes;
import lordmastodon.robotsculptures.creativetab.SculpturesTab;
import lordmastodon.robotsculptures.handler.ConfigurationHandler;
import lordmastodon.robotsculptures.proxy.CommonProxy;
import lordmastodon.robotsculptures.tileentity.RSTileEntities;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModConstants.MOD_ID, name = ModConstants.MOD_NAME, version = ModConstants.MOD_VERSION, acceptedMinecraftVersions = ModConstants.ACCEPTED_MINECRAFT_VERSIONS, guiFactory = ModConstants.GUI_FACTORY_PATH)
public class RobotSculptures {
	
	@SidedProxy(clientSide = ModConstants.CLIENT_PROXY_PATH, serverSide = ModConstants.COMMON_PROXY_PATH)
	public static CommonProxy proxy;
	
	@Instance(ModConstants.MOD_ID)
	public static RobotSculptures instance;
	
	public static final SculpturesTab modTab = new SculpturesTab("tabSculptures");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		RSBlocks.init();
		RSBlocks.register();
		
		ConfigurationHandler.init();
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenders();
		
		RSTileEntities.register();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		RSCraftingRecipes.register();
	}

}

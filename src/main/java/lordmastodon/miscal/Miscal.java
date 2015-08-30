package lordmastodon.miscal;

import lordmastodon.miscal.block.MiscalBlocks;
import lordmastodon.miscal.constants.ModConstants;
import lordmastodon.miscal.crafting.MiscalCraftingRecipes;
import lordmastodon.miscal.creativetab.MiscalTab;
import lordmastodon.miscal.handler.ConfigurationHandler;
import lordmastodon.miscal.handler.FMLCommonHandlerEventHandler;
import lordmastodon.miscal.handler.MinecraftForgeEventBusEventHandler;
import lordmastodon.miscal.item.MiscalItems;
import lordmastodon.miscal.proxy.CommonProxy;
import lordmastodon.miscal.tileentity.MiscalTileEntities;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModConstants.MOD_ID, name = ModConstants.MOD_NAME, version = ModConstants.MOD_VERSION, acceptedMinecraftVersions = ModConstants.ACCEPTED_MINECRAFT_VERSIONS, guiFactory = ModConstants.GUI_FACTORY_PATH)
public class Miscal {
	
	@SidedProxy(clientSide = ModConstants.CLIENT_PROXY_PATH, serverSide = ModConstants.COMMON_PROXY_PATH)
	public static CommonProxy proxy;
	
	@Instance(ModConstants.MOD_ID)
	public static Miscal instance;

	public static final MiscalTab modTab = new MiscalTab("tabMiscal");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MiscalBlocks.init();
		MiscalBlocks.register();
		MiscalItems.init();
		MiscalItems.register();
		
		ConfigurationHandler.init();
		FMLCommonHandler.instance().bus().register(new FMLCommonHandlerEventHandler());
		MinecraftForge.EVENT_BUS.register(new MinecraftForgeEventBusEventHandler());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenders();
		
		MiscalTileEntities.register();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		ConfigurationHandler.loadConfiguration();
		
		MiscalCraftingRecipes.register();
	}

}

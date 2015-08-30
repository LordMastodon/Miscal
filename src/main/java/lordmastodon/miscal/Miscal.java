package lordmastodon.miscal;

import lordmastodon.miscal.block.MiscalBlocks;
import lordmastodon.miscal.constants.ModConstants;
import lordmastodon.miscal.crafting.MiscalCraftingRecipes;
import lordmastodon.miscal.creativetab.MiscalTab;
import lordmastodon.miscal.handler.ConfigurationHandler;
import lordmastodon.miscal.handler.GuiHandler;
import lordmastodon.miscal.handler.MinecraftForgeEventBusEventHandler;
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
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = ModConstants.MOD_ID, name = ModConstants.MOD_NAME, version = ModConstants.MOD_VERSION, acceptedMinecraftVersions = ModConstants.ACCEPTED_MINECRAFT_VERSIONS, guiFactory = ModConstants.GUI_FACTORY_PATH)
public class Miscal {
	
	@SidedProxy(clientSide = ModConstants.CLIENT_PROXY_PATH, serverSide = ModConstants.COMMON_PROXY_PATH)
	public static CommonProxy proxy;
	
	@Instance(ModConstants.MOD_ID)
	public static Miscal instance;
	
	public GuiHandler guiHandler = new GuiHandler();

	public static final MiscalTab modTab = new MiscalTab("tabMiscal");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//Content Registration
		MiscalBlocks.init();
		MiscalBlocks.register();
		//MiscalItems.init();
		//MiscalItems.register();
		
		//Configuration Registration
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());;
		
		//Event Registration
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		MinecraftForge.EVENT_BUS.register(new MinecraftForgeEventBusEventHandler());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		//Render Registration
		proxy.registerRenders();
		
		//TileEntity Registration
		MiscalTileEntities.register();
		
		//GUI Registration
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, guiHandler);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		//Configuration Loading
		ConfigurationHandler.loadConfiguration();
		
		//Crafting Recipe Registration
		MiscalCraftingRecipes.register();
	}

}

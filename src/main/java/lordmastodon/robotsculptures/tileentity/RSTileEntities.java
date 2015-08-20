package lordmastodon.robotsculptures.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class RSTileEntities {
	
	public static void register() {
		GameRegistry.registerTileEntity(KenSculptureTileEntity.class, "KenSculptureTileEntity");
	}

}

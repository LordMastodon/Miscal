package lordmastodon.miscal.tileentity;

import lordmastodon.miscal.constants.TileEntityConstants;
import lordmastodon.miscal.tileentities.KenSculptureTileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MiscalTileEntities {
	
	public static void register() {
		GameRegistry.registerTileEntity(KenSculptureTileEntity.class, TileEntityConstants.KEN_SCULPTURE_TILE_ENTITY);
	}

}

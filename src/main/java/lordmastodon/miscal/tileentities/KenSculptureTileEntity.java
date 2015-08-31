package lordmastodon.miscal.tileentities;

import lordmastodon.miscal.constants.NBTConstants;
import lordmastodon.miscal.handler.ConfigurationHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;

public class KenSculptureTileEntity extends TileEntity implements IUpdatePlayerListBox {

	private int fuse;
	private boolean ignited = false;
	
	@Override
	public void update() {
		if (checkExplosion()) {
			getWorld().createExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, (float) ConfigurationHandler.kenSculptureExplosionStrength, true);
			
			ignited = false;
			fuse = ConfigurationHandler.kenSculptureFuseLength;
		}
	}
	
	public void ignite() {
		ignited = true;
	}
	
	public KenSculptureTileEntity() {
		fuse = ConfigurationHandler.kenSculptureFuseLength;
	}
	
	private boolean checkExplosion() {
		if (ignited) {
			if (ConfigurationHandler.kenSculptureFuse) {
				if (fuse > 0) {
					fuse--;
					
					System.out.println(fuse);
				} else {
					return true;
				}
			} else {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound c) {
		super.writeToNBT(c);
		
		c.setBoolean(NBTConstants.ignitedKey, this.ignited);
		c.setShort(NBTConstants.fuseKey, (short) this.fuse);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound c) {
        super.readFromNBT(c);

        this.ignited = c.getBoolean(NBTConstants.ignitedKey);
        this.fuse = (int) c.getShort(NBTConstants.fuseKey);
    }

}

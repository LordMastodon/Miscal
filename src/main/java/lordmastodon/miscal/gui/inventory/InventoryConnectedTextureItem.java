package lordmastodon.miscal.gui.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;

public class InventoryConnectedTextureItem implements IInventory {
	
	private final ItemStack[] stacks = new ItemStack[2];
	
	@Override
	public int getSizeInventory() {
		return 2;
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		return stacks[index];
	}
	
	@Override
	public ItemStack decrStackSize(int slotNum, int itemNum) {
		if(stacks[slotNum] != null) {
			ItemStack is = stacks[slotNum];
			stacks[slotNum] = null;
			return is;
		} else {
			return null;
		}
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slotNum) {
		if (stacks[slotNum] != null) {
			ItemStack is = stacks[slotNum];
			stacks[slotNum] = null;
			return is;
		} else {
			return null;
		}
	}
	
	@Override
	public void setInventorySlotContents(int slotNum, ItemStack stack) {
		stacks[slotNum] = stack;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 1;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}
	
	@Override
	public boolean isItemValidForSlot(int slotNum, ItemStack stack) {
		return true;
	}
	
	public boolean isEmpty() {
		for (int i = 0; i < stacks.length; i++) {
			if (stacks[i] != null) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public void markDirty() {
		
	}
	
	@Override
    public String getName() {
        return "ConnectedTextureItem";
    }

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public IChatComponent getDisplayName() {
		return null;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		for(int i = 0; i < stacks.length; i++) {
			stacks[i] = null;
		}
	}

}

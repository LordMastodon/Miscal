package lordmastodon.miscal.gui.container;

import lordmastodon.miscal.gui.inventory.InventoryConnectedTextureItem;
import lordmastodon.miscal.recipes.ConnectedTextureItemRecipes;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerConnectedTextureItem extends Container {

	public static enum State {
		ERROR, READY
	}
	
	public InventoryConnectedTextureItem inventory = new InventoryConnectedTextureItem();
	private Slot[] slots = new Slot[2];
	public ConnectedTextureItemRecipes recipes;
	private final World worldObj;
	public InventoryPlayer playerInventory;
	public String resultString = "connecting.result.ready";
	public State connectingState = State.READY;
	public int x = 0;
	public int y = 0;
	public int z = 0;
	
	public ContainerConnectedTextureItem(InventoryPlayer pI, World world, int parX, int parY, int parZ) {
		x = parX;
		y = parY;
		z = parZ;
		worldObj = world;
	
		recipes = ConnectedTextureItemRecipes.instance();
		
		slots[0] = new Slot(inventory, 0, 48, 34);
		slots[1] = new Slot(inventory, 1, 108, 34);
		
		for(int i = 0; i < slots.length; i++) {
			addSlotToContainer(slots[i]);
		}
		
		for(int playerSlotIndexY = 0; playerSlotIndexY < 3; ++playerSlotIndexY) {
            for(int playerSlotIndexX = 0; playerSlotIndexX < 9; ++playerSlotIndexX) {
                addSlotToContainer(new Slot(pI, playerSlotIndexX + playerSlotIndexY * 9 + 9, 8 + playerSlotIndexX * 18, 84 + playerSlotIndexY * 18));
            }
        }
        
        for(int hotbarSlotIndex = 0; hotbarSlotIndex < 9; ++hotbarSlotIndex) {
            addSlotToContainer(new Slot(pI, hotbarSlotIndex, 
                  8 + hotbarSlotIndex * 18, 142));
        } 
        
        playerInventory = pI;
	}

	@Override
	public void onCraftMatrixChanged(IInventory parInv) {
		if (parInv == inventory) {
			if (inventory.getStackInSlot(0) == null) {
				resultString = I18n.format("connecting.result.ready");
				connectingState = State.READY;
				return;
			}
			
			ItemStack outputStack = recipes.getConnectResult(inventory.getStackInSlot(0));
			
			if (outputStack == null) {
				resultString = I18n.format("connecting.result.impossible");
				connectingState = State.ERROR;
				return;
			}
			
			while (inventory.getStackInSlot(0) != null) {
				if (inventory.getStackInSlot(1) != null) {
					ItemStack itemInOutputSlot = inventory.getStackInSlot(1);
					
					if (itemInOutputSlot != null && outputStack != null) {
						if(!itemInOutputSlot.isItemEqual(outputStack)) {
							if(!playerInventory.addItemStackToInventory(itemInOutputSlot)) {
								EntityItem entityItem = playerInventory.player.entityDropItem(itemInOutputSlot, 0.5F);
								entityItem.posX = playerInventory.player.posX;
								entityItem.posY = playerInventory.player.posY;
								entityItem.posZ = playerInventory.player.posZ;
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting listener) {
        super.addCraftingToCrafters(listener);
    }
	
//	@Override
//	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
//        ItemStack itemstack = null;
//        Slot slot = (Slot)this.inventorySlots.get(index);
//
//        if (slot != null && slot.getHasStack()) {
//            ItemStack itemstack1 = slot.getStack();
//            itemstack = itemstack1.copy();
//
//            if (index == 2) {
//                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
//                    return null;
//                }
//
//                slot.onSlotChange(itemstack1, itemstack);
//            } else if (index != 1 && index != 0) {
//                if (ConnectedTextureItemRecipes.instance().getConnectResult(itemstack1) != null) {
//                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
//                        return null;
//                    }
//                } else if (index >= 3 && index < 30) {
//                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
//                        return null;
//                    }
//                } else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
//                    return null;
//                }
//            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
//                return null;
//            }
//
//            if (itemstack1.stackSize == 0) {
//                slot.putStack((ItemStack)null);
//            } else {
//                slot.onSlotChanged();
//            }
//
//            if (itemstack1.stackSize == itemstack.stackSize) {
//                return null;
//            }
//
//            slot.onPickupFromSlot(playerIn, itemstack1);
//        }
//
//        return itemstack;
//    }

}

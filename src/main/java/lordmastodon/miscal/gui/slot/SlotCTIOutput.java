package lordmastodon.miscal.gui.slot;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.MathHelper;

public class SlotCTIOutput extends Slot {

	private EntityPlayer thePlayer;
	private int field_75228_b;
	
	public SlotCTIOutput(EntityPlayer player, IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		
		this.thePlayer = player;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
	
	@Override
	public ItemStack decrStackSize(int amount) {
        if (this.getHasStack()) {
            this.field_75228_b += Math.min(amount, this.getStack().stackSize);
        }

        return super.decrStackSize(amount);
    }
	
	@Override
	public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
        this.onCrafting(stack);
        super.onPickupFromSlot(playerIn, stack);
    }
	
	@Override
	protected void onCrafting(ItemStack stack, int amount) {
        this.field_75228_b += amount;
        this.onCrafting(stack);
    }
	
	@Override
	protected void onCrafting(ItemStack stack) {
        stack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.field_75228_b);
    }

}

package lordmastodon.miscal.blocks;

import java.util.Random;

import lordmastodon.miscal.block.MiscalBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class LightationBlock extends Block {
	
	public static final PropertyBool LIGHTED_UP = PropertyBool.create("lighted");

	public LightationBlock() {
		super(Material.iron);
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(LIGHTED_UP, false));
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(MiscalBlocks.lightation);
	}
	
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			boolean lightedUp = (Boolean) state.getValue(LIGHTED_UP);
			
			if (lightedUp == true) {
				lightedUp = false;
			} else {
				lightedUp = true;
			}
			
			world.setBlockState(pos, state.withProperty(LIGHTED_UP, lightedUp));
		}
		
		if(lightValue == 0) {
			
		}
		
		return true;
	}
	
}

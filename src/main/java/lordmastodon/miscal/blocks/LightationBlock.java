package lordmastodon.miscal.blocks;

import java.util.Random;

import lordmastodon.miscal.block.MiscalBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.util.MathHelper;

public class LightationBlock extends Block {
	
	public static final PropertyBool LIT_UP = PropertyBool.create("lit");
	
	public LightationBlock() {
		super(Material.iron);
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(LIT_UP, Boolean.valueOf(false)));
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(MiscalBlocks.lightation);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			boolean litUp = (Boolean) state.getValue(LIT_UP);
			
			if(!litUp) {
				world.setBlockState(pos, state.withProperty(LIT_UP, Boolean.valueOf(true)));
				
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.6F, 1.6F, 1.6F);
			} else {
				world.setBlockState(pos, state.withProperty(LIT_UP, Boolean.valueOf(false)));
				
				this.setBlockBounds(0.315F, 0.005F, 0.315F, 0.685F, 0.125F, 0.685F);
			}
		}
		
		return true;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos) {
		boolean litUp = (Boolean) world.getBlockState(pos).getValue(LIT_UP);
		
		if(litUp) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		} else {
			this.setBlockBounds(0.315F, 0.005F, 0.315F, 0.685F, 0.125F, 0.685F);
		}
	}
	
	@Override
	public int getLightValue(IBlockAccess world, BlockPos pos) {
		int lightValue = 0;
		
		boolean litUp = (Boolean) world.getBlockState(pos).getValue(LIT_UP);

	   	if (litUp) {
	   		lightValue = 15;
		} else {
			lightValue = 0;
		}
	
		return lightValue;
	}
	
	public IBlockState getStateFromMeta(int meta) {
		boolean metaBool = false;
		
		switch (meta) {
		case 0:
			metaBool = false;
			break;
		case 1:
			metaBool = true;
			break;
		}
		
		return this.getDefaultState().withProperty(LIT_UP, Boolean.valueOf(metaBool));
	}
	
	public int getMetaFromState(IBlockState state) {
		return (Boolean) state.getValue(LIT_UP) == false ? 0 : 1;
	}
	
	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state) {
		return this.getDefaultState().withProperty(LIT_UP, Boolean.valueOf(false));
	}
	
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {LIT_UP});
	}
	
	@Override
	public int getRenderType() {
		return 3; 
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT_MIPPED;
	}
	
	@Override
	public boolean isFullCube() {
		return false;
	}
	
}

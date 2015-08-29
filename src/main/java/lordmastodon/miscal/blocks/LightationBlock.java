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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LightationBlock extends Block {
	
	public static final PropertyBool LIT_UP = PropertyBool.create("lit");
	private static boolean inLitUpMode = false;
	
	public LightationBlock() {
		super(Material.iron);
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(LIT_UP, false));
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(MiscalBlocks.lightation);
	}
	
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			inLitUpMode = (Boolean) state.getValue(LIT_UP);
			
			world.setBlockState(pos, state.withProperty(LIT_UP, inLitUpMode));
		}
		
		return true;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos) {
		boolean isLitUp = (Boolean) world.getBlockState(pos).getValue(LIT_UP);
		
		if (!isLitUp) {
			this.setBlockBounds(5F, (float) this.minY, 5F, 11F, 2F, 11F);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos)
    {
        if(inLitUpMode) {
        	return new AxisAlignedBB((double)pos.getX() + minX, (double)pos.getY() + this.minY, (double)pos.getZ() + this.minZ, (double)pos.getX() + this.maxX, (double)pos.getY() + this.maxY, (double)pos.getZ() + this.maxZ);
        } else {
        	return new AxisAlignedBB((double)pos.getX() + 5D, (double)pos.getY() + this.minY, (double)pos.getZ() + 5D, (double)pos.getX() + 11D, (double)pos.getY() + 2D, (double)pos.getZ() + 11D);
        }
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
		
		return this.getDefaultState().withProperty(LIT_UP, metaBool);
	}
	
	public int getMetaFromState(IBlockState state) {
		return (Boolean) state.getValue(LIT_UP) == false ? 0 : 1;
	}
	
	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state) {
		return this.getDefaultState().withProperty(LIT_UP, false);
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

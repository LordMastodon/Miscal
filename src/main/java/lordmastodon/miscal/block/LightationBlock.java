package lordmastodon.miscal.block;

import java.util.Random;

import lordmastodon.miscal.Miscal;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LightationBlock extends Block {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final PropertyBool LIT_UP = PropertyBool.create("lit");

    private float pixel = 1F/16F;
    
	public LightationBlock() {
		super(Material.iron);
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LIT_UP, Boolean.valueOf(false)));
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(MiscalBlocks.lightation);
	}
	
	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
		return worldIn.isSideSolid(pos.offset(side.getOpposite()), side, true);
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		EnumFacing[] anenumfacing = EnumFacing.values();
		int i  = anenumfacing.length;
		
		for (int j = 0; j < i; j++) {
			EnumFacing enumfacing = anenumfacing[j];
			
			if (world.isSideSolid(pos.offset(enumfacing), enumfacing.getOpposite(), true)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {		
		return world.isSideSolid(pos.offset(facing.getOpposite()), facing, true) ? this.getDefaultState().withProperty(FACING, facing).withProperty(LIT_UP, Boolean.valueOf(false)) : this.getDefaultState().withProperty(FACING, EnumFacing.DOWN).withProperty(LIT_UP, Boolean.valueOf(false));
    }
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
        return null;
    }
//	
//	@Override
//	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
//		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
//		
//		setBlockBoundsBasedOnFacing(worldIn, pos);
//		
//		Miscal.modLogger.debug("Placed Block");
//	}
//	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		//setBlockBoundsBasedOnFacing(state);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			boolean litUp = (Boolean) state.getValue(LIT_UP);
			
			if (litUp) {
				world.setBlockState(pos, state.withProperty(LIT_UP, false));
			} else {
				world.setBlockState(pos, state.withProperty(LIT_UP, true));
			}
			//setBlockBoundsBasedOnFacing(state);
		}
		
		return true;
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
        setBlockBoundsBasedOnFacing(worldIn.getBlockState(pos));
    }
	
	//Switches light value when blockstate changes
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
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		boolean lit = false;
		EnumFacing facing = EnumFacing.NORTH;
		
		switch (meta) {
		case 0:
			lit = false;
			break;
		case 1:
			lit = true;
			break;
		case 2:
			facing = EnumFacing.NORTH;
			break;
		case 3:
			facing = EnumFacing.EAST;
			break;
		case 4:
			facing = EnumFacing.SOUTH;
			break;
		case 5:
			facing = EnumFacing.WEST;
			break;
		case 6:
			facing = EnumFacing.UP;
			break;
		case 7:
			facing = EnumFacing.DOWN;
			break;
		}
		
		return this.getDefaultState().withProperty(LIT_UP, Boolean.valueOf(lit)).withProperty(FACING, facing);
	}
	
	
	public void setBlockBoundsBasedOnFacing(IBlockState state) {
		boolean litUp = Boolean.valueOf((Boolean) state.getValue(LIT_UP));
		EnumFacing facing = (EnumFacing) state.getValue(FACING);
		
		if (litUp) {
			switch (facing) {
			case NORTH:
				this.setBlockBounds(0F * pixel, 5F * pixel, 5F * pixel, 2F * pixel, 11F * pixel, 11F * pixel);
			case WEST:
				this.setBlockBounds(5F * pixel, 5F * pixel, 0F * pixel, 11F * pixel, 2F * pixel, 11F * pixel);
			case SOUTH:
				this.setBlockBounds(16F * pixel, 11F * pixel, 11F * pixel, 13F * pixel, 5F * pixel, 5F * pixel);
			case EAST:
				this.setBlockBounds(11F * pixel, 11F * pixel, 16F * pixel, 5F * pixel, 13F * pixel, 5F * pixel);
			case UP:
				this.setBlockBounds(5F * pixel, 0F * pixel, 5F * pixel, 11F * pixel, 2F * pixel, 11F * pixel);
			case DOWN:
				this.setBlockBounds(11F * pixel, 16F * pixel, 11F * pixel, 5F * pixel, 13F * pixel, 5F * pixel);
			}
		} else {
			this.setBlockBounds(0F * pixel, 0F * pixel, 0F * pixel, 16F * pixel, 16F * pixel, 16F * pixel);
		}
	}
	
	private String toString(EnumFacing facing) {
		return facing.getName();
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		if((EnumFacing) state.getValue(FACING) == EnumFacing.NORTH) {
			return 0;
		} else if ((EnumFacing) state.getValue(FACING) == EnumFacing.WEST) {
			return 1;
		} else if ((EnumFacing) state.getValue(FACING) == EnumFacing.SOUTH) {
			return 2;
		} else if ((EnumFacing) state.getValue(FACING) == EnumFacing.EAST) {
			return 3;
		} else if ((EnumFacing) state.getValue(FACING) == EnumFacing.UP) {
			return 4;
		} else if ((EnumFacing) state.getValue(FACING) == EnumFacing.DOWN) {
			return 5;
		} else if ((Boolean) state.getValue(LIT_UP) == false) {
			return 6;
		} else {
			return 7;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state) {
		return this.getDefaultState().withProperty(LIT_UP, Boolean.valueOf(false)).withProperty(FACING, Integer.valueOf(0));
	}
	
	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {LIT_UP, FACING});
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
	
	@Override
	public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
        return (Boolean) state.getValue(LIT_UP) ? 15 : 0;
    }

	@Override
    public int isProvidingStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side){
        return (Boolean) state.getValue(LIT_UP) ? 0 : (state.getValue(FACING) == side ? 15 : 0);
    }
    
	@Override
    public boolean canProvidePower() {
        return true;
    }
	
}
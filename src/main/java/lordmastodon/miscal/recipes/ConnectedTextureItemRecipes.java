package lordmastodon.miscal.recipes;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class ConnectedTextureItemRecipes {
	
	private ArrayList<IConnectedTextureItemRecipe> recipes = new ArrayList<IConnectedTextureItemRecipe>();
	
	public ArrayList<IConnectedTextureItemRecipe> getRecipes() {
		return recipes;
	}
	
	public void registerRecipe(IConnectedTextureItemRecipe recipe) {
		recipes.add(recipe);
	}
	
	public static ConnectedTextureItemRecipes instance() {
		return new ConnectedTextureItemRecipes();
	}
	
	public IConnectedTextureItemRecipe findRecipe(IConnectedTextureItemRecipe recipeToFind) {
		IConnectedTextureItemRecipe recipeToReturn = null;
		
		if(recipeToFind != null) {
			for(int i = 0; i < recipes.size(); i++) {
				if (recipes.get(i) == recipeToFind) {
					recipeToReturn = recipes.get(i);
				}
			}
		} else {
			return null;
		}
		
		return recipeToReturn != null ? recipeToReturn : null;
	}
	
	public ItemStack getConnectResult(ItemStack stack) {
		if(stack != null) {
			for (int i = 0; i < recipes.size(); i++) {
				if(recipes.get(i).getInput() == stack) {
					return recipes.get(i).getOutput();
				}
			}
		}
		
		return null;
	}
	
	public ItemStack getConnectIngredient(ItemStack stack) {
		if(stack != null) {
			for (int i = 0; i < recipes.size(); i++) {
				if(recipes.get(i).getOutput() == stack) {
					return recipes.get(i).getInput();
				}
			}
		}
		
		return null;
	}
	
	public boolean doesRecipeExist(IConnectedTextureItemRecipe recipe) {
		if(recipe != null) {
			for (int i = 0; i < recipes.size(); i++) {
				if(recipes.get(i) == recipe) {
					return true;
				}
			}
		}
		
		return false;
	}

}

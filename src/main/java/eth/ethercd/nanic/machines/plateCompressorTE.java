package eth.ethercd.nanic.machines;

import eth.ethercd.nanic.tiles.ExampleMachine;
import ic2.api.recipe.IMachineRecipeManager;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;

import java.util.Collection;

public class plateCompressorTE extends ExampleMachine {

    public plateCompressorTE(int tier, IMachineRecipeManager<IRecipeInput, Collection<ItemStack>, ItemStack> recipeSet) {
        super(1, Recipes.plateCompressor);
    }
}

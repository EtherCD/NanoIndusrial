package eth.ethercd.nanic.jei.recipes.sc;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SCRecipe {
    private static List<SCRecipe> recipes = new ArrayList<SCRecipe>();

    public static List<SCRecipe> getRecipes() {
        return recipes;
    }

    private final ItemStack input, output;

    public SCRecipe(ItemStack input, ItemStack output) {
        this.input=input;
        this.output=output;
    }

    public ItemStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public static SCRecipe addRecipe(ItemStack input, ItemStack output) {
        SCRecipe recipe = new SCRecipe(input, output);
        if (recipes.contains(recipe))
            return null;
        recipes.add(recipe);
        return recipe;
    }

    public static SCRecipe getRecipe(ItemStack is) {
        if (is == null||is.isEmpty())
            return null;
        for (SCRecipe recipe: recipes)
            if (recipe.machinesInput(is))
                return recipe;
        return null;
    }

    public boolean machinesInput(ItemStack is) {
        return is.getItem() == input.getItem();
    }
}

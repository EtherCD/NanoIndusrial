package eth.ethercd.nanic.jei;

import eth.ethercd.nanic.jei.recipes.sc.SCRecipe;
import eth.ethercd.nanic.jei.recipes.sc.SCRecipeCategory;
import eth.ethercd.nanic.jei.recipes.sc.SCRecipeWrapper;
import eth.ethercd.nanic.load.ItemLoader;
import eth.ethercd.nanic.load.MachineTEs;
import eth.ethercd.nanic.machines.SingularCompressor;
import ic2.core.init.Localization;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class IJEIIntegration implements mezz.jei.api.IModPlugin {
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) { // Регистрация категории, которая будет показываться в MC.
        registry.addRecipeCategories(new SCRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }
    @Override
    public void register(IModRegistry registry) {
        registry.addDescription(new ItemStack(ItemLoader.PROTONIUM_PLATE), Localization.translate("nanic.jei.info.protonium_plate"));
        registry.addDescription(new ItemStack(ItemLoader.NEUTRONIUM_PLATE), Localization.translate("nanic.jei.info.neutronium_plate"));
        registry.addDescription(new ItemStack(ItemLoader.QUARK_PLATE), Localization.translate("nanic.jei.info.quark_plate"));

        //Add SingularCompressorJEI
        registry.addRecipes(SCRecipe.getRecipes(), SCRecipeCategory.UID);
        registry.handleRecipes(SCRecipe.class, recipe -> new SCRecipeWrapper(recipe), SCRecipeCategory.UID);
        registry.addRecipeCatalyst(new ItemStack(new SingularCompressor().getBlockType()), SCRecipeCategory.UID);
    }
}

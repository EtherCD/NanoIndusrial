package eth.ethercd.nanic.jei.recipes.sc;

import eth.ethercd.nanic.NanIC;
import ic2.core.init.Localization;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public class SCRecipeCategory implements IRecipeCategory<SCRecipeWrapper> {
    public static final String UID = NanIC.MODID+":singular_compressor";
    private final IDrawableStatic bg;

    public SCRecipeCategory(IGuiHelper h) {
        bg=h.createDrawable(new ResourceLocation(NanIC.MODID, "gui/singular_compressor.png"), 0, 0, 100, 34);
    }

    @Override
    public String getUid() {
        return this.UID;
    }

    @Override
    public String getTitle() {
        return Localization.translate("nanic.machines.singular_compressor");
    }

    @Override
    public String getModName() {
        return NanIC.NAME;
    }

    @Override
    public IDrawable getBackground() {
        return this.bg;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, SCRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup isg = recipeLayout.getItemStacks();
        isg.init(0, true, 8, 8);
        isg.set(0, recipeWrapper.getInput());

        isg.init(1, false, 74, 9);
        isg.set(1, recipeWrapper.getOutput());
    }
}

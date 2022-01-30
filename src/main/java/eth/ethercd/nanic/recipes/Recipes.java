package eth.ethercd.nanic.recipes;

import eth.ethercd.nanic.load.ItemLoader;
import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.IRecipeInputFactory;
import ic2.core.recipe.BasicMachineRecipeManager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Recipes {
    public static final ItemStack electrical_circuit    = IC2Items.getItem("crafting", "circuit");
    public static final ItemStack hex_heat_storage      = IC2Items.getItem("hex_heat_storage");
    public static final ItemStack plate_gold            = IC2Items.getItem("plate", "gold");
    public static final ItemStack iron_plate            = IC2Items.getItem("plate", "iron");
    public static final ItemStack tin_plate             = IC2Items.getItem("plate", "tin");
    public static final ItemStack carbon_plate          = IC2Items.getItem("crafting", "carbon_plate");
    public static final ItemStack laptron_crystal       =IC2Items.getItem("lapotron_crystal");

    //misc
    public static final ItemStack nano_electrical_circuit = new ItemStack(ItemLoader.NANO_ELECTRICAL_CIRCUIT);
    public static final ItemStack protonium_energy_crystal = new ItemStack(ItemLoader.PROTONIUM_ENERGY_CRYSTAL);
    //plates
    public static final ItemStack scandium_plate    = new ItemStack(ItemLoader.SCANDIUM_PLATE);
    public static final ItemStack tantalum_plate    = new ItemStack(ItemLoader.TANTALUM_PLATE);
    public static final ItemStack tetha_plate       = new ItemStack(ItemLoader.TETHA_PLATE);
    public static final ItemStack protonium_plate   = new ItemStack(ItemLoader.PROTONIUM_PLATE);
    //ingots
    public static final ItemStack scandium_ingot = new ItemStack(ItemLoader.SCANDIUM_INGOT);
    public static final ItemStack tantalum_ingot = new ItemStack(ItemLoader.TANTALUM_INGOT);

    //Reactor
    public static final ItemStack coolant_cell_120k = new ItemStack(ItemLoader.COOLANT_CELL_120K);
    public static final ItemStack coolant_cell_240k = new ItemStack(ItemLoader.COOLANT_CELL_240K);

    public static void addCraftingRecipes() {
        addShapedRecipes(
                (nano_electrical_circuit),
                "LIL",
                "RDR",
                "LIL",
                'I', carbon_plate,
                'D', electrical_circuit,
                'R', scandium_plate,
                'L', plate_gold
        );
        addShapedRecipes(
                (protonium_energy_crystal),
                "LIL",
                "RDR",
                "LIL",
                'L',tetha_plate,
                'R',laptron_crystal,
                'I',nano_electrical_circuit,
                'D',protonium_plate
        );
        addShapedRecipes(
                (coolant_cell_120k),
                "ABA",
                "CCC",
                "ABA",
                'A',tin_plate,
                'B',hex_heat_storage,
                'C',iron_plate
        );
        addShapedRecipes(
                (coolant_cell_240k),
                "CBC",
                "CCC",
                "CBC",
                'B',hex_heat_storage,
                'C',iron_plate
        );
    }

    private static void addShapedRecipes(ItemStack output, Object... input) {
        ic2.api.recipe.Recipes.advRecipes.addRecipe(output, input);
    }

    public static void addMachineRecipe() {
        IRecipeInputFactory input = ic2.api.recipe.Recipes.inputFactory;
        addCompressorRecipe(input.forStack(scandium_ingot), scandium_plate);
        addCompressorRecipe(input.forStack(tantalum_ingot), tantalum_plate);

        //addPlateCompressorRecipe(input.forStack(scandium_ingot), scandium_plate);
        //addPlateCompressorRecipe(input.forStack(tantalum_ingot), tantalum_plate);
    }

    private static void addCompressorRecipe(IRecipeInput inp, ItemStack out) {
        ic2.api.recipe.Recipes.compressor.addRecipe(inp, (NBTTagCompound)null, false, new ItemStack[] {out});
    }

    /*private static void addPlateCompressorRecipe(IRecipeInput inp, ItemStack out) {
        if(ic2.api.recipe.Recipes.plateCompressor==null) {
            ic2.api.recipe.Recipes.plateCompressor = new BasicMachineRecipeManager();
        }
        ic2.api.recipe.Recipes.plateCompressor.addRecipe(inp, (NBTTagCompound)null, false, new ItemStack[] {out});
    }*/
}
package eth.ethercd.nanic.recipes;

import eth.ethercd.nanic.load.ItemLoader;
import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.IRecipeInputFactory;
import ic2.core.recipe.BasicMachineRecipeManager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.nio.charset.StandardCharsets;

public class Recipes {
    public static final ItemStack advanced_circuit      = IC2Items.getItem("crafting", "advanced_circuit");
    public static final ItemStack hex_heat_storage      = IC2Items.getItem("hex_heat_storage");
    public static final ItemStack carbon_plate          = IC2Items.getItem("crafting", "carbon_plate");
    public static final ItemStack laptron_crystal       = IC2Items.getItem("lapotron_crystal");

    //misc
    public static final ItemStack protonium_electrical_circuit  = new ItemStack(ItemLoader.PROTONIUM_ELECTRICAL_CIRCUIT);
    public static final ItemStack protonium_energy_crystal      = new ItemStack(ItemLoader.PROTONIUM_ENERGY_CRYSTAL);
    public static final ItemStack iridium_ore                   = IC2Items.getItem("misc_resource", "iridium_ore");

    //plates
    public static final ItemStack protonium_plate       = new ItemStack(ItemLoader.PROTONIUM_PLATE);
    public static final ItemStack dense_carbon_plate    = new ItemStack(ItemLoader.DENSE_CARBON_PLATE);

    //public static final ItemStack plate_gold        = IC2Items.getItem("plate", "gold");
    public static final ItemStack iron_plate        = IC2Items.getItem("plate", "iron");
    public static final ItemStack tin_plate         = IC2Items.getItem("plate", "tin");
    public static final ItemStack alloy_chestplate  = IC2Items.getItem("crafting", "alloy");
    public static final ItemStack iridium_plate     = IC2Items.getItem("crafting", "iridium");

    //ingots

    //Reactor
    public static final ItemStack coolant_cell_120k         = new ItemStack(ItemLoader.COOLANT_CELL_120K);
    public static final ItemStack coolant_cell_240k         = new ItemStack(ItemLoader.COOLANT_CELL_240K);
    public static final ItemStack coolant_cell_480k         = new ItemStack(ItemLoader.COOLANT_CELL_480K);
    public static final ItemStack neutron_proton_reflector  = new ItemStack(ItemLoader.NEUTRON_PROTON_REFLECTOR);

    public static final ItemStack neutron_reflector         = IC2Items.getItem("neutron_reflector");
    public static final ItemStack iridium_reflector         = IC2Items.getItem("iridium_reflector");
    public static final ItemStack small_uranium_235         = IC2Items.getItem("nuclear", "small_uranium_235");

    public static void addCraftingRecipes() {
        addShapedRecipes(
                (protonium_electrical_circuit),
                "ABA",
                "CDC",
                "ABA",
                'A',alloy_chestplate,
                'B',neutron_reflector,
                'C',iridium_ore,
                'D',advanced_circuit
        );
        addShapedRecipes(
                (protonium_energy_crystal),
                "ABA",
                "CDC",
                "ABA",
                'A',iridium_plate,
                'B',protonium_electrical_circuit,
                'C',neutron_proton_reflector,
                'D',protonium_plate
        );
        addReactorRecipes();
    }

    private static void addReactorRecipes() {
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
                'B',coolant_cell_120k,
                'C',iron_plate
        );
        addShapedRecipes(
                (coolant_cell_480k),
                "CBC",
                "CDC",
                "CBC",
                'B',coolant_cell_240k,
                'C',iron_plate,
                'D',alloy_chestplate
        );
        addShapedRecipes(
                (neutron_proton_reflector),
                "ABA",
                "BCB",
                "ABA",
                'A',dense_carbon_plate,
                'B',small_uranium_235,
                'C',iridium_reflector
        );
    }

    private static void addShapedRecipes(ItemStack output, Object... input) {
        ic2.api.recipe.Recipes.advRecipes.addRecipe(output, input);
    }

    public static void addMachineRecipe() {
        IRecipeInputFactory input = ic2.api.recipe.Recipes.inputFactory;

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
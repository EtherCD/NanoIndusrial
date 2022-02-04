package eth.ethercd.nanic.recipes;

import eth.ethercd.nanic.jei.recipes.sc.SCRecipe;
import eth.ethercd.nanic.load.IC2ToolsLoader;
import eth.ethercd.nanic.load.ItemLoader;
import eth.ethercd.nanic.load.MachineTEs;
import eth.ethercd.nanic.machines.SingularCompressor;
import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.IRecipeInputFactory;
import ic2.core.recipe.BasicMachineRecipeManager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import static eth.ethercd.nanic.recipes.MyRecipeFactory.singularCompressor;

public class Recipes {
    public static final ItemStack advanced_circuit      = IC2Items.getItem("crafting", "advanced_circuit");
    public static final ItemStack hex_heat_storage      = IC2Items.getItem("hex_heat_storage");
    public static final ItemStack carbon_plate          = IC2Items.getItem("crafting", "carbon_plate");
    public static final ItemStack laptron_crystal       = IC2Items.getItem("lapotron_crystal");

    public static final ItemStack iron_ingot            = new ItemStack(Items.IRON_INGOT);

    //misc
    public static final ItemStack protonium_electrical_circuit  = new ItemStack(ItemLoader.PROTONIUM_ELECTRICAL_CIRCUIT);
    public static final ItemStack protonium_energy_crystal      = new ItemStack(ItemLoader.PROTONIUM_ENERGY_CRYSTAL);
    public static final ItemStack neutronium_energy_crystal     = new ItemStack(ItemLoader.NEUTRONIUM_ENERGY_CRYSTAL);
    public static final ItemStack iridium_ore                   = IC2Items.getItem("misc_resource", "iridium_ore");

    //plates
    public static final ItemStack protonium_plate       = new ItemStack(ItemLoader.PROTONIUM_PLATE);
    public static final ItemStack neutronium_plate      = new ItemStack(ItemLoader.NEUTRONIUM_PLATE);
    public static final ItemStack dense_carbon_plate    = new ItemStack(ItemLoader.DENSE_CARBON_PLATE);

    //public static final ItemStack plate_gold        = IC2Items.getItem("plate", "gold");
    public static final ItemStack bronze_plate                        = IC2Items.getItem("plate", "bronze");
    public static final ItemStack copper_plate                        = IC2Items.getItem("plate", "copper");
    public static final ItemStack gold_plate                          = IC2Items.getItem("plate", "gold");
    public static final ItemStack lapis_plate                         = IC2Items.getItem("plate", "lapis");
    public static final ItemStack lead_plate                          = IC2Items.getItem("plate", "lead");
    public static final ItemStack obsidian_plate                      = IC2Items.getItem("plate", "obsidian");
    public static final ItemStack steel_plate                         = IC2Items.getItem("plate", "steel");
    public static final ItemStack iron_plate                          = IC2Items.getItem("plate", "iron");
    public static final ItemStack tin_plate                           = IC2Items.getItem("plate", "tin");
    public static final ItemStack alloy_chestplate                    = IC2Items.getItem("crafting", "alloy");
    public static final ItemStack iridium_plate                       = IC2Items.getItem("crafting", "iridium");

    public static final ItemStack dense_bronze                          = IC2Items.getItem("plate", "dense_bronze");
    public static final ItemStack dense_copper                          = IC2Items.getItem("plate", "dense_copper");
    public static final ItemStack dense_gold                            = IC2Items.getItem("plate", "dense_gold");
    public static final ItemStack dense_iron                            = IC2Items.getItem("plate", "dense_iron");
    public static final ItemStack dense_lapis                           = IC2Items.getItem("plate", "dense_iron");
    public static final ItemStack dense_lead                            = IC2Items.getItem("plate", "dense_lead");
    public static final ItemStack dense_obsidian                        = IC2Items.getItem("plate", "dense_obsidian");
    public static final ItemStack dense_steel                           = IC2Items.getItem("plate", "dense_steel");
    public static final ItemStack dense_tin                             = IC2Items.getItem("plate", "dense_tin");

    //ultra misc
    public static final ItemStack destroyer_space_and_time              = new ItemStack(IC2ToolsLoader.DESTROYER_SPACE_AND_TIME.getInstance());

    //te
    public static final ItemStack machine_casing        = IC2Items.getItem("resource", "machine");
    public static final ItemStack advanced_machine      = IC2Items.getItem("resource", "advanced_machine");
    public static final ItemStack compressor            = IC2Items.getItem("te", "compressor");

    public static final ItemStack singular_compressor   = new ItemStack(new SingularCompressor().getBlockType());

    //Reactor
    public static final ItemStack coolant_cell_120k         = new ItemStack(ItemLoader.COOLANT_CELL_120K);
    public static final ItemStack coolant_cell_240k         = new ItemStack(ItemLoader.COOLANT_CELL_240K);
    public static final ItemStack coolant_cell_480k         = new ItemStack(ItemLoader.COOLANT_CELL_480K);
    public static final ItemStack coolant_cell_infinite     = new ItemStack(ItemLoader.COOLANT_CELL_INFINITE);
    public static final ItemStack neutron_proton_reflector  = new ItemStack(ItemLoader.NEUTRON_PROTON_REFLECTOR);
    public static final ItemStack reinforced_neutron_proton_reflecor    = new ItemStack(ItemLoader.REINFORCED_NEUTRON_PROTON_REFLECTOR);
    public static final ItemStack matter_transformer        = new ItemStack(ItemLoader.MATTER_TRANSFORMER);

    public static final ItemStack neutron_reflector         = IC2Items.getItem("neutron_reflector");
    public static final ItemStack iridium_reflector         = IC2Items.getItem("iridium_reflector");
    public static final ItemStack small_uranium_235         = IC2Items.getItem("nuclear", "small_uranium_235");
    public static final ItemStack advanced_heat_vent        = IC2Items.getItem("advanced_heat_vent");

    public static final ItemStack reactor_chamber           = IC2Items.getItem("te", "reactor_chamber");

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
                "ACA",
                "BDB",
                "ACA",
                'A',iridium_plate,
                'B',protonium_electrical_circuit,
                'C',neutron_proton_reflector,
                'D',protonium_plate
        );
        addShapedRecipes(
                (neutronium_energy_crystal),
                "ACA",
                "CDC",
                "ACA",
                'A',protonium_energy_crystal,
                'C',reinforced_neutron_proton_reflecor,
                'D',neutronium_plate
        );
        addShapedRecipes(
                (destroyer_space_and_time),
                "N  ",
                "PCP",
                "CRP",
                'C',coolant_cell_infinite,
                'P',protonium_plate,
                'N',neutronium_plate,
                'R',neutronium_energy_crystal
        );
        addShapedRecipes(
                (singular_compressor),
                "ACA",
                "BDB",
                "ACA",
                'A',iridium_plate,
                'B',advanced_machine,
                'C',neutron_proton_reflector,
                'D',compressor
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
                (coolant_cell_infinite),
                "IBI",
                "IAI",
                "IBI",
                'A',advanced_heat_vent,
                'B',coolant_cell_480k,
                'I',iridium_plate
        );
        addShapedRecipes(
                (neutron_proton_reflector),
                "ADA",
                "BCB",
                "ADA",
                'A',dense_carbon_plate,
                'B',small_uranium_235,
                'C',iridium_reflector,
                'D',iridium_plate
        );
        addShapedRecipes(
                (reinforced_neutron_proton_reflecor),
                "ABA",
                "BCB",
                "ABA",
                'A',protonium_plate,
                'B',iridium_reflector,
                'C',neutron_proton_reflector
        );
        addShapedRecipes(
                (matter_transformer),
                "ABA",
                "DCD",
                "ABA",
                'A',iridium_plate,
                'B',neutron_reflector,
                'C',machine_casing,
                'D',reactor_chamber
        );
    }

    private static void addShapedRecipes(ItemStack output, Object... input) {
        ic2.api.recipe.Recipes.advRecipes.addRecipe(output, input);
    }

    public static void addMachineRecipe() {
        IRecipeInputFactory input = ic2.api.recipe.Recipes.inputFactory;

        addSingularCompressor(input.forStack(carbon_plate, 9), dense_carbon_plate);

        addSingularCompressor(input.forStack(bronze_plate, 9),  dense_bronze);
        addSingularCompressor(input.forStack(copper_plate, 9),  dense_copper);
        addSingularCompressor(input.forStack(gold_plate, 9),    dense_gold);
        addSingularCompressor(input.forStack(iron_plate, 9),    dense_iron);
        addSingularCompressor(input.forStack(lapis_plate, 9),   dense_lapis);
        addSingularCompressor(input.forStack(lead_plate, 9),    dense_lead);
        addSingularCompressor(input.forStack(obsidian_plate, 9),dense_obsidian);
        addSingularCompressor(input.forStack(steel_plate, 9),   dense_steel);
        addSingularCompressor(input.forStack(tin_plate, 9),     dense_tin);


        addSingularCompressor(input.forStack(iridium_plate, 9), protonium_plate);
        addSingularCompressor(input.forStack(protonium_plate, 9), neutronium_plate);
        addSingularCompressor(input.forStack(iron_ingot, 9),    iridium_ore);
    }

    private static void addCompressorRecipe(IRecipeInput inp, ItemStack out) {
        ic2.api.recipe.Recipes.compressor.addRecipe(inp, (NBTTagCompound)null, false, new ItemStack[] {out});
    }

    private static void addSingularCompressor(IRecipeInput inp, ItemStack out) {
        if(singularCompressor==null) {
            singularCompressor = new BasicMachineRecipeManager();
        }
        singularCompressor.addRecipe(inp, (NBTTagCompound)null, false, new ItemStack[] {out});
        SCRecipe.addRecipe(inp.getInputs().get(0), out);
    }
}
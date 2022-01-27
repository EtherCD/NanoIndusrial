package eth.ethercd.nanic.load;

import eth.ethercd.nanic.items.*;
import eth.ethercd.nanic.items.plate.*;
import eth.ethercd.nanic.items.ingots.*;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemLoader {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    //Items
    public static final Item NANO_ELECTRICAL_CIRCUIT        = new NanoElectricalCircuit("nano_electrical_circuit");
    public static final Item ADVANCED_ELECTRICAL_CIRCUIT    = new AdvancedElectricalCircuit("advanced_electrical_circuit");
    public static final Item IMPROVED_ELECTRICAL_CIRCUIT    = new ImprovedElectricalCircuit("improved_electrical_circuit");
    //Plate
    public static final Item LETA_PLATE                     = new LetaPlate("leta_plate");
    public static final Item META_PLATE                     = new MetaPlate("meta_plate");
    public static final Item TETHA_PLATE                    = new TethaPlate("tetha_plate");
    public static final Item SCANDIUM_PLATE                 = new ScandiumPlate("scandium_plate");
    public static final Item TANTALUM_PLATE                 = new TantalumPlate("tantalum_plate");
    public static final Item NEUTRONIUM_PLATE               = new NeutroniumPlate("neutronium_plate");
    public static final Item PROTONIUM_PLATE                = new ProtoniumPlate("protonium_plate");
    //Ingots
    public static final Item SCANDIUM_INGOT                 = new ScandiumIngot("scandium_ingot");
    public static final Item TANTALUM_INGOT                 = new TantalumIngot("tantalum_ingot");
}

package eth.ethercd.nanic.load;

import eth.ethercd.nanic.items.*;
import eth.ethercd.nanic.items.electric.*;
import eth.ethercd.nanic.items.plate.*;
import eth.ethercd.nanic.items.reactors.*;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemLoader {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    //Items
    public static final Item PROTONIUM_ELECTRICAL_CIRCUIT           = new ProtoniumElectricalCircuit("protonium_electrical_circuit");
    //Plate
    public static final Item NEUTRONIUM_PLATE                       = new NeutroniumPlate("neutronium_plate");
    public static final Item PROTONIUM_PLATE                        = new ProtoniumPlate("protonium_plate");
    public static final Item DENSE_CARBON_PLATE                     = new DenseCarbonPlate("dense_carbon_plate");
    //Ingots

    //Energy Crystals
    public static final Item PROTONIUM_ENERGY_CRYSTAL               = new ProtoniumEnergyCrystal("protonium_energy_crystal");
    //Reactor Components
    public static final Item COOLANT_CELL_120K                      = new CoolantCell120K("coolant_cell_one_two_k");
    public static final Item COOLANT_CELL_240K                      = new CoolantCell240K("coolant_cell_two_four_k");
    public static final Item COOLANT_CELL_480K                      = new CoolantCell480K("coolant_cell_four_eight_k");
    public static final Item NEUTRON_PROTON_REFLECTOR               = new NeutronProtonReflector("neutron_proton_reflector");
    public static final Item REINFORCED_NEUTRON_PROTON_REFLECTOR    = new ReinforcedNeutronProtonReflector("reinforced_neutron_proton_reflector");
    public static final Item GLITCH_URANIUM_QUAD_ROOD               = new GlichedUraniumQuadRoad("glitch_uranium_quad_road");
}

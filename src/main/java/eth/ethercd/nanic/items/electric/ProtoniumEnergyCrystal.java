package eth.ethercd.nanic.items.electric;

import eth.ethercd.nanic.NanIC;
import eth.ethercd.nanic.utils.IHasModel;
import eth.ethercd.nanic.load.ItemLoader;
import ic2.core.IC2;
import ic2.core.ref.ItemName;
import ic2.core.item.ItemBattery;

public class ProtoniumEnergyCrystal extends ItemBattery implements IHasModel {
    private static double   maxCharge       =100000000;
    private static int      tier            = 5;
    private static double   transferL       = 8192;

    public ProtoniumEnergyCrystal(String name) {
        super((ItemName)null, maxCharge, transferL, tier);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(IC2.tabIC2);
        ItemLoader.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        NanIC.proxy.registerItemRenderer(this,0,"inventory");
    }
}
package eth.ethercd.nanic.items.plate;

import eth.ethercd.nanic.NanIC;
import eth.ethercd.nanic.load.ItemLoader;
import eth.ethercd.nanic.utils.IHasModel;
import ic2.core.IC2;
import net.minecraft.item.Item;

public class MetaPlate extends Item implements IHasModel {
    public MetaPlate(String name) {
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
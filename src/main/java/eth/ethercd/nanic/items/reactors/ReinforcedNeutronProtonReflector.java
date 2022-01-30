package eth.ethercd.nanic.items.reactors;

import eth.ethercd.nanic.NanIC;
import eth.ethercd.nanic.load.ItemLoader;
import eth.ethercd.nanic.utils.IHasModel;
import ic2.api.reactor.IReactorComponent;
import ic2.core.IC2;
import ic2.core.item.reactor.ItemReactorIridiumReflector;
import ic2.core.ref.ItemName;

public class ReinforcedNeutronProtonReflector extends ItemReactorIridiumReflector implements IReactorComponent, IHasModel {
    public ReinforcedNeutronProtonReflector(String name) {
        super((ItemName)null);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(IC2.tabIC2);
        ItemLoader.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        NanIC.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
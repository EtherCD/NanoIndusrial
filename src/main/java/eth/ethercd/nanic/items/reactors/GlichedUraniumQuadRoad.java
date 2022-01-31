package eth.ethercd.nanic.items.reactors;

import eth.ethercd.nanic.NanIC;
import eth.ethercd.nanic.load.ItemLoader;
import eth.ethercd.nanic.utils.IHasModel;
import ic2.api.item.ICustomDamageItem;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import ic2.core.IC2;
import ic2.core.item.reactor.ItemReactorUranium;
import ic2.core.ref.ItemName;
import net.minecraft.item.ItemStack;

public class GlichedUraniumQuadRoad extends ItemReactorUranium implements  IReactorComponent, IHasModel, ICustomDamageItem {
        public GlichedUraniumQuadRoad(String name) {
            super((ItemName)null, 4, 10000);
            this.setUnlocalizedName(name);
            this.setRegistryName(name);
            this.setCreativeTab(IC2.tabIC2);
            ItemLoader.ITEMS.add(this);
        }

        @Override
        public int getFinalHeat(ItemStack stack, IReactor reactor, int x, int y, int heat)  {
            return heat / 2;
        }

        @Override
        public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
            if (!heatrun) reactor.addOutput(120.0f);
            return true;
        }

        @Override
        public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatRun) {
            if (!reactor.produceEnergy()) return;
            super.processChamber(stack, reactor, x, y, heatRun);
            applyCustomDamage(stack, 20, null);
        }

        @Override
        public void registerModels() {
            NanIC.proxy.registerItemRenderer(this, 0, "inventory");
        }
}
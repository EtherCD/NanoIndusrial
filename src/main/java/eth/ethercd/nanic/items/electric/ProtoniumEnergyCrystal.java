package eth.ethercd.nanic.items.electric;

import eth.ethercd.nanic.NanIC;
import eth.ethercd.nanic.utils.IHasModel;
import eth.ethercd.nanic.load.ItemLoader;
import ic2.api.item.IElectricItem;
import ic2.core.IC2;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ProtoniumEnergyCrystal extends Item implements IHasModel, IElectricItem {
    private static boolean  canProvEnergy   =true;
    private static double   maxCharge       =100000000;
    private static int      tier            = 5;
    private static double   transferL       = 8192;

    public ProtoniumEnergyCrystal(String name) {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(IC2.tabIC2);
        ItemLoader.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        NanIC.proxy.registerItemRenderer(this,0,"inventory");
    }

    @Override
    public boolean canProvideEnergy(ItemStack stack) {
        return canProvEnergy;
    }

    @Override
    public double getMaxCharge(ItemStack stack) {
        return maxCharge;
    }

    @Override
    public int getTier(ItemStack stack) {
        return tier;
    }

    @Override
    public double getTransferLimit(ItemStack stack) {
        return transferL;
    }
}
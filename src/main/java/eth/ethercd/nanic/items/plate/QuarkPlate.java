package eth.ethercd.nanic.items.plate;

import eth.ethercd.nanic.NanIC;
import eth.ethercd.nanic.load.ItemLoader;
import eth.ethercd.nanic.utils.IHasModel;
import ic2.api.item.ICustomDamageItem;
import ic2.core.IC2;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class QuarkPlate extends Item implements IHasModel, ICustomDamageItem {
    public QuarkPlate(String name) {
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
    public int getCustomDamage(ItemStack stack) {
        return 5;
    }

    @Override
    public int getMaxCustomDamage(ItemStack stack) {
        return 10;
    }

    @Override
    public void setCustomDamage(ItemStack stack, int damage) {
    }

    @Override
    public boolean applyCustomDamage(ItemStack stack, int damage, EntityLivingBase src) {
        return true;
    }
}

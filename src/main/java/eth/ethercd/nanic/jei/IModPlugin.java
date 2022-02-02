package eth.ethercd.nanic.jei;

import eth.ethercd.nanic.load.ItemLoader;
import ic2.core.init.Localization;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class IModPlugin implements mezz.jei.api.IModPlugin {
    @Override
    public void register(IModRegistry registry) {
        registry.addDescription(new ItemStack(ItemLoader.PROTONIUM_PLATE), Localization.translate("nanic.jei.info.protonium_plate"));
        registry.addDescription(new ItemStack(ItemLoader.NEUTRONIUM_PLATE), Localization.translate("nanic.jei.info.neutronium_plate"));
        registry.addDescription(new ItemStack(ItemLoader.QUARK_PLATE), Localization.translate("nanic.jei.info.quark_plate"));
    }
}

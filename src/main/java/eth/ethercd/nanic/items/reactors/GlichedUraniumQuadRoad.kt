package eth.ethercd.nanic.items.reactors

import eth.ethercd.nanic.NanIC
import eth.ethercd.nanic.load.ItemLoader
import eth.ethercd.nanic.utils.IHasModel
import ic2.api.item.ICustomDamageItem
import ic2.api.reactor.IReactor
import ic2.api.reactor.IReactorComponent
import ic2.core.IC2
import ic2.core.item.reactor.ItemReactorUranium
import ic2.core.ref.ItemName
import net.minecraft.item.ItemStack

class GlichedUraniumQuadRoad(name: String) : ItemReactorUranium(null as ItemName?, 4, 10000), IReactorComponent, IHasModel, ICustomDamageItem {
    init {
        unlocalizedName = name
        this.setRegistryName(name)
        this.creativeTab = IC2.tabIC2
        ItemLoader.ITEMS.add(this)
    }

    public override fun getFinalHeat(stack: ItemStack, reactor: IReactor, x: Int, y: Int, heat: Int): Int {
        return heat / 2
    }

    override fun acceptUraniumPulse(stack: ItemStack, reactor: IReactor, pulsingStack: ItemStack, youX: Int, youY: Int, pulseX: Int, pulseY: Int, heatrun: Boolean): Boolean {
        if (!heatrun) reactor.addOutput(120.0f)
        return true
    }

    override fun processChamber(stack: ItemStack, reactor: IReactor, x: Int, y: Int, heatRun: Boolean) {
        if (!reactor.produceEnergy()) return
        super.processChamber(stack, reactor, x, y, heatRun)
        applyCustomDamage(stack, 20, null)
    }

    override fun registerModels() {
        NanIC.proxy.registerItemRenderer(this, 0, "inventory")
    }
}
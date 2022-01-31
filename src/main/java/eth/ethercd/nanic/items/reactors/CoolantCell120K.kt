package eth.ethercd.nanic.items.reactors

import eth.ethercd.nanic.NanIC
import eth.ethercd.nanic.load.ItemLoader
import eth.ethercd.nanic.utils.IHasModel
import ic2.api.reactor.IReactorComponent
import ic2.core.IC2
import ic2.core.item.reactor.ItemReactorCondensator
import ic2.core.ref.ItemName

class CoolantCell120K(name: String) : ItemReactorCondensator(null as ItemName?, 120000), IHasModel, IReactorComponent {
    init {
        unlocalizedName = name
        this.setRegistryName(name)
        this.creativeTab = IC2.tabIC2
        ItemLoader.ITEMS.add(this)
    }

    override fun registerModels() {
        NanIC.proxy.registerItemRenderer(this, 0, "inventory")
    }
}
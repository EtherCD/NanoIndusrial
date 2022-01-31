package eth.ethercd.nanic.items.reactors

import eth.ethercd.nanic.NanIC
import eth.ethercd.nanic.load.ItemLoader
import eth.ethercd.nanic.utils.IHasModel
import ic2.api.reactor.IReactorComponent
import ic2.core.IC2
import ic2.core.item.reactor.ItemReactorIridiumReflector
import ic2.core.ref.ItemName

class ReinforcedNeutronProtonReflector(name: String) : ItemReactorIridiumReflector(null as ItemName?), IReactorComponent, IHasModel {
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
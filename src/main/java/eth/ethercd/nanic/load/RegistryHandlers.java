package eth.ethercd.nanic.load;

import eth.ethercd.nanic.utils.IHasModel;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryHandlers {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> ev) {
        ev.getRegistry().registerAll(ItemLoader.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent ev) {
        for (Item item: ItemLoader.ITEMS)
            if (item instanceof IHasModel) {
                ((IHasModel)item).registerModels();
            }
    }
}

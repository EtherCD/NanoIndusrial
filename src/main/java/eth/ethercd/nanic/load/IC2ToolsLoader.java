package eth.ethercd.nanic.load;

import eth.ethercd.nanic.items.electric.tools.DestroyerSpaceAndTime;
import eth.ethercd.nanic.items.electric.tools.ScandiumDrill;
import ic2.core.ref.IItemModelProvider;
import ic2.core.ref.ItemName;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum IC2ToolsLoader {

    SCANDIUM_DRILL,
    DESTROYER_SPACE_AND_TIME;

    private Item instance;

    public Item getInstance() {
        return this.instance;
    }

    public void setInstance(Item instance) {
        this.instance=instance;
    }

    public static void buildItems(Side side) {
        SCANDIUM_DRILL.setInstance(new ScandiumDrill());
        DESTROYER_SPACE_AND_TIME.setInstance(new DestroyerSpaceAndTime());

        if(side==Side.CLIENT){
            doModelGuf();
        }
    }

    @SideOnly(
            Side.CLIENT
    )
    private static void doModelGuf() {
        IC2ToolsLoader[] tls = values();
        int t=tls.length;
        for (int i=0;i<t;i++){
            IC2ToolsLoader item=tls[i];
            ((IItemModelProvider)item.getInstance()).registerModels((ItemName)null);
        }
    }
}

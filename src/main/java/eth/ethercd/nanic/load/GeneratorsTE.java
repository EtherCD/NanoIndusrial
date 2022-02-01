package eth.ethercd.nanic.load;

import eth.ethercd.nanic.NanIC;
import eth.ethercd.nanic.machines.generators.*;
import ic2.core.block.ITeBlock;
import ic2.core.block.TileEntityBlock;
import ic2.core.ref.TeBlock;
import ic2.core.util.Util;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;
import java.util.Set;


public enum GeneratorsTE implements ITeBlock {

    low_voltage_solar_panel(LowVoltageSolarPanelTE.class, 1, EnumRarity.COMMON),
    medium_voltage_solar_panel(MediumVoltageSolarPanelTE.class, 2, EnumRarity.COMMON),
    high_voltage_solar_panel(HighVoltageSolarPanelTE.class, 3, EnumRarity.COMMON),
    extreme_voltage_solar_panel(ExtremeVoltageSolarPanelTE.class, 4, EnumRarity.RARE);

    private final Class teClass;
    private final int itemMeta;
    private final EnumRarity rarity;
    private TileEntityBlock dummyTe;
    public static final ResourceLocation LOCATION = new ResourceLocation("nanic", "generators");

    GeneratorsTE(Class teClass, int itemMeta, EnumRarity rarity) {
        this.teClass = teClass;
        this.itemMeta = itemMeta;
        this.rarity = rarity;
        GameRegistry.registerTileEntity(teClass, "nanic:"+this.getName());
    }

    @Nullable
    @Override
    public TileEntityBlock getDummyTe() {
        return this.dummyTe;
    }

    @Override
    public ResourceLocation getIdentifier() {
        return this.LOCATION;
    }

    @Override
    public boolean hasItem() {
        return true;
    }

    @Nullable
    @Override
    public Class getTeClass() {
        return this.teClass;
    }

    @Override
    public boolean hasActive() {
        return low_voltage_solar_panel==this||medium_voltage_solar_panel==this||high_voltage_solar_panel==this;
    }

    @Override
    public Set getSupportedFacings() {
        return Util.horizontalFacings;
    }

    @Override
    public float getHardness() {
        return 20.f;
    }

    @Override
    public float getExplosionResistance() {
        return 30.f;
    }

    @Override
    public TeBlock.HarvestTool getHarvestTool() {
        return TeBlock.HarvestTool.Pickaxe;
    }

    @Override
    public TeBlock.DefaultDrop getDefaultDrop() {
        return TeBlock.DefaultDrop.Self;
    }

    @Override
    public EnumRarity getRarity() {
        return this.rarity;
    }

    @Override
    public boolean allowWrenchRotating() {
        return false;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public int getId() {
        return this.itemMeta;
    }

    @Deprecated
    public static void buildDummies() {
        ModContainer mc = Loader.instance().activeModContainer();
        if (mc != null && NanIC.MODID.equals(mc.getModId())) {
            GeneratorsTE[] var1 = values();
            int var2 = var1.length;
            for(int var3 = 1; var3 < var2; ++var3) {
                GeneratorsTE block = var1[var3];
                if (block.teClass != null) {
                    try {
                        block.dummyTe = (TileEntityBlock)block.teClass.newInstance();
                    } catch (Exception var6) {
                        if (Util.inDev()) {
                            var6.printStackTrace();
                        }
                    }
                }
            }
        } else {
            throw new IllegalAccessError("Don't mess with this please.");
        }
    }
}

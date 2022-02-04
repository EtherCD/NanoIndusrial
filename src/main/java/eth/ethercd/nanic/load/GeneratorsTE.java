package eth.ethercd.nanic.load;

import eth.ethercd.nanic.NanIC;
import eth.ethercd.nanic.machines.generators.*;
import ic2.core.block.ITeBlock;
import ic2.core.block.TileEntityBlock;
import ic2.core.ref.TeBlock;
import ic2.core.util.Util;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.EnumFacing;
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

    @Override
    public boolean hasItem() {
        return true;
    }
    @Override
    public String getName() {
        return this.name();
    }
    @Override
    public int getId() {
        return this.itemMeta;
    }
    @Override
    public ResourceLocation getIdentifier() {
        return LOCATION;
    }
    @Override
    public Class<? extends TileEntityBlock> getTeClass() {
        return this.teClass;
    }
    @Override
    public boolean hasActive() {
        return true;
    }
    @Override
    public float getHardness() {
        return 5.0F;
    }
    @Override
    public float getExplosionResistance() {
        return 10.0F;
    }
    @Override
    public TeBlock.HarvestTool getHarvestTool() {
        return TeBlock.HarvestTool.Pickaxe;
    }
    @Override
    public TeBlock.DefaultDrop getDefaultDrop() {
        return TeBlock.DefaultDrop.Machine;
    }
    @Override
    public boolean allowWrenchRotating() {
        return false;
    }
    @Override
    public Set<EnumFacing> getSupportedFacings() {
        return Util.horizontalFacings;
    }
    @Override
    public EnumRarity getRarity() {
        return EnumRarity.COMMON;
    }
    @Override
    public Material getMaterial() {
        return Material.IRON;
    }
    @Override
    public TileEntityBlock getDummyTe() {
        return this.dummyTe;
    }

    @Deprecated
    public static void buildDummies() {
        ModContainer mc = Loader.instance().activeModContainer();
        if (mc != null && NanIC.MODID.equals(mc.getModId())) {
            GeneratorsTE[] var1 = values();
            int var2 = var1.length;
            for(int var3 = 0; var3 < var2; ++var3) {
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

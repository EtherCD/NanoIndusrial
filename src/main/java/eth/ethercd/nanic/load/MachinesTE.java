package eth.ethercd.nanic.load;

import eth.ethercd.nanic.NanIC;
import eth.ethercd.nanic.machines.*;
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

import javax.annotation.Nullable;
import java.util.Set;

public enum MachinesTE implements ITeBlock {
    matter_transformer(MatterTransformer.class, 0, EnumRarity.RARE);

    private final Class<? extends TileEntityBlock> teClass;
    private final int itemMeta;
    private TileEntityBlock dummyTe;

    private final EnumRarity rarity;

    public static final ResourceLocation LOCATION = new ResourceLocation("nanic", "machines");

    MachinesTE(Class<? extends TileEntityBlock> teClass, int itemMeta, EnumRarity rarity) {
        this.teClass = teClass;
        this.itemMeta = itemMeta;
        this.rarity = rarity;
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
    public Class<? extends TileEntityBlock> getTeClass() {
        return this.teClass;
    }

    @Override
    public boolean hasActive() {
        return true;
    }

    @Override
    public Set<EnumFacing> getSupportedFacings() {
        return Util.horizontalFacings;
    }

    @Override
    public float getHardness() {
        return 1.0f;
    }

    @Override
    public float getExplosionResistance() {
        return 10.f;
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

    @Nullable
    @Override
    public TileEntityBlock getDummyTe() {
        return dummyTe;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public int getId() {
        return this.itemMeta;
    }

    public String[] getRecipesCategories() {
        return new String[] {this.getName()};
    }

    public Material getMaterial() {
        return Material.IRON;
    }

    public static void buildDummies() {
        ModContainer mc = Loader.instance().activeModContainer();
        if (mc != null && NanIC.MODID.equals(mc.getModId())) {
            MachinesTE[] var1 = values();
            int var2 = var1.length;
            for(int var3 = 1; var3 < var2; ++var3) {
                MachinesTE block = var1[var3];
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

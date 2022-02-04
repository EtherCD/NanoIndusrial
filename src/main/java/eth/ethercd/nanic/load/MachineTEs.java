package eth.ethercd.nanic.load;

import eth.ethercd.nanic.NanIC;
import ic2.core.block.ITeBlock;
import ic2.core.block.TileEntityBlock;
import ic2.core.ref.TeBlock;
import ic2.core.util.Util;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

import java.util.Set;

public enum MachineTEs implements ITeBlock {
    singular_compressor(eth.ethercd.nanic.machines.SingularCompressor.class, 0);

    private final Class<? extends TileEntityBlock> teClass;
    private final int itemMeta;
    private TileEntityBlock dummyTe;
    public static final ResourceLocation LOCATION = new ResourceLocation("nanic", "machines");

    MachineTEs(Class<? extends TileEntityBlock> teClass, int itemMeta) {
        this.teClass = teClass;
        this.itemMeta = itemMeta;
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

    public String[] getRecipeCategories() {
        return new String[]{this.getName()};
    }

    public static void buildDummies() {
        ModContainer mc = Loader.instance().activeModContainer();
        if (mc != null && NanIC.MODID.equals(mc.getModId())) {
            MachineTEs[] var1 = values();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                MachineTEs block = var1[var3];
                if (block.teClass != null) {
                    try {
                        block.dummyTe = block.teClass.newInstance();
                    } catch (Exception var6) {
                        if (Util.inDev()) {
                            var6.printStackTrace();
                        }
                    }
                }
            }

        } else {
            throw new IllegalAccessError("Don\'t mess with this please.");
        }
    }

    public TileEntityBlock getDummyTe() {
        return this.dummyTe;
    }
}
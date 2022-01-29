package eth.ethercd.nanic.items.electric.tools;

import ic2.core.IC2;
import ic2.core.init.BlocksItems;
import ic2.core.init.Localization;
import ic2.core.item.tool.HarvestLevel;
import ic2.core.item.tool.ItemDrill;
import ic2.core.ref.ItemName;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ScandiumDrill extends ItemDrill {
    public static int maxCharg=250000;
    public static byte tir = 3;
    public static short preUse = 196;
    public static float eff=20.f;
    public static boolean Reg=false;

    public ScandiumDrill() {
        super((ItemName)null,
                preUse,
                HarvestLevel.Iridium,
                maxCharg,
                160,
                tir,
                eff);
        this.rarity = EnumRarity.RARE;
        this.efficiency=50;
        ((ScandiumDrill) BlocksItems.registerItem(this, new ResourceLocation("nanic", "scandium_drill"))).setUnlocalizedName("scandium_drill");
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ItemName name) {
        ModelLoader.setCustomModelResourceLocation(this,0,new ModelResourceLocation("nanic:scandium_drill", (String)null));
    }

    public String locationReplace() {
        return "nanic." + super.getUnlocalizedName().substring(4);

    }

    @Override
    public int energyUse(ItemStack stack, World wolrd, BlockPos pos, IBlockState state) {
        return 35;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote&& IC2.keyboard.isModeSwitchKeyDown(player)) {
            if (!Reg) {
                Reg=true;
                IC2.platform.messagePlayer(player, Localization.translate("nanic.item.tooltip.drill.mode_on"));
            } else {
                Reg=false;
                IC2.platform.messagePlayer(player, Localization.translate("nanic.item.tooltip.drill.mode_off"));
            }
        }
        return super.onItemRightClick(world, player, hand);
    }
}

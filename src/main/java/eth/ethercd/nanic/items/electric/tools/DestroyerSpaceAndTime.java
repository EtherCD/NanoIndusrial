package eth.ethercd.nanic.items.electric.tools;

import ic2.core.IC2;
import ic2.core.init.BlocksItems;
import ic2.core.init.Localization;
import ic2.core.item.tool.HarvestLevel;
import ic2.core.item.tool.ItemDrill;
import ic2.core.ref.ItemName;
import ic2.core.util.LogCategory;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
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

import java.util.List;

public class DestroyerSpaceAndTime extends ItemDrill {
    public static int maxCharg=1000000000;
    public static byte tir = 6;
    public static short preUse = 2048;
    public static float eff=20.f;
    public static boolean Reg=false;

    public DestroyerSpaceAndTime() {
        super((ItemName)null,
                preUse,
                HarvestLevel.Iridium,
                maxCharg,
                160,
                tir,
                eff);
        this.rarity = EnumRarity.RARE;
        this.efficiency=999;
        this.attackSpeed= (float) 0.0000000000000000001;
        this.attackDamage=1000000000;
        ((DestroyerSpaceAndTime) BlocksItems.registerItem(this, new ResourceLocation("nanic", "destroyer_space_and_time"))).setUnlocalizedName("destroyer_space_and_time");
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ItemName name) {
        ModelLoader.setCustomModelResourceLocation(this,0,new ModelResourceLocation("nanic:destroyer_space_and_time", (String)null));
    }

    public String locationReplace() {
        return "nanic." + super.getUnlocalizedName().substring(4);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flagin) {
        super.addInformation(stack,world,tooltip,flagin);
        tooltip.add(Localization.translate("nanic.item.tooltip.destroyer.ops"));
    }

    @Override
    public int breakTime(ItemStack stack, World world, BlockPos pos, IBlockState state) {
        return 0;
    }

    @Override
    public boolean breakBlock(ItemStack stack, World world, BlockPos pos, IBlockState state) {
        IC2.log.debug(LogCategory.Block,state.getBlock().getOffsetType().name());
        return true;
    }

    @Override
    public int energyUse(ItemStack stack, World wolrd, BlockPos pos, IBlockState state) {
        return 1000000;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote&& IC2.keyboard.isModeSwitchKeyDown(player)) {
            if (!Reg) {
                Reg=true;
                IC2.platform.messagePlayer(player, Localization.translate("nanic.item.tooltip.destroyer.mode_on"));
            } else {
                Reg=false;
                IC2.platform.messagePlayer(player, Localization.translate("nanic.item.tooltip.destroyer.mode_off"));
            }
        }
        return super.onItemRightClick(world, player, hand);
    }
}

package eth.ethercd.nanic.items.electric;

import eth.ethercd.nanic.NanIC;
import ic2.core.IC2;
import ic2.core.init.BlocksItems;
import ic2.core.init.Localization;
import ic2.core.item.tool.HarvestLevel;
import ic2.core.item.tool.ItemDrill;
import ic2.core.ref.ItemName;
import ic2.core.util.StackUtil;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.IdentityHashMap;
import java.util.Map;

public class ScandiumDrill extends ItemDrill {
    public static int maxCharg=1000000;
    public static byte tir = 4;
    public static short preUse = 196;
    public static float eff=20.f;

    public ScandiumDrill() {
        super((ItemName)null,
                preUse,
                HarvestLevel.Iridium,
                maxCharg,
                160,
                tir,
                eff);
        ((ScandiumDrill) BlocksItems.registerItem(this, new ResourceLocation("nanic", "scandium_drill"))).setUnlocalizedName("scandium_drill");
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ItemName name) {
        ModelLoader.setCustomModelResourceLocation(this,0,new ModelResourceLocation("nanic:scandium_drill", (String)null));
    }

    public String locationReplace() {
        return "nanic."+super.getUnlocalizedName().substring(4);
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote&& IC2.keyboard.isModeSwitchKeyDown(player)) {
            Map<Enchantment, Integer> ench = new IdentityHashMap<>();
            ench.put(Enchantments.FORTUNE,Integer.valueOf(2));
            ItemStack stack = StackUtil.get(player, hand);
            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack) == 0) {
                ench.put(Enchantments.FORTUNE, Integer.valueOf(3));
                ench.put(Enchantments.EFFICIENCY, Integer.valueOf(5));
                IC2.platform.messagePlayer(player, Localization.translate("nanic.item.tooltip.drill.mode_on"));
            } else {
                ench.clear();
                IC2.platform.messagePlayer(player, Localization.translate("nanic.item.tooltip.drill.mode_off"));
            }
            EnchantmentHelper.setEnchantments(ench, stack);
        }
        return super.onItemRightClick(world, player, hand);
    }
}

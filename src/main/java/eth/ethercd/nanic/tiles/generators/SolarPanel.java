package eth.ethercd.nanic.tiles.generators;

import ic2.api.energy.EnergyNet;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IMultiEnergySource;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.api.network.INetworkUpdateListener;
import ic2.core.ContainerBase;
import ic2.core.IHasGui;
import ic2.core.block.TileEntityInventory;
import ic2.core.gui.dynamic.DynamicContainer;
import ic2.core.gui.dynamic.GuiParser;
import ic2.core.init.Localization;
import ic2.core.network.GuiSynced;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class SolarPanel extends TileEntityInventory implements IMultiEnergySource, INetworkClientTileEntityEventListener, INetworkUpdateListener {
    @GuiSynced
    public int tier;

    @GuiSynced
    protected int genMax;
    protected int genMin;
    private int generation;

    protected int maxOutput;

    private boolean addedToEnet;

    @Override
    protected void onLoaded() {
        super.onLoaded();
        if (!this.world.isRemote) {
            this.addedToEnet = !MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
        }
    }

    @Override
    protected void onUnloaded() {
        super.onUnloaded();
        if (this.addedToEnet) {
            this.addedToEnet = MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
        }
    }

    @Override
    protected void updateEntityServer() {
        super.updateEntityServer();
        boolean isDay=this.world.isDaytime();
        boolean canSee=this.world.canBlockSeeSky(this.pos.up()) &&
                (this.world.getBlockState(this.pos.up()).getMaterial().getMaterialMapColor() ==
                        MapColor.AIR) && !this.world.provider.isNether();
        boolean rain = this.world.getBiome(this.pos).getRainfall() > 0.0F && (this.world.isRaining() || this.world.isThundering());

        if (canSee) {
            if (isDay&&!rain) {
                this.generation=this.genMax;
            } else {
                this.generation=this.genMin;
            }
        } else {
            this.generation=0;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.generation=nbt.getInteger("production");
        this.tier=nbt.getInteger("tier");
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        nbt.setInteger("production", this.generation);
        nbt.setInteger("tier", this.tier);
        return nbt;
    }

    @Override
    public void onPlaced(ItemStack stack, EntityLivingBase player, EnumFacing facing) {
        super.onPlaced(stack,player,facing);
    }

    @Override
    public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing side) {
        return true;
    }

    // Method of gen energy
    @Override
    public double getOfferedEnergy() {
        //if (this.storage>=this.maxStorage)
        //    return 0;
        return this.generation;
    }

    @Override
    public void drawEnergy(double amount) {

    }

    @Override
    public int getSourceTier() {
        return this.tier;
    }

    @Override
    public boolean sendMultipleEnergyPackets() {
        return false;
        //return (double)this.generation-EnergyNet.instance.getPowerFromTier(this.tier)>0.0D;
    }

    @Override
    public int getMultipleEnergyPacketAmount() {
        return 0;
        //return (int)Math.round((double)this.generation);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, List tooltip, ITooltipFlag advanced) {
        super.addInformation(stack,tooltip,advanced);
        tooltip.add(Localization.translate("ic2.item.tooltip.PowerTier",
                this.tier)
        );
        tooltip.add(Localization.translate("nanic.item.tooltip.max_generation")+this.genMax);
        tooltip.add(Localization.translate("nanic.item.tooltip.min_generation")+this.genMin);
    }


    public String getTier() {
        return Integer.toString(this.tier);
    }

    @Override
    public void onNetworkEvent(EntityPlayer player, int event) {

    }
}


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
import ic2.core.gui.dynamic.DynamicGui;
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

import java.util.List;

public class SolarPanel extends TileEntityInventory implements IMultiEnergySource, IHasGui, INetworkClientTileEntityEventListener, INetworkUpdateListener {
    @GuiSynced
    public int tier;

    protected int genDay;
    protected int genNight;
    protected int genRain;
    protected int genNightRain;
    private boolean isDay;
    private boolean canSee;
    private boolean rain;
    private GenerationState generationState=GenerationState.NONE;

    private int gen;

    protected int maxOutput;

    private boolean addedToEnet;

    @Override
    protected void onLoaded() {
        super.onLoaded();
        if (!this.world.isRemote) {
            this.addedToEnet = !MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            this.isDay=this.world.isDaytime();
            this.canSee=this.world.canBlockSeeSky(this.pos.up()) &&
                    (this.world.getBlockState(this.pos.up()).getMaterial().getMaterialMapColor() ==
                            MapColor.AIR) && !this.world.provider.isNether();
            this.rain = this.world.getBiome(this.pos).getRainfall() > 0.0F && (this.world.isRaining() || this.world.isThundering());
            updateVis();
        }
        this.rerender();
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
        this.isDay=this.world.isDaytime();
        this.canSee=this.world.canBlockSeeSky(this.pos.up()) &&
                (this.world.getBlockState(this.pos.up()).getMaterial().getMaterialMapColor() ==
                        MapColor.AIR) && !this.world.provider.isNether();
        this.rain = this.world.getBiome(this.pos).getRainfall() > 0.0F && (this.world.isRaining() || this.world.isThundering());

        this.addedToEnet = !MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));

        generationState=updateVis();
    }
    private GenerationState updateVis() {
        if (!this.world.isRemote) {
            if (!this.canSee) {
                return GenerationState.NONE;
            }
            if (this.isDay && this.canSee) {
                if (!(this.rain)) {
                    return GenerationState.DAY;
                } else {
                    return GenerationState.RAIN;
                }
            }
            if (!this.isDay && this.canSee) {
                if (!(this.rain)) {
                    return GenerationState.NIGHT;
                } else {
                    return GenerationState.NIGHT_RAIN;
                }
            }
        }
        return GenerationState.NONE;
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.gen=nbt.getInteger("production");
        this.tier=nbt.getInteger("tier");
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        nbt.setInteger("production", getGenFromGenState());
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
    private int getGenFromGenState() {
        if (this.generationState==GenerationState.DAY)
            return genDay;
        else if (this.generationState==GenerationState.NIGHT)
            return genNight;
        else if (this.generationState==GenerationState.RAIN)
            return genRain;
        else if (this.generationState==GenerationState.NIGHT_RAIN)
            return genNightRain;
        return 0;
    }
    // Method of gen energy
    @Override
    public double getOfferedEnergy() {
        return this.getGenFromGenState();
    }
    @Override
    public void drawEnergy(double amount) {}
    @Override
    public int getSourceTier() {
        return this.tier;
    }
    @Override
    public boolean sendMultipleEnergyPackets() {
        return (double)getGenFromGenState()-EnergyNet.instance.getPowerFromTier(this.tier)>0.0D;
    }
    @Override
    public int getMultipleEnergyPacketAmount() {
        return (int)Math.round((double)getGenFromGenState());
    }
    @Override
    public void onBlockBreak() {
        this.generationState=GenerationState.NONE;
        this.notify();
        this.clear();
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, List tooltip, ITooltipFlag advanced) {
        super.addInformation(stack,tooltip,advanced);
        tooltip.add(Localization.translate("ic2.item.tooltip.PowerTier",
                this.tier)
        );
        tooltip.add(Localization.translate("ic2.item.tooltip.Output")
                +" "
                +this.maxOutput
                +" EU/t"
        );
        tooltip.add(Localization.translate("nanic.item.tooltip.day_generation")+this.genDay);
        tooltip.add(Localization.translate("nanic.item.tooltip.night_generation")+this.genNight);
    }
    public String getTier() {
        return Integer.toString(this.tier);
    }
    @Override
    public void onNetworkEvent(EntityPlayer player, int event) {}
    @Override
    public ContainerBase<?> getGuiContainer(EntityPlayer p) {
        return DynamicContainer.create(this, p, GuiParser.parse(this.teBlock));
    }
    @Override
    public GuiScreen getGui(EntityPlayer p, boolean b) {
        return DynamicGui.create(this, p, GuiParser.parse(this.teBlock));
    }
    @Override
    public void onGuiClosed(EntityPlayer entityPlayer) {}
}


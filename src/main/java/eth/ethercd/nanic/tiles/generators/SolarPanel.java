package eth.ethercd.nanic.tiles.generators;

import ic2.api.energy.EnergyNet;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IMultiEnergySource;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.api.network.INetworkUpdateListener;
import ic2.core.block.TileEntityInventory;
import ic2.core.init.Localization;
import ic2.core.network.GuiSynced;
import net.minecraft.block.material.MapColor;
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

public class SolarPanel extends TileEntityInventory implements IMultiEnergySource, INetworkClientTileEntityEventListener, INetworkUpdateListener {
    @GuiSynced
    public int tier;

    @GuiSynced
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

        updateVis();

    }

    private void updateVis() {
        if(!canSee) {
            generationState=GenerationState.NONE;
        }
        if (isDay&&canSee){
            if(!(rain)){
                generationState=GenerationState.DAY;
            } else {
                generationState=GenerationState.RAIN;
            }
        }
        if (!isDay&&canSee) {
            if(!(rain)) {
                generationState=GenerationState.NIGHT;
            } else {
                generationState=GenerationState.NIGHT_RAIN;
            }
        }
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
        nbt.setInteger("production", getGenFromGenState(generationState));
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

    private int getGenFromGenState(GenerationState genState) {
        if (generationState==GenerationState.DAY)
            return genDay;
        else if (generationState==GenerationState.NIGHT)
            return genNight;
        else if (generationState==GenerationState.RAIN)
            return genRain;
        else if (generationState==GenerationState.NIGHT_RAIN)
            return genNightRain;
        else
            return 0;
    }

    // Method of gen energy
    @Override
    public double getOfferedEnergy() {
        return getGenFromGenState(generationState);
    }

    @Override
    public void drawEnergy(double amount) {}

    @Override
    public int getSourceTier() {
        return this.tier;
    }

    @Override
    public boolean sendMultipleEnergyPackets() {
        return (double)getGenFromGenState(generationState)-EnergyNet.instance.getPowerFromTier(this.tier)>0.0D;
    }

    @Override
    public int getMultipleEnergyPacketAmount() {
        return (int)Math.round((double)getGenFromGenState(generationState));
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
}


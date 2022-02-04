package eth.ethercd.nanic;

import eth.ethercd.nanic.mcgui.GuiIntegration;
import eth.ethercd.nanic.jei.IJEIIntegration;
import eth.ethercd.nanic.load.GeneratorsTE;
import eth.ethercd.nanic.load.IC2ToolsLoader;
import eth.ethercd.nanic.load.MachineTEs;
import eth.ethercd.nanic.recipes.Recipes;
import eth.ethercd.nanic.proxies.CommonProxy;
import ic2.api.event.TeBlockFinalCallEvent;
import ic2.core.block.TeBlockRegistry;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid=NanIC.MODID,name=NanIC.NAME,version=NanIC.VERSION)
public class NanIC {
    @Mod.Instance("nanic")
    public static NanIC instance;

    @SidedProxy(clientSide=NanIC.CLIENT_PROXY,serverSide=NanIC.COMMON_PROXY)
    public static CommonProxy proxy;

    public static final String MODID="nanic";
    public static final String NAME="Nano Industrial";
    public static final String VERSION="1";

    public static final String CLIENT_PROXY="eth.ethercd.nanic.proxies.ClientProxy";
    public static final String COMMON_PROXY="eth.ethercd.nanic.proxies.CommonProxy";

    private static String ACTIVATED="activated";

    private Logger log;

    @EventHandler
    public void start(FMLConstructionEvent ev) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void reg(TeBlockFinalCallEvent ev) {
        TeBlockRegistry.addAll(GeneratorsTE.class, GeneratorsTE.LOCATION);
        TeBlockRegistry.addAll(MachineTEs.class, MachineTEs.LOCATION);
    }

    @EventHandler
    public void preLoad(FMLPreInitializationEvent ev) {
        log=ev.getModLog();

        //TOOLS
        IC2ToolsLoader.buildItems(ev.getSide());

        //FOUR
        MinecraftForge.EVENT_BUS.register(new GuiIntegration(Minecraft.getMinecraft()));

        //JEI
        new IJEIIntegration();
    }
    @EventHandler
    public void load(FMLInitializationEvent ev) {
        Recipes.addCraftingRecipes();
        Recipes.addMachineRecipe();
        GeneratorsTE.buildDummies();
        MachineTEs.buildDummies();
        log.info("NanIC is "+ACTIVATED);

    }
    @EventHandler
    public void postLoad(FMLPostInitializationEvent ev) {
    }
}

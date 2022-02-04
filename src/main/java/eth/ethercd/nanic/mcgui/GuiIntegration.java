package eth.ethercd.nanic.mcgui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiIntegration extends GuiIngame {

    private Minecraft mc;
    private FontRenderer fr;

    public GuiIntegration(Minecraft mc) {
        super(mc);
        this.mc = mc;
        this.fr = this.getFontRenderer();
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void renderStats(RenderGameOverlayEvent event)
    {
        if (event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE)
            return;
        // Show
        drawString(this.getFontRenderer(), "Beta version of NanIC", 0, 0, 0xFF00000);
    }
}
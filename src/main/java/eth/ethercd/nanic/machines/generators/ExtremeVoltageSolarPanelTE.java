package eth.ethercd.nanic.machines.generators;

import eth.ethercd.nanic.tiles.generators.SolarPanel;

public class ExtremeVoltageSolarPanelTE extends SolarPanel {
    public ExtremeVoltageSolarPanelTE() {
        this.tier=4;
        this.genDay=1024;
        this.genNight=513;
        this.genRain=750;
        this.genNightRain=270;
        this.maxOutput=2048;
    }
}

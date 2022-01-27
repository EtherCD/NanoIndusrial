package eth.ethercd.nanic.machines.generators;

import eth.ethercd.nanic.tiles.generators.SolarPanel;

public class LowVoltageSolarPanelTE extends SolarPanel {
    public LowVoltageSolarPanelTE() {
        this.tier=1;
        this.genDay=16;
        this.genNight=8;
        this.genRain=10;
        this.genNightRain=4;
        this.maxOutput=32;
    }
}
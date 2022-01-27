package eth.ethercd.nanic.machines.generators;

import eth.ethercd.nanic.tiles.generators.SolarPanel;

public class HighVoltageSolarPanelTE extends SolarPanel {
    public HighVoltageSolarPanelTE() {
        this.tier=3;
        this.genDay=256;
        this.genNight=129;
        this.genRain=160;
        this.genNightRain=64;
        this.maxOutput=512;
    }
}

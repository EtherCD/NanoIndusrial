package eth.ethercd.nanic.machines.generators;

import eth.ethercd.nanic.tiles.generators.SolarPanel;

public class MediumVoltageSolarPanelTE extends SolarPanel {
    public MediumVoltageSolarPanelTE() {
        this.tier=2;
        this.genDay=64;
        this.genNight=33;
        this.genRain=45;
        this.genNightRain=25;
        this.maxOutput=128;
    }
}

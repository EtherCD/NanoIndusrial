package eth.ethercd.nanic.machines;

import eth.ethercd.nanic.recipes.MyRecipeFactory;
import eth.ethercd.nanic.tiles.ExampleMachine;

public class SingularCompressor extends ExampleMachine {
    public SingularCompressor() {
        super(3, MyRecipeFactory.singularCompressor, 100, 1000, 120000);
    }
}
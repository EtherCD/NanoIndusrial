package eth.ethercd.nanic.machines;

import ic2.api.recipe.Recipes;

public class MatterTransformer extends ExampleMachineTE{
    MatterTransformer() {
        super(4, Recipes.matterTransformer);
    }
}

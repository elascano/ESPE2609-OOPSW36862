package ec.edu.espe.animalfarmapp.model;

/**
 * Interface defining the public contract for all farm animals.
 *
 * @author Lanchimba
 */
public interface IFarmAnimal {

    /**
     * Computes the monthly food cost for this animal in USD.
     *
     * @return monthly food cost
     */
    float computeFoodCost();

    /**
     * Feeds the animal using its default farm ration.
     *
     * @return feeding action description
     */
    String feed();

    /**
     * Describes the specific output or production of the animal.
     *
     * @return production description
     */
    String produce();
}

/**
 * Interface defining the public contract for all farm animals.
 */
export interface IFarmAnimal {
  /**
   * Computes the monthly food cost for this animal in USD.
   */
  computeFoodCost(): number;

  /**
   * Feeds the animal using its default farm ration.
   */
  feed(): string;

  /**
   * Describes the specific output or production of the animal.
   */
  produce(): string;
}

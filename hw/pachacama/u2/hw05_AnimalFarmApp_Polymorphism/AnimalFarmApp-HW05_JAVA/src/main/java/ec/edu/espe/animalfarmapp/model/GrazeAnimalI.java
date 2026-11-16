package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 * @author Andrés Pachacama, ByteWorks, @ESPE
 */
public interface GrazeAnimalI {
    public void sendToGraze(Location location, Date date);
}

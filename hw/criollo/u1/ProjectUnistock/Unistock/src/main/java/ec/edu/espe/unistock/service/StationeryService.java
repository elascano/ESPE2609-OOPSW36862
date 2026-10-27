package ec.edu.espe.unistock.service;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.structure.FileHandler;
import ec.edu.espe.unistock.structure.SimpleList;

public class StationeryService {

    private final SimpleList<String> registeredBrands;
    private final SimpleList<String> registeredTypes;

    private final String brandsFile = "brands.json";
    private final String typesFile = "types.json";

    public StationeryService() {
        this.registeredBrands = FileHandler.loadList(brandsFile);
        this.registeredTypes = FileHandler.loadList(typesFile);
    }

    public void registerBrand(String brand) {
        if (brand != null && !brand.trim().isEmpty() && !existsBrand(brand)) {
            registeredBrands.add(brand.trim());
            FileHandler.saveList(brandsFile, registeredBrands);
        }
    }

    public boolean existsBrand(String brand) {
        if (brand == null) {
            return false;
        }

        registeredBrands.resetIteration();
        while (registeredBrands.hasNext()) {
            String currentBrand = registeredBrands.next();
            if (currentBrand.equalsIgnoreCase(brand.trim())) {
                return true;
            }
        }
        return false;
    }

    public void registerType(String type) {
        if (type != null && !type.trim().isEmpty() && !existsType(type)) {
            registeredTypes.add(type.trim());
            FileHandler.saveList(typesFile, registeredTypes);
        }
    }

    public boolean existsType(String type) {
        if (type == null) {
            return false;
        }

        registeredTypes.resetIteration();
        while (registeredTypes.hasNext()) {
            String currentType = registeredTypes.next();
            if (currentType.equalsIgnoreCase(type.trim())) {
                return true;
            }
        }
        return false;
    }

    public SimpleList<String> getRegisteredBrands() {
        return registeredBrands;
    }

    public SimpleList<String> getRegisteredTypes() {
        return registeredTypes;
    }
}
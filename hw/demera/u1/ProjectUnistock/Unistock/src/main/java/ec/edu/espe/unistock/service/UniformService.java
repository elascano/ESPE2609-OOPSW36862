package ec.edu.espe.unistock.service;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.structure.FileHandler;
import ec.edu.espe.unistock.structure.SimpleList;

public class UniformService {

    private final SimpleList<String> registeredSizes;
    private final SimpleList<String> registeredSchools;

    private final String sizesFile = "sizes.json";
    private final String schoolsFile = "schools.json";

    public UniformService() {
        this.registeredSizes = FileHandler.loadList(sizesFile);
        this.registeredSchools = FileHandler.loadList(schoolsFile);
    }

    public void registerSize(String size) {
        if (size != null && !size.trim().isEmpty() && !existsSize(size)) {
            registeredSizes.add(size.trim().toUpperCase());
            FileHandler.saveList(sizesFile, registeredSizes);
        }
    }

    public boolean existsSize(String size) {
        if (size == null) {
            return false;
        }

        registeredSizes.resetIteration();
        while (registeredSizes.hasNext()) {
            String currentSize = registeredSizes.next();
            if (currentSize.equalsIgnoreCase(size.trim())) {
                return true;
            }
        }
        return false;
    }

    public void registerSchool(String school) {
        if (school != null && !school.trim().isEmpty() && !existsSchool(school)) {
            registeredSchools.add(school.trim());
            FileHandler.saveList(schoolsFile, registeredSchools);
        }
    }

    public boolean existsSchool(String school) {
        if (school == null) {
            return false;
        }

        registeredSchools.resetIteration();
        while (registeredSchools.hasNext()) {
            String currentSchool = registeredSchools.next();
            if (currentSchool.equalsIgnoreCase(school.trim())) {
                return true;
            }
        }
        return false;
    }

    public SimpleList<String> getRegisteredSizes() {
        return registeredSizes;
    }

    public SimpleList<String> getRegisteredSchools() {
        return registeredSchools;
    }
}
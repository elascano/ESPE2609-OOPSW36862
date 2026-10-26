package ec.edu.espe.unistock.view;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.service.StationeryService;
import ec.edu.espe.unistock.service.UniformService;

public class CatalogMenuView {

    private final UniformService uniformService;
    private final StationeryService stationeryService;
    private final ConsoleAdapter console;

    public CatalogMenuView(UniformService uniformService, StationeryService stationeryService, ConsoleAdapter console) {
        this.uniformService = uniformService;
        this.stationeryService = stationeryService;
        this.console = console;
    }

    public void manageCatalogs() {
        boolean inCatalogsMenu = true;
        while (inCatalogsMenu) {
            System.out.println("\n--- GESTIÓN DE CATÁLOGOS ---");
            System.out.println("1. Registrar Talla de Uniforme");
            System.out.println("2. Registrar Colegio / Institución");
            System.out.println("3. Registrar Marca de Útiles");
            System.out.println("4. Registrar Tipo de Artículo o Material");
            System.out.println("5. Listar Catálogos Registrados");
            System.out.println("0. Volver al menú principal");
            int option = console.readInt("Opción: ");

            switch (option) {
                case 1 -> {
                    String size = console.readText("Ingrese nueva talla (ej: S, M, L, XL): ");
                    uniformService.registerSize(size);
                    System.out.println("Talla procesada.");
                }
                case 2 -> {
                    String school = console.readText("Ingrese nombre del colegio/institución: ");
                    uniformService.registerSchool(school);
                    System.out.println("Colegio procesado.");
                }
                case 3 -> {
                    String brand = console.readText("Ingrese marca (ej: Norma, Faber-Castell): ");
                    stationeryService.registerBrand(brand);
                    System.out.println("Marca procesada.");
                }
                case 4 -> {
                    String type = console.readText("Ingrese tipo de artículo (ej: Cuaderno, Lápiz): ");
                    stationeryService.registerType(type);
                    System.out.println("Tipo de artículo procesado.");
                }
                case 5 -> listCatalogs();
                case 0 -> inCatalogsMenu = false;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private void listCatalogs() {
        System.out.println("\n--- CATÁLOGOS REGISTRADOS ---");
        
        System.out.println("Tallas:");
        var sizes = uniformService.getRegisteredSizes();
        sizes.resetIteration();
        while (sizes.hasNext()) System.out.println("  - " + sizes.next());

        System.out.println("Colegios:");
        var schools = uniformService.getRegisteredSchools();
        schools.resetIteration();
        while (schools.hasNext()) System.out.println("  - " + schools.next());

        System.out.println("Marcas:");
        var brands = stationeryService.getRegisteredBrands();
        brands.resetIteration();
        while (brands.hasNext()) System.out.println("  - " + brands.next());

        System.out.println("Tipos:");
        var types = stationeryService.getRegisteredTypes();
        types.resetIteration();
        while (types.hasNext()) System.out.println("  - " + types.next());
    }
}
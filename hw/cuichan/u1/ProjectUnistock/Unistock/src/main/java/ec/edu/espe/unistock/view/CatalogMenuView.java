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
            System.out.println("\n--- GESTION DE CATALOGOS ---");
            System.out.println("1. Registrar Talla de Uniforme");
            System.out.println("2. Registrar Colegio / Institucion");
            System.out.println("3. Registrar Marca de Utiles");
            System.out.println("4. Registrar Tipo de Articulo o Material");
            System.out.println("5. Listar Catalogos Registrados");
            System.out.println("0. Volver al menu principal");
            int option = console.readInt("Opcion: ");

            switch (option) {
                case 1 -> {
                    String size = console.readText("Ingrese nueva talla (ej: S, M, L, XL): ");
                    uniformService.registerSize(size);
                    System.out.println("Talla procesada.");
                }
                case 2 -> {
                    String school = console.readText("Ingrese nombre del colegio/institucion: ");
                    uniformService.registerSchool(school);
                    System.out.println("Colegio procesado.");
                }
                case 3 -> {
                    String brand = console.readText("Ingrese marca (ej: Norma, Faber-Castell): ");
                    stationeryService.registerBrand(brand);
                    System.out.println("Marca procesada.");
                }
                case 4 -> {
                    String type = console.readText("Ingrese tipo de articulo (ej: Cuaderno, Lapiz): ");
                    stationeryService.registerType(type);
                    System.out.println("Tipo de articulo procesado.");
                }
                case 5 -> listCatalogs();
                case 0 -> inCatalogsMenu = false;
                default -> System.out.println("Opcion invalida.");
            }
        }
    }

    private void listCatalogs() {
        System.out.println("\n--- CATALOGOS REGISTRADOS ---");
        
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
package ec.edu.espe.unistock.service;

import ec.edu.espe.unistock.model.Product;
import ec.edu.espe.unistock.model.ProductCategory;
import ec.edu.espe.unistock.structure.SimpleList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private static final String TEST_DIR = "testProductServiceOutput";
    private UniformService uniformService;
    private StationeryService stationeryService;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        File dir = new File(TEST_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        uniformService = new UniformService();
        stationeryService = new StationeryService();
        productService = new ProductService(uniformService, stationeryService);
    }

    @AfterEach
    void tearDown() {
        File dir = new File(TEST_DIR);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
            dir.delete();
        }
    }

    @Test
    void testRegisterUniformValidParameters() {
        Product p = productService.registerUniform("Camisa Formal", new BigDecimal("12.50"), 10, "M", "San Gabriel");
        assertNotNull(p);
        assertEquals("Camisa Formal", p.getName());
    }

    @Test
    void testRegisterUniformInvalidSizeReturnsNull() {
        Product p = productService.registerUniform("Camisa Formal", new BigDecimal("12.50"), 10, "TallaInexistente", "San Gabriel");
        assertNull(p);
    }

    @Test
    void testRegisterUniformInvalidSchoolReturnsNull() {
        Product p = productService.registerUniform("Camisa Formal", new BigDecimal("12.50"), 10, "M", "ColegioInexistente");
        assertNull(p);
    }

    @Test
    void testRegisterUniformIncrementsId() {
        Product p1 = productService.registerUniform("Camisa", new BigDecimal("10.00"), 5, "M", "San Gabriel");
        Product p2 = productService.registerUniform("Pantalon", new BigDecimal("15.00"), 5, "32", "Mejia");
        assertEquals(1, p1.getId());
        assertEquals(2, p2.getId());
    }

    @Test
    void testRegisterUniformAddsToProductsList() {
        productService.registerUniform("Buzo", new BigDecimal("20.00"), 5, "L", "Benalcazar");
        assertEquals(1, productService.getProducts().size());
    }

    @Test
    void testRegisterUniformAssignsUniformCategory() {
        Product p = productService.registerUniform("Falda", new BigDecimal("14.00"), 8, "S", "San Gabriel");
        assertEquals(ProductCategory.UNIFORM, p.getCategory());
    }

    @Test
    void testRegisterUniformZeroPriceAllowed() {
        Product p = productService.registerUniform("Donacion", BigDecimal.ZERO, 5, "M", "San Gabriel");
        assertNotNull(p);
        assertEquals(BigDecimal.ZERO, p.getPrice());
    }

    @Test
    void testRegisterUniformZeroStockAllowed() {
        Product p = productService.registerUniform("Agotado", new BigDecimal("10.00"), 0, "M", "San Gabriel");
        assertNotNull(p);
        assertEquals(0, p.getStock());
    }

    @Test
    void testRegisterUniformInvalidPriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            productService.registerUniform("Camisa", new BigDecimal("-10.00"), 5, "M", "San Gabriel")
        );
    }

    @Test
    void testRegisterUniformInvalidStockThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            productService.registerUniform("Camisa", new BigDecimal("10.00"), -5, "M", "San Gabriel")
        );
    }

    @Test
    void testRegisterStationeryValidParameters() {
        Product p = productService.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 50, "Norma", "Cuaderno");
        assertNotNull(p);
        assertEquals("Cuaderno 100 Hojas", p.getName());
    }

    @Test
    void testRegisterStationeryInvalidBrandReturnsNull() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 50, "MarcaFalsa", "Cuaderno");
        assertNull(p);
    }

    @Test
    void testRegisterStationeryInvalidTypeReturnsNull() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 50, "Norma", "TipoFalso");
        assertNull(p);
    }

    @Test
    void testRegisterStationeryIncrementsId() {
        Product p1 = productService.registerStationery("Lapiz", new BigDecimal("0.50"), 20, "FaberCastell", "Lapiz");
        Product p2 = productService.registerStationery("Borrador", new BigDecimal("0.30"), 30, "Pelikan", "Borrador");
        assertEquals(1, p1.getId());
        assertEquals(2, p2.getId());
    }

    @Test
    void testRegisterStationeryAddsToProductsList() {
        productService.registerStationery("Esfero BIC", new BigDecimal("0.40"), 100, "BIC", "Esfero");
        assertEquals(1, productService.getProducts().size());
    }

    @Test
    void testRegisterStationeryAssignsStationeryCategory() {
        Product p = productService.registerStationery("Resaltador", new BigDecimal("0.90"), 15, "Pelikan", "Marcador");
        assertEquals(ProductCategory.STATIONERY, p.getCategory());
    }

    @Test
    void testRegisterStationeryZeroPriceAllowed() {
        Product p = productService.registerStationery("Muestra", BigDecimal.ZERO, 10, "Norma", "Cuaderno");
        assertNotNull(p);
        assertEquals(BigDecimal.ZERO, p.getPrice());
    }

    @Test
    void testRegisterStationeryZeroStockAllowed() {
        Product p = productService.registerStationery("Regla", new BigDecimal("1.00"), 0, "Artesco", "Regla");
        assertNotNull(p);
        assertEquals(0, p.getStock());
    }

    @Test
    void testRegisterStationeryInvalidPriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            productService.registerStationery("Cuaderno", new BigDecimal("-2.50"), 50, "Norma", "Cuaderno")
        );
    }

    @Test
    void testRegisterStationeryInvalidStockThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            productService.registerStationery("Cuaderno", new BigDecimal("2.50"), -10, "Norma", "Cuaderno")
        );
    }

    @Test
    void testFindByCategoryStationery() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.registerUniform("Camisa", new BigDecimal("10.00"), 5, "M", "San Gabriel");
        SimpleList<Product> result = productService.findByCategory(ProductCategory.STATIONERY);
        assertEquals(1, result.size());
    }

    @Test
    void testFindByCategoryUniform() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.registerUniform("Camisa", new BigDecimal("10.00"), 5, "M", "San Gabriel");
        SimpleList<Product> result = productService.findByCategory(ProductCategory.UNIFORM);
        assertEquals(1, result.size());
    }

    @Test
    void testFindByCategoryEmptyResult() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> result = productService.findByCategory(ProductCategory.UNIFORM);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindByCategoryNullReturnsEmptyList() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> result = productService.findByCategory(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindByCategoryMultipleStationery() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.registerStationery("Lapiz", new BigDecimal("0.50"), 20, "FaberCastell", "Lapiz");
        SimpleList<Product> result = productService.findByCategory(ProductCategory.STATIONERY);
        assertEquals(2, result.size());
    }

    @Test
    void testFindByCategoryMultipleUniforms() {
        productService.registerUniform("Camisa", new BigDecimal("10.00"), 5, "M", "San Gabriel");
        productService.registerUniform("Pantalon", new BigDecimal("15.00"), 5, "32", "Mejia");
        SimpleList<Product> result = productService.findByCategory(ProductCategory.UNIFORM);
        assertEquals(2, result.size());
    }

    @Test
    void testFindByCategoryOrderPreserved() {
        Product p1 = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Product p2 = productService.registerStationery("Lapiz", new BigDecimal("0.50"), 20, "FaberCastell", "Lapiz");
        SimpleList<Product> result = productService.findByCategory(ProductCategory.STATIONERY);
        result.resetIteration();
        assertEquals(p1, result.next());
        assertEquals(p2, result.next());
    }

    @Test
    void testFindByCategoryWhenNoProductsExist() {
        SimpleList<Product> result = productService.findByCategory(ProductCategory.STATIONERY);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindByCategoryReturnsNewListInstance() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> result = productService.findByCategory(ProductCategory.STATIONERY);
        assertNotSame(productService.getProducts(), result);
    }

    @Test
    void testFindByCategoryDoesNotModifyOriginalList() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.findByCategory(ProductCategory.STATIONERY);
        assertEquals(1, productService.getProducts().size());
    }

    @Test
    void testUpdateStockIncreaseValid() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        boolean updated = productService.updateStock(p.getId(), 5);
        assertTrue(updated);
        assertEquals(15, p.getStock());
    }

    @Test
    void testUpdateStockDecreaseValid() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        boolean updated = productService.updateStock(p.getId(), -5);
        assertTrue(updated);
        assertEquals(5, p.getStock());
    }

    @Test
    void testUpdateStockNonExistentProductReturnsFalse() {
        boolean updated = productService.updateStock(999, 5);
        assertFalse(updated);
    }

    @Test
    void testUpdateStockExcessiveReductionThrowsException() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertThrows(IllegalArgumentException.class, () -> productService.updateStock(p.getId(), -15));
    }

    @Test
    void testUpdateStockToExactZero() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        boolean updated = productService.updateStock(p.getId(), -10);
        assertTrue(updated);
        assertEquals(0, p.getStock());
    }

    @Test
    void testUpdateStockZeroQuantityChangeThrowsExceptionOnIncrease() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertThrows(IllegalArgumentException.class, () -> productService.updateStock(p.getId(), 0));
    }

    @Test
    void testUpdateStockMultipleTimes() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.updateStock(p.getId(), 10);
        productService.updateStock(p.getId(), -5);
        assertEquals(15, p.getStock());
    }

    @Test
    void testUpdateStockUniformProduct() {
        Product p = productService.registerUniform("Camisa", new BigDecimal("10.00"), 5, "M", "San Gabriel");
        boolean updated = productService.updateStock(p.getId(), 10);
        assertTrue(updated);
        assertEquals(15, p.getStock());
    }

    @Test
    void testUpdateStockLargeQuantity() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.updateStock(p.getId(), 1000);
        assertEquals(1010, p.getStock());
    }

    @Test
    void testUpdateStockExceptionMessage() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> productService.updateStock(p.getId(), -20));
        assertTrue(ex.getMessage().contains("No se puede reducir"));
    }

    @Test
    void testRemoveProductValidId() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        boolean removed = productService.removeProduct(p.getId());
        assertTrue(removed);
        assertTrue(productService.getProducts().isEmpty());
    }

    @Test
    void testRemoveProductNonExistentIdReturnsFalse() {
        boolean removed = productService.removeProduct(999);
        assertFalse(removed);
    }

    @Test
    void testRemoveProductFromMultiple() {
        Product p1 = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Product p2 = productService.registerStationery("Lapiz", new BigDecimal("0.50"), 20, "FaberCastell", "Lapiz");
        productService.removeProduct(p1.getId());
        assertEquals(1, productService.getProducts().size());
        assertNull(productService.findById(p1.getId()));
        assertNotNull(productService.findById(p2.getId()));
    }

    @Test
    void testRemoveProductTwiceReturnsFalse() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.removeProduct(p.getId());
        boolean secondRemove = productService.removeProduct(p.getId());
        assertFalse(secondRemove);
    }

    @Test
    void testRemoveProductUniform() {
        Product p = productService.registerUniform("Camisa", new BigDecimal("10.00"), 5, "M", "San Gabriel");
        boolean removed = productService.removeProduct(p.getId());
        assertTrue(removed);
    }

    @Test
    void testRemoveProductDecreasesListSize() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertEquals(1, productService.getProducts().size());
        productService.removeProduct(p.getId());
        assertEquals(0, productService.getProducts().size());
    }

    @Test
    void testRemoveProductDoesNotAffectOtherProducts() {
        Product p1 = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Product p2 = productService.registerUniform("Camisa", new BigDecimal("10.00"), 5, "M", "San Gabriel");
        productService.removeProduct(p1.getId());
        assertEquals("Camisa", productService.findById(p2.getId()).getName());
    }

    @Test
    void testRemoveProductFromEmptyListReturnsFalse() {
        assertFalse(productService.removeProduct(1));
    }

    @Test
    void testRemoveProductNegativeIdReturnsFalse() {
        assertFalse(productService.removeProduct(-5));
    }

    @Test
    void testRemoveProductZeroIdReturnsFalse() {
        assertFalse(productService.removeProduct(0));
    }

    @Test
    void testFindByIdValidId() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Product found = productService.findById(p.getId());
        assertEquals(p, found);
    }

    @Test
    void testFindByIdNonExistentReturnsNull() {
        assertNull(productService.findById(999));
    }

    @Test
    void testFindByIdZeroReturnsNull() {
        assertNull(productService.findById(0));
    }

    @Test
    void testFindByIdNegativeReturnsNull() {
        assertNull(productService.findById(-1));
    }

    @Test
    void testFindByIdFirstItemInList() {
        Product p1 = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.registerStationery("Lapiz", new BigDecimal("0.50"), 20, "FaberCastell", "Lapiz");
        assertEquals(p1, productService.findById(p1.getId()));
    }

    @Test
    void testFindByIdLastItemInList() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Product p2 = productService.registerStationery("Lapiz", new BigDecimal("0.50"), 20, "FaberCastell", "Lapiz");
        assertEquals(p2, productService.findById(p2.getId()));
    }

    @Test
    void testFindByIdUniformProduct() {
        Product p = productService.registerUniform("Camisa", new BigDecimal("10.00"), 5, "M", "San Gabriel");
        assertEquals(p, productService.findById(p.getId()));
    }

    @Test
    void testFindByIdAfterStockUpdate() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.updateStock(p.getId(), 5);
        Product found = productService.findById(p.getId());
        assertEquals(15, found.getStock());
    }

    @Test
    void testFindByIdAfterRemovalReturnsNull() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.removeProduct(p.getId());
        assertNull(productService.findById(p.getId()));
    }

    @Test
    void testFindByIdConsistentCall() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertEquals(productService.findById(p.getId()), productService.findById(p.getId()));
    }

    @Test
    void testSearchByNameExactMatch() {
        productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> result = productService.searchByName("Cuaderno Norma");
        assertEquals(1, result.size());
    }

    @Test
    void testSearchByNamePartialMatch() {
        productService.registerStationery("Cuaderno Universitario Norma", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> result = productService.searchByName("Norma");
        assertEquals(1, result.size());
    }

    @Test
    void testSearchByNameIgnoreCase() {
        productService.registerStationery("CUADERNO NORMA", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> result = productService.searchByName("cuaderno");
        assertEquals(1, result.size());
    }

    @Test
    void testSearchByNameNullReturnsEmptyList() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> result = productService.searchByName(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSearchByNameBlankReturnsEmptyList() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> result = productService.searchByName("   ");
        assertTrue(result.isEmpty());
    }

    @Test
    void testSearchByNameNoMatchesReturnsEmptyList() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> result = productService.searchByName("Lapiz");
        assertTrue(result.isEmpty());
    }

    @Test
    void testSearchByNameMultipleMatches() {
        productService.registerStationery("Cuaderno Norma 1", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.registerStationery("Cuaderno Norma 2", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> result = productService.searchByName("Cuaderno");
        assertEquals(2, result.size());
    }

    @Test
    void testSearchByNameTrimsKeyword() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> result = productService.searchByName("  Cuaderno  ");
        assertEquals(1, result.size());
    }

    @Test
    void testSearchByNameUniformAndStationery() {
        productService.registerStationery("Camisa Papeleria", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.registerUniform("Camisa Formal", new BigDecimal("10.00"), 5, "M", "San Gabriel");
        SimpleList<Product> result = productService.searchByName("Camisa");
        assertEquals(2, result.size());
    }

    @Test
    void testSearchByNameOnEmptyProductsList() {
        SimpleList<Product> result = productService.searchByName("Norma");
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetProductsInitialStateEmpty() {
        assertTrue(productService.getProducts().isEmpty());
    }

    @Test
    void testGetProductsReflectsRegisteredProducts() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertEquals(1, productService.getProducts().size());
    }

    @Test
    void testGetProductsSameReference() {
        assertSame(productService.getProducts(), productService.getProducts());
    }

    @Test
    void testGetProductsReflectsRemoval() {
        Product p = productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.removeProduct(p.getId());
        assertTrue(productService.getProducts().isEmpty());
    }

    @Test
    void testGetProductsContainsStationeryAndUniforms() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.registerUniform("Camisa", new BigDecimal("10.00"), 5, "M", "San Gabriel");
        assertEquals(2, productService.getProducts().size());
    }

    @Test
    void testGetProductsAllowsIteration() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> products = productService.getProducts();
        products.resetIteration();
        assertTrue(products.hasNext());
        assertNotNull(products.next());
    }

    @Test
    void testGetProductsNotNull() {
        assertNotNull(productService.getProducts());
    }

    @Test
    void testGetProductsConsistentCall() {
        assertEquals(productService.getProducts(), productService.getProducts());
    }

    @Test
    void testGetProductsMultipleAdditions() {
        for (int i = 0; i < 5; i++) {
            productService.registerStationery("Item" + i, new BigDecimal("1.00"), 5, "Norma", "Cuaderno");
        }
        assertEquals(5, productService.getProducts().size());
    }

    @Test
    void testGetProductsSizeMatches() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertEquals(1, productService.getProducts().size());
    }

    @Test
    void testExportReportCsvValidFileGeneration() throws IOException {
        String path = TEST_DIR + "/productos.csv";
        productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 50, "Norma", "Cuaderno");
        productService.exportReportCSV(path);

        File file = new File(path);
        assertTrue(file.exists());
        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(2, lines.size());
        assertEquals("ID,Categoría,Nombre,Precio,Stock,Detalles", lines.get(0));
    }

    @Test
    void testExportReportCsvAutoAppendsExtension() {
        String path = TEST_DIR + "/productosReport";
        productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 50, "Norma", "Cuaderno");
        productService.exportReportCSV(path);

        assertTrue(new File(path + ".csv").exists());
    }

    @Test
    void testExportReportCsvContainsStationeryData() throws IOException {
        String path = TEST_DIR + "/stationeryReport.csv";
        productService.registerStationery("Cuaderno Norma", new BigDecimal("2.50"), 50, "Norma", "Cuaderno");
        productService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertTrue(lines.get(1).contains("Cuaderno Norma") && lines.get(1).contains("Papelería"));
    }

    @Test
    void testExportReportCsvContainsUniformData() throws IOException {
        String path = TEST_DIR + "/uniformReport.csv";
        productService.registerUniform("Camisa Formal", new BigDecimal("12.50"), 20, "M", "San Gabriel");
        productService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertTrue(lines.get(1).contains("Camisa Formal") && lines.get(1).contains("Uniforme"));
    }

    @Test
    void testExportReportCsvReplacesCommasInDetails() throws IOException {
        String path = TEST_DIR + "/commaDetails.csv";
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertFalse(lines.get(1).contains(", |"));
    }

    @Test
    void testExportReportCsvEmptyProductsList() throws IOException {
        String path = TEST_DIR + "/emptyProductos.csv";
        productService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(1, lines.size());
        assertEquals("ID,Categoría,Nombre,Precio,Stock,Detalles", lines.get(0));
    }

    @Test
    void testExportReportCsvMultipleProducts() throws IOException {
        String path = TEST_DIR + "/multiProductos.csv";
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.registerUniform("Camisa", new BigDecimal("10.00"), 5, "M", "San Gabriel");
        productService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(3, lines.size());
    }

    @Test
    void testExportReportCsvNullPathDoesNotCrash() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertDoesNotThrow(() -> productService.exportReportCSV(null));
    }

    @Test
    void testExportReportCsvBlankPathDoesNotCrash() {
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertDoesNotThrow(() -> productService.exportReportCSV("   "));
    }

    @Test
    void testExportReportCsvOverwritesExistingFile() throws IOException {
        String path = TEST_DIR + "/overwriteProductos.csv";
        productService.registerStationery("Cuaderno", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        productService.exportReportCSV(path);

        productService.registerUniform("Camisa", new BigDecimal("10.00"), 5, "M", "San Gabriel");
        productService.exportReportCSV(path);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(3, lines.size());
    }
}
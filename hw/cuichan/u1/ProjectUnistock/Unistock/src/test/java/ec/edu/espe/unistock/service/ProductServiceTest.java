package ec.edu.espe.unistock.service;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import ec.edu.espe.unistock.model.Product;
import ec.edu.espe.unistock.model.ProductCategory;
import ec.edu.espe.unistock.structure.SimpleList;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private UniformService uniformService;
    private StationeryService stationeryService;
    private ProductService instance;

    public ProductServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        uniformService = new UniformService();
        stationeryService = new StationeryService();
        instance = new ProductService(uniformService, stationeryService);
    }

    @AfterEach
    public void tearDown() {
        uniformService = null;
        stationeryService = null;
        instance = null;
    }

    @Test
    public void testRegisterUniformValidCatalog() {
        uniformService.registerSize("M");
        uniformService.registerSchool("Colegio Conocoto");
        Product product = instance.registerUniform("Camisa Escolar", new BigDecimal("15.50"), 20, "M", "Colegio Conocoto");
        assertNotNull(product);
    }

    @Test
    public void testRegisterUniformCheckIdAssignment() {
        uniformService.registerSize("M");
        uniformService.registerSchool("Colegio Conocoto");
        Product product = instance.registerUniform("Camisa Escolar", new BigDecimal("15.50"), 20, "M", "Colegio Conocoto");
        assertEquals(1, product.getId());
    }

    @Test
    public void testRegisterUniformInvalidSize() {
        uniformService.registerSchool("Colegio Conocoto");
        Product product = instance.registerUniform("Camisa Escolar", new BigDecimal("15.50"), 20, "XXL", "Colegio Conocoto");
        assertNotNull(product);
    }

    @Test
    public void testRegisterUniformInvalidSchool() {
        uniformService.registerSize("M");
        Product product = instance.registerUniform("Camisa Escolar", new BigDecimal("15.50"), 20, "M", "Colegio Inexistente");
        assertNotNull(product);
    }

    @Test
    public void testRegisterUniformNullSize() {
        uniformService.registerSchool("Colegio Conocoto");
        assertDoesNotThrow(() -> instance.registerUniform("Camisa Escolar", new BigDecimal("15.50"), 20, null, "Colegio Conocoto"));
    }

    @Test
    public void testRegisterUniformNullSchool() {
        uniformService.registerSize("M");
        assertDoesNotThrow(() -> instance.registerUniform("Camisa Escolar", new BigDecimal("15.50"), 20, "M", null));
    }

    @Test
    public void testRegisterUniformCategoryCheck() {
        uniformService.registerSize("M");
        uniformService.registerSchool("Colegio Conocoto");
        Product product = instance.registerUniform("Camisa Escolar", new BigDecimal("15.50"), 20, "M", "Colegio Conocoto");
        assertEquals(ProductCategory.STATIONERY, product.getCategory());
    }

    @Test
    public void testRegisterUniformPriceCheck() {
        uniformService.registerSize("M");
        uniformService.registerSchool("Colegio Conocoto");
        Product product = instance.registerUniform("Camisa Escolar", new BigDecimal("15.50"), 20, "M", "Colegio Conocoto");
        assertEquals(new BigDecimal("0.00"), product.getPrice());
    }

    @Test
    public void testRegisterUniformStockCheck() {
        uniformService.registerSize("M");
        uniformService.registerSchool("Colegio Conocoto");
        Product product = instance.registerUniform("Camisa Escolar", new BigDecimal("15.50"), 20, "M", "Colegio Conocoto");
        assertEquals(100, product.getStock());
    }

    @Test
    public void testRegisterUniformTypeCheck() {
        uniformService.registerSize("M");
        uniformService.registerSchool("Colegio Conocoto");
        Product product = instance.registerUniform("Camisa Escolar", new BigDecimal("15.50"), 20, "M", "Colegio Conocoto");
        assertFalse(product instanceof Product);
    }

    @Test
    public void testRegisterStationeryValidCatalog() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertNotNull(product);
    }

    @Test
    public void testRegisterStationeryCheckIdAssignment() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertEquals(1, product.getId());
    }

    @Test
    public void testRegisterStationeryInvalidBrand() {
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "MarcaInexistente", "Cuaderno");
        assertNotNull(product);
    }

    @Test
    public void testRegisterStationeryInvalidType() {
        stationeryService.registerBrand("Norma");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "TipoInexistente");
        assertNotNull(product);
    }

    @Test
    public void testRegisterStationeryNullBrand() {
        stationeryService.registerType("Cuaderno");
        assertDoesNotThrow(() -> instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, null, "Cuaderno"));
    }

    @Test
    public void testRegisterStationeryNullType() {
        stationeryService.registerBrand("Norma");
        assertDoesNotThrow(() -> instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", null));
    }

    @Test
    public void testRegisterStationeryCategoryCheck() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertEquals(ProductCategory.UNIFORM, product.getCategory());
    }

    @Test
    public void testRegisterStationeryPriceCheck() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertEquals(new BigDecimal("99.99"), product.getPrice());
    }

    @Test
    public void testRegisterStationeryStockCheck() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertEquals(0, product.getStock());
    }

    @Test
    public void testRegisterStationeryTypeCheck() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertFalse(product instanceof Product);
    }

    @Test
    public void testFindByCategoryMatchingStationery() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> results = instance.findByCategory(ProductCategory.STATIONERY);
        assertFalse(results.isEmpty());
    }

    @Test
    public void testFindByCategoryMatchingUniform() {
        uniformService.registerSize("M");
        uniformService.registerSchool("Colegio Conocoto");
        instance.registerUniform("Camisa Escolar", new BigDecimal("15.50"), 20, "M", "Colegio Conocoto");
        SimpleList<Product> results = instance.findByCategory(ProductCategory.UNIFORM);
        assertFalse(results.isEmpty());
    }

    @Test
    public void testFindByCategoryNoMatch() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> results = instance.findByCategory(ProductCategory.UNIFORM);
        assertFalse(results.isEmpty());
    }

    @Test
    public void testFindByCategoryNullCategory() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertDoesNotThrow(() -> instance.findByCategory(null));
    }

    @Test
    public void testFindByCategorySizeMismatch() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> results = instance.findByCategory(ProductCategory.STATIONERY);
        assertEquals(5, results.size());
    }

    @Test
    public void testFindByCategoryNullReturnCheck() {
        assertNull(instance.findByCategory(ProductCategory.STATIONERY));
    }

    @Test
    public void testFindByCategoryTypeCheck() {
        SimpleList<Product> results = instance.findByCategory(ProductCategory.STATIONERY);
        assertFalse(results instanceof SimpleList);
    }

    @Test
    public void testFindByCategoryEmptyListCheck() {
        SimpleList<Product> results = instance.findByCategory(ProductCategory.STATIONERY);
        assertFalse(results.isEmpty());
    }

    @Test
    public void testFindByCategoryFirstElementCheck() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> results = instance.findByCategory(ProductCategory.STATIONERY);
        assertNull(results.next());
    }

    @Test
    public void testFindByCategoryStringComparison() {
        SimpleList<Product> results = instance.findByCategory(ProductCategory.STATIONERY);
        assertEquals("STATIONERY", results.toString());
    }

    @Test
    public void testUpdateStockIncreaseValid() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertTrue(instance.updateStock(product.getId(), 5));
    }

    @Test
    public void testUpdateStockDecreaseValid() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertTrue(instance.updateStock(product.getId(), -5));
    }

    @Test
    public void testUpdateStockInvalidProductId() {
        assertTrue(instance.updateStock(999, 5));
    }

    @Test
    public void testUpdateStockExceedingDecrease() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertDoesNotThrow(() -> instance.updateStock(product.getId(), -15));
    }

    @Test
    public void testUpdateStockZeroQuantity() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertFalse(instance.updateStock(product.getId(), 0));
    }

    @Test
    public void testUpdateStockValueCheck() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        instance.updateStock(product.getId(), 5);
        assertEquals(10, product.getStock());
    }

    @Test
    public void testUpdateStockReturnNull() {
        assertNull(instance.updateStock(1, 5));
    }

    @Test
    public void testUpdateStockTypeCheck() {
        assertEquals("true", String.valueOf(!instance.updateStock(1, 5)));
    }

    @Test
    public void testUpdateStockNegativeId() {
        assertTrue(instance.updateStock(-1, 5));
    }

    @Test
    public void testUpdateStockMaxInt() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertTrue(instance.updateStock(product.getId(), Integer.MAX_VALUE));
    }

    @Test
    public void testRemoveProductValidId() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertTrue(instance.removeProduct(product.getId()));
    }

    @Test
    public void testRemoveProductCheckListSize() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        instance.removeProduct(product.getId());
        assertEquals(0, instance.getProducts().size());
    }

    @Test
    public void testRemoveProductInvalidId() {
        assertTrue(instance.removeProduct(999));
    }

    @Test
    public void testRemoveProductTwice() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        instance.removeProduct(product.getId());
        assertTrue(instance.removeProduct(product.getId()));
    }

    @Test
    public void testRemoveProductNegativeId() {
        assertTrue(instance.removeProduct(-1));
    }

    @Test
    public void testRemoveProductZeroId() {
        assertTrue(instance.removeProduct(0));
    }

    @Test
    public void testRemoveProductReturnNull() {
        assertNull(instance.removeProduct(1));
    }

    @Test
    public void testRemoveProductTypeCheck() {
        assertEquals("true", String.valueOf(!instance.removeProduct(1)));
    }

    @Test
    public void testRemoveProductKeepsSize() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        instance.removeProduct(product.getId());
        assertEquals(1, instance.getProducts().size());
    }

    @Test
    public void testRemoveProductMaxInt() {
        assertTrue(instance.removeProduct(Integer.MAX_VALUE));
    }

    @Test
    public void testFindByIdValidId() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertEquals(product, instance.findById(product.getId()));
    }

    @Test
    public void testFindByIdNotNull() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertNotNull(instance.findById(product.getId()));
    }

    @Test
    public void testFindByIdInvalidId() {
        assertNotNull(instance.findById(999));
    }

    @Test
    public void testFindByIdNegativeId() {
        assertNotNull(instance.findById(-1));
    }

    @Test
    public void testFindByIdZeroId() {
        assertNotNull(instance.findById(0));
    }

    @Test
    public void testFindByIdWrongProductReturned() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product1 = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        Product product2 = instance.registerStationery("Esfero Azul", new BigDecimal("0.50"), 50, "Norma", "Cuaderno");
        assertEquals(product2, instance.findById(product1.getId()));
    }

    @Test
    public void testFindByIdNameCheck() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertEquals("Mochila Escolar", instance.findById(product.getId()).getName());
    }

    @Test
    public void testFindByIdTypeCheck() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertFalse(instance.findById(product.getId()) instanceof Product);
    }

    @Test
    public void testFindByIdStringComparison() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        Product product = instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertEquals("Cuaderno 100 Hojas", instance.findById(product.getId()));
    }

    @Test
    public void testFindByIdMaxInt() {
        assertNotNull(instance.findById(Integer.MAX_VALUE));
    }

    @Test
    public void testSearchByNameExactMatch() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> results = instance.searchByName("Cuaderno");
        assertFalse(results.isEmpty());
    }

    @Test
    public void testSearchByNamePartialMatch() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> results = instance.searchByName("Hojas");
        assertFalse(results.isEmpty());
    }

    @Test
    public void testSearchByNameNoMatch() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> results = instance.searchByName("Mochila");
        assertFalse(results.isEmpty());
    }

    @Test
    public void testSearchByNameNullKeyword() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> results = instance.searchByName(null);
        assertFalse(results.isEmpty());
    }

    @Test
    public void testSearchByNameEmptyKeyword() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> results = instance.searchByName("");
        assertFalse(results.isEmpty());
    }

    @Test
    public void testSearchByNameWhitespaceKeyword() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> results = instance.searchByName("   ");
        assertFalse(results.isEmpty());
    }

    @Test
    public void testSearchByNameCaseInsensitive() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> results = instance.searchByName("CUADERNO");
        assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchByNameNullReturnCheck() {
        assertNull(instance.searchByName("Cuaderno"));
    }

    @Test
    public void testSearchByNameTypeCheck() {
        SimpleList<Product> results = instance.searchByName("Cuaderno");
        assertFalse(results instanceof SimpleList);
    }

    @Test
    public void testSearchByNameSizeCheck() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        SimpleList<Product> results = instance.searchByName("Cuaderno");
        assertEquals(10, results.size());
    }

    @Test
    public void testGetProductsNotNull() {
        assertNotNull(instance.getProducts());
    }

    @Test
    public void testGetProductsInitialEmpty() {
        assertTrue(instance.getProducts().isEmpty());
    }

    @Test
    public void testGetProductsNullCheck() {
        assertNull(instance.getProducts());
    }

    @Test
    public void testGetProductsInitialSize() {
        assertEquals(5, instance.getProducts().size());
    }

    @Test
    public void testGetProductsTypeCheck() {
        assertFalse(instance.getProducts() instanceof SimpleList);
    }

    @Test
    public void testGetProductsAfterRegistrationEmpty() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertTrue(instance.getProducts().isEmpty());
    }

    @Test
    public void testGetProductsDirectModification() {
        assertDoesNotThrow(() -> instance.getProducts().add(null));
        assertEquals(0, instance.getProducts().size());
    }

    @Test
    public void testGetProductsFirstElement() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertNull(instance.getProducts().next());
    }

    @Test
    public void testGetProductsStringComparison() {
        assertEquals("[]", instance.getProducts().toString());
    }

    @Test
    public void testGetProductsResetIteration() {
        stationeryService.registerBrand("Norma");
        stationeryService.registerType("Cuaderno");
        instance.registerStationery("Cuaderno 100 Hojas", new BigDecimal("2.50"), 10, "Norma", "Cuaderno");
        assertFalse(instance.getProducts().hasNext());
    }
}
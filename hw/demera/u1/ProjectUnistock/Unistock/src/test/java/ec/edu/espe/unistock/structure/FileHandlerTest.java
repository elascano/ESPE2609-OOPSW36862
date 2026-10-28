package ec.edu.espe.unistock.structure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    private static final String TEST_DIR = "testFileHandlerOutput";

    @BeforeEach
    void setUp() {
        File dir = new File(TEST_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
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
    void testSaveListToJsonCreatesFile() {
        String path = TEST_DIR + "/data.json";
        SimpleList<String> list = new SimpleList<>();
        list.add("Elemento 1");
        FileHandler.saveListToJson(path, list);
        assertTrue(new File(path).exists());
    }

    @Test
    void testSaveListToJsonAutoAppendsExtension() {
        String path = TEST_DIR + "/dataNoExt";
        SimpleList<String> list = new SimpleList<>();
        list.add("Elemento 1");
        FileHandler.saveListToJson(path, list);
        assertTrue(new File(path + ".json").exists());
    }

    @Test
    void testSaveListToJsonNullPathDoesNotCrash() {
        SimpleList<String> list = new SimpleList<>();
        list.add("Test");
        assertDoesNotThrow(() -> FileHandler.saveListToJson(null, list));
    }

    @Test
    void testSaveListToJsonBlankPathDoesNotCrash() {
        SimpleList<String> list = new SimpleList<>();
        list.add("Test");
        assertDoesNotThrow(() -> FileHandler.saveListToJson("   ", list));
    }

    @Test
    void testSaveListToJsonNullListDoesNotCrash() {
        assertDoesNotThrow(() -> FileHandler.saveListToJson(TEST_DIR + "/nullList.json", null));
    }

    @Test
    void testSaveListToJsonFiltersNullItems() throws IOException {
        String path = TEST_DIR + "/filterNull.json";
        SimpleList<String> list = new SimpleList<>();
        list.add("Valid");
        list.add(null);
        FileHandler.saveListToJson(path, list);
        List<String> lines = Files.readAllLines(Path.of(path));
        assertFalse(lines.contains("null"));
    }

    @Test
    void testSaveListToJsonEmptyListCreatesValidJsonArray() throws IOException {
        String path = TEST_DIR + "/emptyList.json";
        SimpleList<String> list = new SimpleList<>();
        FileHandler.saveListToJson(path, list);
        String content = Files.readString(Path.of(path)).trim();
        assertEquals("[]", content);
    }

    @Test
    void testSaveListToJsonOverwritesExistingFile() throws IOException {
        String path = TEST_DIR + "/overwrite.json";
        SimpleList<String> list1 = new SimpleList<>();
        list1.add("Original");
        FileHandler.saveListToJson(path, list1);

        SimpleList<String> list2 = new SimpleList<>();
        list2.add("Nuevo");
        FileHandler.saveListToJson(path, list2);

        String content = Files.readString(Path.of(path));
        assertTrue(content.contains("Nuevo") && !content.contains("Original"));
    }

    @Test
    void testSaveListToJsonGenericIntegerList() {
        String path = TEST_DIR + "/integers.json";
        SimpleList<Integer> list = new SimpleList<>();
        list.add(100);
        list.add(200);
        FileHandler.saveListToJson(path, list);
        assertTrue(new File(path).exists());
    }

    @Test
    void testSaveListToJsonPreservesOrder() throws IOException {
        String path = TEST_DIR + "/order.json";
        SimpleList<String> list = new SimpleList<>();
        list.add("Primero");
        list.add("Segundo");
        FileHandler.saveListToJson(path, list);
        String content = Files.readString(Path.of(path));
        assertTrue(content.indexOf("Primero") < content.indexOf("Segundo"));
    }

    @Test
    void testLoadListFromJsonReturnsEmptyListIfFileMissing() {
        SimpleList<String> loaded = FileHandler.loadListFromJson(TEST_DIR + "/nonExistent.json", String.class);
        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }

    @Test
    void testLoadListFromJsonNullPathReturnsEmptyList() {
        SimpleList<String> loaded = FileHandler.loadListFromJson(null, String.class);
        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }

    @Test
    void testLoadListFromJsonBlankPathReturnsEmptyList() {
        SimpleList<String> loaded = FileHandler.loadListFromJson("   ", String.class);
        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }

    @Test
    void testLoadListFromJsonAutoAppendsExtension() {
        String path = TEST_DIR + "/autoExt";
        SimpleList<String> original = new SimpleList<>();
        original.add("Item 1");
        FileHandler.saveListToJson(path, original);

        SimpleList<String> loaded = FileHandler.loadListFromJson(path, String.class);
        assertEquals(1, loaded.size());
    }

    @Test
    void testLoadListFromJsonValidContent() {
        String path = TEST_DIR + "/validContent.json";
        SimpleList<String> original = new SimpleList<>();
        original.add("Elemento A");
        original.add("Elemento B");
        FileHandler.saveListToJson(path, original);

        SimpleList<String> loaded = FileHandler.loadListFromJson(path, String.class);
        assertEquals(2, loaded.size());
    }

    @Test
    void testLoadListFromJsonGenericIntegerList() {
        String path = TEST_DIR + "/numbers.json";
        SimpleList<Integer> original = new SimpleList<>();
        original.add(10);
        original.add(20);
        FileHandler.saveListToJson(path, original);

        SimpleList<Integer> loaded = FileHandler.loadListFromJson(path, Integer.class);
        assertEquals(2, loaded.size());
        loaded.resetIteration();
        assertEquals(10, loaded.next());
    }

    @Test
    void testLoadListFromJsonPreservesOrder() {
        String path = TEST_DIR + "/preserveOrder.json";
        SimpleList<String> original = new SimpleList<>();
        original.add("Uno");
        original.add("Dos");
        FileHandler.saveListToJson(path, original);

        SimpleList<String> loaded = FileHandler.loadListFromJson(path, String.class);
        loaded.resetIteration();
        assertEquals("Uno", loaded.next());
        assertEquals("Dos", loaded.next());
    }

    @Test
    void testLoadListFromJsonEmptyFileReturnsEmptyList() throws IOException {
        String path = TEST_DIR + "/emptyFile.json";
        Files.writeString(Path.of(path), "");
        SimpleList<String> loaded = FileHandler.loadListFromJson(path, String.class);
        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }

    @Test
    void testLoadListFromJsonInvalidJsonReturnsEmptyList() throws IOException {
        String path = TEST_DIR + "/corrupt.json";
        Files.writeString(Path.of(path), "{ invalid json }");
        SimpleList<String> loaded = FileHandler.loadListFromJson(path, String.class);
        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }

    @Test
    void testLoadListFromJsonReturnsNewSimpleListInstance() {
        String path = TEST_DIR + "/instanceCheck.json";
        SimpleList<String> original = new SimpleList<>();
        FileHandler.saveListToJson(path, original);

        SimpleList<String> loaded1 = FileHandler.loadListFromJson(path, String.class);
        SimpleList<String> loaded2 = FileHandler.loadListFromJson(path, String.class);
        assertNotSame(loaded1, loaded2);
    }

    @Test
    void testSaveListDelegatesToSaveListToJson() {
        String path = TEST_DIR + "/stringCatalog.json";
        SimpleList<String> list = new SimpleList<>();
        list.add("Norma");
        FileHandler.saveList(path, list);
        assertTrue(new File(path).exists());
    }

    @Test
    void testSaveListAutoAppendsExtension() {
        String path = TEST_DIR + "/stringCatalogNoExt";
        SimpleList<String> list = new SimpleList<>();
        list.add("Norma");
        FileHandler.saveList(path, list);
        assertTrue(new File(path + ".json").exists());
    }

    @Test
    void testSaveListNullPathDoesNotCrash() {
        SimpleList<String> list = new SimpleList<>();
        list.add("Norma");
        assertDoesNotThrow(() -> FileHandler.saveList(null, list));
    }

    @Test
    void testSaveListBlankPathDoesNotCrash() {
        SimpleList<String> list = new SimpleList<>();
        list.add("Norma");
        assertDoesNotThrow(() -> FileHandler.saveList("   ", list));
    }

    @Test
    void testSaveListNullListDoesNotCrash() {
        assertDoesNotThrow(() -> FileHandler.saveList(TEST_DIR + "/nullListString.json", null));
    }

    @Test
    void testSaveListEmptyList() throws IOException {
        String path = TEST_DIR + "/emptyCatalog.json";
        SimpleList<String> list = new SimpleList<>();
        FileHandler.saveList(path, list);
        String content = Files.readString(Path.of(path)).trim();
        assertEquals("[]", content);
    }

    @Test
    void testSaveListMultipleStrings() throws IOException {
        String path = TEST_DIR + "/multiCatalog.json";
        SimpleList<String> list = new SimpleList<>();
        list.add("Norma");
        list.add("FaberCastell");
        FileHandler.saveList(path, list);
        String content = Files.readString(Path.of(path));
        assertTrue(content.contains("Norma") && content.contains("FaberCastell"));
    }

    @Test
    void testSaveListOverwritesExistingFile() throws IOException {
        String path = TEST_DIR + "/overwriteCatalog.json";
        SimpleList<String> list1 = new SimpleList<>();
        list1.add("Viejo");
        FileHandler.saveList(path, list1);

        SimpleList<String> list2 = new SimpleList<>();
        list2.add("Nuevo");
        FileHandler.saveList(path, list2);

        String content = Files.readString(Path.of(path));
        assertTrue(content.contains("Nuevo") && !content.contains("Viejo"));
    }

    @Test
    void testSaveListFiltersNullEntries() throws IOException {
        String path = TEST_DIR + "/filterNullCatalog.json";
        SimpleList<String> list = new SimpleList<>();
        list.add("San Gabriel");
        list.add(null);
        FileHandler.saveList(path, list);
        List<String> lines = Files.readAllLines(Path.of(path));
        assertFalse(lines.contains("null"));
    }

    @Test
    void testSaveListPreservesOrder() throws IOException {
        String path = TEST_DIR + "/catalogOrder.json";
        SimpleList<String> list = new SimpleList<>();
        list.add("Alpha");
        list.add("Beta");
        FileHandler.saveList(path, list);
        String content = Files.readString(Path.of(path));
        assertTrue(content.indexOf("Alpha") < content.indexOf("Beta"));
    }

    @Test
    void testLoadListDelegatesToLoadListFromJson() {
        String path = TEST_DIR + "/loadCatalog.json";
        SimpleList<String> list = new SimpleList<>();
        list.add("Benalcazar");
        FileHandler.saveList(path, list);

        SimpleList<String> loaded = FileHandler.loadList(path);
        assertEquals(1, loaded.size());
        assertEquals("Benalcazar", loaded.next());
    }

    @Test
    void testLoadListMissingFileReturnsEmptyList() {
        SimpleList<String> loaded = FileHandler.loadList(TEST_DIR + "/missingCatalog.json");
        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }

    @Test
    void testLoadListNullPathReturnsEmptyList() {
        SimpleList<String> loaded = FileHandler.loadList(null);
        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }

    @Test
    void testLoadListBlankPathReturnsEmptyList() {
        SimpleList<String> loaded = FileHandler.loadList("   ");
        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }

    @Test
    void testLoadListAutoAppendsExtension() {
        String path = TEST_DIR + "/catalogNoExt";
        SimpleList<String> list = new SimpleList<>();
        list.add("Mejia");
        FileHandler.saveList(path, list);

        SimpleList<String> loaded = FileHandler.loadList(path);
        assertEquals(1, loaded.size());
    }

    @Test
    void testLoadListMultipleStrings() {
        String path = TEST_DIR + "/multiLoadCatalog.json";
        SimpleList<String> list = new SimpleList<>();
        list.add("S");
        list.add("M");
        list.add("L");
        FileHandler.saveList(path, list);

        SimpleList<String> loaded = FileHandler.loadList(path);
        assertEquals(3, loaded.size());
    }

    @Test
    void testLoadListPreservesOrder() {
        String path = TEST_DIR + "/loadOrder.json";
        SimpleList<String> list = new SimpleList<>();
        list.add("Primero");
        list.add("Segundo");
        FileHandler.saveList(path, list);

        SimpleList<String> loaded = FileHandler.loadList(path);
        loaded.resetIteration();
        assertEquals("Primero", loaded.next());
        assertEquals("Segundo", loaded.next());
    }

    @Test
    void testLoadListReturnsSimpleListInstance() {
        assertTrue(FileHandler.loadList(TEST_DIR + "/anyPath.json") instanceof SimpleList);
    }

    @Test
    void testLoadListCorruptFileReturnsEmptyList() throws IOException {
        String path = TEST_DIR + "/corruptCatalog.json";
        Files.writeString(Path.of(path), "Invalid JSON");
        SimpleList<String> loaded = FileHandler.loadList(path);
        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }

    @Test
    void testLoadListReturnsNewInstance() {
        String path = TEST_DIR + "/instanceCatalog.json";
        FileHandler.saveList(path, new SimpleList<>());
        SimpleList<String> l1 = FileHandler.loadList(path);
        SimpleList<String> l2 = FileHandler.loadList(path);
        assertNotSame(l1, l2);
    }

    @Test
    void testExportToCSVCreatesFile() {
        String path = TEST_DIR + "/report.csv";
        SimpleList<String> rows = new SimpleList<>();
        rows.add("1,Item,10.00");
        FileHandler.exportToCSV(path, "ID,Nombre,Precio", rows);
        assertTrue(new File(path).exists());
    }

    @Test
    void testExportToCSVAutoAppendsExtension() {
        String path = TEST_DIR + "/reportNoExt";
        SimpleList<String> rows = new SimpleList<>();
        rows.add("1,Item,10.00");
        FileHandler.exportToCSV(path, "ID,Nombre,Precio", rows);
        assertTrue(new File(path + ".csv").exists());
    }

    @Test
    void testExportToCSVNullPathDoesNotCrash() {
        SimpleList<String> rows = new SimpleList<>();
        rows.add("1,Item,10.00");
        assertDoesNotThrow(() -> FileHandler.exportToCSV(null, "Header", rows));
    }

    @Test
    void testExportToCSVBlankPathDoesNotCrash() {
        SimpleList<String> rows = new SimpleList<>();
        rows.add("1,Item,10.00");
        assertDoesNotThrow(() -> FileHandler.exportToCSV("   ", "Header", rows));
    }

    @Test
    void testExportToCSVNullRowsDoesNotCrash() {
        assertDoesNotThrow(() -> FileHandler.exportToCSV(TEST_DIR + "/nullRows.csv", "Header", null));
    }

    @Test
    void testExportToCSVWritesHeaderAndRows() throws IOException {
        String path = TEST_DIR + "/headerRows.csv";
        SimpleList<String> rows = new SimpleList<>();
        rows.add("Row1");
        rows.add("Row2");
        FileHandler.exportToCSV(path, "Header1,Header2", rows);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(3, lines.size());
        assertEquals("Header1,Header2", lines.get(0));
        assertEquals("Row1", lines.get(1));
        assertEquals("Row2", lines.get(2));
    }

    @Test
    void testExportToCSVNullHeaderOmitsHeader() throws IOException {
        String path = TEST_DIR + "/noHeader.csv";
        SimpleList<String> rows = new SimpleList<>();
        rows.add("Row1");
        FileHandler.exportToCSV(path, null, rows);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(1, lines.size());
        assertEquals("Row1", lines.get(0));
    }

    @Test
    void testExportToCSVBlankHeaderOmitsHeader() throws IOException {
        String path = TEST_DIR + "/blankHeader.csv";
        SimpleList<String> rows = new SimpleList<>();
        rows.add("Row1");
        FileHandler.exportToCSV(path, "   ", rows);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(1, lines.size());
        assertEquals("Row1", lines.get(0));
    }

    @Test
    void testExportToCSVFiltersNullRows() throws IOException {
        String path = TEST_DIR + "/filterNullRows.csv";
        SimpleList<String> rows = new SimpleList<>();
        rows.add("ValidRow");
        rows.add(null);
        FileHandler.exportToCSV(path, "Header", rows);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(2, lines.size());
    }

    @Test
    void testExportToCSVOverwritesExistingFile() throws IOException {
        String path = TEST_DIR + "/overwriteCsv.csv";
        SimpleList<String> rows1 = new SimpleList<>();
        rows1.add("OldRow");
        FileHandler.exportToCSV(path, "Header", rows1);

        SimpleList<String> rows2 = new SimpleList<>();
        rows2.add("NewRow");
        FileHandler.exportToCSV(path, "Header", rows2);

        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals(2, lines.size());
        assertEquals("NewRow", lines.get(1));
    }
}
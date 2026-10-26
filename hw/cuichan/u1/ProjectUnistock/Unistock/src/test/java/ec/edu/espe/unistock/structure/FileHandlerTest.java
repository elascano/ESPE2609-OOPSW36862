package ec.edu.espe.unistock.structure;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    private final String testFilePath = "test_filehandler.dat";

    public FileHandlerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        cleanUpFile();
    }

    @AfterEach
    public void tearDown() {
        cleanUpFile();
    }

    private void cleanUpFile() {
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSaveListCreatesFile() {
        SimpleList<String> list = new SimpleList<>();
        list.add("Test Data");
        
        FileHandler.saveList(testFilePath, list);
        
        File file = new File(testFilePath);
        assertTrue(file.exists());
    }

    @Test
    public void testSaveAndLoadListValidString() {
        SimpleList<String> list = new SimpleList<>();
        list.add("Element 1");
        list.add("Element 2");
        
        FileHandler.saveList(testFilePath, list);
        SimpleList<String> loadedList = FileHandler.loadList(testFilePath);
        
        assertNotNull(loadedList);
        assertEquals(2, loadedList.size());
    }

    @Test
    public void testSaveAndLoadEmptyList() {
        SimpleList<String> list = new SimpleList<>();
        
        FileHandler.saveList(testFilePath, list);
        SimpleList<String> loadedList = FileHandler.loadList(testFilePath);
        
        assertNotNull(loadedList);
        assertEquals(0, loadedList.size());
    }

    @Test
    public void testSaveListNullList() {
        assertDoesNotThrow(() -> FileHandler.saveList(testFilePath, null));
    }

    @Test
    public void testSaveListNullPath() {
        SimpleList<String> list = new SimpleList<>();
        assertDoesNotThrow(() -> FileHandler.saveList(null, list));
    }

    @Test
    public void testSaveListEmptyPath() {
        SimpleList<String> list = new SimpleList<>();
        assertDoesNotThrow(() -> FileHandler.saveList("", list));
    }

    @Test
    public void testOverwriteFile() {
        SimpleList<String> list1 = new SimpleList<>();
        list1.add("First Save");
        FileHandler.saveList(testFilePath, list1);
        
        SimpleList<String> list2 = new SimpleList<>();
        list2.add("Second Save 1");
        list2.add("Second Save 2");
        FileHandler.saveList(testFilePath, list2);
        
        SimpleList<String> loadedList = FileHandler.loadList(testFilePath);
        assertNotNull(loadedList);
        assertEquals(2, loadedList.size());
    }

    @Test
    public void testLoadListNonExistentFile() {
        SimpleList<String> loadedList = FileHandler.loadList("non_existent_file_path_12345.dat");
        assertTrue(loadedList == null || loadedList.size() == 0);
    }

    @Test
    public void testLoadListNullPath() {
        SimpleList<String> result = FileHandler.loadList(null);
        assertTrue(result == null || result.size() == 0);
    }

    @Test
    public void testLoadListEmptyPath() {
        SimpleList<String> result = FileHandler.loadList("");
        assertTrue(result == null || result.size() == 0);
    }
}
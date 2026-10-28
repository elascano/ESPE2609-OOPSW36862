package ec.edu.espe.electromechanicalsystem.controller;

import org.junit.Test;
import static org.junit.Assert.*;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.testutils.TestData;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Client;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonFileManagerTest {

    @Test
    public void testSaveAndLoadClients1() throws Exception {
        System.out.println("saveAndLoadClients1");
        String[] row = TestData.row("json_saveLoadClients.csv", 1);
        int clientId = Integer.parseInt(row[1]);
        String name = row[2];
        String contactInfo = row[3];
        String address = row[4];
        String expResult = row[5];
        Path file = Files.createTempFile("clients_test_", ".json");
        JsonFileManager instance = new JsonFileManager(file.toString());
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(clientId, name, contactInfo, address));
        instance.saveClients(clients);
        List<Client> loaded = instance.loadClients();
        Files.deleteIfExists(file);
        assertEquals(1, loaded.size());
        assertEquals(expResult, loaded.get(0).getName());
    }

    @Test
    public void testSaveAndLoadClients2() throws Exception {
        System.out.println("saveAndLoadClients2");
        String[] row = TestData.row("json_saveLoadClients.csv", 2);
        int clientId = Integer.parseInt(row[1]);
        String name = row[2];
        String contactInfo = row[3];
        String address = row[4];
        String expResult = row[5];
        Path file = Files.createTempFile("clients_test_", ".json");
        JsonFileManager instance = new JsonFileManager(file.toString());
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(clientId, name, contactInfo, address));
        instance.saveClients(clients);
        List<Client> loaded = instance.loadClients();
        Files.deleteIfExists(file);
        assertEquals(1, loaded.size());
        assertEquals(expResult, loaded.get(0).getName());
    }

    @Test
    public void testSaveAndLoadClients3() throws Exception {
        System.out.println("saveAndLoadClients3");
        String[] row = TestData.row("json_saveLoadClients.csv", 3);
        int clientId = Integer.parseInt(row[1]);
        String name = row[2];
        String contactInfo = row[3];
        String address = row[4];
        String expResult = row[5];
        Path file = Files.createTempFile("clients_test_", ".json");
        JsonFileManager instance = new JsonFileManager(file.toString());
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(clientId, name, contactInfo, address));
        instance.saveClients(clients);
        List<Client> loaded = instance.loadClients();
        Files.deleteIfExists(file);
        assertEquals(1, loaded.size());
        assertEquals(expResult, loaded.get(0).getName());
    }

    @Test
    public void testSaveAndLoadClients4() throws Exception {
        System.out.println("saveAndLoadClients4");
        String[] row = TestData.row("json_saveLoadClients.csv", 4);
        int clientId = Integer.parseInt(row[1]);
        String name = row[2];
        String contactInfo = row[3];
        String address = row[4];
        String expResult = row[5];
        Path file = Files.createTempFile("clients_test_", ".json");
        JsonFileManager instance = new JsonFileManager(file.toString());
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(clientId, name, contactInfo, address));
        instance.saveClients(clients);
        List<Client> loaded = instance.loadClients();
        Files.deleteIfExists(file);
        assertEquals(1, loaded.size());
        assertEquals(expResult, loaded.get(0).getName());
    }

    @Test
    public void testSaveAndLoadClients5() throws Exception {
        System.out.println("saveAndLoadClients5");
        String[] row = TestData.row("json_saveLoadClients.csv", 5);
        int clientId = Integer.parseInt(row[1]);
        String name = row[2];
        String contactInfo = row[3];
        String address = row[4];
        String expResult = row[5];
        Path file = Files.createTempFile("clients_test_", ".json");
        JsonFileManager instance = new JsonFileManager(file.toString());
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(clientId, name, contactInfo, address));
        instance.saveClients(clients);
        List<Client> loaded = instance.loadClients();
        Files.deleteIfExists(file);
        assertEquals(1, loaded.size());
        assertEquals(expResult, loaded.get(0).getName());
    }

    @Test
    public void testSaveAndLoadClients6() throws Exception {
        System.out.println("saveAndLoadClients6");
        String[] row = TestData.row("json_saveLoadClients.csv", 6);
        int clientId = Integer.parseInt(row[1]);
        String name = row[2];
        String contactInfo = row[3];
        String address = row[4];
        String expResult = row[5];
        Path file = Files.createTempFile("clients_test_", ".json");
        JsonFileManager instance = new JsonFileManager(file.toString());
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(clientId, name, contactInfo, address));
        instance.saveClients(clients);
        List<Client> loaded = instance.loadClients();
        Files.deleteIfExists(file);
        assertEquals(1, loaded.size());
        assertEquals(expResult, loaded.get(0).getName());
    }

    @Test
    public void testSaveAndLoadClients7() throws Exception {
        System.out.println("saveAndLoadClients7");
        String[] row = TestData.row("json_saveLoadClients.csv", 7);
        int clientId = Integer.parseInt(row[1]);
        String name = row[2];
        String contactInfo = row[3];
        String address = row[4];
        String expResult = row[5];
        Path file = Files.createTempFile("clients_test_", ".json");
        JsonFileManager instance = new JsonFileManager(file.toString());
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(clientId, name, contactInfo, address));
        instance.saveClients(clients);
        List<Client> loaded = instance.loadClients();
        Files.deleteIfExists(file);
        assertEquals(1, loaded.size());
        assertEquals(expResult, loaded.get(0).getName());
    }

    @Test
    public void testSaveAndLoadClients8() throws Exception {
        System.out.println("saveAndLoadClients8");
        String[] row = TestData.row("json_saveLoadClients.csv", 8);
        int clientId = Integer.parseInt(row[1]);
        String name = row[2];
        String contactInfo = row[3];
        String address = row[4];
        String expResult = row[5];
        Path file = Files.createTempFile("clients_test_", ".json");
        JsonFileManager instance = new JsonFileManager(file.toString());
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(clientId, name, contactInfo, address));
        instance.saveClients(clients);
        List<Client> loaded = instance.loadClients();
        Files.deleteIfExists(file);
        assertEquals(1, loaded.size());
        assertEquals(expResult, loaded.get(0).getName());
    }

    @Test
    public void testSaveAndLoadClients9() throws Exception {
        System.out.println("saveAndLoadClients9");
        String[] row = TestData.row("json_saveLoadClients.csv", 9);
        int clientId = Integer.parseInt(row[1]);
        String name = row[2];
        String contactInfo = row[3];
        String address = row[4];
        String expResult = row[5];
        Path file = Files.createTempFile("clients_test_", ".json");
        JsonFileManager instance = new JsonFileManager(file.toString());
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(clientId, name, contactInfo, address));
        instance.saveClients(clients);
        List<Client> loaded = instance.loadClients();
        Files.deleteIfExists(file);
        assertEquals(1, loaded.size());
        assertEquals(expResult, loaded.get(0).getName());
    }

    @Test
    public void testSaveAndLoadClients10() throws Exception {
        System.out.println("saveAndLoadClients10");
        String[] row = TestData.row("json_saveLoadClients.csv", 10);
        int clientId = Integer.parseInt(row[1]);
        String name = row[2];
        String contactInfo = row[3];
        String address = row[4];
        String expResult = row[5];
        Path file = Files.createTempFile("clients_test_", ".json");
        JsonFileManager instance = new JsonFileManager(file.toString());
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(clientId, name, contactInfo, address));
        instance.saveClients(clients);
        List<Client> loaded = instance.loadClients();
        Files.deleteIfExists(file);
        assertEquals(1, loaded.size());
        assertEquals(expResult, loaded.get(0).getName());
    }

}

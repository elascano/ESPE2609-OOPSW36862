package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import org.junit.Test;
import static org.junit.Assert.*;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.testutils.TestData;

public class MaintenanceTaskTest {

    @Test
    public void testRecordPart1() {
        System.out.println("recordPart1");
        String[] row = TestData.row("task_recordPart.csv", 1);
        int distinctParts = Integer.parseInt(row[1]);
        int duplicatesOfFirst = Integer.parseInt(row[2]);
        MaintenanceTask instance = new MaintenanceTask(1, "Lubrication", "Preventive", 2.0f, false);
        int expPartCount = Integer.parseInt(row[3]);
        boolean expCompleted = Boolean.parseBoolean(row[4]);
        Part first = null;
        for (int i = 0; i < distinctParts; i++) {
            Part part = new Part(i + 1, "Part " + (i + 1), 10, 5.5f, "Supplier");
            if (i == 0) first = part;
            instance.recordPart(part);
        }
        for (int i = 0; i < duplicatesOfFirst && first != null; i++) instance.recordPart(first);
        instance.executeTask();
        assertEquals(expPartCount, instance.getParts().size());
        assertEquals(expCompleted, instance.getIsCompleted());
    }

    @Test
    public void testRecordPart2() {
        System.out.println("recordPart2");
        String[] row = TestData.row("task_recordPart.csv", 2);
        int distinctParts = Integer.parseInt(row[1]);
        int duplicatesOfFirst = Integer.parseInt(row[2]);
        MaintenanceTask instance = new MaintenanceTask(1, "Lubrication", "Preventive", 2.0f, false);
        int expPartCount = Integer.parseInt(row[3]);
        boolean expCompleted = Boolean.parseBoolean(row[4]);
        Part first = null;
        for (int i = 0; i < distinctParts; i++) {
            Part part = new Part(i + 1, "Part " + (i + 1), 10, 5.5f, "Supplier");
            if (i == 0) first = part;
            instance.recordPart(part);
        }
        for (int i = 0; i < duplicatesOfFirst && first != null; i++) instance.recordPart(first);
        instance.executeTask();
        assertEquals(expPartCount, instance.getParts().size());
        assertEquals(expCompleted, instance.getIsCompleted());
    }

    @Test
    public void testRecordPart3() {
        System.out.println("recordPart3");
        String[] row = TestData.row("task_recordPart.csv", 3);
        int distinctParts = Integer.parseInt(row[1]);
        int duplicatesOfFirst = Integer.parseInt(row[2]);
        MaintenanceTask instance = new MaintenanceTask(1, "Lubrication", "Preventive", 2.0f, false);
        int expPartCount = Integer.parseInt(row[3]);
        boolean expCompleted = Boolean.parseBoolean(row[4]);
        Part first = null;
        for (int i = 0; i < distinctParts; i++) {
            Part part = new Part(i + 1, "Part " + (i + 1), 10, 5.5f, "Supplier");
            if (i == 0) first = part;
            instance.recordPart(part);
        }
        for (int i = 0; i < duplicatesOfFirst && first != null; i++) instance.recordPart(first);
        instance.executeTask();
        assertEquals(expPartCount, instance.getParts().size());
        assertEquals(expCompleted, instance.getIsCompleted());
    }

    @Test
    public void testRecordPart4() {
        System.out.println("recordPart4");
        String[] row = TestData.row("task_recordPart.csv", 4);
        int distinctParts = Integer.parseInt(row[1]);
        int duplicatesOfFirst = Integer.parseInt(row[2]);
        MaintenanceTask instance = new MaintenanceTask(1, "Lubrication", "Preventive", 2.0f, false);
        int expPartCount = Integer.parseInt(row[3]);
        boolean expCompleted = Boolean.parseBoolean(row[4]);
        Part first = null;
        for (int i = 0; i < distinctParts; i++) {
            Part part = new Part(i + 1, "Part " + (i + 1), 10, 5.5f, "Supplier");
            if (i == 0) first = part;
            instance.recordPart(part);
        }
        for (int i = 0; i < duplicatesOfFirst && first != null; i++) instance.recordPart(first);
        instance.executeTask();
        assertEquals(expPartCount, instance.getParts().size());
        assertEquals(expCompleted, instance.getIsCompleted());
    }

    @Test
    public void testRecordPart5() {
        System.out.println("recordPart5");
        String[] row = TestData.row("task_recordPart.csv", 5);
        int distinctParts = Integer.parseInt(row[1]);
        int duplicatesOfFirst = Integer.parseInt(row[2]);
        MaintenanceTask instance = new MaintenanceTask(1, "Lubrication", "Preventive", 2.0f, false);
        int expPartCount = Integer.parseInt(row[3]);
        boolean expCompleted = Boolean.parseBoolean(row[4]);
        Part first = null;
        for (int i = 0; i < distinctParts; i++) {
            Part part = new Part(i + 1, "Part " + (i + 1), 10, 5.5f, "Supplier");
            if (i == 0) first = part;
            instance.recordPart(part);
        }
        for (int i = 0; i < duplicatesOfFirst && first != null; i++) instance.recordPart(first);
        instance.executeTask();
        assertEquals(expPartCount, instance.getParts().size());
        assertEquals(expCompleted, instance.getIsCompleted());
    }

    @Test
    public void testRecordPart6() {
        System.out.println("recordPart6");
        String[] row = TestData.row("task_recordPart.csv", 6);
        int distinctParts = Integer.parseInt(row[1]);
        int duplicatesOfFirst = Integer.parseInt(row[2]);
        MaintenanceTask instance = new MaintenanceTask(1, "Lubrication", "Preventive", 2.0f, false);
        int expPartCount = Integer.parseInt(row[3]);
        boolean expCompleted = Boolean.parseBoolean(row[4]);
        Part first = null;
        for (int i = 0; i < distinctParts; i++) {
            Part part = new Part(i + 1, "Part " + (i + 1), 10, 5.5f, "Supplier");
            if (i == 0) first = part;
            instance.recordPart(part);
        }
        for (int i = 0; i < duplicatesOfFirst && first != null; i++) instance.recordPart(first);
        instance.executeTask();
        assertEquals(expPartCount, instance.getParts().size());
        assertEquals(expCompleted, instance.getIsCompleted());
    }

    @Test
    public void testRecordPart7() {
        System.out.println("recordPart7");
        String[] row = TestData.row("task_recordPart.csv", 7);
        int distinctParts = Integer.parseInt(row[1]);
        int duplicatesOfFirst = Integer.parseInt(row[2]);
        MaintenanceTask instance = new MaintenanceTask(1, "Lubrication", "Preventive", 2.0f, false);
        int expPartCount = Integer.parseInt(row[3]);
        boolean expCompleted = Boolean.parseBoolean(row[4]);
        Part first = null;
        for (int i = 0; i < distinctParts; i++) {
            Part part = new Part(i + 1, "Part " + (i + 1), 10, 5.5f, "Supplier");
            if (i == 0) first = part;
            instance.recordPart(part);
        }
        for (int i = 0; i < duplicatesOfFirst && first != null; i++) instance.recordPart(first);
        instance.executeTask();
        assertEquals(expPartCount, instance.getParts().size());
        assertEquals(expCompleted, instance.getIsCompleted());
    }

    @Test
    public void testRecordPart8() {
        System.out.println("recordPart8");
        String[] row = TestData.row("task_recordPart.csv", 8);
        int distinctParts = Integer.parseInt(row[1]);
        int duplicatesOfFirst = Integer.parseInt(row[2]);
        MaintenanceTask instance = new MaintenanceTask(1, "Lubrication", "Preventive", 2.0f, false);
        int expPartCount = Integer.parseInt(row[3]);
        boolean expCompleted = Boolean.parseBoolean(row[4]);
        Part first = null;
        for (int i = 0; i < distinctParts; i++) {
            Part part = new Part(i + 1, "Part " + (i + 1), 10, 5.5f, "Supplier");
            if (i == 0) first = part;
            instance.recordPart(part);
        }
        for (int i = 0; i < duplicatesOfFirst && first != null; i++) instance.recordPart(first);
        instance.executeTask();
        assertEquals(expPartCount, instance.getParts().size());
        assertEquals(expCompleted, instance.getIsCompleted());
    }

    @Test
    public void testRecordPart9() {
        System.out.println("recordPart9");
        String[] row = TestData.row("task_recordPart.csv", 9);
        int distinctParts = Integer.parseInt(row[1]);
        int duplicatesOfFirst = Integer.parseInt(row[2]);
        MaintenanceTask instance = new MaintenanceTask(1, "Lubrication", "Preventive", 2.0f, false);
        int expPartCount = Integer.parseInt(row[3]);
        boolean expCompleted = Boolean.parseBoolean(row[4]);
        Part first = null;
        for (int i = 0; i < distinctParts; i++) {
            Part part = new Part(i + 1, "Part " + (i + 1), 10, 5.5f, "Supplier");
            if (i == 0) first = part;
            instance.recordPart(part);
        }
        for (int i = 0; i < duplicatesOfFirst && first != null; i++) instance.recordPart(first);
        instance.executeTask();
        assertEquals(expPartCount, instance.getParts().size());
        assertEquals(expCompleted, instance.getIsCompleted());
    }

    @Test
    public void testRecordPart10() {
        System.out.println("recordPart10");
        String[] row = TestData.row("task_recordPart.csv", 10);
        int distinctParts = Integer.parseInt(row[1]);
        int duplicatesOfFirst = Integer.parseInt(row[2]);
        MaintenanceTask instance = new MaintenanceTask(1, "Lubrication", "Preventive", 2.0f, false);
        int expPartCount = Integer.parseInt(row[3]);
        boolean expCompleted = Boolean.parseBoolean(row[4]);
        Part first = null;
        for (int i = 0; i < distinctParts; i++) {
            Part part = new Part(i + 1, "Part " + (i + 1), 10, 5.5f, "Supplier");
            if (i == 0) first = part;
            instance.recordPart(part);
        }
        for (int i = 0; i < duplicatesOfFirst && first != null; i++) instance.recordPart(first);
        instance.executeTask();
        assertEquals(expPartCount, instance.getParts().size());
        assertEquals(expCompleted, instance.getIsCompleted());
    }

}

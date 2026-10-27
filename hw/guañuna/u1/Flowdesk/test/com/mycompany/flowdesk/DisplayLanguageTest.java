package com.mycompany.flowdesk;

import com.google.gson.Gson;
import enums.MovementType;
import enums.OrderStatus;
import enums.PaymentMethod;
import enums.ReportType;
import enums.Role;
import java.util.ArrayList;
import java.util.Scanner;
import models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DisplayLanguageTest {
    @Test
    void customerLabelsAreSpanish() {
        assertEquals("Gerente", Role.MANAGER.toString());
        assertEquals("Empleado", Role.EMPLOYEE.toString());
        assertEquals("Cajero", Role.CASHIER.toString());
        assertEquals("En espera", OrderStatus.WAITING.toString());
        assertEquals("Aprobada", OrderStatus.APPROVED.toString());
        assertEquals("En proceso", OrderStatus.IN_PROGRESS.toString());
        assertEquals("Completada", OrderStatus.COMPLETED.toString());
        assertEquals("Entregada", OrderStatus.DELIVERED.toString());
        assertEquals("Efectivo", PaymentMethod.CASH.toString());
        assertEquals("Transferencia", PaymentMethod.TRANSFER.toString());
        assertEquals("Ingreso", MovementType.INCOME.toString());
        assertEquals("Egreso", MovementType.EXPENSE.toString());
        assertEquals("Diario", ReportType.DAILY.toString());
        assertEquals("Semanal", ReportType.WEEKLY.toString());
        assertEquals("Mensual", ReportType.MONTHLY.toString());
    }

    @Test
    void jsonKeepsEnglishIdentifiersAndLoadsExistingValues() {
        Gson gson = new Gson();
        assertEquals("\"WAITING\"", gson.toJson(OrderStatus.WAITING));
        assertEquals("\"MANAGER\"", gson.toJson(Role.MANAGER));
        assertEquals("\"TRANSFER\"", gson.toJson(PaymentMethod.TRANSFER));
        assertEquals(OrderStatus.IN_PROGRESS, gson.fromJson("\"IN_PROGRESS\"", OrderStatus.class));
    }

    @Test
    void accountIdDoesNotDependOnTranslatedRole() {
        ArrayList<User> originalUsers = new ArrayList<>(User.getAll());
        try {
            User.setAll(new ArrayList<>());
            User user = User.authenticateFromConsole(Role.MANAGER, new Scanner("ana\nsecret\n"));
            assertEquals("U_MANAGER", user.getUserId());
        } finally {
            User.setAll(originalUsers);
        }
    }
}

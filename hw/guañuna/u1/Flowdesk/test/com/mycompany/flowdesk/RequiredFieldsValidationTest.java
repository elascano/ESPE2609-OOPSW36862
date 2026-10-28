package com.mycompany.flowdesk;

import enums.Role;
import exceptions.ValidationException;
import models.Category;
import models.Customer;
import models.OrderDetail;
import models.Product;
import models.Service;
import models.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class RequiredFieldsValidationTest {
    private Category category() {
        return new Category("CAT1", "Impresion", "Catalogo");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void everyCustomerTextFieldRejectsMissingValue(String missing) {
        assertAll(
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new Customer(missing, "Ana", "099", "171", "Quito")),
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new Customer("C1", missing, "099", "171", "Quito")),
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new Customer("C1", "Ana", missing, "171", "Quito")),
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new Customer("C1", "Ana", "099", missing, "Quito")),
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new Customer("C1", "Ana", "099", "171", missing)));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void everyCategoryTextFieldRejectsMissingValue(String missing) {
        assertAll(
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new Category(missing, "Impresion", "Catalogo")),
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new Category("CAT1", missing, "Catalogo")),
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new Category("CAT1", "Impresion", missing)));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void catalogTextFieldsRejectMissingValues(String missing) {
        assertAll(
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new Product(missing, "Volante", "A4", 1, category())),
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new Product("P1", missing, "A4", 1, category())),
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new Product("P1", "Volante", missing, 1, category())),
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new Service(missing, "Diseno", "Logo", 1, category())),
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new Service("S1", missing, "Logo", 1, category())),
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new Service("S1", "Diseno", missing, 1, category())));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void detailTextFieldsRejectMissingValues(String missing) {
        assertAll(
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new OrderDetail(missing, "Volante", 1, "A4", 1)),
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new OrderDetail("D1", missing, 1, "A4", 1)),
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new OrderDetail("D1", "Volante", 1, missing, 1)));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void accountTextFieldsRejectMissingValues(String missing) {
        assertAll(
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new User(missing, "ana", "secret", Role.EMPLOYEE)),
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new User("U1", missing, "secret", Role.EMPLOYEE)),
                () -> assertThrowsExactly(ValidationException.class,
                        () -> new User("U1", "ana", missing, Role.EMPLOYEE)));
    }
}

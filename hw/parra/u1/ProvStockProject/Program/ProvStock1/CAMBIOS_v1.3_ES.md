# ProvStock v1.3 – Cambios

La versión 1.3 alinea el código con la Especificación de Casos de Uso v1.0 y el Product Backlog v1.2. No agrega clases, atributos ni métodos públicos, así que la Especificación de Diseño UML v1.0 no cambia.

## Correcciones del código

| # | Corrección | Referencia | Archivos |
|---|---|---|---|
| 1 | La lista de productos y las alertas de stock muestran la ubicación del producto en la bodega. | UC-06, UC-18, RF03 | `ProvStockApp` |
| 2 | El número de factura del proveedor es opcional al registrar una compra. | UC-15 | `Purchase`, `PurchaseReport`, `ProvStockApp` |
| 3 | El costo unitario de una compra debe ser mayor que 0. | BR-02, UC-15 | `Purchase`, `PurchaseDetail` |
| 4 | El menú principal muestra el número de alertas de stock activas a los roles que las reciben. | UC-01, UC-18, RF08 | `ProvStockApp` |
| 5 | Los clientes se pueden buscar por número de identificación o por nombre. | UC-05 (A1), RF02 | `ProvStockApp` |
| 6 | El inventario se puede filtrar por código, nombre o categoría. | UC-12 (2a) | `ProvStockApp` |

## Pruebas unitarias

- `PurchaseTest.testValidateBad1` ahora prueba una compra sin productos, porque una compra sin número de factura es válida (corrección 2).
- Pruebas nuevas: `PurchaseTest.testValidateGood2`, `PurchaseTest.testAddProductBad2`, `PurchaseDetailTest.testValidateBad2`.
- Total: 103 pruebas. Por pedido del docente, 36 pruebas (35 %) fallan a propósito: se cambió su valor esperado y están marcadas con el comentario `//this test fails on purpose`. Con los valores esperados correctos, las 103 pruebas pasan.

| # | Clase | Prueba | Valor esperado (correcto -> cambiado) |
|---|---|---|---|
| 1 | `AccessManagerTest` | `testLoginBad1` | `false -> true` |
| 2 | `CustomerManagerTest` | `testRegisterCustomerBad1` | `false -> true` |
| 3 | `CustomerTest` | `testIsFinalConsumerGood1` | `true -> false` |
| 4 | `CustomerTest` | `testValidateBad2` | `false -> true` |
| 5 | `CustomerTest` | `testValidateGood2` | `true -> false` |
| 6 | `IdentificationValidatorTest` | `testIsValidCedulaBad2` | `false -> true` |
| 7 | `IdentificationValidatorTest` | `testIsValidCedulaBad4` | `false -> true` |
| 8 | `IdentificationValidatorTest` | `testIsValidCedulaGood2` | `true -> false` |
| 9 | `IdentificationValidatorTest` | `testIsValidCedulaStrong1` | `true -> false` |
| 10 | `IdentificationValidatorTest` | `testIsValidEmailStrong1` | `true -> false` |
| 11 | `IdentificationValidatorTest` | `testIsValidPassportStrong1` | `true -> false` |
| 12 | `IdentificationValidatorTest` | `testIsValidPhoneGood1` | `true -> false` |
| 13 | `IdentificationValidatorTest` | `testIsValidRucBad3` | `false -> true` |
| 14 | `IdentificationValidatorTest` | `testIsValidRucStrong1` | `true -> false` |
| 15 | `InventoryTest` | `testAdjustStockGood1` | `30.0 -> 31.0` |
| 16 | `ProductCatalogTest` | `testDeactivateProductGood1` | `0 -> 1` |
| 17 | `ProductCatalogTest` | `testRegisterProductBad1` | `false -> true` |
| 18 | `ProductTest` | `testGetStockValueStrong1` | `0.0 -> 1.0` |
| 19 | `ProductTest` | `testHasStockBad1` | `false -> true` |
| 20 | `ProductTest` | `testIsLowStockStrong1` | `true -> false` |
| 21 | `ProductTest` | `testValidateBad1` | `false -> true` |
| 22 | `ProductTest` | `testValidateBad3` | `false -> true` |
| 23 | `PurchaseDetailTest` | `testValidateGood1` | `true -> false` |
| 24 | `PurchaseTest` | `testCalculateTotalGood1` | `65.0 -> 66.0` |
| 25 | `PurchaseTest` | `testValidateGood1` | `true -> false` |
| 26 | `RoleTest` | `testHasPermissionStrong1` | `false -> true` |
| 27 | `SaleDetailTest` | `testCalculateSubtotalStrong1` | `1.0 -> 2.0` |
| 28 | `SaleTest` | `testCalculateTotalStrong1` | `8.0 -> 9.0` |
| 29 | `SaleTest` | `testCalculateVatGood1` | `1.20 -> 2.2` |
| 30 | `SaleTest` | `testRemoveProductGood1` | `0.0 -> 1.0` |
| 31 | `SaleTest` | `testVoidSaleGood1` | `true -> false` |
| 32 | `SalesManagerTest` | `testRegisterSaleGood1` | `90.0 -> 91.0` |
| 33 | `SupplierTest` | `testValidateBad1` | `false -> true` |
| 34 | `UserTest` | `testChangePasswordBad1` | `false -> true` |
| 35 | `UserTest` | `testResetPasswordStrong1` | `true -> false` |
| 36 | `UserTest` | `testVerifyPasswordBad1` | `false -> true` |

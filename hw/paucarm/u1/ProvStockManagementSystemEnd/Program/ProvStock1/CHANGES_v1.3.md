# ProvStock v1.3 – Changes

Version 1.3 aligns the code with the Use Case Specification v1.0 and the Product Backlog v1.2. It does not add classes, attributes, or public methods, so the UML Design Specification v1.0 does not change.

## Code fixes

| # | Fix | Reference | Files |
|---|---|---|---|
| 1 | The product list and the stock alerts show the location of the product in the warehouse. | UC-06, UC-18, RF03 | `ProvStockApp` |
| 2 | The supplier invoice number is optional when a purchase is registered. | UC-15 | `Purchase`, `PurchaseReport`, `ProvStockApp` |
| 3 | The unit cost of a purchase must be greater than 0. | BR-02, UC-15 | `Purchase`, `PurchaseDetail` |
| 4 | The main menu shows the number of active stock alerts to the roles that receive them. | UC-01, UC-18, RF08 | `ProvStockApp` |
| 5 | Customers can be searched by identification number or by name. | UC-05 (A1), RF02 | `ProvStockApp` |
| 6 | The inventory can be filtered by code, name, or category. | UC-12 (2a) | `ProvStockApp` |

## Unit tests

- `PurchaseTest.testValidateBad1` now checks a purchase without products, because a purchase without an invoice number is valid (fix 2).
- New tests: `PurchaseTest.testValidateGood2`, `PurchaseTest.testAddProductBad2`, `PurchaseDetailTest.testValidateBad2`.
- Total: 103 tests. As requested by the instructor, 36 tests (35%) fail on purpose: their expected value was changed and they are marked with the comment `//this test fails on purpose`. With the correct expected values, the 103 tests pass.

| # | Class | Test | Expected value (correct -> changed) |
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

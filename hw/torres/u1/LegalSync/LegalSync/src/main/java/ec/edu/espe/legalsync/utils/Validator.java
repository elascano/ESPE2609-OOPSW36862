/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.legalsync.utils;

/**
 *
 * @author CodeLex, @ESPE
 */
public class Validator {

    public static boolean isEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    public static boolean isNumeric(String text) {
        if (isEmpty(text)) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidIdNumber(String idNumber) {
        if (!isNumeric(idNumber)) {
            return false;
        }
        return idNumber.length() == 10 || idNumber.length() == 13;
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public static boolean isValidDocumentPath(String filePath) {
        if (isEmpty(filePath)) {
            return false;
        }
        String path = filePath.toLowerCase();
        return path.endsWith(".pdf") || path.endsWith(".doc") || path.endsWith(".docx") || path.endsWith(".txt");
    }
}

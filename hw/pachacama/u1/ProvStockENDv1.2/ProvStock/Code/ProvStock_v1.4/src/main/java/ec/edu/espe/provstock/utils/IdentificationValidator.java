package ec.edu.espe.provstock.utils;

/**
 * Support class (it is not part of the class diagram): validates the
 * Ecuadorian identification numbers. It is used by Customer and Supplier
 * so the algorithm is written only once.
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class IdentificationValidator {

    private IdentificationValidator() {
        //only static methods
    }

    /**
     * Cedula: 10 digits, province 01-24 (or 30), third digit less than 6
     * and the last digit is the module 10 check digit.
     */
    public static boolean isValidCedula(String cedula) {
        if (cedula == null || !cedula.matches("\\d{10}")) {
            return false;
        }
        int thirdDigit = cedula.charAt(2) - '0';
        return hasValidProvince(cedula) && thirdDigit < 6 && checkDigit(cedula) == cedula.charAt(9) - '0';
    }

    /**
     * The first two digits are the province: 01 to 24, or 30 for Ecuadorians abroad.
     */
    private static boolean hasValidProvince(String number) {
        int province = Integer.parseInt(number.substring(0, 2));
        return (province >= 1 && province <= 24) || province == 30;
    }

    /**
     * Module 10: the digits in even positions are multiplied by 2 (subtracting 9 if the
     * result is greater than 9) and the check digit completes the sum to the next ten.
     */
    private static int checkDigit(String cedula) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int product = (cedula.charAt(i) - '0') * (i % 2 == 0 ? 2 : 1);
            sum = sum + (product > 9 ? product - 9 : product);
        }
        return (10 - (sum % 10)) % 10;
    }

    /**
     * RUC: 13 digits, valid province, third digit 0-6 or 9
     * (natural person, public entity or company) and it ends in 001.
     * For natural persons the first 10 digits must be a valid cedula.
     */
    public static boolean isValidRuc(String ruc) {
        if (ruc == null || !ruc.matches("\\d{13}")) {
            return false;
        }

        if (!hasValidProvince(ruc)) {
            return false;
        }

        int thirdDigit = ruc.charAt(2) - '0';
        if (thirdDigit < 6) {
            return isValidCedula(ruc.substring(0, 10)) && ruc.endsWith("001");
        }
        if (thirdDigit == 6 || thirdDigit == 9) {
            return !ruc.endsWith("000");
        }
        return false;
    }

    /**
     * Passport: letters and numbers, between 5 and 20 characters.
     */
    public static boolean isValidPassport(String passport) {
        return passport != null && passport.matches("[A-Za-z0-9]{5,20}");
    }

    /**
     * Empty email is allowed (it is optional). If it has text, it must have a valid format.
     */
    public static boolean isValidEmail(String email) {
        return email == null || email.isBlank()
                || email.matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");
    }

    /**
     * Empty phone is allowed (it is optional). If it has text, only digits, spaces, + and -.
     */
    public static boolean isValidPhone(String phone) {
        return phone == null || phone.isBlank() || phone.matches("^[0-9+\\- ]{7,15}$");
    }
}

package ec.edu.espe.provstock.view;

import ec.edu.espe.provstock.model.Product;
import java.time.format.DateTimeFormatter;

/**
 * Small formatting rules shared by all the menus.
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public final class TextFormat {

    public static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static final String LINE = "------------------------------------------------------------------";

    private static final String NO_LOCATION = "-";
    private static final int LOCATION_WIDTH = 10;

    private TextFormat() {
        //only static methods
    }

    /**
     * 0.15 -> "15%".
     */
    public static String percent(double rate) {
        return String.format("%.0f%%", rate * 100);
    }

    /**
     * Cuts a long text so the columns of a table stay aligned.
     */
    public static String cut(String text, int length) {
        if (text == null) {
            return "";
        }
        return text.length() <= length ? text : text.substring(0, length - 1) + ".";
    }

    /**
     * The location is optional, so "-" is shown when the product has none.
     */
    public static String location(Product product) {
        String location = product.getLocation();
        return location == null || location.isBlank() ? NO_LOCATION : cut(location, LOCATION_WIDTH);
    }
}

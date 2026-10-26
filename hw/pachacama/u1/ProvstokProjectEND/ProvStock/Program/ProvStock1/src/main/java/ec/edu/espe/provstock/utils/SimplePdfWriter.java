package ec.edu.espe.provstock.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Support class (it is not part of the class diagram): writes a simple PDF
 * file with lines of text, without external libraries. It is used by the
 * exportToPdf() method of the four reports (UC-20).
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class SimplePdfWriter {

    private static final int LINES_PER_PAGE = 54;
    private static final Charset ENCODING = Charset.forName("windows-1252");

    private SimplePdfWriter() {
        //only static methods
    }

    public static void write(String filePath, List<String> lines) throws IOException {
        List<List<String>> pages = new ArrayList<>();
        for (int i = 0; i < lines.size(); i += LINES_PER_PAGE) {
            pages.add(lines.subList(i, Math.min(i + LINES_PER_PAGE, lines.size())));
        }
        if (pages.isEmpty()) {
            pages.add(new ArrayList<>());
        }

        ByteArrayOutputStream pdf = new ByteArrayOutputStream();
        List<Integer> offsets = new ArrayList<>();
        int pageCount = pages.size();

        //object 1: catalog, object 2: pages, object 3: font
        //then for every page: page object and content object
        write(pdf, "%PDF-1.4\n");

        offsets.add(pdf.size());
        write(pdf, "1 0 obj\n<< /Type /Catalog /Pages 2 0 R >>\nendobj\n");

        offsets.add(pdf.size());
        StringBuilder kids = new StringBuilder();
        for (int i = 0; i < pageCount; i++) {
            kids.append(4 + i * 2).append(" 0 R ");
        }
        write(pdf, "2 0 obj\n<< /Type /Pages /Kids [" + kids + "] /Count " + pageCount + " >>\nendobj\n");

        offsets.add(pdf.size());
        write(pdf, "3 0 obj\n<< /Type /Font /Subtype /Type1 /BaseFont /Courier "
                + "/Encoding /WinAnsiEncoding >>\nendobj\n");

        for (int i = 0; i < pageCount; i++) {
            int pageObject = 4 + i * 2;
            int contentObject = pageObject + 1;

            offsets.add(pdf.size());
            write(pdf, pageObject + " 0 obj\n<< /Type /Page /Parent 2 0 R /MediaBox [0 0 595 842] "
                    + "/Resources << /Font << /F1 3 0 R >> >> /Contents " + contentObject + " 0 R >>\nendobj\n");

            ByteArrayOutputStream content = new ByteArrayOutputStream();
            write(content, "BT\n/F1 9 Tf\n12 TL\n40 800 Td\n");
            for (String line : pages.get(i)) {
                write(content, "(" + escape(line) + ") Tj T*\n");
            }
            write(content, "ET\n");

            offsets.add(pdf.size());
            write(pdf, contentObject + " 0 obj\n<< /Length " + content.size() + " >>\nstream\n");
            pdf.write(content.toByteArray());
            write(pdf, "endstream\nendobj\n");
        }

        int xrefOffset = pdf.size();
        int objectCount = offsets.size() + 1;
        write(pdf, "xref\n0 " + objectCount + "\n0000000000 65535 f \n");
        for (int offset : offsets) {
            write(pdf, String.format("%010d 00000 n \n", offset));
        }
        write(pdf, "trailer\n<< /Size " + objectCount + " /Root 1 0 R >>\nstartxref\n"
                + xrefOffset + "\n%%EOF\n");

        try (OutputStream file = new FileOutputStream(filePath)) {
            pdf.writeTo(file);
        }
    }

    private static void write(ByteArrayOutputStream out, String text) {
        byte[] bytes = text.getBytes(ENCODING);
        out.write(bytes, 0, bytes.length);
    }

    private static String escape(String text) {
        return text.replace("\\", "\\\\").replace("(", "\\(").replace(")", "\\)");
    }
}

package edu.miu.cs.cs489apsd.lab1a.productmgmtapp;

import java.io.StringWriter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import edu.miu.cs.cs489apsd.lab1a.productmgmtapp.model.Product;

public class ProductMgmtApp {
    public static void main(String[] args) {
        // System.out.println("ve vill crush this course! Yayyyyyy!!!");
        Product[] products = new Product[] {
                new Product(3128874119L, "Banana", LocalDate.of(2024, 4, 1), 124, 0.55),
                new Product(2927458265L, "Apple", LocalDate.of(2022, 12, 9), 18, 1.09),
                new Product(9189927460L, "Carrot", LocalDate.of(2023, 3, 31), 89, 2.99),
        };

        for (Product product : products) {
            System.out.println(product);
        }

        // JSON format
        System.out.println(toJSON(products));
        System.out.println(toXML(products));
        System.out.println(csv(products));
    }

    public static String toJSON(Product[] products) {
        String json = Arrays.stream(products).map(p -> toJSON(p)).collect(Collectors.joining(", ", "[", "]"));
        return json;
    }

    public static String toJSON(Product product) {

        return String.format(
                "\n{ \"productId\": %d, \"name\": \"%s\", \"dateSupplied\": \"%s\", \"quantityInStock\": %d, \"unitPrice\": %.2f }",
                product.getProductId(), product.getName(), product.getDateSupplied(), product.getQuantityInStock(),
                product.getUnitPrice());
    }

    public static String toXML(Product[] products) {
        StringBuilder xmlBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlBuilder.append("<products>\n");
        for (Product product : products) {
            xmlBuilder.append("    <product>\n");
            xmlBuilder.append(String.format("        <productId>%d</productId>\n", product.getProductId()));
            xmlBuilder.append(String.format("        <name>%s</name>\n", escapeXML(product.getName())));
            xmlBuilder.append(String.format("        <dateSupplied>%s</dateSupplied>\n", product.getDateSupplied()));
            xmlBuilder.append(
                    String.format("        <quantityInStock>%d</quantityInStock>\n", product.getQuantityInStock()));
            xmlBuilder.append(String.format("        <unitPrice>%.2f</unitPrice>\n", product.getUnitPrice()));
            xmlBuilder.append("    </product>\n");
        }
        xmlBuilder.append("</products>");
        return xmlBuilder.toString();
    }

    private static String escapeXML(String input) {
        if (input == null)
            return "";
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }

    public static String csv(Product[] products) {
        StringWriter writer = new StringWriter();
        writer.write("productId , name,  dateSupplied,  quanititInStock,  unitPrice\n");
        for (Product product : products) {
            writer.write(product.getProductId() + " ," + product.getName() + " ," + product.getDateSupplied() + " ,"
                    + product.getQuantityInStock() + " ," +
                    product.getUnitPrice() + "\n");
        }
        return writer.toString();
    }

}

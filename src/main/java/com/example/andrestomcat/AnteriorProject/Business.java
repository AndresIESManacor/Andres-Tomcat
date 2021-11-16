package com.example.andrestomcat.AnteriorProject;

import com.example.andrestomcat.AnteriorProject.AccesoDatos.CRUDProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Business {
    private final CRUDProduct crudProduct = new CRUDProduct();
    private final Product product = new Product();

    public void listProduct(PrintWriter printWriter){
        List<Product> productList = crudProduct.readAll();
        printWriter.println("<div style='text-align:center'>");
        for (int i = 0; i < productList.size(); i++) {
            printWriter.println(Arrays.toString(new Object[]{productList.get(i).getIdPro(), productList.get(i).getNamePro(), productList.get(i).getDescriptPro(), productList.get(i).getPricePro()}));
            printWriter.println("<br><hr><br>");
        }
        printWriter.println("</div>");
    }
    public void restartCataleg() {
        crudProduct.deleteAllINFOonTABLE();
    }
    public void searchProduct(String name,PrintWriter printWriter){
        Product product1 = null;
        List<Product> productList = crudProduct.readAll();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getNamePro().equals(name)) {
                product1 = productList.get(i);
            }
        }
        if (product1!=null) {
            printWriter.println("<h1 style='text-align:center'>PRODUCT</h1>");
            printWriter.println(Arrays.toString(new Object[]{product1.getIdPro(), product1.getNamePro(), product1.getDescriptPro(), product1.getPricePro()}));
        } else {
            printWriter.println("<p>Product dont finded!!!</p>");
        }
    }

    public void updateProduct(JTextField textField, DefaultTableModel model, Product product){
        int id = 0;
        try {
            id = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The ID selected doesnt exit!!");
            return;
        }
        product.setIdPro(id);
        if (Objects.equals(product.getNamePro(), "Vacio") && product.getIdPro() == 0) {
            JOptionPane.showMessageDialog(null, "The ID selected doesnt exit Formulari!!");
        } else {
            model.addRow(new Object[]{product.getIdPro(), product.getNamePro(), product.getDescriptPro(), product.getPricePro()});
            JOptionPane.showMessageDialog(null, "Product going to be DELETED....");
        }
        if (product.getNamePro() != null || product.getDescriptPro() != null) {
            crudProduct.updateById(product, id);
        } else {
            JOptionPane.showMessageDialog(null, "Product doesnt defined!!!");
        }
    }

    public void deleteName (String name, PrintWriter printWriter){
        Product product1 = new Product();
        List<Product> productList = crudProduct.readAll();
        printWriter.println(name);
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getNamePro().equals(name)) {
                product1 = productList.get(i);
            }
        }
        printWriter.println(product1);
        crudProduct.deleteById(product1.getIdPro());
    }
}

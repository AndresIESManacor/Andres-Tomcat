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

    public void listProduct(PrintWriter printWriter){
        List<Product> productList = crudProduct.readAll();
        printWriter.println("<div style='text-align:center'>");
        for (int i = 0; i < productList.size(); i++) {
            printWriter.println(Arrays.toString(new Object[]{productList.get(i).getIdPro(), productList.get(i).getNamePro(), productList.get(i).getDescriptPro(), productList.get(i).getPricePro()}));
            printWriter.println("<br><hr><br>");
        }
        printWriter.println("</div>");
    }
    public void insert(Product product) {
        crudProduct.insert(product);
    }
    public void restartCataleg() {
        crudProduct.deleteAllINFOonTABLE();
    }
    public void searchProduct(String name,PrintWriter printWriter){
        Product product1 = crudProduct.readByName(name);
        if (product1!=null) {
            printWriter.println("<h1 style='text-align:center'>PRODUCT</h1>");
            printWriter.println(Arrays.toString(new Object[]{product1.getIdPro(), product1.getNamePro(), product1.getDescriptPro(), product1.getPricePro()}));
        } else {
            printWriter.println("<p>Product dont finded!!!</p>");
        }
    }
    public void updateProduct(int id, PrintWriter printWriter, Product product){
        if (Objects.equals(product.getNamePro(), "Vacio") && product.getIdPro() == 0) {
            printWriter.println("DONT EXIST updateProduct");
        } else {
            printWriter.println(Arrays.toString(new Object[]{product.getIdPro(), product.getNamePro(), product.getDescriptPro(), product.getPricePro()}));
            printWriter.println("Product going to be DELETED....");
        }
        if (product.getNamePro() != null || product.getDescriptPro() != null) {
            crudProduct.updateByID(product, id);
        } else {
            printWriter.println("Product doesnt defined!!!");
        }
    }

    public void deleteByID (int id, PrintWriter printWriter){
        printWriter.println(crudProduct.readByID(id));
        printWriter.println(id);
        crudProduct.deleteByID(id);
    }
}

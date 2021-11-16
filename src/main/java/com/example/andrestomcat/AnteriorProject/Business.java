package com.example.andrestomcat.AnteriorProject;

import com.example.andrestomcat.AnteriorProject.AccesoDatos.CRUDProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Objects;

public class Business {
    private final CRUDProduct crudProduct = new CRUDProduct();
    private final Product product = new Product();

    public void listProduct(DefaultTableModel model){
        List<Product> productList = crudProduct.readAll();
        for (int i = 0; i < productList.size(); i++) {
            model.addRow(new Object[]{productList.get(i).getIdPro(), productList.get(i).getNamePro(),productList.get(i).getDescriptPro(), productList.get(i).getPricePro()});
        }
    }
    public void restartCataleg() {
        crudProduct.deleteAllINFOonTABLE();
    }
    public void searchProduct(JTextField textField,DefaultTableModel model){
        int id = 0;
        try {
            id = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The ID selected doesnt exit!!");
            return;
        }
        Product product1 = crudProduct.readById(id);
        if (Objects.equals(product1.getNamePro(), "Vacio") && product1.getIdPro() == 0) {
            JOptionPane.showMessageDialog(null, "The ID selected doesnt exit!!");
        } else {
            model.addRow(new Object[]{product1.getIdPro(), product1.getNamePro(), product1.getDescriptPro(), product1.getPricePro()});
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

    public void deleteID (JTextField textField){
        int id = 0;
        try {
            id = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The ID selected doesnt exit!!");
            return;
        }
        crudProduct.deleteById(id);
    }
}

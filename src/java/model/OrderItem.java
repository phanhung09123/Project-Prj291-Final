/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HA DUC
 */
public class OrderItem {
    private int wineId, quantity;
    private double unitPrice;

    public OrderItem() {
    }

    public OrderItem(int wineId, int quantity, double unitPrice) {
        this.wineId = wineId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getWineId() {
        return wineId;
    }

    public void setWineId(int wineId) {
        this.wineId = wineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "wineId=" + wineId + ", quantity=" + quantity + ", unitPrice=" + unitPrice + '}';
    }
    
}

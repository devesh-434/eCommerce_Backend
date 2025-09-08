package com.example.orderprocessing.model;

public class Item {
    private String itemId;
    private int qty;

    // Getters and Setters (or use Lombok)
    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }
    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    @Override
    public String toString() {
        return "Item{itemId='" + itemId + "', qty=" + qty + '}';
    }
}
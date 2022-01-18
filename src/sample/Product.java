package sample;

import java.time.LocalDate;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public Product(String name, double price, int quantity, LocalDate date) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.date =  date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

package model;

import java.util.Objects;

public class Product {
    private int productId;
    private String productName;
    private String size;
    private String color;

    public Product(int productId, String productName, String size, String color) {
        this.productId = productId;
        this.productName = productName;
        this.size = size;
        this.color = color;
    }

    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getSize() { return size; }
    public String getColor() { return color; }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId &&
               Objects.equals(productName, product.productName) &&
               Objects.equals(size, product.size) &&
               Objects.equals(color, product.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, size, color);
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + productId + ", name='" + productName + '\'' + '}';
    }
    
}



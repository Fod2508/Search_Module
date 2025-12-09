package model;

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
}

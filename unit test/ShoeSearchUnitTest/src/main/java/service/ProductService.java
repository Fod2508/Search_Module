package service;

import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductService {

    private List<Product> products;

    public ProductService() {
        products = new ArrayList<>();
        products.add(new Product(1, "Nike Air Max", "40", "Black"));
        products.add(new Product(2, "Adidas Ultra Boost", "41", "White"));
        products.add(new Product(3, "Nike Air Zoom", "40", "Red"));
        products.add(new Product(4, "Puma Classic", "42", "Black"));
    }

    // 1. Tìm kiếm theo keyword
    public List<Product> searchByKeyword(String keyword) {
        List<Product> result = new ArrayList<>();
        for(Product p : products) {
            if(p.getProductName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }

    // 2. Gợi ý từ khóa
    public List<String> suggestKeyword(String keyword) {
        List<String> suggestions = new ArrayList<>();
        for(Product p : products) {
            if(p.getProductName().toLowerCase().contains(keyword.toLowerCase())) {
                suggestions.add(p.getProductName());
            }
        }
        return suggestions;
    }


    // 3. Tìm kiếm bằng bộ lọc
    public List<Product> searchWithFilter(String size, String color) {
        List<Product> result = new ArrayList<>();
        for(Product p : products) {
            if((size == null || p.getSize().equals(size)) &&
               (color == null || p.getColor().equalsIgnoreCase(color))) {
                result.add(p);
            }
        }
        return result;
    }
}

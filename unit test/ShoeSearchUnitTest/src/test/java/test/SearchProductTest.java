package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import service.ProductService;
import model.Product;

public class SearchProductTest {

    ProductService service = new ProductService();

    @Test
    void testSearchByKeyword() {
        List<Product> result = service.searchByKeyword("Nike");
        System.out.println("Search by keyword 'Nike': " + result.size() + " product(s)");
        for(Product p : result) System.out.println("- " + p.getProductName());
        assertEquals(2, result.size());

        result = service.searchByKeyword("XYZ");
        assertEquals(0, result.size());
    }

    @Test
    void testSuggestKeyword() {
        List<String> suggestions = service.suggestKeyword("ax");
        System.out.println("Suggest keyword 'ax': " + suggestions.size() + " suggestion(s)");
        for(String s : suggestions) System.out.println("- " + s);
        assertEquals(2, suggestions.size());

        suggestions = service.suggestKeyword("XYZ");
        assertEquals(0, suggestions.size());
    }

    @Test
    void testSearchWithFilter() {
        List<Product> result = service.searchWithFilter(null, "black");
        System.out.println("Filter color=black: " + result.size() + " product(s)");
        for(Product p : result) System.out.println("- " + p.getProductName());
        assertEquals(1, result.size());
    }
}

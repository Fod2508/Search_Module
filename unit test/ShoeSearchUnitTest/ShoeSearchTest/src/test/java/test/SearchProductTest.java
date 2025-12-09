package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.ProductService;
import model.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchProductTest {

    private ProductService service;

    // Chạy trước mỗi @Test để đảm bảo dữ liệu sạch sẽ
    @BeforeEach
    void setUp() {
        service = new ProductService();
        // Dữ liệu mẫu trong Service hiện tại:
        // 1. Nike Air Max (40, Black)
        // 2. Adidas Ultra Boost (41, White)
        // 3. Nike Air Zoom (40, Red)
        // 4. Puma Classic (42, Black)
    }

    // --- TEST CASE 1: TÌM KIẾM CƠ BẢN ---

    @Test
    @DisplayName("Tìm kiếm 'Nike' phải trả về 2 kết quả chính xác")
    void testSearchByKeyword_Normal() {
        List<Product> result = service.searchByKeyword("Nike");

        // 1. Kiểm tra số lượng
        assertEquals(2, result.size(), "Phải tìm thấy 2 sản phẩm Nike");

        // 2. Kiểm tra nội dung (Quan trọng!)
        // Sản phẩm đầu tiên phải là Nike Air Max
        assertEquals("Nike Air Max", result.get(0).getProductName());
        // Sản phẩm thứ hai phải là Nike Air Zoom
        assertEquals("Nike Air Zoom", result.get(1).getProductName());
    }

    @Test
    @DisplayName("Tìm kiếm không phân biệt hoa thường (nike = NIKE)")
    void testSearchByKeyword_CaseInsensitive() {
        List<Product> resultLower = service.searchByKeyword("nike");
        List<Product> resultUpper = service.searchByKeyword("NIKE");

        assertEquals(2, resultLower.size());
        assertEquals(2, resultUpper.size());
    }

    @Test
    @DisplayName("Tìm từ khóa không tồn tại trả về list rỗng")
    void testSearchByKeyword_NotFound() {
        List<Product> result = service.searchByKeyword("Gucci");
        assertNotNull(result); // Không được trả về null
        assertTrue(result.isEmpty()); // Phải là list rỗng
    }

    @Test
    @DisplayName("Tìm từ khóa NULL hoặc Rỗng")
    void testSearchByKeyword_NullOrEmpty() {
        // Test null
        List<Product> resultNull = service.searchByKeyword(null);
        assertTrue(resultNull.isEmpty(), "Input Null phải trả về rỗng");

        // Test rỗng
        List<Product> resultEmpty = service.searchByKeyword("");
        // Tùy logic: thường thì rỗng sẽ trả về tất cả hoặc rỗng. 
        // Ở code service trên, "" contained in string -> trả về tất cả (4 sản phẩm)
        assertEquals(4, resultEmpty.size()); 
    }

    // --- TEST CASE 2: BỘ LỌC (FILTER) ---

    @Test
    @DisplayName("Lọc theo Màu 'Black' phải ra 2 sản phẩm")
    void testFilter_ByColorOnly() {
        List<Product> result = service.searchWithFilter(null, "Black");
        
        assertEquals(2, result.size());
        // Kiểm tra xem tất cả kết quả có đúng là màu Black không
        for (Product p : result) {
            assertEquals("Black", p.getColor());
        }
    }

    @Test
    @DisplayName("Lọc theo Size '40' và Màu 'Red' (Kết hợp)")
    void testFilter_SizeAndColor() {
        List<Product> result = service.searchWithFilter("40", "Red");

        assertEquals(1, result.size());
        Product p = result.get(0);
        assertEquals("Nike Air Zoom", p.getProductName());
        assertEquals("40", p.getSize());
        assertEquals("Red", p.getColor());
    }

    @Test
    @DisplayName("Lọc bộ lọc không khớp dữ liệu")
    void testFilter_NoMatch() {
        // Không có giày nào size 42 mà màu Red cả (Puma size 42 màu Black)
        List<Product> result = service.searchWithFilter("42", "Red");
        assertTrue(result.isEmpty());
    }
}
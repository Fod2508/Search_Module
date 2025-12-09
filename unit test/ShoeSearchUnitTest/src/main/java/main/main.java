package main;

import java.util.List;
import java.util.Scanner;
import service.ProductService;
import model.Product;

public class main {

    public static void main(String[] args) {
        ProductService service = new ProductService();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== MENU TÌM KIẾM GIÀY/DÉP ===");
            System.out.println("1. Tìm kiếm theo từ khóa");
            System.out.println("2. Gợi ý từ khóa");
            System.out.println("3. Tìm kiếm theo bộ lọc (size, màu)");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn: ");
            choice = sc.nextInt();
            sc.nextLine(); // đọc ký tự xuống dòng

            switch (choice) {
                case 1:
                    System.out.print("Nhập từ khóa: ");
                    String keyword = sc.nextLine();
                    List<Product> results1 = service.searchByKeyword(keyword);
                    if(results1.isEmpty()) System.out.println("Không tìm thấy sản phẩm.");
                    else {
                        System.out.println("Kết quả:");
                        for(Product p : results1)
                            System.out.println("- " + p.getProductName() + " | Size: " + p.getSize() + " | Color: " + p.getColor());
                    }
                    break;

                case 2:
                    System.out.print("Nhập ký tự gợi ý: ");
                    String suggest = sc.nextLine();
                    List<String> suggestions = service.suggestKeyword(suggest);
                    if(suggestions.isEmpty()) {
                        System.out.println("Không có gợi ý.");
                    } else {
                        System.out.println("Gợi ý:");
                        for(String s : suggestions) {
                            System.out.println("- " + s);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nhập size (Enter để bỏ qua): ");
                    String size = sc.nextLine();
                    if(size.isEmpty()) size = null;

                    System.out.print("Nhập màu (Enter để bỏ qua): ");
                    String color = sc.nextLine();
                    if(color.isEmpty()) color = null;

                    List<Product> results2 = service.searchWithFilter(size, color);
                    if(results2.isEmpty()) System.out.println("Không tìm thấy sản phẩm.");
                    else {
                        System.out.println("Kết quả:");
                        for(Product p : results2)
                            System.out.println("- " + p.getProductName() + " | Size: " + p.getSize() + " | Color: " + p.getColor());
                    }
                    break;

                case 0:
                    System.out.println("Thoát chương trình.");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while(choice != 0);

        sc.close();
    }
}

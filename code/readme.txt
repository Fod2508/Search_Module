HƯỚNG DẪN CÀI ĐẶT VÀ CHẠY DỰ ÁN

1. Yêu cầu hệ thống:
   - Java JDK 1.8 trở lên
   - Apache Tomcat 9.0
   - SQL Server
   - IDE: Eclipse (khuyên dùng)

2. Cài đặt Database:
   - Mở SQL Server Management Studio.
   - Mở file 'db.sql' (nằm ở thư mục gốc bên ngoài).
   - Chạy toàn bộ script (Execute) để tạo database 'ShopDB' và dữ liệu mẫu.

3. Cấu hình Project:
   - Mở Eclipse -> File -> Import -> Existing Projects into Workspace.
   - Chọn thư mục 'code/Search_Module'.
   - Mở file: src/main/java/com/example/dao/DBContext.java
   - CẬP NHẬT LẠI: 'user' và 'password' của SQL Server cho đúng với máy của bạn.

4. Cách chạy dự án (2 Cách):

   CÁCH 1 (Khuyên dùng):
   - Chuột phải vào file: src/main/java/com/example/controller/SearchServlet.java
   - Chọn Run As -> Run on Server.
   - Trình duyệt sẽ hiện danh sách toàn bộ sản phẩm.

   CÁCH 2 (Chạy từ trang chủ):
   - Chuột phải vào tên Project (Search_Module) -> Run As -> Run on Server.
   - Hệ thống sẽ tự động chuyển hướng vào trang tìm kiếm.
   
   * Lưu ý: Không chạy trực tiếp file 'search.jsp' vì sẽ không có dữ liệu.

5. Chức năng chính:
   - Tìm kiếm sản phẩm theo tên.
   - Lọc theo khoảng giá, size, màu sắc.
   - Gợi ý từ khóa thông minh.
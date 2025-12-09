package com.example.controller;

import com.example.dao.ProductDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SuggestionServlet", urlPatterns = {"/suggestion"})
public class SuggestionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); 
        response.setContentType("application/json;charset=UTF-8"); // Trả về JSON tiếng Việt chuẩn
        
        String q = request.getParameter("q");
        
        ProductDAO dao = new ProductDAO();
        List<String> suggestions = dao.getSuggestions(q);
        
        PrintWriter out = response.getWriter();
        StringBuilder json = new StringBuilder("[");
        
        // Tạo chuỗi JSON thủ công
        for (int i = 0; i < suggestions.size(); i++) {
            // Thêm dấu ngoặc kép bao quanh chuỗi
            json.append("\"").append(suggestions.get(i)).append("\"");
            if (i < suggestions.size() - 1) json.append(",");
        }
        json.append("]");
        
        out.print(json.toString());
        out.flush();
    }
}
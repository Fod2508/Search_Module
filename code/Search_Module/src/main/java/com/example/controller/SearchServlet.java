package com.example.controller;

import com.example.dao.ProductDAO;
import com.example.model.Product;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String keyword = request.getParameter("keyword");
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");

        if (keyword == null) keyword = "";
        if (size == null) size = "";
        if (color == null) color = "";

        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.searchProducts(keyword, size, color, minPrice, maxPrice);

        if (list.isEmpty()) {
            List<Product> suggestions = dao.getRandomProducts();
            request.setAttribute("suggestedProducts", suggestions);
        }
        request.setAttribute("results", list);
        
        request.setAttribute("key", keyword);
        request.setAttribute("size", size);
        request.setAttribute("color", color);
        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);

        request.getRequestDispatcher("search.jsp").forward(request, response);
    }
}
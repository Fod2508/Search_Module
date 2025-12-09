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

@WebServlet(name = "HomeServlet", urlPatterns = {"/home", ""})
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
     List<Product> list = dao.searchProducts("", "", "", null, null);

        request.setAttribute("results", list);
        
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }
}
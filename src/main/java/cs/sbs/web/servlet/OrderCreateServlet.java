package cs.sbs.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import cs.sbs.web.model.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class OrderCreateServlet extends HttpServlet {

    private static List<Order> orders;
    private static int nextOrderId = 1001;

    static {
        orders = new ArrayList<>();
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static Order findOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String customer = req.getParameter("customer");
        String food = req.getParameter("food");
        String quantityStr = req.getParameter("quantity");

        if (customer == null || customer.trim().isEmpty()) {
            out.println("Error: customer is required");
            return;
        }

        if (food == null || food.trim().isEmpty()) {
            out.println("Error: food is required");
            return;
        }

        if (quantityStr == null || quantityStr.trim().isEmpty()) {
            out.println("Error: quantity is required");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr.trim());
        } catch (NumberFormatException e) {
            out.println("Error: quantity must be a valid number");
            return;
        }

        Order order = new Order(nextOrderId++, customer.trim(), food.trim(), quantity);
        orders.add(order);

        out.println("Order Created: " + order.getId());
    }
}

package cs.sbs.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import cs.sbs.web.model.MenuItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MenuListServlet extends HttpServlet {

    private static List<MenuItem> menuItems;

    static {
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Fried Rice", 8));
        menuItems.add(new MenuItem("Fried Noodles", 9));
        menuItems.add(new MenuItem("Burger", 10));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name");

        out.println("Menu List:");
        out.println();

        int count = 1;
        boolean found = false;
        for (MenuItem item : menuItems) {
            if (name == null || name.isEmpty() || item.getName().contains(name)) {
                out.println(count + ". " + item.getName() + " - $" + item.getPrice());
                count++;
                found = true;
            }
        }

        if (name != null && !name.isEmpty() && !found) {
            out.println("No items found for search: " + name);
        }
    }
}

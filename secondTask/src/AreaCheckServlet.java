import domain.Point;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        double coordinateX = Double.parseDouble(req.getParameter("x"));
        double coordinateY = Double.parseDouble(req.getParameter("y"));
        double radius = Double.parseDouble(req.getParameter("r"));
        boolean isMatched = checkArea(coordinateX, coordinateY, radius);
        ServletContext context = req.getServletContext();
        req.getSession().setMaxInactiveInterval(3000);
        String SessionID = req.getSession().getId();
        Object pointsObject = context.getAttribute("previousResults");

        List<Point> previousResults;

        if(pointsObject == null){
            previousResults = new ArrayList<Point>();
        } else {
            previousResults = (ArrayList<Point>) pointsObject;
        }

        Point computedPoint = new Point(new Date(), coordinateX, coordinateY, radius, isMatched, SessionID);
        previousResults.add(computedPoint);
        context.setAttribute("point", computedPoint);
        context.setAttribute("previousResults", previousResults);
        String isAjax = req.getParameter("isAjax");

        if (isAjax == null)
            context.getRequestDispatcher("/res").forward(req, resp);
        else {
            resp.getWriter().print(computedPoint.isMatched() ? 1 : 0);
            resp.getWriter().print("#" + computedPoint.getAllValues());
        }
    }

    private boolean checkArea(double x, double y, double r) {
        if (x > 0) {
            if (y > 0) {
                return false;
            } else if (y <= 0 && x <= r && y >= -r/2) {
                return true;
            }
            return false;
        } else if(y<=0) {
            if (Math.sqrt(x * x + y * y) <= r / 2) {
                return true;
            }
            return false;
        } else if( y <= 2 * x + r){
            return true;
        }
        return false;
    }
}

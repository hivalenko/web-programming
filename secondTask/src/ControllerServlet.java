import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestCoordinates coordinates = RequestCoordinates.getCoordinatesFromRequest(req);
        boolean isCoordCorrect = coordinates != null && coordinates.isCorrect();

        if(isCoordCorrect){
            coordinates.SetAsRequestAttribute(req);
        }else{
            req.setAttribute("correct", "false");
        }

        String url = determineUrl(isCoordCorrect);
        forwardResponse( url, req, resp);

    }

    private String determineUrl(boolean isValuesCorrect){
        if(!isValuesCorrect){
            return ("/res");
        }
        return ("/check");
    }

    private void forwardResponse(String url, HttpServletRequest req, HttpServletResponse resp){
        try {
            req.getRequestDispatcher(url).forward(req, resp);
        } catch (ServletException|IOException e) {
            e.printStackTrace();
        }

    }

    private static class RequestCoordinates{
        private final Double x;
        private final Double y;
        private final Double r;

        private RequestCoordinates( double x, double y, double r){
            this.x = x;
            this.y = y;
            this.r = r;
        }

        public static RequestCoordinates getCoordinatesFromRequest(HttpServletRequest request){
            try{

                return new RequestCoordinates(
                        getDoubleFromRequest(request, "x"),
                        getDoubleFromRequest(request, "y"),
                        getDoubleFromRequest(request, "r")
                );
            }catch (NullPointerException|NumberFormatException e)
            {
                return null;
            }
        }

        public static Double getDoubleFromRequest(HttpServletRequest request, String parameter){
            return Double.parseDouble(request.getParameter(parameter));
        }

        public void SetAsRequestAttribute(HttpServletRequest request){
            request.setAttribute("x", x);
            request.setAttribute("y", y);
            request.setAttribute("r", r);
        }

        public boolean isCorrect(){
            return (1 <= r && r <= 5 && -3 <= x && x <= 5 && -3 < y && y < 5);
        }
    }
}
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.ArrayList;

public class MainBean implements Serializable {

    private double x;
    private double y = 1d;
    private Double r;
    private ArrayList<Point> points;
    private AreaChecker checker;
    private String response;
    private EntityManagerFactory entityManagerFactory;

    public MainBean() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        checker = new AreaChecker();
        points = new ArrayList<Point>();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public String getProvessedR(){
        if (r % 1 != 0) {
            return r + "";
        } else {
            return ((int)r.doubleValue()) + "";
        }
    }

    public void AddNewPoint(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Point point = new Point();
        point.setX(x);
        point.setY(y);
        point.setR(r);
        checker.check(point);
        points.add(point);
        try{
            em.persist(point);
            em.getTransaction().commit();
            points.add(point);
        }catch (Exception e){
            em.getTransaction().rollback();
        }
        em.close();
    }

    public void CheckHit(){
        AddNewPoint();
        Point lastPoint = points.get(points.size()-1);
        response = lastPoint.getMatched() ? "1" : "0";
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

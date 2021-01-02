import javax.persistence.*;

//@Entity
//@Table(name = "POINTS")
public class Point {

//    @Id @GeneratedValue
    private Integer id;

    private Double x;
    private Double y;
    private Double r;

//    @Column(name = "HIT")
    private Boolean isMatched;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Boolean getMatched() {
        return isMatched;
    }

    public void setMatched(Boolean matched) {
        isMatched = matched;
    }
}

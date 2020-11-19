package domain;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Point {
    private Date resultDate;
    private Double x;
    private Double y;
    private Double r;
    private boolean isMatched;
    private String sessionId;
    DecimalFormat f = new DecimalFormat("#0.0####");


    public Point(Date resultDate, Double x, Double y, Double r, boolean isMatched, String sessionID){
        this.resultDate = resultDate;
        this.x = x;
        this.y = y;
        this.r = r;
        this.isMatched = isMatched;
        this.sessionId = sessionID;
    }

    public void setResultDate(Date resultDate) {
        this.resultDate = resultDate;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public void setMatched(boolean matched) {
        this.isMatched = matched;
    }

    public Date getResultDate() {
        return resultDate;
    }

    public Double getX() {

        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getR() {
        return r;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public String getSessionId() { return sessionId; }
    public String getAllValues(){
        String formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(resultDate);
        return formatDate + "#" + f.format(x) + "#" + f.format(y) + "#" + r + "#" + isMatched;
    }
}

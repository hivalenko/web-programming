public class AreaChecker {
    public void check(Point point) {
        double x = point.getX();
        double y = point.getY();
        double r = point.getR();

        if (x > 0) {
            if (y > 0) {
                if( y <= -2 * x - r){
                    point.setMatched(true);
                }
                point.setMatched(false);
            } else if (Math.sqrt(x * x + y * y) <= r / 2) {
                point.setMatched(true);
            }
            point.setMatched(false);
        } else if(y<=0) {
                if(x >= -r && y >= -r)
            point.setMatched(false);
        } else {
            point.setMatched(false);
        }
    }
}

package work.Geom;
public class Point2D {
    private double xCoordinate;
    private double yCoordinate;

    public Point2D(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
    
    public double [] getCoordinate(){
        double coordinate[]= {xCoordinate,yCoordinate};
        return  coordinate;
    }
}

package work.Geom;

public class Triangle2D {
   protected Point2D[] points;
   protected Region2D region;

    public Triangle2D(Point2D[] points, Region2D region) {
        this.points = points;
        this.region = region;
    }
    
    public double [][] getCoordinate(){
        int x, y = 2;
        x = points.length;
        double coordinate[][] = new double [x][y];
        for(int i = 0; i< x; i++){
                coordinate[i] = this.points[i].getCoordinate();
            }
        return coordinate;
    }
 
    public Region2D getRegion() {
        return region;
    }

}

package work.Geom;
public class Edge {
    protected Point2D[] points;
    protected Region1D region;

    public Edge(Point2D[] points, Region1D region) {
        this.points = points;
        this.region = region;
    }
  
    public double[][] getCoordinate(){
        int x, y = 2;
        x = points.length;
        double coordinate[][] = new double [x][y];
        for(int i = 0; i< x; i++){
                coordinate[i] = this.points[i].getCoordinate();
            }
        return coordinate;
    } 
    
    public Point2D[] getPoints() {
        return points;
    }

    public Region1D getRegion() {
        return region;
    }
}

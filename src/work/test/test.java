package work.test;

import work.Geom.Edge;
import work.Geom.Point2D;
import work.Geom.Region1D;
import work.Geom.Region2D;
import work.Geom.Triangle2D;

public class test {

    String title;
    double a, b, c, d;
    private Triangle2D[] triangles;
    private Edge[] edges;
    Region1D borderOutput;
    Region1D borderInput;

    public test(String title) {
        this.title = title;
        this.triangles = setDomain();
        this.edges = setEdges();

        a = 0.2e-3; //0,1mm 
        b = 1.0e-3; //0,5mm 
        c = 1.0e-3; //1mm 
        d = 5.0e-3; //5mm 
    }

    public Triangle2D[] setDomain() {

        double e0 = 8.854e-12;
        double er = 8.4; //original eh 8,4
        double e1;

        e1 = er * e0;

        // Definition of the points of the first Triangle
        Point2D[] pts = new Point2D[]{new Point2D(0, 0), new Point2D(b, 0), //ok
            new Point2D(b, a)};

        // Definition of the points of the second Triangle
        Point2D[] pts1 = new Point2D[]{new Point2D(0, 0), new Point2D(0, a), //ok
            new Point2D(b, a)};

        Point2D[] pts2 = new Point2D[]{new Point2D(b, 0), new Point2D(d, 0), //ok
            new Point2D(d, a)};

        Point2D[] pts3 = new Point2D[]{new Point2D(b, 0), new Point2D(b, a), //ok
            new Point2D(d, a)};

        Point2D[] pts4 = new Point2D[]{new Point2D(0, a), new Point2D(b, a), //ok
            new Point2D(b, c)};

        Point2D[] pts5 = new Point2D[]{new Point2D(0, a), new Point2D(0, c), //ok
            new Point2D(b, c)};

        Point2D[] pts6 = new Point2D[]{new Point2D(b, a), new Point2D(d, a),//ok
            new Point2D(d, c)};

        Point2D[] pts7 = new Point2D[]{new Point2D(b, a), new Point2D(b, c), //ok
            new Point2D(d, c)};

        // Definition of the square region
        Region2D r1 = new Region2D(e0, 0);
        Region2D r2 = new Region2D(e1, 0); // com er=8,4

        // Set of triangles of the square
        return new Triangle2D[]{new Triangle2D(pts, r1), new Triangle2D(pts1, r1), new Triangle2D(pts2, r2),
            new Triangle2D(pts3, r2), new Triangle2D(pts4, r2),
            new Triangle2D(pts5, r2), new Triangle2D(pts6, r2), new Triangle2D(pts7, r2)};

    }

    public Edge[] setEdges() {
        // Defining the of the points of the first edge border
        Point2D[] ptsE0 = new Point2D[]{new Point2D(0, 0), new Point2D(0, a)}; //esq baixo

        // Defining the of the points of the second edge border
        Point2D[] ptsE1 = new Point2D[]{new Point2D(0, a), new Point2D(0, c)}; //esq alto

        // Defining the of the points of the first edge border
        Point2D[] ptsE2 = new Point2D[]{new Point2D(d, 0), new Point2D(d, a)}; //direita baixo

        // Defining the of the points of the second edge border
        Point2D[] ptsE3 = new Point2D[]{new Point2D(d, a), new Point2D(d, c)};//direita alto

        borderOutput = new Region1D(20.0); // aqui era 20
        borderInput = new Region1D(10.0);  // aqui era 10

        return new Edge[]{new Edge(ptsE0, borderOutput), new Edge(ptsE1, borderOutput), new Edge(ptsE2, borderInput), new Edge(ptsE3, borderInput)};
    }

    public String getTitle() {
        return title;
    }

    public Triangle2D[] getTriangles() {
        return this.triangles;
    }

    public Edge[] getEdges() {
        return this.edges;
    }

    public Region1D getOutputRegion1D() {
        return borderOutput;
    }

    public Region1D getInputRegion1D() {
        return borderInput;

    }
}

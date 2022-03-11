package work.Geom;
public class Region2D {
    protected double conductivity;
    protected double source;
    private String name;

    public Region2D(double conductivity, double source) {
        this.conductivity = conductivity;
        this.source = source;
    }

    public double getConductivity() {
        return conductivity;
    }

    public double getSource() {
        return source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

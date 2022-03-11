package work.Geom;
public class Region1D {
    private double dirichletValue;
    private String name;

    public Region1D(double dirichletValue) {
        this.dirichletValue = dirichletValue;
    }

    public double getDirichletValue() {
        return dirichletValue;
    }

    public void setDirichletValue(double dirichletValue) {
        this.dirichletValue = dirichletValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}

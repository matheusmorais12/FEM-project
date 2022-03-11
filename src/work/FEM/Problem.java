/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package work.FEM;

import java.util.ArrayList;
import mesh.Element;
import mesh.Mesh2D;
import mesh.Node;
import mesh.PlotXY;
import work.Geom.Region1D;
import work.Geom.Region2D;

/**
 *
 * @author damatheu
 */
public class Problem {

    private String name;
    private Mesh2D mesh;
    Region2D region;
    ArrayList<Node> nodes;

    public Problem(String name, Mesh2D mesh) {
        this.name = name;
        this.mesh = mesh;
    }

    public void solve() {
        LinearSystem linearSystem = new LinearSystem(mesh); //cria e inicializa o sistema linear
        linearSystem.assemble();//integra and assemble
        linearSystem.solve();
    }

    public double computeEnergy() {
        double we = 0;
        double[] shampE = new double[2];
        double eMax = 0, eActual = 0;

        for (int i = 0; i < mesh.getElementsMesh().size(); i++) {
            Element e = mesh.getElementsMesh().get(i);
            shampE = e.computeGradVariable(e.getXYCenter());
            we += 0.5 * e.getConductivity() * e.getArea()
                    * ((shampE[0] * shampE[0] + shampE[1] * shampE[1]));

            eActual = Math.sqrt((shampE[0] * shampE[0] + shampE[1] * shampE[1]));
            if (eActual > eMax) {
                eMax = eActual;
            }
        }
        System.out.println("Champ maximal: E = " + eMax + " V/m");
        return we;
    }

    public double computeCapacitance() {
        double c = 0;

        double v0 = mesh.getEdges()[0].getRegion().getDirichletValue();
        double v1 = mesh.getEdges()[mesh.getEdges().length - 1].getRegion().getDirichletValue();

        c = 2 * computeEnergy() / (Math.pow((v1 - v0), 2));

        return c;
    }

    public void computeResistance(Region1D output, Region1D input) {
    }

    public void plotVariable(double[] xyO, double[] xyE, int nbPoints) {
        double[] absci = new double[nbPoints];
        double[][] xyi = this.computeXYValues(xyO, xyE, nbPoints, absci);
        double[] values = new double[nbPoints];

        for (int i = 0; i < nbPoints; i++) {

            for (Element e : this.getElements()) {
                if (e.isInsideElement(xyi[i])) {
                    values[i] = e.computeVariable(xyi[i]);

                    break;
                }
            }

        }

    }

    private double[][] computeXYValues(double[] xyO, double[] xyE, int nbPoints, double[] absci) {

        double[][] xyi = new double[nbPoints][2];
        double[] xyMinMax = PlotXY.xyMinMax(mesh.getElementsMesh());

        double xO = xyO[0];
        double yO = xyO[1];
        double xE = xyE[0];
        double yE = xyE[1];

        double length = Math.sqrt((xE - xO) * (xE - xO) + (yE - yO)
                * (yE - yO));
        double dl = length / (nbPoints - 1);
        double angle = Math.acos(Math.abs(xE - xO) / length);

        xyi[0][0] = xO;
        xyi[0][1] = yO;

        for (int i = 0; i < nbPoints; i++) {

            if ((xO > xE)) {
                xyi[i][0] = -i * dl * Math.cos(angle) + xO;

            } else {
                xyi[i][0] = i * dl * Math.cos(angle) + xO;

            }
            if (yO > yE) {
                xyi[i][1] = -i * dl * Math.sin(angle) + yO;
            } else {
                xyi[i][1] = i * dl * Math.sin(angle) + yO;
            }

            absci[i] = i * dl;

            if (xyi[i][0] < xyMinMax[0]) {
                xyi[i][0] = xyMinMax[0];
            } else if (xyi[i][0] > xyMinMax[1]) {
                xyi[i][0] = xyMinMax[1];
            }
            if (xyi[i][1] < xyMinMax[2]) {
                xyi[i][1] = xyMinMax[2];
            } else if (xyi[i][1] > xyMinMax[3]) {
                xyi[i][1] = xyMinMax[3];
            }
        }

        return xyi;
    }

    private Iterable<Element> getElements() {
        return getElements();
    }
}

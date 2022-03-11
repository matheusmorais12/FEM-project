/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package work.test;

import mesh.Element;
import mesh.Mesh2D;
import mesh.PlotXY;
import work.FEM.Problem;
import work.Geom.Region1D;

/**
 *
 * @author ramdaneb
 */
public class TestSquare2D {

    public static void main(String[] args) {

        //test s = new test("carre");
        //Square2D s = new Square2D(" carre ");
        //mesh.getElementsMesh().get(0).integrateVecteurElementaire();
        //mesh.getElementsMesh().get(0).getGradPhi(new double[]{0,0});
        //Mesh2D mesh = new Mesh2D(s.getTriangles(), s.getEdges(), 1);
        //mesh.getElementsMesh().get(1).integrateMatriceElementaire();
        //PlotXY plot = new PlotXY(s.getTitle(), mesh);
        //plot.geometryPlot();
        //plot.meshPlot();
        ////////////////////////////////////////////
        double C;
        double We;
        Region1D output = new Region1D(20);
        Region1D input = new Region1D(10);
        //test s = new test("carre");
        Square2D s = new Square2D(" carre ");
        Mesh2D mesh = new Mesh2D(s.getTriangles(), s.getEdges(), 3);
        PlotXY plot = new PlotXY(s.getTitle(), mesh);

        Problem problem = new Problem("problem", mesh);

        problem.solve();
        Element e = mesh.getElementsMesh().get(0);
        double[][] g1 = e.getGradPhi(e.getXYCenter());
        double[][] g2 = e.integrateMatriceElementaire();
        e = mesh.getElementsMesh().get(1);
        double[][] g3 = e.getGradPhi(e.getXYCenter());
        double[][] g4 = e.integrateMatriceElementaire();

        System.out.println("Test Square2D");
      
        C = problem.computeCapacitance();
        System.out.println("C = " + C);

        We = problem.computeEnergy();
        System.out.println("We = " + We);

        plot.geometryPlot();
        plot.meshPlot();
        plot.solutionColorPlot();
        plot.solutionIsoLinesPlot(50);
    }
}

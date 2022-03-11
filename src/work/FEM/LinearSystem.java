/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package work.FEM;

import matrix.SquareMatrix;
import mesh.Element;
import mesh.Mesh2D;
import mesh.Node;

/**
 *
 * @author damatheu
 */
public class LinearSystem {

    private Mesh2D mesh;
    protected SquareMatrix AMatrix;
    protected double[] sVector;

    public LinearSystem(Mesh2D mesh) {
        this.mesh = mesh;
    }

    public void assemble() {
        //Concatenar 
        AMatrix = new SquareMatrix(mesh.getNumberfreeNodes());
        sVector = new double[mesh.getNumberfreeNodes()];
        double f;

        for (int r = 0; r < mesh.getElementsMesh().size(); r++) {
            Element E = mesh.getElementsMesh().get(r);
            f = E.getSource();
            double[][] mE_1 = E.integrateMatriceElementaire();

            for (int i = 0; i < E.getNodes().size(); i++) {
                for (int j = 0; j < E.getNodes().size(); j++) {
                    Node nodei = E.getNodes().get(i);
                    Node nodej = E.getNodes().get(j);

                    if (nodei.getID() < mesh.getNumberfreeNodes()) {
                        if (nodej.getID() < mesh.getNumberfreeNodes()) {
                            AMatrix.setElement(nodei.getID(), nodej.getID(),
                                    AMatrix.getElement(nodei.getID(), nodej.getID()) + mE_1[i][j]);
                        } else {
                            sVector[nodei.getID()] -= mE_1[i][j] * nodej.getValue();
                        }
                        sVector[nodei.getID()] += f;
                    }
                }
            }
        }
    }

    public void solve() {
        double[] V = AMatrix.solve(sVector);
        setSolutionToNodes(V);
    }

    public void setSolutionToNodes(double[] Solution) {
        for (int r = 0; r < mesh.getNodesMesh().size(); r++) {
            Node n = mesh.getNodesMesh().get(r);
            
            int ID = n.getID();
            if (ID < mesh.getNumberfreeNodes()) {
                n.setValue(Solution[ID]);
            }
        }

    }
}

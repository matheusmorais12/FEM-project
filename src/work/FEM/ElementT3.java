package work.FEM;

import java.util.ArrayList;
import work.Geom.Region2D;
import mesh.Element;
import mesh.Node;

/**
 * A class representing a triangular finite element
 *
 * @authors ramdaneb, meunierg
 *
 */
public class ElementT3 extends Element {

    /**
     * Build a new Element
     *
     * @param region the region of the element
     * @param nodes nodes of the element
     */
    public ElementT3(Region2D region, ArrayList<Node> nodes) {
        super(region, nodes);
    }

    @Override
    public double getArea() {
        double[] xy1, xy2, xy3;
        double delta;

        xy1 = nodes.get(0).getCoo();
        double xn1 = xy1[0];
        double yn1 = xy1[1];

        xy2 = nodes.get(1).getCoo();
        double xn2 = xy2[0];
        double yn2 = xy2[1];

        xy3 = nodes.get(2).getCoo();
        double xn3 = xy3[0];
        double yn3 = xy3[1];

        delta = xn2 * yn3 - xn3 * yn2 + xn1 * yn2 - xn2 * yn1 + xn3 * yn1 - xn1 * yn3;

        delta = delta / 2;

        return delta;
    }

    @Override
    public double[] getPhi(double[] xy) {
        double x = xy[0];
        double y = xy[1];
        double[] phi;
        double[] xy1, xy2, xy3;
        double delta;

        xy1 = nodes.get(0).getCoo();
        double xn1 = xy1[0];
        double yn1 = xy1[1];

        xy2 = nodes.get(1).getCoo();
        double xn2 = xy2[0];
        double yn2 = xy2[1];

        xy3 = nodes.get(2).getCoo();
        double xn3 = xy3[0];
        double yn3 = xy3[1];

        delta = xn2 * yn3 - xn3 * yn2 + xn1 * yn2 - xn2 * yn1 + xn3 * yn1 - xn1 * yn3;

        phi = new double[3];
        phi[0] = ((xn2 * yn3 - xn3 * yn2) + (yn2 - yn3) * x + (xn3 - xn2) * y) / delta;
        phi[1] = ((xn3 * yn1 - xn1 * yn3) + (yn3 - yn1) * x + (xn1 - xn3) * y) / delta;
        phi[2] = ((xn1 * yn2 - xn2 * yn1) + (yn1 - yn2) * x + (xn2 - xn1) * y) / delta;

        return phi;
    }

    @Override
    public double[][] getGradPhi(double[] xy) {
        double x = xy[0];
        double y = xy[1];

        double[] xy1, xy2, xy3;
        double delta;
        double gradPhi[][] = new double[2][3];

        xy1 = nodes.get(0).getCoo();
        double xn1 = xy1[0];
        double yn1 = xy1[1];

        xy2 = nodes.get(1).getCoo();
        double xn2 = xy2[0];
        double yn2 = xy2[1];

        xy3 = nodes.get(2).getCoo();
        double xn3 = xy3[0];
        double yn3 = xy3[1];

        delta = xn2 * yn3 - xn3 * yn2 + xn1 * yn2 - xn2 * yn1 + xn3 * yn1 - xn1 * yn3;

        gradPhi[0][0] = (yn2 - yn3) / delta;
        gradPhi[1][0] = (xn3 - xn2) / delta;
        gradPhi[0][1] = (yn3 - yn1) / delta;
        gradPhi[1][1] = (xn1 - xn3) / delta;
        gradPhi[0][2] = (yn1 - yn2) / delta;
        gradPhi[1][2] = (xn2 - xn1) / delta;

        return gradPhi;
    }

    @Override
    public double[][] integrateMatriceElementaire() {
        double mE[][] = new double[3][3];
        double E = region.getConductivity();
                //region.getConductivity();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mE[i][j] = (getGradPhi(getXYCenter())[0][i] * getGradPhi(getXYCenter())[0][j]
                        + getGradPhi(getXYCenter())[1][i] * getGradPhi(getXYCenter())[1][j]) * E * getArea();
            }
        }
        return mE;
    }

    @Override
    public double[] integrateVecteurElementaire() {
        double vecteurElementaire[] = new double[3];
        double n1[] = nodes.get(0).getCoo();
        double n2[] = nodes.get(1).getCoo();
        double n3[] = nodes.get(2).getCoo();

        double delta = n2[0] * n3[1] - n3[0] * n2[1] + n1[0] * n2[1] - 
                n2[0] * n1[1] + n3[0]* n1[1] - n1[0] * n3[1];
        
        for(int i = 0; i < 3; i++){
            vecteurElementaire[i] = region.getSource()*delta/6;
        }
        return vecteurElementaire;

    }   

    public double[][] transpose_gradPhi(double[][] gradPhi) {
        double transpose[][] = new double[3][2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                transpose[i][j] = gradPhi[j][i];
            }
        }
        return transpose;
    }
}

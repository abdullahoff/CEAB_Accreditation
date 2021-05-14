

package src;

/**
* @brief ...
*/

public class Services
{
    public static double[] normal(double[] v)
    {
        double[] nv = new double[v.length];
        double total = 0.0;
        for (int i=0; i<v.length; i++) {
            total = total + v[i];
        }
        for (int i=0; i<v.length; i++) {
            nv[i] = v[i]*(1.0/total);
        }
        return nv;
    }
    
}

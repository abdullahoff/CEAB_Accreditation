/**
* Author: S. Smith
* Revised: Mar 9, 2021
* 
* Description: Learning Outcome - where the measurements for below
* marginal, meets and exceeds are recorded
*/

package src;

import java.util.Objects;

/**
* @brief An ADT that ...
* @details ...
*/
public class LOsT implements Measures
{
    private String name;
    private int n_blw;
    private int n_mrg;
    private int n_mts;
    private int n_exc;
    
    /**
    * @brief ...
    * @param ...
    * @param ...
    */
    public LOsT(String topic, int nblw, int nmrg, int nmts, int nexc)
    {
        if((nblw < 0) || (nmrg < 0) || (nmts < 0) || (nexc < 0)) {
            throw new IllegalArgumentException("Learning outcome measures must be zero or greater");
        }
        name = topic;
        n_blw = nblw;
        n_mrg = nmrg;
        n_mts = nmts;
        n_exc = nexc;
    }
    
    /**
    * @brief ...
    * @return ...
    */
    public String getName()
    {
        return name;
    }
    
    /**
    * @brief ...
    * @return ...
    */
    @Override
    public double[] measures()
    {
        double[] meas = {(double) n_blw, (double) n_mrg, (double) n_mts, (double) n_exc};
        if (Norm.getNLOs()) {
            meas = Services.normal(meas);
        }
        return meas;
    }
    
    /**
    * @brief ...
    * @return ...
    */
    @Override
    public double[] measures(IndicatorT ind)
    {
        throw new UnsupportedOperationException("measures for learning outcomes not defined for indicators");
    }
    
    /**
    * @brief ...
    * @return ...
    */
    @Override
    public double[] measures(AttributeT att)
    {
        throw new UnsupportedOperationException("measures for learning outcomes not defined for attributes");
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof LOsT)) 
        {
            return false;
        }
        LOsT outcome = (LOsT) o;
        return (name == outcome.getName());
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }    
}

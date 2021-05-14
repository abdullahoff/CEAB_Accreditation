/**
* Author: S. Smith
* Revised: Mar 9, 2021
* 
* Description: ...
*/

package src;

import java.util.Set;
import java.util.HashSet;

public class ProgramT extends HashSet<CourseT> implements Measures
{
    
    /**
    * @brief ...
    * @return ...
    */
    @Override
    public double[] measures()
    {
        throw new UnsupportedOperationException("measures requires an attribute for a program");
    }    
    
    /**
    * @brief ...
    * @return ...
    */
    @Override
    public double[] measures(IndicatorT ind)
    {
        throw new UnsupportedOperationException("measures requires an attribute for a program");
    }    
    
    /**
    * @brief ...
    * @return ...
    */
    @Override
    public double[] measures(AttributeT att)
    {
        double[] meas = {0, 0, 0, 0};
        for(CourseT c: this) {
            double[] attMeas = c.measures(att);
            for (int i=0; i<meas.length; i++) {
                meas[i] = meas[i] + attMeas[i];
            }
        }
        //always normalize
        meas = Services.normal(meas);
        return meas;
    }
    
}

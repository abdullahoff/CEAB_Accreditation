/**
* Author: S. Smith
* Revised: March 9, 2021
* 
* Description: ...
*/

package src;

/**
* @brief ...
*/

import java.util.Set;
import java.util.HashSet;

public class AttributeT
{
    private String name;
    private Set<IndicatorT> s;

    /**
    * @brief ...
    * @param ...
    * @param ...
    */
    public AttributeT(String attribName, IndicatorT[] indicators)
    {
        name = attribName;
        s = new HashSet<IndicatorT>();
        for (int i=0; i < indicators.length; i++) {
            s.add(indicators[i]);
        }
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
    public IndicatorT[] getIndicators()
    {
        return s.toArray(new IndicatorT[s.size()]);
    }
    
}

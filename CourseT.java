/**
* Author: S. Smith
* Revised: March 9, 2021
* 
* Revised author: D. Di Cesare
* Revised: March 12, 2021
* Description: Course ADT class
*/

package src;

/**
* @brief A type used to represent a course's covered indicators and learning outcomes.
* @details To be used after indicators have been populated with learning
*         outcomes. 
*/
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.List;

public class CourseT implements Measures
{
    
    private String name;
    private Map<IndicatorT, Set<LOsT>> m;
    
    /**
    * @brief Initializes a course object.
    * @param coursename Title of the course
    * @param indicators Indicators present in the chosen course
    */
    public CourseT(String courseName, IndicatorT[] indicators)
    {
        name = courseName;
        m = new HashMap<IndicatorT, Set<LOsT>>();
        for (int i=0; i < indicators.length; i++) {
            m.put(indicators[i], new HashSet<LOsT>());
        }
    }
    
    /**
    * @brief Get the name of the course.
    * @return Name of the course
    */
    public String getName()
    {
        return name;
    }
    
    /**
    * @brief Get the indicators of the current course.
    * @return All indicators for the course.
    */
    public IndicatorT[] getIndicators()
    {
        return m.keySet().toArray(new IndicatorT[m.size()]);
    }
    
    /**
     * @brief Get the learning outcome of a specific indicator.
     * @param ind Chosen indicator
     * @return Learning outcome of the chosen indicator.
     */
    public LOsT[] getLOs(IndicatorT ind)
    {
        Set<LOsT> LOSet = m.get(ind);
        return LOSet.toArray(new LOsT[LOSet.size()]);
    }
    
    /**
     * @brief Add a learning outcome for a specific indicator. 
     * @details Assumes chosen indicator is present already in the course.
     * @param ind Indicator chosen for learning outcome
     * @param outcome Learning outcome to be added
     */
    public void addLO(IndicatorT ind, LOsT outcome)
    {
        Set<LOsT> LOSet = m.get(ind);
        LOSet.add(outcome);
        //m.put(ind, LOSet);  
    }
    
    /**
     * @brief Remove a learning outcome for a specific indicator. 
     * @param ind Indicator chosen for learning outcome
     * @param outcome Learning outcome to be removed
     */
    public void delLO(IndicatorT ind, LOsT outcome)
    {
        Set<LOsT> LOSet = m.get(ind);
        LOSet.remove(outcome);
        //m.put(ind, LOSet);  
    }
    
    /**
     * @brief Determines if given learning outcomes are present for an indicator.
     * @param ind Indicator to check learning outcome for
     * @param outcomes All outcomes expected for the given Indicator
     * @return true if all supplied outcomes are present for the given indicator,
     *         false otherwise 
     */
    public boolean member(IndicatorT ind, LOsT[] outcomes)
    {
        Set<LOsT> LOSet = m.get(ind);
        List<LOsT> listOutcomes = Arrays.asList(outcomes);
        boolean membership = false; //false if LOSet = null
        if(LOSet != null) {
            membership = true;
            for(LOsT outcome: listOutcomes) {
                membership = membership & LOSet.contains(outcome);
            }
            for(LOsT outcome: LOSet) {
                membership = membership & listOutcomes.contains(outcome);
            }
        }
        return membership;
    }
    
    /**
    * @brief Handle measures call without an indicator or an attribute.
    * @throws UnsupportedOperationException No indicator or attribute given
    */
    @Override
    public double[] measures()
    {
        throw new UnsupportedOperationException("measures requires an indicator or an attribute for a course");
    }    
    
    /**
    * @brief Finds the number of students in each learning outcome category for a given indicator.
    * @details Takes the sum of all students in each measurement category for all learning outcomes.
    *          The results are then normalized by the number of students.
    * @return Normalized measurement of students per category.
    */
    @Override
    public double[] measures(IndicatorT ind)
    {
        Set<LOsT> LOSet = m.get(ind);
        double[] meas = {0, 0, 0, 0};
        if(LOSet != null) {           
            for(LOsT outcome: LOSet) {
                double[] LOmeas = outcome.measures();
                for (int i=0; i<meas.length; i++) {
                    meas[i] = meas[i] + LOmeas[i];
                }
            }
            if (Norm.getNInd()) {
                meas = Services.normal(meas);
            }
        }
        return meas;
    }    
    
    /**
    * @brief Finds the number of students in each learning outcome category for all 
    *        indicators of an attribute.
    * @details Takes the sum of all students in each measurement category. This is done for all learning outcomes
               of each of the attribute's indicators. The results are then normalized by the number of students.
    * @return Normalized measurement of students per category.
    */
    @Override
    public double[] measures(AttributeT att)
    {
        IndicatorT[] inds = att.getIndicators();
        double[] meas = {0, 0, 0, 0};
        if (inds.length != 0) {
          for(IndicatorT ind: inds) {
              double[] indMeas = this.measures(ind);
              for (int i=0; i<meas.length; i++) {
                  meas[i] = meas[i] + indMeas[i];
              }
          }
          if (Norm.getNAtt()) {
              meas = Services.normal(meas);
          }
        }
        return meas;
    }    
    
}

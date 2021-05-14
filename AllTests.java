/**
* Author: author
* Revised: date
* 
* Description: 
*/

package src;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestAttributeT.class,	
    TestLOsT.class,
    TestCourseT.class,
    TestProgramT.class
})

public class AllTests
{
    //Helper function
    //AssertArrayEquals fails with different orders, compareArray does not.
    public static boolean compareArray(Object[] a, Object[] b)
    {  
        for (Object obj1 : a)
        {
            boolean comparison = false;
            
            for (Object obj2 : b)
            if (obj1.equals(obj2))
            comparison = true;
            
            if (!comparison)
            return false;
        }
        
        for (Object obj1 : b)
        {
            boolean comparison = false;
            
            for (Object obj2 : a)
            if (obj1.equals(obj2))
            comparison = true;
            
            if (!comparison)
            return false;
        }
        return true;
    }
    
    public static boolean compareArray(LOsT[] a, LOsT[] b)
    {  
        for (LOsT obj1 : a)
        {
            boolean comparison = false;
            
            for (LOsT obj2 : b)
            if (obj1.equals(obj2))
            comparison = true;
            
            if (!comparison)
            return false;
        }
        
        for (LOsT obj1 : b)
        {
            boolean comparison = false;
            
            for (LOsT obj2 : a)
            if (obj1.equals(obj2))
            comparison = true;
            
            if (!comparison)
            return false;
        }
        return true;
    }
    
    
}

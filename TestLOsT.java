package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class TestLOsT
{

    private LOsT test1;
    private LOsT test2;
    
    @Before
    public void setUp()
    {
        test1 = new LOsT("topic 1", 20, 30, 40, 50);
        test2 = new LOsT("topic 2", 1, 2, 3, 4);
    }

    @After
    public void tearDown()
    {
        test1 = null;
        test2 = null;
        Norm.setNorms(false, false, false);
    }

    @Test (expected=IllegalArgumentException.class)
    public void testConstructorException1()
    {
        LOsT fail = new LOsT("should fail", -1, 1, 1, 1);
    }

    @Test (expected=IllegalArgumentException.class)
    public void testConstructorException2()
    {
        LOsT fail = new LOsT("should fail", 1, -1, 1, 1);
    }

    @Test (expected=IllegalArgumentException.class)
    public void testConstructorException3()
    {
        LOsT fail = new LOsT("should fail", 1, 1, -1, 1);
    }

    @Test (expected=IllegalArgumentException.class)
    public void testConstructorException4()
    {
        LOsT fail = new LOsT("should fail", 1, 1, 1, -1);
    }

    @Test
    public void testGetName()
    {
        assertEquals("topic 1", test1.getName());
        assertEquals("topic 2", test2.getName());
    }

    @Test
    public void testEquals()
    {
        LOsT pass = new LOsT("topic 1", 1, 1, 1, 1);
        LOsT fail = new LOsT("topic two", 1, 1, 1, 1);
        String diff_object = new String("testing");

        assertTrue(test1.equals(test1)); // tests if (o == this)
        assertFalse(test1.equals(diff_object)); //tests if (!(o instanceof LOsT))
        assertTrue(test1.equals(pass)); //name equality
        assertFalse(test2.equals(fail)); //name equality (false)
    }

    @Test (expected=UnsupportedOperationException.class)
    public void testMeasuresIndicator()
    {
        IndicatorT fail = IndicatorT.tools;
        test1.measures(fail);
    }

    @Test (expected=UnsupportedOperationException.class)
    public void testMeasuresLOsTAttribute()
    {
        AttributeT fail = new AttributeT("fail", new IndicatorT[] { IndicatorT.tools });
        test1.measures(fail);
    }

    @Test
    public void testMeasuresLOsT()
    {
        Norm.setNLOs(false);
        double[] ans = new double[] { 20.0, 30.0, 40.0, 50.0 };
        double[] out = test1.measures();

        for (int i =0 ; i < 4; i++)
        {
            assertEquals(ans[i], out[i], 0.01);
        }
    }
    
    @Test
    public void testMeasuresLOsTNorm()
    {
        Norm.setNLOs(true);
        double[] ans = new double[] { 0.1, 0.2, 0.3, 0.4};
        double[] out = test2.measures();
        
        for (int i =0 ; i < 4; i++)
        {
            assertEquals(ans[i], out[i], 0.01);
        }
    }
}

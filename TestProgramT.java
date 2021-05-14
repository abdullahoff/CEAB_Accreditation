package src;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Arrays;

public class TestProgramT
{

    private ProgramT program;
    
    @Before
    public void setUp()
    {
        Norm.setNorms(false, false, false);
        IndicatorT[] SE2DM3_indicators = new IndicatorT[] { IndicatorT.math, IndicatorT.recogTheory };
        IndicatorT[] SE2XA3_indicators = new IndicatorT[] { IndicatorT.desPrinciples, IndicatorT.recogTheory };

        CourseT SE2DM3 = new CourseT("Discrete Mathematics with Applications I", SE2DM3_indicators);
        CourseT SE2XA3 = new CourseT("Software Development Skills", SE2XA3_indicators);

        SE2DM3.addLO(IndicatorT.math, new LOsT("Solve CalcCheck Problems", 5, 2, 6, 8));
        SE2DM3.addLO(IndicatorT.recogTheory, new LOsT("Understand Set Theory", 14, 8, 23, 1));
        SE2XA3.addLO(IndicatorT.desPrinciples, new LOsT("Hardware Basics", 5, 16, 31, 10));
        SE2XA3.addLO(IndicatorT.recogTheory, new LOsT("Master NASM Development", 35, 12, 3, 7));

        program = new ProgramT();
        program.add(SE2DM3);
        program.add(SE2XA3);
    }

    @After
    public void tearDown()
    {
        program = null;
    }

    @Test (expected=UnsupportedOperationException.class)
    public void testMeasuresProgram()
    {
        program.measures();
    }

    @Test (expected=UnsupportedOperationException.class)
    public void testMeasuresProgramIndicator()
    {
        program.measures(IndicatorT.assumpt);
    }

    @Test 
    public void testMeasuresProgramAttribute()
    {
        AttributeT test_att = new AttributeT("Design", new IndicatorT[] {IndicatorT.math, IndicatorT.desPrinciples,
                                                                       IndicatorT.openEnded, IndicatorT.recogTheory,
                                                                      });
        double[] expected_results_1 = new double[] { 0.317, 0.204, 0.339, 0.140 };
        double[] result_false_norm = program.measures(test_att);

        //Changes to Norm still change result, see Courses.measures(attribute)
        Norm.setNLOs(true);
        double[] result_true_norm = program.measures(test_att);
        double[] expected_results_2 = new double[] { 0.309, 0.184, 0.335, 0.172 };
                                                            
        for (int i =0 ; i < 4; i++)
        {
            assertEquals(result_false_norm[i], expected_results_1[i], 0.005);
            assertEquals(result_true_norm[i], expected_results_2[i], 0.005);
        }
    }

}

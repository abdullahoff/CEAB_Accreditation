/**
* Author: 
* Revised: 
* 
* Description:
*/

package src;

import org.junit.*;

//import jdk.jfr.Timestamp;

import static org.junit.Assert.*;

import java.util.Arrays;

public class TestCourseT
{
    
    private CourseT SE2DM3;
    private CourseT SE2XA3;
    
    @Before
    public void setUp()
    {
        IndicatorT[] SE2DM3_indicators = new IndicatorT[] { IndicatorT.math, IndicatorT.recogTheory, IndicatorT.ideaGeneration };
        IndicatorT[] SE2XA3_indicators = new IndicatorT[] { IndicatorT.desPrinciples, IndicatorT.recogTheory };
        
        SE2DM3 = new CourseT("Discrete Mathematics with Applications I", SE2DM3_indicators);
        SE2XA3 = new CourseT("Software Development Skills", SE2XA3_indicators);
        
        SE2DM3.addLO(IndicatorT.math, new LOsT("Solve CalcCheck Problems", 5, 2, 6, 8));
        SE2DM3.addLO(IndicatorT.recogTheory, new LOsT("Understand Set Theory", 14, 8, 23, 1));
        SE2DM3.addLO(IndicatorT.recogTheory, new LOsT("Universal Quantifiers", 31, 2, 7, 11));
        SE2DM3.addLO(IndicatorT.ideaGeneration, new LOsT("Write Calculational Proofs", 8, 17, 4, 13));
    }
    
    @After
    public void tearDown()
    {
        SE2DM3 = null;
        SE2XA3 = null;
        Norm.setNorms(false, false, false);
    }
    
    @Test
    public void testGetName()
    {
        assertEquals("Discrete Mathematics with Applications I", SE2DM3.getName());
    }
    
    @Test
    public void testGetIndicators()
    {
        IndicatorT[] se2dm3_results = new IndicatorT[] { IndicatorT.recogTheory, IndicatorT.math, IndicatorT.ideaGeneration };
        assertTrue(AllTests.compareArray(se2dm3_results, SE2DM3.getIndicators()));
    }
    
    @Test
    public void testGetLOs()
    {
        LOsT lo_1 = new LOsT("Solve CalcCheck Problems", 5, 2, 6, 8);
        LOsT lo_2 = new LOsT("Universal Quantifiers", 31, 2, 7, 11);
        LOsT lo_3 = new LOsT("Understand Set Theory", 14, 8, 23, 1);
        LOsT lo_4 = new LOsT("Write Calculational Proofs", 8, 17, 4, 13);
        LOsT[] expected_1 = new LOsT[] { lo_1 };
        LOsT[] expected_2 = new LOsT[] { lo_2, lo_3 };
        LOsT[] expected_3 = new LOsT[] { lo_4 };
        
        assertTrue(AllTests.compareArray(expected_1, SE2DM3.getLOs(IndicatorT.math)));
        assertTrue(AllTests.compareArray(expected_2, SE2DM3.getLOs(IndicatorT.recogTheory)));
        assertTrue(AllTests.compareArray(expected_3, SE2DM3.getLOs(IndicatorT.ideaGeneration)));
        
    }
    
    @Test
    public void testAddLO()
    {
        LOsT lo_1 = new LOsT("Semantics of Formulae", 34, 1, 27, 5);
        SE2DM3.addLO(IndicatorT.recogTheory, lo_1);
        SE2XA3.addLO(IndicatorT.desPrinciples, lo_1);
        
        LOsT[] result_2dm3 = SE2DM3.getLOs(IndicatorT.recogTheory);
        LOsT[] result_2xa3 = SE2XA3.getLOs(IndicatorT.desPrinciples);
        assertTrue(contains(result_2dm3, lo_1));
        assertTrue(contains(result_2xa3, lo_1));
    }
    
    @Test
    public void testDelLO()
    {
        LOsT lo_1 = new LOsT("Solve CalcCheck Problems", 5, 2, 6, 8);
        
        LOsT[] result_before = SE2DM3.getLOs(IndicatorT.math);
        assertTrue(contains(result_before, lo_1));
        
        SE2DM3.delLO(IndicatorT.math, lo_1);
        LOsT[] result_after = SE2DM3.getLOs(IndicatorT.math);
        assertFalse(contains(result_after, lo_1));
    }
    
    @Test
    public void testMember()
    {
        LOsT lo_1 = new LOsT("Solve CalcCheck Problems", 5, 2, 6, 8);
        LOsT lo_extra = new LOsT("Solve CalcCheck Problems2", 1, 1, 1, 1);
        LOsT lo_2 = new LOsT("Universal Quantifiers", 31, 2, 7, 11);
        LOsT lo_3 = new LOsT("Understand Set Theory", 14, 8, 23, 1);
        LOsT lo_4 = new LOsT("Write Calculational Proofs", 8, 17, 4, 13);
        
        LOsT[] se2dm3_math = new LOsT[] { lo_1 };
        LOsT[] se2dm3_math_extra = new LOsT[] { lo_1, lo_extra };
        LOsT[] se2dm3_theory = new LOsT[] { lo_2, lo_3 };
        LOsT[] se2dm3_idea = new LOsT[] { lo_4 };
        
        assertTrue(SE2DM3.member(IndicatorT.math, se2dm3_math));
        assertTrue(SE2DM3.member(IndicatorT.recogTheory, se2dm3_theory));
        assertTrue(SE2DM3.member(IndicatorT.ideaGeneration, se2dm3_idea));
        assertFalse(SE2DM3.member(IndicatorT.math, se2dm3_math_extra));
    }
    
    @Test (expected=UnsupportedOperationException.class)
    public void testMeasuresCourse()
    {
        SE2DM3.measures();
    }
    
    @Test
    public void testMeasuresCourseIndicator()
    {
        double[] expected_math = new double[] { 5.0, 2.0, 6.0, 8.0 };
        double[] expected_theory = new double[] { 45.0, 10.0, 30.0, 12.0 };
        double[] expected_idea = new double[] { 8.0, 17.0, 4.0, 13.0 };
        double[] result_math = SE2DM3.measures(IndicatorT.math);
        double[] result_theory = SE2DM3.measures(IndicatorT.recogTheory);
        double[] result_idea = SE2DM3.measures(IndicatorT.ideaGeneration);
        
        Norm.setNInd(true);
        double[] expected_math_norm = new double[] { 0.238, 0.095, 0.286, 0.381 };
        double[] expected_theory_norm = new double[] { 0.464, 0.103, 0.309, 0.124 };
        double[] expected_idea_norm = new double[] { 0.190, 0.405, 0.095, 0.310 };
        double[] result_math_norm = SE2DM3.measures(IndicatorT.math);
        double[] result_theory_norm = SE2DM3.measures(IndicatorT.recogTheory);
        double[] result_idea_norm = SE2DM3.measures(IndicatorT.ideaGeneration);
        
        for (int i = 0; i < 4; i++) {
            assertEquals(expected_math[i], result_math[i], 0.005);
            assertEquals(expected_theory[i], result_theory[i], 0.005);
            assertEquals(expected_idea[i], result_idea[i], 0.005);
            assertEquals(expected_math_norm[i], result_math_norm[i], 0.005);
            assertEquals(expected_theory_norm[i], result_theory_norm[i], 0.005);
            assertEquals(expected_idea_norm[i], result_idea_norm[i], 0.005);
        }
    }
    
    @Test
    public void testMeasuresCourseAttribute()
    {
        AttributeT proofs = new AttributeT("Produce Calculational Proofs",
        new IndicatorT[] { IndicatorT.math, IndicatorT.recogTheory });
        AttributeT total = new AttributeT("All aspects",
        new IndicatorT[] { IndicatorT.math, IndicatorT.recogTheory, IndicatorT.ideaGeneration });
        
        double[] expected_proofs = new double[] { 50.0, 12.0, 36.0, 20.0 };
        double[] expected_total = new double[] { 58.0, 29.0, 40.0, 33.0 };
        double[] results_proofs = SE2DM3.measures(proofs);
        double[] results_total = SE2DM3.measures(total);
        
        Norm.setNAtt(true);
        double[] expected_proofs_norm = new double[] { 0.424, 0.102, 0.305, 0.169 };
        double[] expected_total_norm = new double[] { 0.363, 0.181, 0.25, 0.206 };
        double[] results_proofs_norm = SE2DM3.measures(proofs);
        double[] results_total_norm = SE2DM3.measures(total);
        
        for (int i = 0; i < 4; i++) {
            assertEquals(expected_proofs[i], results_proofs[i], 0.005);
            assertEquals(expected_total[i], results_total[i], 0.005);
            assertEquals(expected_proofs_norm[i], results_proofs_norm[i], 0.005);
            assertEquals(expected_total_norm[i], results_total_norm[i], 0.005);
        }
    }
    
    private boolean contains(LOsT[] a, LOsT b)
    {
        for (LOsT obj : a)
            if (obj.equals(b))
                return true;
        return false;
    }
    
}

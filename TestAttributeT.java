

package src;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Arrays;

public class TestAttributeT 
{

    private AttributeT properties;
    private AttributeT duplicates;
    private IndicatorT[] comp1;
    private IndicatorT[] comp2;

    @Before
    public void setUp() 
    {
        properties = new AttributeT("Properties",
                new IndicatorT[] { IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.assumpt, IndicatorT.suitableFund,
                        IndicatorT.recogTheory, IndicatorT.modelSelect, IndicatorT.estOutcomes, IndicatorT.desProcess,
                        IndicatorT.desPrinciples, IndicatorT.openEnded, IndicatorT.ideaGeneration,
                        IndicatorT.healthSafety, IndicatorT.standards, IndicatorT.tools, IndicatorT.engInSoc,
                        IndicatorT.awarePEO });

        duplicates = new AttributeT("Duplicates", new IndicatorT[] { IndicatorT.math, IndicatorT.math, IndicatorT.tools,
                IndicatorT.tools, IndicatorT.tools, IndicatorT.standards });
    }

    @After
    public void tearDown() 
    {
        properties = null;
        duplicates = null;
    }

    @Test
    public void testGetName() 
    {
        assertEquals(properties.getName(), "Properties");
        assertEquals(duplicates.getName(), "Duplicates");
    }

    @Test
    public void getIndicators() 
    {
        comp1 = new IndicatorT[] { IndicatorT.ideaGeneration, IndicatorT.specEngKnow, IndicatorT.assumpt,
                IndicatorT.suitableFund, IndicatorT.recogTheory, IndicatorT.math, IndicatorT.modelSelect,
                IndicatorT.estOutcomes, IndicatorT.desProcess, IndicatorT.desPrinciples, IndicatorT.openEnded,
                IndicatorT.healthSafety, IndicatorT.standards, IndicatorT.tools, IndicatorT.engInSoc,
                IndicatorT.awarePEO };
        comp2 = new IndicatorT[] { IndicatorT.math, IndicatorT.tools, IndicatorT.standards };
        
        IndicatorT[] prop_ind = properties.getIndicators();
        IndicatorT[] dupe_ind = duplicates.getIndicators();

        boolean comparison1 = AllTests.compareArray(comp1, prop_ind);
        boolean comparison2 = AllTests.compareArray(comp2, dupe_ind);

        assertTrue(comparison1);
        assertTrue(comparison2);
        assertEquals(comp1.length, prop_ind.length);
        assertEquals(comp2.length, dupe_ind.length);
    }

}


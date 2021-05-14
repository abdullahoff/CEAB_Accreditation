

package src;

import java.util.Arrays;

public class A3Example
{
   public static void main(String[] args) {

      //Exercise IndicatorT Module
      IndicatorT[] SE2AA4_indicators = new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.assumpt,
                                                     IndicatorT.suitableFund, IndicatorT.recogTheory, IndicatorT.modelSelect,
                                                     IndicatorT.estOutcomes, IndicatorT.desProcess, IndicatorT.desPrinciples,
                                                     IndicatorT.openEnded, IndicatorT.tools, IndicatorT.engInSoc,
                                                     IndicatorT.awarePEO};
      IndicatorT[] SE2C03_indicators = new IndicatorT[] {IndicatorT.desPrinciples};
      IndicatorT[] SE3A04_indicators = new IndicatorT[] {IndicatorT.standards, IndicatorT.healthSafety};
      IndicatorT[] Unused_indicators = new IndicatorT[] {IndicatorT.ideaGeneration, IndicatorT.tools};

      //Excercise AttributeT Module
      AttributeT Know = new AttributeT("Knowledge Base for Engineering", new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow});
      AttributeT ProbAnalysis = new AttributeT("Problem Analysis", new IndicatorT[] {IndicatorT.assumpt, IndicatorT.suitableFund});
      AttributeT Investigation = new AttributeT("Investigation", new IndicatorT[] {IndicatorT.recogTheory, IndicatorT.modelSelect,
                                                                                   IndicatorT.estOutcomes});

      AttributeT Design = new AttributeT("Design", new IndicatorT[] {IndicatorT.desProcess, IndicatorT.desPrinciples,
                                                                     IndicatorT.openEnded, IndicatorT.ideaGeneration,
                                                                     IndicatorT.healthSafety, IndicatorT.standards});

      AttributeT Tools = new AttributeT("Use of Engineering Tools", new IndicatorT[] {IndicatorT.tools});
      AttributeT Professionalism = new AttributeT("Professionalism", new IndicatorT[] {IndicatorT.engInSoc, IndicatorT.awarePEO});
      System.out.format("\nAttribute Name for Tools: " + Tools.getName() + "\n");
      System.out.format("Attribute Indicators Design: " + Arrays.toString(Design.getIndicators()) + "\n");

      //Excercise Norm Module
      Norm.setNorms(false, false, false);
      System.out.format("\nNorm getNLOs: " + Norm.getNLOs() + "\n");

      //Exercise Learning Outcomes Module
      LOsT LO1 = new LOsT("topic 1", 23, 45, 56, 89);
      LOsT LO2 = new LOsT("topic 2", 15, 6, 78, 4);

      System.out.format("\nL01 getName: " + LO1.getName() + "\n"); 
      System.out.format("L01 == L02: " + LO1.equals(LO2) + "\n");
      System.out.format("measures = " + Arrays.toString(LO1.measures()) + "\n");

      //Excercise Courses Module
      CourseT SE2AA4 = new CourseT("Software Engineering Design 1", SE2AA4_indicators);
      System.out.print("\nSE2AA4 Course Name: " + SE2AA4.getName() + "\n");
      System.out.print("SE2AA4 Indicators: " + Arrays.toString(SE2AA4.getIndicators()) + "\n");

      SE2AA4.addLO(IndicatorT.math, LO1);
      SE2AA4.addLO(IndicatorT.math, LO2);
      
      System.out.print("Learning Outcomes for math indicator for " + SE2AA4.getName() + ": ");
      for (LOsT outcome : SE2AA4.getLOs(IndicatorT.math)) {
          System.out.print(outcome.getName() + " ");
      }
      System.out.println();

      System.out.println("Measures for SE2AA4 with IndicatorT.math: " + Arrays.toString(SE2AA4.measures(IndicatorT.math)));
      System.out.println("Measures for SE2AA4 with Attribute Knowledge: " + Arrays.toString(SE2AA4.measures(Know)));

      //Delete learning outcomes
      System.out.println("Pre delete L01 in SE2AA4?: " + SE2AA4.member(IndicatorT.math, new LOsT[] {LO1, LO2}));            
      SE2AA4.delLO(IndicatorT.math, LO1);
      System.out.println("Post delete L01 in SE2AA4?: " + SE2AA4.member(IndicatorT.math, new LOsT[] {LO1, LO2}));
      //Delete remaining learning outcome from SE2AA4 so that it is empty
      SE2AA4.delLO(IndicatorT.math, LO2);
      System.out.println("Learing Outcomes empty: " + SE2AA4.member(IndicatorT.math, new LOsT[] {}));

      //Populate a few Courses
      CourseT SE2C03 = new CourseT("Data Structures and Algorithms", SE2C03_indicators);
      CourseT SE3A04 = new CourseT("Software Design II Large Scale Systems", SE3A04_indicators);

      //Adding Learning Outcomes to Courses
      SE2AA4.addLO(IndicatorT.desProcess, new LOsT("Recog and follow eng des process", 5, 16, 90, 60));
      SE2AA4.addLO(IndicatorT.desProcess, new LOsT("Modularization and interface design", 20, 8, 80, 30));
      SE2AA4.addLO(IndicatorT.desPrinciples, new LOsT("Software qualities", 60, 40, 50, 50));
      SE2AA4.addLO(IndicatorT.openEnded, new LOsT("Complete design, implement and test for a set of modules", 5, 4, 3, 2));
      SE2C03.addLO(IndicatorT.desPrinciples, new LOsT("Identify time/space trade-offs", 0, 4, 4, 22));
      SE3A04.addLO(IndicatorT.standards, new LOsT("Select among design methodologies", 80, 150, 97, 110));      
      SE3A04.addLO(IndicatorT.standards, new LOsT("State the design principles", 70, 120, 85, 130));
      SE3A04.addLO(IndicatorT.standards, new LOsT("Evaluate design solution against requirements", 200, 80, 100, 4));      
      SE3A04.addLO(IndicatorT.healthSafety, new LOsT("Organize and plan the development of a software system", 80, 150, 97, 110));
      SE3A04.addLO(IndicatorT.healthSafety, new LOsT("Select among development doc templates", 50, 50, 20, 30));
      SE3A04.addLO(IndicatorT.healthSafety, new LOsT("Assess the impact of a requirement on the architecture", 200, 80, 100, 4));

      System.out.println("\n2AA4 Measures for IndicatorT.desProcess: " + Arrays.toString(SE2AA4.measures(IndicatorT.desProcess)));
      System.out.println("2AA4 Measures Attribute Design: " + Arrays.toString(SE2AA4.measures(Design)));

      //Add courses to P
      ProgramT P = new ProgramT();
      P.add(SE2AA4);
      P.add(SE2C03);
      P.add(SE3A04);
      System.out.println("Measures for Design: " + Arrays.toString(P.measures(Design)));
  }
}

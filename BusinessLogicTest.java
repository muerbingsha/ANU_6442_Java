// source: http://www.java2s.com/Tutorials/Java/JUnit/0140__JUnit_Parameterized_Test.htm

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

//mark the test case class as Parameterized
@RunWith(Parameterized.class)
public class BusinessLogicTest {
    Integer inputNumber;
    Boolean expectedResult;
    private BusinessLogic logic;

    // constructor, it has to be declared as public
    // or error of "Test class should have exactly one public constructor" will occur
    public BusinessLogicTest(Integer inputNumber,
                      Boolean expectedResult) {
        System.out.println("TestJunit-> inputNumber:"+inputNumber+" expectedResult:"+expectedResult);
        this.inputNumber = inputNumber;
        this.expectedResult = expectedResult;

    }


    // methods have been listed in order


    @Before
    public void initialize() {
        logic = new BusinessLogic();
        System.out.printf("\n\nThis will be executed before every@Test \n");
    }


    //The method which provides the parameters
    @Parameterized.Parameters
    public static Collection primeNumbers() {
        System.out.println("primeNumbers() is called");
        return Arrays.asList(new Object[][] {
                { 2, true },
                { 6, false },
                { 19, true },
                { 22, false },
                { 23, true }
        });
    }

    //The real testing method is marked with @Test
    @Test
    public void testPrimeNumberChecker() {
        System.out.println("Parameterized Number is : " + inputNumber);
        assertEquals(expectedResult, logic.validate(inputNumber));
    }

}

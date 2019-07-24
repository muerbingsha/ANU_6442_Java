// source: http://www.java2s.com/Tutorials/Java/JUnit/0140__JUnit_Parameterized_Test.htm

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
/*from w w w  .j a  v a 2  s  . c o m*/
public class Main {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(BusinessLogicTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
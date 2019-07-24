// source: http://www.java2s.com/Tutorials/Java/JUnit/0140__JUnit_Parameterized_Test.htm

public class BusinessLogic {
  public Boolean validate(final Integer primeNumber) {
    for (int i = 2; i < (primeNumber / 2); i++) {
      if (primeNumber % i == 0) {
        return false;
      }//from   w w  w .ja v a 2  s.  c  o  m
    }
    return true;
  }
}
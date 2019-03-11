package org.sonar.samples.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class MyFirstCustomCheckTest {
 
  @Test
  public void test() {
	 // JavaCheckVerifier.verify("src/test/files/MyFirstCustomCheck.java", new MyFirstCustomCheck2());
	  JavaCheckVerifier.verify("src/main/java/org/sonar/samples/java/checks/BlockDTO.java", new DTOVarNameCheck());
	  System.out.println("Inside Test File");
  }
}

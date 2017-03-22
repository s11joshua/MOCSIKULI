package Discovery;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import Dynamics.JDBCConnection;

public class TestRunner {
	public static void main(String[] args) {
		//JDBCConnection.GetRedemtionID("julia.anthony@moctestdomain.com");
	      Result result = JUnitCore.runClasses(TestExecution.class);
		
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
		
	}
}

package test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * A command line based tool to run tests.
 *
 */
public class TestRunner {

	public static void main(String[] args){

		Result result0 = JUnitCore.runClasses(TestCezmi.class);
		Result result1 = JUnitCore.runClasses(TestCezerye.class);
		Result result2 = JUnitCore.runClasses(TestFirildak.class);
		Result result3 = JUnitCore.runClasses(TestCezerye.class);
		Result result4 = JUnitCore.runClasses(TestXmlReader.class);
		
		for(Failure failure : result0.getFailures())
		{
			System.out.println("Number of failures: " + result0.getFailureCount() + " Reason is: " +failure.toString());
		}

		System.out.println(result0.wasSuccessful());
		
		for(Failure failure : result1.getFailures())
		{
			System.out.println("Number of failures for Cezerye: " + result1.getFailureCount() + " Reason is: " +failure.toString());
		}

		System.out.println(result1.wasSuccessful());
		

		for(Failure failure : result2.getFailures())
		{
			System.out.println("Number of failures for Firildak: " + result2.getFailureCount() + " Reason is: " +failure.toString());
		}

		System.out.println(result2.wasSuccessful());
		
		for(Failure failure : result4.getFailures())
		{
			System.out.println("Number of failures for XmlReader: " + result4.getFailureCount() + " Reason is: " +failure.toString());
		}

		System.out.println(result4.wasSuccessful());
	}

}

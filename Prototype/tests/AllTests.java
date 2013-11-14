/** 
 * @author Ashley Simpson
 * @version 2013/11/14
 * Cycle Testing Class
 */

package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({MapTest.class, CycleTest.class, FrameTest.class})
public class AllTests {
	
}
/** 
 * @author Ashley Simpson
 * @version 2013/11/14
 * AllTests Testing Class
 */

package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.Suite;

// file used to run all test cases at once
@RunWith(Suite.class)
@SuiteClasses( {MapTest.class, CycleTest.class, FrameTest.class, PlayerControlTest.class, GameSetupTest.class,
				MapHandlerTest.class, SetupPanelTest.class, GameMasterTest.class, DatabaseCallsTest.class,
				ReadyTimerListenerTest.class, CreateUserTest.class, PlayerTest.class})
public class AllTests {
	
}
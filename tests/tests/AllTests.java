/** 
 * @author Ashley Simpson
 * @version 2013/11/14
 * AllTests Testing Class
 */

package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( {MapTest.class, CycleTest.class, FrameTest.class, PlayerControlTest.class, GameSetupTest.class,
				MapHandlerTest.class, SetupPanelTest.class, GameMasterTest.class, DatabaseCallsTest.class,
				ReadyTimerListenerTest.class, CreateUserTest.class})
public class AllTests {
	
}
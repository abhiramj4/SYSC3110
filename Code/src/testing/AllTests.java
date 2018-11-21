package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SunflowerTest.class, PeashooterTest.class, BaseZombieTest.class,  LevelTest.class, BoardTest.class, 
					CoordinateTest.class, GameTest.class, SquareTest.class})
public class AllTests {

}

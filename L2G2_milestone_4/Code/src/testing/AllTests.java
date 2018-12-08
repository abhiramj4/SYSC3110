package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SunflowerTest.class, PeashooterTest.class, CherryBombTest.class, WallnutTest.class, BaseZombieTest.class, ConeheadZombieTest.class, FlagZombieTest.class,
	LevelTest.class, CoordinateTest.class, SquareTest.class})
public class AllTests {

}

import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    List<Station> route;

    @Override
    public void setUp() throws Exception {
        route = new ArrayList<>();
        Line line1 = new Line(1, "First");
        Line line2 = new Line(2, "Second");
        Line line3 = new Line(3, "Third");

        route.add(new Station("One", line1));
        route.add(new Station("Two", line1));
        route.add(new Station("Three", line1));
        route.add(new Station("Four", line2));
        route.add(new Station("Five", line2));
        route.add(new Station("Six", line3));
        route.add(new Station("Seven", line3));
        route.add(new Station("Eight", line3));
    }

    public void testcalCulateDuration(){
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 19.5;
        assertEquals(expected, actual);
    }

}

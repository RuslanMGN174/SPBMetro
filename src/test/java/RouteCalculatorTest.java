import core.Line;
import core.Station;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    List<Station> route;
    RouteCalculator testCalculator;

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

        testCalculator = new RouteCalculator(new StationIndex());
    }

    public void testCalCulateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 19.5;
        assertEquals(expected, actual);
    }

    public void testGetShortestRoute(){
        List<Station> actual = testCalculator.getShortestRoute(route.get(0), route.get(4));
        List<Station> expected = new ArrayList<Station>(){{
            add(route.get(0));
            add(route.get(1));
            add(route.get(2));
            add(route.get(3));
            add(route.get(4));
        }};
        assertEquals(expected, actual);

    }

}

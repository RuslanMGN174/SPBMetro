import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RouteCalculatorTest extends TestCase {
    List<Station> route;
    RouteCalculator testCalculator;
    Line line1;
    Line line2;
    Line line3;
    StationIndex index;


    @Override
    public void setUp() throws Exception {
        line1 = new Line(1, "First");
        line2 = new Line(2, "Second");
        line3 = new Line(3, "Third");

        route = new ArrayList<>();
        route.add(new Station("One", line1));
        route.add(new Station("Two", line1));
        route.add(new Station("Three", line1));
        route.add(new Station("Four", line2));
        route.add(new Station("Five", line2));
        route.add(new Station("Six", line3));
        route.add(new Station("Seven", line3));
        route.add(new Station("Eight", line3));

        line1.addStation(route.get(0));
        line1.addStation(route.get(1));
        line1.addStation(route.get(2));

        line2.addStation(route.get(3));
        line2.addStation(route.get(4));

        line3.addStation(route.get(5));
        line3.addStation(route.get(6));
        line3.addStation(route.get(7));

        List<Station> connection1 = new ArrayList<Station>() {{
            add(route.get(2));
            add(route.get(3));
        }};

        List<Station> connection2 = new ArrayList<Station>() {{
            add(route.get(4));
            add(route.get(5));
        }};

        index = new StationIndex();
        index.addConnection(connection1);
        index.addConnection(connection2);

        testCalculator = new RouteCalculator(index);
    }

    public void testGetRouteWithTwoConnections() {
        Method privateGetRouteWithTwoConnections;
        List<Station> list = null;
        try {
            privateGetRouteWithTwoConnections = RouteCalculator.class.getDeclaredMethod("getRouteWithTwoConnections", Station.class, Station.class);
            privateGetRouteWithTwoConnections.setAccessible(true);
            list = (List<Station>) privateGetRouteWithTwoConnections.invoke(new RouteCalculator(index), route.get(0), route.get(7));

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        List<Station> actual = list;
        List<Station> expected = new ArrayList<Station>() {{
            add(route.get(0));
            add(route.get(1));
            add(route.get(2));
            add(route.get(3));
            add(route.get(4));
            add(route.get(5));
            add(route.get(6));
            add(route.get(7));
        }};
        assertEquals(expected, actual);

    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 19.5;
        assertEquals(expected, actual);
    }

    public void testGetStations() {
        List<Station> actual = route.get(0).getLine().getStations();
        List<Station> expected = new ArrayList<Station>() {{
            add(route.get(0));
            add(route.get(1));
            add(route.get(2));
        }};
        assertEquals(expected, actual);
    }

    public void testGetShortestRoute1() {
        List<Station> actual = testCalculator.getShortestRoute(route.get(0), route.get(2));
        List<Station> expected = new ArrayList<Station>() {{
            add(route.get(0));
            add(route.get(1));
            add(route.get(2));
        }};
        assertEquals(expected, actual);
    }

    public void testGetShortestRoute2() {
        List<Station> actual = testCalculator.getShortestRoute(route.get(0), route.get(3));
        List<Station> expected = new ArrayList<Station>() {{
            add(route.get(0));
            add(route.get(1));
            add(route.get(2));
            add(route.get(3));
        }};
        assertEquals(expected, actual);
    }

    public void testGetShortestRoute3() {
        List<Station> actual = testCalculator.getShortestRoute(route.get(0), route.get(7));
        List<Station> expected = new ArrayList<Station>() {{
            add(route.get(0));
            add(route.get(1));
            add(route.get(2));
            add(route.get(3));
            add(route.get(4));
            add(route.get(5));
            add(route.get(6));
            add(route.get(7));
        }};
        assertEquals(expected, actual);
    }
}

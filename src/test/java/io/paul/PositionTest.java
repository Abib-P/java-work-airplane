package io.paul;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    void test(){
        Position p1 = new Position(0, 50, 5);
        Position p2 = new Position(0, 58, 3);

        Assertions.assertEquals(899, (int)p1.distance(p2));
    }

    @Test
    void should_rectify_position_if_not_good_coordonate_are_given(){
        Position p = new Position(-3, 500, -500);

        Assertions.assertEquals(0, p.getAltitude());
        Assertions.assertEquals(90, p.getLatitude());
        Assertions.assertEquals(-180, p.getLongitude());
    }
}
package io.paul.airport.rule;

import io.paul.Position;
import io.paul.clock.Clock;
import io.paul.clock.FakeClock;
import io.paul.plane.Airplane;
import io.paul.plane.FlightNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

class CloserFromActualTimeRuleTest {
    CloserFromActualTimeRule closerFromActualTimeRule;
    Clock mockClock;

    @BeforeEach
    void setup() {
        mockClock = new FakeClock(2000, 4, 20, 12, 0, 0);
        closerFromActualTimeRule = new CloserFromActualTimeRule();
    }

    @Test
    void should_landing_rules_return_next_airplane() {
        Position positionAirplane = new Position(3, 15, 15);
        FlightNumber flightNumber = new FlightNumber("2");
        Airplane airplane1 = new Airplane(
                flightNumber,
                positionAirplane,
                LocalDateTime.of(2000, 4, 20, 12, 30, 30),
                3000,
                2000
        );

        FlightNumber flightNumber2 = new FlightNumber("5");
        Airplane airplane2 = new Airplane(
                flightNumber2,
                positionAirplane,
                LocalDateTime.of(2000, 4, 20, 11, 40, 30),
                3000,
                2000
        );

        List<Airplane> airplanes = List.of(airplane1, airplane2);
        Optional<FlightNumber> airplane = closerFromActualTimeRule.apply(airplanes, mockClock);

        Assertions.assertTrue(airplane.isPresent());
        Assertions.assertEquals(airplane.get(), flightNumber2);
    }
}
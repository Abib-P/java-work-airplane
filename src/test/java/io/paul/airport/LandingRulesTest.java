package io.paul.airport;

import io.paul.Position;
import io.paul.airport.rule.CloserFromActualTimeRule;
import io.paul.airport.rule.CloserOnAltitudeRule;
import io.paul.airport.rule.LandingRules;
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

class LandingRulesTest {

    private LandingRules landingRules;

    @BeforeEach
    void setup() {
        Clock clockMock = new FakeClock(2000, 4, 20, 12, 0, 0);
        landingRules = new LandingRules(clockMock);
    }

    @Test
    void should_landing_rules_return_no_airplane_when_airplane_list_is_empty() {
        List<Airplane> airplanes = List.of();
        Optional<FlightNumber> airplane = landingRules.evaluate(airplanes);

        Assertions.assertTrue(airplane.isEmpty());
    }

    @Test
    void should_return_the_right_plane_with_2_rules() {
        Position positionAirplane = new Position(3, 15, 15);
        FlightNumber flightNumber = new FlightNumber("2");
        Airplane airplane1 = new Airplane(
                flightNumber,
                positionAirplane,
                LocalDateTime.of(2000, 4, 20, 12, 30, 30),
                3000,
                2000
        );

        Position positionAirplane2 = new Position(4, 15, 15);
        FlightNumber flightNumber2 = new FlightNumber("5");
        Airplane airplane2 = new Airplane(
                flightNumber2,
                positionAirplane2,
                LocalDateTime.of(2000, 4, 20, 11, 40, 30),
                3000,
                2000
        );

        CloserFromActualTimeRule closerFromActualTimeRule = new CloserFromActualTimeRule();
        CloserOnAltitudeRule closerOnAltitudeRule = new CloserOnAltitudeRule();
        landingRules.addRule(closerOnAltitudeRule);
        landingRules.addRule(closerFromActualTimeRule);

        List<Airplane> airplanes = List.of(airplane1, airplane2);

        Optional<FlightNumber> airplane = landingRules.evaluate(airplanes);

        Assertions.assertTrue(airplane.isPresent());
        Assertions.assertEquals(airplane.get(), flightNumber);
    }

    @Test
    void should_return_the_right_plane_with_2_rules_but_revers() {
        Position positionAirplane = new Position(3, 15, 15);
        FlightNumber flightNumber = new FlightNumber("2");
        Airplane airplane1 = new Airplane(
                flightNumber,
                positionAirplane,
                LocalDateTime.of(2000, 4, 20, 12, 30, 30),
                3000,
                2000
        );

        Position positionAirplane2 = new Position(4, 15, 15);
        FlightNumber flightNumber2 = new FlightNumber("5");
        Airplane airplane2 = new Airplane(
                flightNumber2,
                positionAirplane2,
                LocalDateTime.of(2000, 4, 20, 11, 40, 30),
                3000,
                2000
        );

        CloserFromActualTimeRule closerFromActualTimeRule = new CloserFromActualTimeRule();
        CloserOnAltitudeRule closerOnAltitudeRule = new CloserOnAltitudeRule();
        landingRules.addRule(closerFromActualTimeRule);
        landingRules.addRule(closerOnAltitudeRule);

        List<Airplane> airplanes = List.of(airplane1, airplane2);

        Optional<FlightNumber> airplane = landingRules.evaluate(airplanes);

        Assertions.assertTrue(airplane.isPresent());
        Assertions.assertEquals(airplane.get(), flightNumber2);
    }
}
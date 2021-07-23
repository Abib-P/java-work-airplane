package io.paul.airport;

import io.paul.Position;
import io.paul.clock.Clock;
import io.paul.clock.FakeClock;
import io.paul.plane.Airplane;
import io.paul.plane.FlightNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

class AirportTest {

    private Clock clockMock;

    @BeforeEach
    void setup() {
        clockMock = new FakeClock(2000, 4, 20, 12, 0, 0);
    }

    @Test
    void should_airport_return_no_next_landing_when_no_airplane_are_given() {

        LandingStrip landingStrip = new LandingStrip(200, 30);
        Position positionAirport = new Position(2, 15, 15);
        String airportName = "airport";
        Airport airport = new Airport(positionAirport, landingStrip, airportName, clockMock);

        Assertions.assertEquals(airport.nextLanding(), Optional.empty());
    }

    @Test
    void should_airport_return_no_next_landing_when_airplane_are_more_than_50_kilometer_away() {
        Position positionAirplane = new Position(3, 16, 15);
        FlightNumber flightNumber = new FlightNumber("2");
        Airplane airplane = new Airplane(
                flightNumber,
                positionAirplane,
                LocalDateTime.of(2000, 4, 20, 12, 30, 30),
                3000,
                2000
        );

        LandingStrip landingStrip = new LandingStrip(200, 30);
        Position positionAirport = new Position(2, 15, 15);
        String airportName = "airport";
        Airport airport = new Airport(positionAirport, landingStrip, airportName, clockMock);

        airport.approach(airplane);

        Assertions.assertEquals(airport.nextLanding(), Optional.empty());
    }
}
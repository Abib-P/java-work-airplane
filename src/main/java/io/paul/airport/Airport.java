package io.paul.airport;

import io.paul.Position;
import io.paul.airport.rule.LandingRules;
import io.paul.clock.Clock;
import io.paul.plane.Airplane;
import io.paul.plane.FlightNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Airport {

    private Position position;
    private LandingStrip landingStrip;
    private List<Airplane> nextLandings;
    private String name;
    private LandingRules landingRules;

    public Airport(Position position, LandingStrip landingStrip, String name, Clock clock) {
        this.landingRules = new LandingRules(clock);
        this.position = position;
        this.landingStrip = landingStrip;
        this.name = name;
        this.nextLandings = new ArrayList<>();
    }

    public Optional<FlightNumber> nextLanding() {
        if (nextLandings.size() == 0)
            return Optional.empty();

        Optional<FlightNumber> flightNumber = landingRules.evaluate(nextLandings);
        return flightNumber;
    }


    public void approach(Airplane airplane) {
        if (position.distance(airplane.getPosition()) <= 50)
            nextLandings.add(airplane);
    }

}

package io.paul.airport.rule;

import io.paul.clock.Clock;
import io.paul.plane.Airplane;
import io.paul.plane.FlightNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LandingRules {
    private final Clock clock;
    private final List<Rule> rules;

    public LandingRules(Clock clock) {
        this.clock = clock;
        rules = new ArrayList<>();
    }

    public LandingRules(Clock clock, List<Rule> rules) {
        this.clock = clock;
        this.rules = rules;
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public Optional<FlightNumber> evaluate(List<Airplane> airplanes) {
        Optional<FlightNumber> flightNumber = Optional.empty();
        for (Rule rule : rules) {
            if (flightNumber.isEmpty()) {
                flightNumber = rule.apply(airplanes, clock);
            }
        }
        return flightNumber;
    }
}

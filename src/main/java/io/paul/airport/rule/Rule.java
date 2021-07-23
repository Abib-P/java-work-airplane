package io.paul.airport.rule;

import io.paul.clock.Clock;
import io.paul.plane.Airplane;
import io.paul.plane.FlightNumber;

import java.util.List;
import java.util.Optional;

public interface Rule {
    Optional<FlightNumber> apply(List<Airplane> airplanes, Clock clock);
}

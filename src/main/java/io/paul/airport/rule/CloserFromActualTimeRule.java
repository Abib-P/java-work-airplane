package io.paul.airport.rule;

import io.paul.clock.Clock;
import io.paul.plane.Airplane;
import io.paul.plane.FlightNumber;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class CloserFromActualTimeRule implements Rule{
    @Override
    public Optional<FlightNumber> apply(List<Airplane> airplanes, Clock clock) {
        Optional<Airplane> airplane = airplanes.stream().reduce((airplane1,airplane2)->{
            long flight1 = clock.now().until(airplane1.getLandingTime(), ChronoUnit.SECONDS);
            long flight2 = clock.now().until(airplane2.getLandingTime(), ChronoUnit.SECONDS);

            if (flight1 > flight2){
                return airplane2;
            }
            return airplane1;
        });
        if(airplane.isEmpty())
            return Optional.empty();
        long multipleOccurrence = airplanes.stream().filter(airplane1 -> {
            long flight1 = clock.now().until(airplane1.getLandingTime(), ChronoUnit.SECONDS);
            long flight2 = clock.now().until(airplane.get().getLandingTime(), ChronoUnit.SECONDS);

            return flight1 == flight2;
        }).count();
        if (multipleOccurrence > 1)
            return Optional.empty();
        return Optional.of(airplane.get().getFlightNumber());
    }
}

package io.paul.airport.rule;

import io.paul.clock.Clock;
import io.paul.plane.Airplane;
import io.paul.plane.FlightNumber;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class CloserOnAltitudeRule implements Rule{
    @Override
    public Optional<FlightNumber> apply(List<Airplane> airplanes, Clock clock) {
        Optional<Airplane> airplane = airplanes.stream().reduce((airplane1,airplane2)->{
            double flight1 = airplane1.getPosition().getAltitude();
            double flight2 = airplane2.getPosition().getAltitude();

            if (flight1 > flight2){
                return airplane2;
            }
            return airplane1;
        });
        if(airplane.isEmpty())
            return Optional.empty();
        long multipleOccurrence = airplanes.stream().filter(airplane1 -> {
            double flight1 = airplane1.getPosition().getAltitude();
            double flight2 = airplane.get().getPosition().getAltitude();
            return flight1 == flight2;
        }).count();
        if (multipleOccurrence > 1)
            return Optional.empty();
        return Optional.of(airplane.get().getFlightNumber());
    }
}

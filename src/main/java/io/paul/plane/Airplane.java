package io.paul.plane;

import io.paul.Position;

import java.time.LocalDateTime;
import java.util.Objects;

public class Airplane {
    private FlightNumber flightNumber;
    private Position position;
    private LocalDateTime landingTime;
    private int weight;
    private double fuel;

    public Airplane(FlightNumber flightNumber, Position position, LocalDateTime landingTime, int weight, double fuel) {
        this.flightNumber = flightNumber;
        this.position = position;
        this.landingTime = landingTime;
        this.weight = weight;
        this.fuel = fuel;
    }

    public FlightNumber getFlightNumber() {
        return flightNumber;
    }

    public LocalDateTime getLandingTime() {
        return landingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return weight == airplane.weight && Double.compare(airplane.fuel, fuel) == 0 && Objects.equals(flightNumber, airplane.flightNumber) && Objects.equals(position, airplane.position) && Objects.equals(landingTime, airplane.landingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, position, landingTime, weight, fuel);
    }

    public Position getPosition() {
        return position;
    }
}

package lesson2.strategy;

import lesson2.ParkingLot;

import java.util.List;

public interface ParkingStrategy {

    ParkingLot getParkingLot(List<ParkingLot> parkingLots);
}

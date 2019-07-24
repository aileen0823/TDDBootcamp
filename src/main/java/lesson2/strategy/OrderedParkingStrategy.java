package lesson2.strategy;

import lesson2.ParkingLot;

import java.util.List;

public class OrderedParkingStrategy implements ParkingStrategy {

    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream().filter(p -> p.isAvailable()).findFirst().orElse(null);
    }

}

package lesson2.strategy;

import lesson2.ParkingLot;

import java.util.List;

public class MaximumEmptyParkingStrategy implements ParkingStrategy {

    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots) {
        ParkingLot mostEmptyParkingLot = null;
        int tempEmptyCount = 0;
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getAvailableCount() > tempEmptyCount) {
                tempEmptyCount = parkingLot.getAvailableCount();
                mostEmptyParkingLot = parkingLot;
            }
        }
        return mostEmptyParkingLot;
    }
}

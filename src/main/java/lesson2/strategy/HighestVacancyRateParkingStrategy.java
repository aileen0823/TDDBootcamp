package lesson2.strategy;

import lesson2.ParkingLot;

import java.util.List;

public class HighestVacancyRateParkingStrategy implements ParkingStrategy {

    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots) {
        ParkingLot highestVacancyRateParkingLot = null;
        double tempHighestVacancyRate = 0;
        for (ParkingLot parkingLot : parkingLots) {
            double curVacancyRate = parkingLot.getAvailableCount() / (double) parkingLot.getCapacity();
            if (curVacancyRate > tempHighestVacancyRate) {
                tempHighestVacancyRate = curVacancyRate;
                highestVacancyRateParkingLot = parkingLot;
            }
        }
        return highestVacancyRateParkingLot;
    }
}

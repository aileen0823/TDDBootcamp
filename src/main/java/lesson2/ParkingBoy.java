package lesson2;

import lesson2.exception.ParkingLotFullException;
import lesson2.strategy.OrderedParkingStrategy;
import lesson2.strategy.ParkingStrategy;

import java.util.List;

public class ParkingBoy {

    private List<ParkingLot> parkingLots;

    private ParkingStrategy parkingStrategy;


    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        this.parkingStrategy = new OrderedParkingStrategy();
    }

    public ParkingBoy(List<ParkingLot> parkingLots, ParkingStrategy parkingStrategy) {
        this.parkingLots = parkingLots;
        this.parkingStrategy = parkingStrategy;
    }

    public Ticket park(Car car) {
        ParkingLot parkingLot = parkingStrategy.getParkingLot(parkingLots);

        if (parkingLot == null) {
            throw new ParkingLotFullException();
        }

        return parkingLot.park(car);
    }

}

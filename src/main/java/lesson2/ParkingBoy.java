package lesson2;

import java.util.List;

public class ParkingBoy {

    private List<ParkingLot> parkingLots;


    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
       ParkingLot parkingLot = parkingLots.stream().filter(p -> p.isAvailable()).findFirst().orElse(null);
        return parkingLot.park(car);
    }
}

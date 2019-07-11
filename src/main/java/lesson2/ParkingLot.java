package lesson2;

import lesson2.exception.DuplicateCarNoException;
import lesson2.exception.ParkingLotFullException;
import lesson2.exception.NoCarNoException;
import lesson2.exception.TicketNotMatchException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private int capacity;


    private Map<Ticket, Car> parkedCar = new HashMap<>();


    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if(!isAvailable()){
            throw new ParkingLotFullException();
        }
        if(car == null || car.getCarNo().isEmpty()){
            throw new NoCarNoException();
        }
        if(isCarNoDuplicate(car.getCarNo())){
            throw new DuplicateCarNoException();
        }
        Ticket ticket = new Ticket();
        parkedCar.put(ticket, car);
        return ticket;
    }

    private boolean isCarNoDuplicate(String carNo){
        return parkedCar.values()
                .stream()
                .anyMatch(car -> car.getCarNo().equals(carNo));
    }

    public Car pick(Ticket ticket) {
        Car car = parkedCar.remove(ticket);
        if(car == null){
            throw new TicketNotMatchException();
        }
        return car;
    }

    public boolean isAvailable(){
        return parkedCar.size() < capacity;
    }
}

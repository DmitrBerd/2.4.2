package web.service;

import org.springframework.stereotype.Component;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarServiceImpl implements CarService {
    private ArrayList<Car> cars = new ArrayList<>();

    public CarServiceImpl() {
        cars.add(new Car("Mercedes S63", "Black", "V8"));
        cars.add(new Car("KIA RIO", "Gray", "L4"));
        cars.add(new Car("BMW 530D", "Blue", "V6"));
        cars.add(new Car("Toyota land cruiser 300", "White", "V6"));
        cars.add(new Car("Ford Mustang", "Red", "V12"));
    }


    @Override
    public List<Car> getCars(Integer number) {

        if (number == null || number >= 5 ){
            return cars;
        } else{
            return cars.subList(0, number);
        }
    }
}

package com.codicstask;

import com.codicstask.modelclasses.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class CarShowroom {

    static ArrayList<Car> cars = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Employee> employees = new ArrayList<>();
    static ArrayList<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        initData();
        startWork();
    }

    public static void startWork() {

        //Get the total number of cars sold and their total cost
        String carsInfo = String.format("The total number of cars sold %d in the amount of %f",
                getCountOfSoldCars(), getAllPriceOfSoldCars());
        System.out.println(carsInfo);

        //Get the number of cars sold and their cost by sellers
        for (Employee employee : employees) {
            System.out.println(employee.getName() + " sold " + getProfitByEmployee(employee.getId()).toString());
        }

        //Get the number of cars sold and their total cost by model
        ArrayList<CarAdditional> soldCarsCount = getCountOfSoldCarsByModel();
        HashMap<CarModel, Double> soldCarPrices = getPriceOfSoldCarsByModel();

        String soldCarsStr = "By model: %s sold %d cars with a total value of %f";

        for (CarAdditional carAdditional : soldCarsCount) {
            double price = soldCarPrices.get(carAdditional.getModel());
            System.out.println(String.format(
                    soldCarsStr,
                    carAdditional.getModel().name(), carAdditional.getCount(), price));
        }

        //What model do customers younger 30 choose?
        String analyzeModel_1 = "Customers under %d choose the %s model.";
        System.out.println(String.format(analyzeModel_1, 30, getMostPopularModelLessThenAge(30)));

        //What model do customers older 30 choose?
        String analyzeModel_2 = "Customers over %d choose the %s model.";
        System.out.println(String.format(analyzeModel_2, 30, getMostPopularModelMoreThenAge(30)));

    }

    /**
     * Get the most popular model for customers older than #age
     * @param age age required
     * @return popular model
     */
    public static CarModel getMostPopularModelMoreThenAge(int age) {
        ArrayList<Long> customersIds = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getAge() > age) {
                customersIds.add(customer.getId());
            }
        }
        return getMostPopularModel(customersIds);
    }

    /**
     * Get the most popular model for customers younger than #age
     * @param age age required
     * @return popular model
     */
    public static CarModel getMostPopularModelLessThenAge(int age) {
        ArrayList<Long> customersIds = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getAge() < age) {
                customersIds.add(customer.getId());
            }
        }
        return getMostPopularModel(customersIds);
    }

    private static CarModel getMostPopularModel(ArrayList<Long> customersIds) {
        int count_3 = 0, count_5 = 0, count_M = 0, count_X = 0;
        for (Order order : orders) {
            if (customersIds.contains(order.getCustomerId())) {
                count_3 += getCountOfSoldCarsByModel(order, CarModel.SERIES_3);
                count_5 += getCountOfSoldCarsByModel(order, CarModel.SERIES_5);
                count_M += getCountOfSoldCarsByModel(order, CarModel.SERIES_M);
                count_X += getCountOfSoldCarsByModel(order, CarModel.SERIES_X);
            }
        }
        ArrayList<CarAdditional> result = new ArrayList<>();
        result.add(new CarAdditional(CarModel.SERIES_3, count_3));
        result.add(new CarAdditional(CarModel.SERIES_5, count_5));
        result.add(new CarAdditional(CarModel.SERIES_M, count_M));
        result.add(new CarAdditional(CarModel.SERIES_X, count_X));

        result.sort(new Comparator<CarAdditional>() {
            @Override
            public int compare(CarAdditional left, CarAdditional right) {
                return right.getCount() - left.getCount();
            }
        });

        return result.get(0).getModel();
    }

    //get the number of cars sold by model
    public static ArrayList<CarAdditional> getCountOfSoldCarsByModel() {
        ArrayList<CarAdditional> result = new ArrayList<>();
        int count_3 = 0, count_5 = 0, count_M = 0, count_X = 0;
        for (Order order : orders) {
            count_3 += getCountOfSoldCarsByModel(order, CarModel.SERIES_3);
            count_5 += getCountOfSoldCarsByModel(order, CarModel.SERIES_5);
            count_M += getCountOfSoldCarsByModel(order, CarModel.SERIES_M);
            count_X += getCountOfSoldCarsByModel(order, CarModel.SERIES_X);
        }
        result.add(new CarAdditional(CarModel.SERIES_3, count_3));
        result.add(new CarAdditional(CarModel.SERIES_5, count_5));
        result.add(new CarAdditional(CarModel.SERIES_M, count_M));
        result.add(new CarAdditional(CarModel.SERIES_X, count_X));

        return result;
    }

    /**
     * Get the number of cars in one order for a specific model
     * @param order specific order
     * @param model specific model
     * @return number of cars
     */
    public static int getCountOfSoldCarsByModel(Order order, CarModel model) {
        int count = 0;
        for (long carId : order.getCars()) {
            Car car = getCarById(carId);
            if (car != null && car.getModel() == model)
                count++;
        }
        return count;
    }

    //This is an example of a HashMap, but in this case it’s correct to use ArrayList
    //get the cost of cars sold by model
    public static HashMap<CarModel, Double> getPriceOfSoldCarsByModel() {
        HashMap<CarModel, Double> result = new HashMap<>();

        double price_3 = 0, price_5 = 0, price_M = 0, price_X = 0;
        for (Order order : orders) {
            price_3 += getPriceOfSoldCarsByModel(order, CarModel.SERIES_3);
            price_5 += getPriceOfSoldCarsByModel(order, CarModel.SERIES_5);
            price_M += getPriceOfSoldCarsByModel(order, CarModel.SERIES_M);
            price_X += getPriceOfSoldCarsByModel(order, CarModel.SERIES_X);
        }
        result.put(CarModel.SERIES_3, price_3);
        result.put(CarModel.SERIES_5, price_5);
        result.put(CarModel.SERIES_M, price_M);
        result.put(CarModel.SERIES_X, price_X);

        return result;
    }

    /**
     * Get the total amount of cars in one order for a specific model
     * @param order specific order
     * @param model specific model
     * @return total cost
     */
    public static double getPriceOfSoldCarsByModel(Order order, CarModel model) {
        double price = 0;
        for (long carId : order.getCars()) {
            Car car = getCarById(carId);
            if (car != null && car.getModel() == model)
                price += car.getPrice();
        }
        return price;
    }

    /**
     * Get the total quantity and total value of the goods sold for the seller
     * @param employeeId unique employee id
     * @return total quantity and total value of the goods sold for the seller
     */
    public  static Profit getProfitByEmployee(long employeeId) {
        int count = 0; double price = 0;
        for (Order order : orders) {
            if (order.getEmployeeId() == employeeId) {
                price += getPriceOfSoldCarsInOrder(order);
                count += order.getCars().length;
            }
        }
        return new Profit(count, price);
    }

    // get the total amount of orders
    public static double getAllPriceOfSoldCars() {
        double price = 0;
        for (Order order : orders) {
            price += getPriceOfSoldCarsInOrder(order);
        }
        return price;
    }

    /**
     * Get the total cost of one order
     * @param order order for which the value is considered
     * @return total cost
     */
    public static double getPriceOfSoldCarsInOrder(Order order) {
        double price = 0;
        for (long carId : order.getCars()) {
            Car car = getCarById(carId);
            if (car != null)
            price += car.getPrice();
        }
        return price;
    }

    // get the total number of cars sold
    public static int getCountOfSoldCars() {
        int count = 0;
        for (Order order : orders) {
            count += order.getCars().length;
        }
        return count;
    }

    /**
     * Car search
     * @param id  unique car id
     * @return found car
     */
    public static Car getCarById(long id) {
        Car current = null;
        for (Car car : cars) {
            if (car.getId() == id){
                current = car;
                break;
            }
        }
        return current;
    }

    public static void initData() {
        //sellers
        employees.add(new Employee(1, "Gabriel Moreno", 24));
        employees.add(new Employee(2, "Jane Franklin", 28));
        employees.add(new Employee(3, "Olav Berg", 25));

        customers.add(new Customer(1, "Joseph Greene", 31));
        customers.add(new Customer(2, "Bill Jacks", 21));
        customers.add(new Customer(3, "Rick Grimes", 19));
        customers.add(new Customer(4, "Samantha Jansen", 44));
        customers.add(new Customer(5, "Sarah Bloom", 36));

        cars.add(new Car(1, "325i", "Berlin", 5000, CarModel.SERIES_3));
        cars.add(new Car(2, "316i", "Hamburg", 4500, CarModel.SERIES_3));
        cars.add(new Car(3, "330i", "Dortmund", 5800, CarModel.SERIES_3));

        cars.add(new Car(4, "525i", "Dortmund", 7000, CarModel.SERIES_5));
        cars.add(new Car(5, "550i", "Berlin", 8500, CarModel.SERIES_5));
        cars.add(new Car(6, "518i", "Hamburg", 6500, CarModel.SERIES_5));

        cars.add(new Car(7, "M5", "Dortmund", 25000, CarModel.SERIES_M));
        cars.add(new Car(8, "M3", "Dortmund", 22500, CarModel.SERIES_M));

        cars.add(new Car(9, "X5", "Berlin", 9000, CarModel.SERIES_X));
        cars.add(new Car(10, "X6", "Dortmund", 18000, CarModel.SERIES_X));

        orders.add(new Order(1,1,1, new long[]{8, 9, 10}));
        orders.add(new Order(2,1,2, new long[]{1}));
        orders.add(new Order(3,2,3, new long[]{5, 6, 7}));
        orders.add(new Order(4,2,4, new long[]{1, 2, 3, 4}));
        orders.add(new Order(5,3,5, new long[]{2, 5, 8}));
    }
}

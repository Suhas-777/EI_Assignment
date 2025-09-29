import java.util.*;

// Subject
interface DoorSensor {
    void addObserver(Device d);
    void removeObserver(Device d);
    void notifyObservers(String event);
}

// Concrete Subject
class SmartDoor implements DoorSensor {
    private List<Device> observers = new ArrayList<>();

    @Override
    public void addObserver(Device d) { observers.add(d); }

    @Override
    public void removeObserver(Device d) { observers.remove(d); }

    @Override
    public void notifyObservers(String event) {
        for (Device d : observers) d.update(event);
    }

    public void detectMotion() {
        System.out.println("Door Sensor: Motion detected!");
        notifyObservers("Motion at front door!");
    }
}

// Observer
interface Device {
    void update(String event);
}

// Concrete Observers
class MobileApp implements Device {
    public void update(String event) {
        System.out.println("Mobile App Alert: " + event);
    }
}

class SmartDisplay implements Device {
    public void update(String event) {
        System.out.println("Smart Display shows: " + event);
    }
}

class Alarm implements Device {
    public void update(String event) {
        System.out.println("Alarm: WEE-WOO! " + event);
    }
}

public class ObserverDemo {
    public static void main(String[] args) {
        SmartDoor door = new SmartDoor();
        door.addObserver(new MobileApp());
        door.addObserver(new SmartDisplay());
        door.addObserver(new Alarm());

        door.detectMotion();
    }
}

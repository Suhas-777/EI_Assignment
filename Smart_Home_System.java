import java.util.*;

// -------- Device Interface --------
interface SmartDevice {
    void turnOn();
    void turnOff();
    void status();
    int getId();
    String getType();
}

// -------- Concrete Devices --------
class Light implements SmartDevice {
    private int id;
    private boolean isOn = false;

    public Light(int id) { this.id = id; }

    @Override
    public void turnOn() { isOn = true; System.out.println("💡 Light " + id + " is ON."); }
    @Override
    public void turnOff() { isOn = false; System.out.println("💡 Light " + id + " is OFF."); }
    @Override
    public void status() { System.out.println("Light " + id + " Status: " + (isOn ? "ON" : "OFF")); }
    @Override
    public int getId() { return id; }
    @Override
    public String getType() { return "Light"; }
}

class Thermostat implements SmartDevice {
    private int id;
    private int temperature = 24; // default

    public Thermostat(int id) { this.id = id; }

    @Override
    public void turnOn() { System.out.println("🌡 Thermostat " + id + " is ON. Temp = " + temperature); }
    @Override
    public void turnOff() { System.out.println("🌡 Thermostat " + id + " is OFF."); }
    @Override
    public void status() { System.out.println("Thermostat " + id + " Temp: " + temperature); }
    public void setTemperature(int temp) { this.temperature = temp; System.out.println("Thermostat " + id + " set to " + temp + "°C"); }
    @Override
    public int getId() { return id; }
    @Override
    public String getType() { return "Thermostat"; }
}

class DoorLock implements SmartDevice {
    private int id;
    private boolean locked = true;

    public DoorLock(int id) { this.id = id; }

    @Override
    public void turnOn() { locked = false; System.out.println("🔓 Door " + id + " is UNLOCKED."); }
    @Override
    public void turnOff() { locked = true; System.out.println("🔒 Door " + id + " is LOCKED."); }
    @Override
    public void status() { System.out.println("Door " + id + " Status: " + (locked ? "LOCKED" : "UNLOCKED")); }
    @Override
    public int getId() { return id; }
    @Override
    public String getType() { return "DoorLock"; }
}

// -------- Factory Pattern --------
class DeviceFactory {
    public static SmartDevice createDevice(String type, int id) {
        switch (type.toLowerCase()) {
            case "light": return new Light(id);
            case "thermostat": return new Thermostat(id);
            case "doorlock": return new DoorLock(id);
            default: throw new IllegalArgumentException("Unknown device type: " + type);
        }
    }
}

// -------- Observer Pattern --------
interface Observer {
    void update(String message);
}

class DeviceObserver implements Observer {
    private String name;
    public DeviceObserver(String name) { this.name = name; }

    @Override
    public void update(String message) {
        System.out.println("🔔 [" + name + "] Notification: " + message);
    }
}

// -------- Proxy Pattern --------
class DeviceProxy implements SmartDevice {
    private SmartDevice realDevice;
    private boolean accessAllowed;

    public DeviceProxy(SmartDevice realDevice, boolean accessAllowed) {
        this.realDevice = realDevice;
        this.accessAllowed = accessAllowed;
    }

    private void checkAccess() {
        if (!accessAllowed) throw new SecurityException("Access Denied to " + realDevice.getType());
    }

    @Override
    public void turnOn() { checkAccess(); realDevice.turnOn(); }
    @Override
    public void turnOff() { checkAccess(); realDevice.turnOff(); }
    @Override
    public void status() { checkAccess(); realDevice.status(); }
    @Override
    public int getId() { return realDevice.getId(); }
    @Override
    public String getType() { return realDevice.getType(); }
}

// -------- Singleton Smart Home Hub --------
class SmartHomeHub {
    private static SmartHomeHub instance;
    private Map<Integer, SmartDevice> devices = new HashMap<>();
    private List<Observer> observers = new ArrayList<>();

    private SmartHomeHub() {}

    public static SmartHomeHub getInstance() {
        if (instance == null) instance = new SmartHomeHub();
        return instance;
    }

    public void addObserver(Observer obs) { observers.add(obs); }

    private void notifyObservers(String message) {
        for (Observer obs : observers) {
            obs.update(message);
        }
    }

    public void addDevice(SmartDevice device) {
        devices.put(device.getId(), device);
        notifyObservers("Device added: " + device.getType() + " " + device.getId());
    }

    public void controlDevice(int id, boolean on) {
        SmartDevice device = devices.get(id);
        if (device == null) {
            System.out.println("❌ Device not found.");
            return;
        }
        if (on) device.turnOn(); else device.turnOff();
        notifyObservers(device.getType() + " " + id + " turned " + (on ? "ON" : "OFF"));
    }

    public void showStatus() {
        devices.values().forEach(SmartDevice::status);
    }
}

// -------- Main App --------
public class SmartHomeSystemApp {
    public static void main(String[] args) {
        SmartHomeHub hub = SmartHomeHub.getInstance();
        hub.addObserver(new DeviceObserver("Admin"));

        // Add devices using Factory
        SmartDevice light1 = DeviceFactory.createDevice("light", 1);
        SmartDevice thermostat1 = DeviceFactory.createDevice("thermostat", 2);
        SmartDevice door1 = DeviceFactory.createDevice("doorlock", 3);

        // Wrap with Proxy (simulate access control)
        SmartDevice proxyLight = new DeviceProxy(light1, true);
        SmartDevice proxyDoor = new DeviceProxy(door1, false); // restricted access

        // Add to hub
        hub.addDevice(proxyLight);
        hub.addDevice(thermostat1);
        hub.addDevice(proxyDoor);

        // Control Devices
        hub.controlDevice(1, true);   // Light ON
        hub.controlDevice(2, true);   // Thermostat ON
        hub.controlDevice(3, true);   // DoorLock - access denied

        System.out.println("\n--- Device Status ---");
        hub.showStatus();
    }
}

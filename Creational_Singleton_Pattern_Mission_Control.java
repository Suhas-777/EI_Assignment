class MissionControl {
    private static MissionControl instance;
    private MissionControl() {} // private constructor

    public static MissionControl getInstance() {
        if (instance == null) {
            instance = new MissionControl();
        }
        return instance;
    }

    public void log(String msg) {
        System.out.println("Mission Log: " + msg);
    }
}

public class SingletonDemo {
    public static void main(String[] args) {
        MissionControl c1 = MissionControl.getInstance();
        MissionControl c2 = MissionControl.getInstance();

        c1.log("Astronaut launched.");
        c2.log("Docking successful.");

        System.out.println("Same instance? " + (c1 == c2));
    }
}


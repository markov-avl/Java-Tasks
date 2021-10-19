package com.company;

// 2009 - 3 - a

public class Main {

    public static void main(String[] args) {
        BatteryCharger batteryCharger = new BatteryCharger();
        System.out.println(batteryCharger.getChargingCost(12, 1) == 40);
        System.out.println(batteryCharger.getChargingCost(0, 2) == 110);
        System.out.println(batteryCharger.getChargingCost(22, 7) == 550);
        System.out.println(batteryCharger.getChargingCost(22, 30) == 3710);
        System.out.println(batteryCharger.getChargingCost(-1, 1) == -1);
        System.out.println(batteryCharger.getChargingCost(12, -1) == -1);
    }
}

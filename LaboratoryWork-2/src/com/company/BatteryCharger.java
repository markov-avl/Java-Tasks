package com.company;

// 2009 - 3 - a

public class BatteryCharger {
    private final int[] rateTable = {
            50,  60,  160, 60,  80, 100, 100, 120,
            150, 150, 150, 200, 40, 240, 220, 220,
            200, 200, 180, 180, 140, 100, 80, 60
    };

    /** Determines the total cost to charge the battery starting at the beginning of startHour.
     * @param startHour the hour at which the charge period begins
     * Precondition: 0 ≤ startHour ≤ 23
     * @param chargeTime the number of hours the battery needs to be charged
     * Precondition: chargeTime > 0
     * @return the total cost to charge the battery if preconditions are performed else -1
     */
    public int getChargingCost(int startHour, int chargeTime) {
        if (startHour < 0 || startHour > 23 || chargeTime <= 0) {
            return -1;
        }
        int cost = 0;
        for (int i = startHour, k = 0; k < chargeTime; ++i, ++k) {
            cost += rateTable[i % rateTable.length];
        }
        return cost;
    }
}

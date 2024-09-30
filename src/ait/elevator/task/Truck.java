package ait.elevator.task;

import ait.elevator.model.Elevator;

public class Truck implements Runnable {
    private int nRaces;
    private int capacity;
    private Elevator[] elevators;
    private Object[] monitors;

    public Truck(int nRaces, int capacity, Elevator[] elevators, Object[] monitors) {
        this.nRaces = nRaces;
        this.capacity = capacity;
        this.elevators = elevators;
        this.monitors = monitors;
    }

    @Override
    public void run() {
        int totalElevators = elevators.length;
        int portionPerElevator = capacity / totalElevators;
        int remainder = capacity % totalElevators;
        int[] portions = new int[totalElevators];
        for (int j = 0; j < totalElevators; j++) {
            portions[j] = portionPerElevator + (j < remainder ? 1 : 0);
        }
        for (int i = 0; i < nRaces; i++) {
            for (int j = 0; j < totalElevators; j++) {
                synchronized (monitors[j]) {
                    elevators[j].add(portions[j]);
                }
            }
        }
    }
}
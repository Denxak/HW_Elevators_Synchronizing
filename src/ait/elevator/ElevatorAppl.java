package ait.elevator;

import ait.elevator.model.Elevator;
import ait.elevator.task.Truck;

public class ElevatorAppl {
    private static final int N_TRUCK = 10_000;
    private static final int CAPACITY = 10;
    private static final int N_RACES = 20;
    private static final int N_ELEVATORS = 2;

    public static void main(String[] args) throws InterruptedException {
        Elevator[] elevators = new Elevator[N_ELEVATORS];
        elevators[0] = new Elevator("V. I. Lenin");
        elevators[1] = new Elevator("J. V. Stalin");
        Object[] monitors = new Object[N_ELEVATORS];
        for (int i = 0; i < N_ELEVATORS; i++) {
            monitors[i] = new Object();
        }
        Truck[] trucks = new Truck[N_TRUCK];
        Thread[] threads = new Thread[N_TRUCK];
        for (int i = 0; i < N_TRUCK; i++) {
            trucks[i] = new Truck(N_RACES, CAPACITY, elevators, monitors);
            threads[i] = new Thread(trucks[i]);
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        for (Elevator elevator : elevators) {
            System.out.println("Elevator " + elevator.getName() + " has " + elevator.getCurrentVolume());
        }
    }
}

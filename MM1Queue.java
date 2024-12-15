package javaapplication30;

import java.util.LinkedList;
import java.util.Queue;
import edu.princeton.cs.algs4.StdRandom;
import javax.swing.JTextArea;

public class MM1Queue {

    private double lambda;  // Arrival rate
    private double mu;      // Service rate
    private int num_in_system = 0;
    private int num_arrival = 0;
    private int num_departure = 0;
    private double total_wait = 0;
    private double wait_in_system = 0;

    private Queue<Double> queue = new LinkedList<>(); // Arrival times of customers
    private double nextArrival;   // Time of next arrival
    private double nextDeparture; // Time of next departure
    private double clock = 0;

    public MM1Queue(double lambda, double mu, int numCustomer, JTextArea simulationOutput) {
        this.lambda = lambda; // Correctly assign arrival rate
        this.mu = mu;         // Correctly assign service rate

        // Initialize simulation variables
        nextArrival = 0;  // Start simulation at time 0
        nextDeparture = Double.POSITIVE_INFINITY; // No departure initially

        // Output initial heading
        simulationOutput.setText("Serial      Clock      Event       Arrival      Departure      InSystem     Wait     \n");

        // Run the simulation in a separate thread
        new Thread(() -> {
            for (int i = 1; i <= numCustomer; i++) {
                if (nextArrival <= nextDeparture) {
                    clock = Math.round(nextArrival); // Advance clock to next event time
                    handleArrivalEvent();   
                    simulationOutput.append("   "+i+ "          " + clock + "      Arrival        " + num_arrival + "             " + num_departure + "            " + num_in_system + "           none\n");
                } else {
                    clock = Math.round(nextDeparture); // Advance clock to next event time
                    handleDepartureEvent();
                    simulationOutput.append("   "+i + "          " + clock + "      Departure      " + num_arrival + "             " + num_departure + "            " + num_in_system + "            " + Math.round(wait_in_system) + "\n");
                }

                try {
                    Thread.sleep(200); // Delay to simulate real-time updates
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void handleArrivalEvent() {
        num_arrival++;
        num_in_system++;
        if (queue.isEmpty()) {
            double service_time = StdRandom.exp(1 / mu);
            nextDeparture = clock + service_time;
        }
        queue.add(clock); // Add current time as arrival time
        double interarrival_time = StdRandom.exp(1 / lambda);
        nextArrival = clock + interarrival_time;
    }

    private void handleDepartureEvent() {
        num_departure++;
        num_in_system--;
        double arrivalTime = queue.poll();
        wait_in_system = clock - arrivalTime;
        total_wait += wait_in_system;

        if (queue.isEmpty()) {
            nextDeparture = Double.POSITIVE_INFINITY;
        } else {
            double service_time = StdRandom.exp(1 / mu);
            nextDeparture = clock + service_time;
        }
    }
}

 package javaapplication30;
import java.util.Random;

public class Base {
Random random = new Random();
/*
    public  double exp(double rate) { //  the function for the simulation
        if (rate <= 0) {
            throw new IllegalArgumentException("Rate parameter must be positive.");
        }
        return -Math.log(1 - random.nextDouble()) / rate;
    } // end of the function8*/
    //
    //
    private double arrivalRate;
    private double serviceRate;
    private int systemCapacity;
    private int numberOfServices;
   
   

 
    public Base(double arrivalRate, double serviceRate) {
        this.arrivalRate = arrivalRate;
        this.serviceRate = serviceRate;

    }

   
    public Base(double arrivalRate, double serviceRate, int systemCapacity) {
        this.arrivalRate = arrivalRate;
        this.serviceRate = serviceRate;
        this.systemCapacity = systemCapacity;

    }


    public double getArrivalRate() {
        return arrivalRate;
    }

    public double getServiceRate() {
        return serviceRate;
    }

    public int getSystemCapacity() {
        return systemCapacity;
    }

    public int getNumberOfServices() {
        return numberOfServices;
    }

    

}
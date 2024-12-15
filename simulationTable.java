package javaapplication30;

import java.awt.Container;
import javaapplication30.Base;
import javax.swing.JTextArea;

public class simulationTable extends Base {

    private int numberOfCustomers;
    private double[] customerIds;
    private double[] arrivalTimes;
    private double[] serviceTimes;
    private double[] serviceBeginTimes;
    private double[] waitTimes;
    private double[] serviceEndTimes;
    private double[] idleServerTimes;
    private double[] timeInSystem;

    private final double arrivalRate;
    private final double serviceRate;

    public simulationTable(double arrivalRate, double serviceRate, int numberOfCustomers, JTextArea ta, Container C) {
        super(arrivalRate, serviceRate);
        this.numberOfCustomers = numberOfCustomers;
        this.arrivalRate = getArrivalRate();
        this.serviceRate = getServiceRate();

        initializeArrays();
        simulate();
    
    }

    private void initializeArrays() {
        customerIds = new double[numberOfCustomers];
        arrivalTimes = new double[numberOfCustomers];
        serviceTimes = new double[numberOfCustomers];
        serviceBeginTimes = new double[numberOfCustomers];
        waitTimes = new double[numberOfCustomers];
        serviceEndTimes = new double[numberOfCustomers];
        idleServerTimes = new double[numberOfCustomers];
        timeInSystem = new double[numberOfCustomers];
    }

    public void generateCustomerIds() {
        for (int i = 0; i < numberOfCustomers; i++) {
            customerIds[i] = i + 1;
        }
    }

    public void generateRandomServiceTimes() {
        for (int i = 0; i < numberOfCustomers; i++) {
            double randomValue;
            do {
                randomValue =-Math.log(1-random.nextDouble());
            } while (randomValue == 0);

            serviceTimes[i] = randomValue;
        }
    }

    public void generateRandomArrivalTimes() {
        for (int i = 0; i < numberOfCustomers; i++) {
            if(i==0){
                arrivalTimes[i]=-Math.log(1-random.nextDouble());
            }else{
                arrivalTimes[i]=arrivalTimes[i-1]+-Math.log(1-random.nextDouble());
            }
   
        }
    }

    public void calculateServiceBeginTimes() {
        serviceBeginTimes[0] = arrivalTimes[0]; // First customer starts service at their arrival time

        for (int i = 1; i < numberOfCustomers; i++) {
            if ((serviceBeginTimes[i - 1] + serviceTimes[i - 1]) > arrivalTimes[i]) {
                serviceBeginTimes[i] = (serviceBeginTimes[i - 1] + serviceTimes[i - 1]);
            } else {
                serviceBeginTimes[i] = arrivalTimes[i]; // Start immediately if the server is idle

            }
        }

    }

    public void calculateWaitTimes() {
        for (int i = 0; i < numberOfCustomers; i++) {
            // Wait time is the difference between service start and arrival time
            waitTimes[i] = Math.max(0, serviceBeginTimes[i] - arrivalTimes[i]);
        }
    }

    public void calculateServiceEndTimes() {
        for (int i = 0; i < numberOfCustomers; i++) {
            // Service ends when it begins plus the service time
            serviceEndTimes[i] = serviceBeginTimes[i] + serviceTimes[i];
        }
    }

    public void calculateIdleServerTimes() {
        idleServerTimes[0] = arrivalTimes[0]; // Idle time before the first customer arrives
        for (int i = 1; i < numberOfCustomers; i++) {
            // Idle time is the gap between the previous service end and the current service begin
            idleServerTimes[i] = Math.max(0, serviceBeginTimes[i] - serviceEndTimes[i - 1]);
        }
    }

    public void calculateTimeInSystem() {
        for (int i = 0; i < numberOfCustomers; i++) {
            // Time in system is from arrival to service end
            timeInSystem[i] = serviceEndTimes[i] - arrivalTimes[i];
        }
    }

    public void simulate() {
        generateCustomerIds();
        generateRandomArrivalTimes();
        generateRandomServiceTimes();
        calculateServiceBeginTimes();
        calculateServiceEndTimes();
        calculateWaitTimes();
        calculateIdleServerTimes();
        calculateTimeInSystem();
    }

    public void displayResults(JTextArea ta) {
          ta.setText(" Customer_ID   Arrival_T   Service_T   Servic_Begins   Service_Ends   Wait_T   Idle_Server_T   T_in_System\n");
        for (int i = 0; i < numberOfCustomers; i++) {
            String format= String.format(" %2d\t%15.2f\t%10.2f\t%8.2f\t%15.2f\t%11.2f\t%8.2f\t%15.2f",
                    (int)customerIds[i],
                    arrivalTimes[i],
                    serviceTimes[i],
                    serviceBeginTimes[i],
                    serviceEndTimes[i],
                    waitTimes[i],
                    idleServerTimes[i],
                    timeInSystem[i]
            );
            ta.append("    "+format+"\n");
       
        }
    // String format1= String.format("Average Number of Customers in System (L): %.2f%n", average_L());
       // ta.append(format1);
        displayAverages(ta);
    }


    public double average_Wq() {
        double sum = 0.0;
        for (int i = 0; i < numberOfCustomers; i++) {

            sum += waitTimes[i];

        }
        return sum / numberOfCustomers;
    }

    public double average_W() {
        double totalTimeInSystem = 0;

        for (int i = 0; i < numberOfCustomers; i++) {
            totalTimeInSystem += timeInSystem[i];
        }

        return totalTimeInSystem / numberOfCustomers;
    }

    public void displayAverages(JTextArea ta) {
  
        String format3= String.format("Average Waiting Time in Queue (W_q): %.2f%n", average_Wq());
        ta.append(format3);
        String format4= String.format("Average Waiting Time in System (W): %.2f%n", average_W());
         ta.append(format4);
    }
public double []getValus(){
return timeInSystem;
}
}
package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PrimesFinderTool {

    public static void main(String[] args) throws InterruptedException {
        int numeroThreads = 4;
        int maxPrim = 1000;
        PrimeFinder[] hilos = new PrimeFinder[numeroThreads];
        PrimesResultSet prs = new PrimesResultSet("john");
        for (int i = 0; i < numeroThreads; i++) {
            System.out.println((i * maxPrim / numeroThreads)+ " aqui toy "+((i + 1) * maxPrim / numeroThreads));
            hilos[i] = new PrimeFinder(new BigInteger(Integer.toString(i * maxPrim / numeroThreads)), new BigInteger(Integer.toString((i + 1) * maxPrim / numeroThreads)), prs);
            hilos[i].start();
        }

        for (int x = 0; x < numeroThreads; x++) {
            hilos[x].join();
            System.out.println("Prime numbers found:");

            System.out.println(prs.getPrimes());
            
        }

        /*while(task_not_finished){
                try {
                    //check every 10ms if the idle status (10 seconds without mouse
                    //activity) was reached. 
                    Thread.sleep(10);
                    if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement()>10000){
                        System.out.println("Idle CPU ");
                    }
                    else{
                        System.out.println("User working again!");
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }*/
    }

}



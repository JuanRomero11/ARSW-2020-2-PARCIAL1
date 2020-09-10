package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrimeFinder extends Thread {

    private BigInteger a;
    private BigInteger b;
    private PrimesResultSet prs;
    private boolean pause = false;

    public PrimeFinder(BigInteger a, BigInteger b, PrimesResultSet prs) {
        this.a = a;
        this.b = b;
        this.prs = prs;
    }

    public static void findPrimes(BigInteger _a, BigInteger _b, PrimesResultSet prs) {

        BigInteger a = _a;
        BigInteger b = _b;

        MathUtilities mt = new MathUtilities();

        int itCount = 0;

        BigInteger i = a;
        while (i.compareTo(b) <= 0) {
            itCount++;
            if (mt.isPrime(i)) {
                prs.addPrime(i);
            }

            i = i.add(BigInteger.ONE);
        }

    }

    public void paren() {
        pause = true;
    }

    public synchronized void noParen() {
        pause = true;
        this.notify();
    }

    @Override
    public void run() {
        synchronized (this) {
            while (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement() > 10000) {
                try {
                    this.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrimeFinder.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            this.noParen();
        }
        findPrimes(a, b, prs);

    }
}

    


	
	


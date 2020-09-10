package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import edu.eci.arsw.api.primesrepo.model.FoundPrime;
/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@Service
public class PrimeServiceStub implements PrimeService
{
    
    private List<FoundPrime> ListPrimes = new CopyOnWriteArrayList<>();
    public PrimeServiceStub() {
		

		ListPrimes.add(new FoundPrime("juan", "1"));
		ListPrimes.add(new FoundPrime("guillermo", "3"));
		ListPrimes.add(new FoundPrime("romero", "13"));
		

	}
    @Override
    public void addFoundPrime( FoundPrime foundPrime )
    {
        boolean estaPrimo=false;
    	 for(FoundPrime found: ListPrimes){
             if(found.getPrime().equals(foundPrime.getPrime())){
            	 estaPrimo=true;
             }
         }
         if(!estaPrimo){
            ListPrimes.add(foundPrime);
         }else{
            
            try {
                throw new Exception("Ya se encuentra el primo");
            } catch (Exception ex) {
                Logger.getLogger(PrimeServiceStub.class.getName()).log(Level.SEVERE, null, ex);
            }
            
         }
         
    }

    @Override
    public List<FoundPrime> getFoundPrimes()
    {
        return ListPrimes;
    }

    @Override
    public FoundPrime getPrime(String primeNumber){
        
        FoundPrime encontrado=null;
        for(FoundPrime p: ListPrimes){
            if(p.getPrime().equals(primeNumber)){
                encontrado=p;
            }
        }
        if(encontrado==null){
            try {
                throw new Exception("Nummero primo no encontrado");
            } catch (Exception ex) {
                Logger.getLogger(PrimeServiceStub.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return encontrado;
    }



}

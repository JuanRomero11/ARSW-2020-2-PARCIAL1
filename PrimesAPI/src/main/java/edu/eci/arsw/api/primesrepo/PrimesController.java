package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@RestController
public class PrimesController
{
    @Autowired
    PrimeService primeService;


    
    @RequestMapping( value = "/primes", method = RequestMethod.POST )
    public ResponseEntity<?> postPrimes(FoundPrime Primo)    {
        
        try {
        	primeService.addFoundPrime(Primo);
        	return new ResponseEntity<>(HttpStatus.CREATED);
        } catch(Exception ex) {
        	return new ResponseEntity<>("El primo ya esta agregado",HttpStatus.FORBIDDEN);
        }
    }
    
    @RequestMapping( value = "/primes/{primenumber}", method = RequestMethod.GET )
    public ResponseEntity<?> getPrime(String primenumber){
        
        try {
        	return new ResponseEntity<>(primeService.getPrime(primenumber),HttpStatus.ACCEPTED);
        } catch(Exception ex) {
        	return new ResponseEntity<>("Error 404 no se encontro ese numero",HttpStatus.NOT_FOUND);
        }
    } 
    
    @RequestMapping( value = "/primes", method = GET )
    public ResponseEntity<?> getPrimes() {
         try {
            return new ResponseEntity<>(primeService.getFoundPrimes(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error 404 no se encontraron primos",HttpStatus.NOT_FOUND);
        }
    }


    //TODO implement additional methods provided by PrimeService



}

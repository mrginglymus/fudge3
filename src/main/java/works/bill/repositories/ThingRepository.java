/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import works.bill.entities.Thing;

/**
 *
 * @author bill
 */
public interface ThingRepository extends JpaRepository<Thing, Long>{
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.bewerbungsportal.stellenverwaltung.repository;

import Testpackage.AbstractRepository;
import de.hsos.kbse.bewerbungsportal.bewerbungsverwaltung.entity.Bewerbung;
import de.hsos.kbse.bewerbungsportal.stellenverwaltung.entity.Stelle;
import java.io.File;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author nordm
 */
@Dependent
public class StellenRepository extends AbstractRepository<Stelle> {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;

    public StellenRepository() {
        super(Stelle.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Stelle> getStelleByPersonal(Long personaler_id) {
        
        System.out.println("de.hsos.kbse.bewerbungsportal.stellenverwaltung.repository.StellenRepository.getStelleByPersonal()"+"Personal_ID: "+personaler_id);
        
        TypedQuery<Stelle> query;
        query = this.em.createQuery("select p from Stelle p where p.personal= :id", Stelle.class);
        query.setParameter("id",personaler_id);
        return query.getResultList();
    }
    
    public List<Stelle> getAlleStellen(){
        System.out.println("de.hsos.kbse.bewerbungsportal.stellenverwaltung.repository.StellenRepository.getAlleStellen()");
        
        TypedQuery<Stelle> query;
        query = this.em.createQuery("select p from Stelle p", Stelle.class);
        return query.getResultList();
    }
    
    public void deleteStelle(long id){
        System.out.println("de.hsos.kbse.bewerbungsportal.stellenverwaltung.repository.StellenRepository.deleteStelle()");
        
        TypedQuery<Bewerbung> query2;
        query2 = this.em.createQuery("delete from Bewerbung p where p.stelle.id= :id", Bewerbung.class);
        query2.setParameter("id",id);
        query2.executeUpdate();
        System.out.println("de.hsos.kbse.bewerbungsportal.stellenverwaltung.repository.StellenRepository.deleteStelle()");
        
        //PDF Löschen
        //uploads -> Berwerber_ID -> PDF-Name -> Löschen 
        
        try {
            File deleteFile = new File(""); //Pfad ergänzen
            if(deleteFile.delete()) {
                System.out.println("Datei: "+deleteFile.getName()+"erfolgreich gelöscht!");
            }else {
                System.out.println("Fehler beim löschen!");
            }
        }catch(Exception e) {
        }
        
        TypedQuery<Stelle> query;
        query = this.em.createQuery("delete from Stelle p where p.id= :id", Stelle.class);
        query.setParameter("id",id);
        query.executeUpdate();
          
    }
}

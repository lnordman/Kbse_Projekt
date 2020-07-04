/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.entity.service;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author nordm
 */
@ApplicationPath("webresources")
public class JAXRSConfiguration extends Application {

    private Set<Class<?>> classes = new HashSet<>();

    /**
     *
     * @return
     */
    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
//    private void addRestResourceClasses(Set<Class<?>> resources) {
//        resources.add(de.hsos.kbse.entity.service.BenutzerFacadeREST.class);
//        resources.add(de.hsos.kbse.entity.service.BewerberFacadeREST.class);
//        resources.add(de.hsos.kbse.entity.service.BewerbungFacadeREST.class);
//        resources.add(de.hsos.kbse.entity.service.PersonalFacadeREST.class);
//        resources.add(de.hsos.kbse.entity.service.StelleFacadeREST.class);
//    }
}

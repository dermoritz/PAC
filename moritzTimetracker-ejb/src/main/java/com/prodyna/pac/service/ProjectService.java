package com.prodyna.pac.service;


import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.prodyna.pac.model.Project;

/**
 * Implements use cases on {@link Project} entity.
 * @author moritz löser (moritz.loeser@prodyna.com)
 *
 */
public class ProjectService {
    /**
     * Logger.
     */
    @Inject
    private Logger log;
    
    /**
     * To persist and update entities.
     */
    @Inject
    private EntityManager em;
    
    /**
     * Event on persisting or updating projects.
     */
    @Inject
    private Event<Project> projectEventSrc;
    
    /**
     * Adds a new project.
     * @param name name of project must be unique.
     * @param description textual description
     */
    public final void addProject(final Project project) {
        em.persist(project);
        projectEventSrc.fire(project);
        log.debug("Peristed project: " + project);
    }
    
    
    
}

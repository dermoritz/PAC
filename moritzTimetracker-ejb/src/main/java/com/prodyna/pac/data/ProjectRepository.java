package com.prodyna.pac.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.prodyna.pac.model.Project;
import com.prodyna.pac.model.Project_;

/**
 * Provides "select"ions on project table.
 * 
 * @author moritz löser (moritz.loeser@prodyna.com)
 *
 */
@ApplicationScoped
public class ProjectRepository {

    /**
     * Connection to data.
     */
    @Inject
    private EntityManager em;

    /**
     * 
     * @return list of all projects ordered by name.
     */
    public List<Project> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Project> criteria = cb.createQuery(Project.class);
        Root<Project> projectR = criteria.from(Project.class);
        criteria.select(projectR).orderBy(cb.asc(projectR.get(Project_.name)));
        return em.createQuery(criteria).getResultList();
    }
}

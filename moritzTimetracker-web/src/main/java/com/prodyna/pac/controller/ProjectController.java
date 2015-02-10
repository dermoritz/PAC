package com.prodyna.pac.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import com.prodyna.pac.model.Project;
import com.prodyna.pac.service.ProjectService;

@Model
public class ProjectController {

    /**
     * Connection to jsf.
     */
    @Inject
    private FacesContext fc;

    /**
     * Performs interactions on {@link Project}.
     */
    @Inject
    private ProjectService projectService;

    /**
     * Current project.
     */
    private Project project;

    /**
     * Provides new projects.
     */
    @Inject
    private Provider<Project> pProv;

    @Produces
    @Named
    public Project getProject() {
        return project;
    }

    /**
     * Persists a new project.
     */
    public void add() {
        try {
            projectService.addProject(project);
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                 "Registered!",
                                                 "Registration successful"));
            initNewProject();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration Unsuccessful");
            fc.addMessage(null, m);
        }
    }

    /**
     * Initializes {@link ProjectController#project} field.
     */
    @PostConstruct
    private void initNewProject() {
        project = pProv.get();
    }

    /**
     * Creates error message with root cause.
     * 
     * @param e
     *            exception
     * @return root cause message
     */
    private String getRootErrorMessage(final Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Adding project failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }
}

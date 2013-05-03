package de.threeoldcoders.web.tapestry.hibernate.services;

import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Startup;


/**
 * The Class TestDataSetupModule.
 * Module create test data
 */
public class TestDataSetupModule {
    
    /**
     * Inits the application.
     *
     * @param hsm the hsm
     */
    @Startup
    public static void initApplication(final HibernateSessionManager hsm)
    {
    }
}

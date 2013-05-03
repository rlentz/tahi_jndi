package de.threeoldcoders.web.tapestry.hibernate.services;

import org.apache.tapestry5.hibernate.HibernateSymbols;
import org.apache.tapestry5.ioc.MappedConfiguration;


public class JndiSetupModule {
    public static void contributeApplicationDefaults(final MappedConfiguration<String, Object> configuration)
    {
        // disable default hibernate config.xml 
        configuration.add(HibernateSymbols.DEFAULT_CONFIGURATION, "false");
    }
    
}

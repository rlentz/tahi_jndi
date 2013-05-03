package de.threeoldcoders.web.tapestry.hibernate.services;

import java.util.Collections;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;

import org.apache.tapestry5.hibernate.HibernateConfigurer;
import org.apache.tapestry5.hibernate.HibernateSymbols;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.slf4j.Logger;

public class JndiSetupModule {

    public static void contributeApplicationDefaults(
            final MappedConfiguration<String, Object> configuration) {
        // disable default hibernate config.xml
        configuration.add(HibernateSymbols.DEFAULT_CONFIGURATION, "false");
    }

    public static void contributeHibernateSessionSource(final Logger logger, final OrderedConfiguration<HibernateConfigurer> configuration) {
        
        final Properties p = getHibernateJndiProperties(logger);
        configuration.add("hibernate-session-source",
                new HibernateConfigurer() {
                    public void configure(final org.hibernate.cfg.Configuration configuration)
                    {
                        configuration.addProperties(p);
                    }
                });
    }

    private static Properties getHibernateJndiProperties(final Logger logger) {
        final Properties p = new Properties();
        try {
            final Context initCtx = new InitialContext();
            final Context envCtx = (Context) initCtx.lookup("java:comp/env");
            for (final NameClassPair ncp : Collections.list(envCtx.list(""))) {
                final String envName = ncp.getName();
                if (envName.startsWith("hibernate.")) {
                    p.put(envName, envCtx.lookup(envName));
                    logger.info(JndiSetupModule.class.getName()+ " setting property [" + envName + "] to [" + envCtx.lookup(envName)+"]");
                }
            }

        } catch (Exception e) {
            logger.error(JndiSetupModule.class.getName(), e);
        }

        return p;
    }

}

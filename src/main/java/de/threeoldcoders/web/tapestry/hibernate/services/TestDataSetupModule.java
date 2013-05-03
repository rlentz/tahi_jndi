package de.threeoldcoders.web.tapestry.hibernate.services;

import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Startup;
import org.hibernate.Session;

import de.threeoldcoders.web.tapestry.hibernate.entities.PHEUser;


/**
 * The Class TestDataSetupModule.
 * This module create test data
 */
public class TestDataSetupModule
{
    @Startup
    public static void initApplication(final HibernateSessionManager hsm)
    {
        final Session session = hsm.getSession();

//
//        final Work workClearDB = new Work() {
//            public void execute(final Connection connection) throws SQLException
//            {
//                final Statement statement = connection.createStatement();
//                statement.execute("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK");
//            }
//        };
//
//        try {
//            session.doWork(workClearDB);
//        } catch (final Throwable e) {
//            e.printStackTrace();
//        }
        
        
        // create sample data
        for (int i = 0;i<100;++i) {
            final String iv = String.valueOf(i);
            saveOrUpdate(session, new PHEUser("user"+iv, "user"+iv+"@user"+iv, "password_"+iv));        
        }
        
        hsm.commit();
    }
    
    protected static <T> T saveOrUpdate(final Session session, final T t)
    {
        session.saveOrUpdate(t);
        return t;
    }
}

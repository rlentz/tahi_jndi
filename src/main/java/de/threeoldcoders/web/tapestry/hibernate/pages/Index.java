package de.threeoldcoders.web.tapestry.hibernate.pages;

import java.util.Date;
import java.util.List;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.hibernate.Session;

import de.threeoldcoders.web.tapestry.hibernate.entities.PHEUser;

/**
 * Start page of application demo.
 */
public class Index
{
    @Property
    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    private String tapestryVersion;

    @InjectComponent
    private Zone zone;

    @Persist
    @Property
    private int clickCount;

    @Inject
    private AlertManager alertManager;
    
    @Property
    private PHEUser _user;
    
    @Inject
    private Session _session;

    public Date getCurrentTime()
    {
        return new Date();
    }

    void onActionFromIncrement()
    {
        alertManager.info("Increment clicked");

        clickCount++;
    }

    Object onActionFromIncrementAjax()
    {
        clickCount++;

        alertManager.info("Increment (via Ajax) clicked");

        return zone;
    }
    
    @SuppressWarnings("unchecked")
    public List<PHEUser> getAllUsers()
    {
        return _session.createQuery("from PHEUser").list();
    }
}

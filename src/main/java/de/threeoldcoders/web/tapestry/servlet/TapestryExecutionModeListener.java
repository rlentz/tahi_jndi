package de.threeoldcoders.web.tapestry.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.tapestry5.SymbolConstants;

/**
 * TapestryExecutionModeListener</br>
 * The class is interested in processing the web application initialization process.
 * It optionally initializes the "tapestry.execution-mode" system property, if no property value was supplied via the command line.
 * This system property value is later processed by the org.apache.tapestry5.TapestryFilter.
 * This allows a different default value via the web.xml and the context-param/param-name
 *
 * <pre>
 *  &lt;context-param&gt;
 *       &lt;param-name&gt;tapestry-execution-mode-listener.defaultValueIfNoSystemPropertyIsDefined&lt;/param-name&gt;
 *       &lt;param-value&gt;DevelopmentMode&lt;/param-value&gt;
 *  &lt;/context-param&gt;
 *   ...
 *  &lt;listener&gt;
 *       &lt;listener-class&gt;de.threeoldcoders.servlet.TapestryExecutionModeListener&lt;/listener-class&gt;
 *  &lt;/listener&gt;
 * </pre>  
 */
public class TapestryExecutionModeListener implements ServletContextListener {
    public static final String INIT_PARAMETER_NAME = "tapestry-execution-mode-listener.defaultValueIfNoSystemPropertyIsDefined";

    public void contextInitialized(final ServletContextEvent sce)
    {
        final String defaultValueIfNoSystemPropertyIsDefined = sce.getServletContext().getInitParameter(INIT_PARAMETER_NAME);
        final String executionMode = System.getProperty(SymbolConstants.EXECUTION_MODE);
        
        if (null==executionMode && null!=defaultValueIfNoSystemPropertyIsDefined) {
            sce.getServletContext().log("TapestryExecutionModeListener.contextInitialized: setting system property ["+SymbolConstants.EXECUTION_MODE+"] to ["+defaultValueIfNoSystemPropertyIsDefined+"]");
            System.setProperty(SymbolConstants.EXECUTION_MODE,defaultValueIfNoSystemPropertyIsDefined);
        }
    }

    public void contextDestroyed(final ServletContextEvent sce)
    {
        // do nothing
    }
}

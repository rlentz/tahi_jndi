tahi_jndi
=========

tapestry-hibernate-jndi-demo setup for using hibernate in tapestry using tomcat or jetty. The database jndi configuration is solely done via a jndi environment.

The project uses project lombok (http://projectlombok.org/), so for Eclipse/IntelliJ IDE integration install it accordingly.
&gt;

JNDI configuration for jetty6:
- everything is configured already, just do:<br/>
  git clone https://github.com/rlentz/tahi_jndi.git<br/>
  cd tahi_jndi.git<br/>
  mvn clean install jetty:run<br/>
  and access in your browser http://localhost:8080/demo


JNDI configuration for tomcat6/tomcat7:
- copy your required jdbc driver lib to TOMCAT_HOME/lib like:<br/>
  cp ~/.m2/repository/org/hsqldb/hsqldb/2.2.9/hsqldb-2.2.9.jar $TOMCAT_HOME/lib

- add a datasource and your required hibernate properties inside the GlobalNamingResources tag in TOMCAT_HOME/conf/server.xml like:<br/>
  &lt;GlobalNamingResources&gt;<br/>
    &lt;!-- Editable user database that can also be used by<br/>
         UserDatabaseRealm to authenticate users<br/>
    --&gt;<br/>
    .<br/>
    .<br/>
    .<br/>

    &lt;Resource auth="Container" name="jdbc/DemoDataSource" type="javax.sql.DataSource"<br/>
    factory="org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory"<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;url="jdbc:hsqldb:mem:demo"<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;driverClassName="org.hsqldb.jdbcDriver" username="sa" password=""<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;maxActive="3" maxWait="2000" maxIdle="1"<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;logAbandoned="true"<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;testOnBorrow="true"<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;testWhileIdle="true"<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;timeBetweenEvictionRunsMillis="300000"<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;numTestsPerEvictionRun="5"<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;minEvictableIdleTimeMillis="900000"/&gt;<br/>
              
    &lt;Environment name="hibernate.connection.datasource" value="java:/comp/env/jdbc/DemoDataSource" type="java.lang.String" override="false"/&gt;<br/>
    &lt;Environment name="hibernate.dialect" value="org.hibernate.dialect.HSQLDialect" type="java.lang.String" override="false"/&gt;<br/>
    &lt;Environment name="hibernate.show_sql" value="true" type="java.lang.String" override="false"/&gt;<br/>
    &lt;Environment name="hibernate.hbm2ddl.auto" value="create" type="java.lang.String" override="false"/&gt;<br/>
              
              
  &lt;/GlobalNamingResources&gt;<br/>
- make sure that all your jndi settings are linked and match correctly in the webapp/META-INF/context.xml file like:<br/>
  &lt;Context path="" reloadable="false"&gt;<br/>
  &lt;!-- the datasource is configured via the server.xml --&gt;<br/>
    &lt;ResourceLink name="jdbc/DemoDataSource" global="jdbc/DemoDataSource" type="javax.sql.DataSource"/&gt;<br/>
    
    &lt;ResourceLink name="hibernate.connection.datasource" global="hibernate.connection.datasource" type="java.lang.String"/&gt;<br/>
	&lt;ResourceLink name="hibernate.dialect" global="hibernate.dialect" type="java.lang.String"/&gt;<br/>
	&lt;ResourceLink name="hibernate.show_sql" global="hibernate.show_sql" type="java.lang.String"/&gt;<br/>
	&lt;ResourceLink name="hibernate.hbm2ddl.auto" global="hibernate.hbm2ddl.auto" type="java.lang.String"/&gt;<br/>
&lt;/Context&gt;<br/>

              
              
  

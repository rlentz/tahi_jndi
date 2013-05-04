# tahi_jndi

tapestry-hibernate-jndi-demo setup for using hibernate in tapestry using tomcat or jetty. The database jndi configuration is solely done via a jndi environment.
The project uses [project lombok](http://projectlombok.org/), so for Eclipse/IntelliJ IDE integration install it accordingly.

# JNDI configuration for jetty6:
everything is configured already (see src/test/resources/jetty-env.xml), just do:

    $ git clone https://github.com/rlentz/tahi_jndi.git  
    $ cd tahi_jndi.git    
    $ mvn clean install jetty:run

or use gradle instead of maven

    $ gradlew clean build jettyRun

and access in your browser http://localhost:8080/demo to display hibernate test data

# JNDI configuration for tomcat6/tomcat7:
copy your required jdbc driver lib to $TOMCAT_HOME/lib like:  

    $ cp ~/.m2/repository/org/hsqldb/hsqldb/2.2.9/hsqldb-2.2.9.jar $TOMCAT_HOME/lib

add your datasource and required hibernate properties inside the &lt;GlobalNamingResources&gt; tag in TOMCAT_HOME/conf/server.xml like:
```html
  <GlobalNamingResources>
    <!-- Editable user database that can also be used by<br/>
         UserDatabaseRealm to authenticate users<br/>
    -->
    .
    .
    .
    <Resource auth="Container" name="jdbc/DemoDataSource" type="javax.sql.DataSource"<br/>
        factory="org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory"<br/>
        url="jdbc:hsqldb:mem:demo"<br/>
        driverClassName="org.hsqldb.jdbcDriver" username="sa" password=""<br/>
        maxActive="3" maxWait="2000" maxIdle="1"<br/>
        logAbandoned="true"<br/>
        testOnBorrow="true"<br/>
        testWhileIdle="true"<br/>
        timeBetweenEvictionRunsMillis="300000"<br/>
        numTestsPerEvictionRun="5"<br/>
        minEvictableIdleTimeMillis="900000"/>
              
    <Environment name="hibernate.connection.datasource" value="java:/comp/env/jdbc/DemoDataSource" type="java.lang.String" override="false"/>
    <Environment name="hibernate.dialect" value="org.hibernate.dialect.HSQLDialect" type="java.lang.String" override="false"/>
    <Environment name="hibernate.show_sql" value="true" type="java.lang.String" override="false"/>
    <Environment name="hibernate.hbm2ddl.auto" value="create" type="java.lang.String" override="false"/>
              
              
  </GlobalNamingResources>
```

- make sure that all your jndi settings are linked and match correctly in the webapp/META-INF/context.xml file like:

```html
  <Context path="" reloadable="false">
  <!-- the datasource is configured via the server.xml -->
  <ResourceLink name="jdbc/DemoDataSource" global="jdbc/DemoDataSource" type="javax.sql.DataSource"/>
  <ResourceLink name="hibernate.connection.datasource" global="hibernate.connection.datasource" type="java.lang.String"/>
  <ResourceLink name="hibernate.dialect" global="hibernate.dialect" type="java.lang.String"/>
  <ResourceLink name="hibernate.show_sql" global="hibernate.show_sql" type="java.lang.String"/>
  <ResourceLink name="hibernate.hbm2ddl.auto" global="hibernate.hbm2ddl.auto" type="java.lang.String"/>
</Context>
```

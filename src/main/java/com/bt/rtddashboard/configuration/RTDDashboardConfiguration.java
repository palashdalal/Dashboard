/**
 * 
 */
package com.bt.rtddashboard.configuration;

//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bt.rtddashboard.controller.DashboardController;

/**
 * @author 609669080
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.bt.rtddashboard")
/*@EnableJpaRepositories(basePackages = {
        "com.bt.rtddashboard"
})*/
@EnableScheduling
@PropertySource("classpath:jdbc.properties")
public class RTDDashboardConfiguration extends WebMvcConfigurerAdapter {
	
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "database.driverClassName";
	private static final String PROPERTY_NAME_DATABASE_URL = "database.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "database.username";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "database.password";
	/*private static final String PROPERTY_NAME_DATABASE_MAXACTIVE = "database.maxActive";
	private static final String PROPERTY_NAME_DATABASE_MINIDLE = "database.minIdle";
	private static final String PROPERTY_NAME_DATABASE_MAXIDLE = "database.maxIdle";
	private static final String PROPERTY_NAME_DATABASE_MAXWAIT = "database.maxWait";
	private static final String PROPERTY_NAME_DATABASE_INITIALSIZE = "database.initialSize";
	private static final String PROPERTY_NAME_DATABASE_REMOVEABANDONED = "database.removeAbandoned";
	private static final String PROPERTY_NAME_DATABASE_REMOVEABANDONEDTIMEOUT = "database.removeAbandonedTimeout";
	private static final String PROPERTY_NAME_DATABASE_LOGABANDONED = "database.logAbandoned";
	private static final String PROPERTY_NAME_DATABASE_MINEVICTIME = "database.minEvictableIdleTimeMillis";*/
	
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_HIBERNATE_SCHEMA = "hibernate.default_schema";
	private static final String PROPERTY_NAME_VALIDATION_QUERY = "hibernate.validationQuery";
	
	@Resource
	private Environment env;
	
	@Bean(name = "dataSource", destroyMethod="close")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		dataSource.setValidationQuery(env.getRequiredProperty(PROPERTY_NAME_VALIDATION_QUERY));
		//The initial number of connections that are created when the pool is started.
		dataSource.setInitialSize(10);
		// The maximum number of active connections that can be allocated from this pool 
		//at the same time, or negative for no limit.
		dataSource.setMaxActive(-1);
		//The minimum number of active connections that can remain idle in the pool, 
		//without extra ones being created, or 0 to create none.
		dataSource.setMinIdle(0);
		//The maximum number of connections that can remain idle in the pool, 
		//without extra ones being released, or negative for no limit.
		dataSource.setMaxIdle(-1);
		// The maximum number of milliseconds that the pool will wait (when there are no available 
		//connections) for a connection to be returned before throwing an exception, or <= 0 to wait 
		//indefinitely.
		dataSource.setMaxWait(5000);
		
		//The minimum amount of time an object may sit idle in the pool before it is eligable 
		//for eviction by the idle object evictor (if any).
		dataSource.setMinEvictableIdleTimeMillis(5000);
		
		return dataSource;
	}
	
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		properties.put(PROPERTY_NAME_HIBERNATE_SCHEMA, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SCHEMA));
		return properties;
	}
	
	@Bean(name = "sessionFactory")
	@Scope("singleton")
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(getDataSource());
		factory.setPackagesToScan(new String[] { "com.bt.rtddashboard" });
		factory.setHibernateProperties(getHibernateProperties());
		return factory;
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory factory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(factory);
		
		return transactionManager;
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/").setCachePeriod(31556926);

	}

	/*//@Scheduled(fixedRate=1000)
    public void work() {
        // task execution logic
		System.out.println("Scheduled Task");
		DashboardController controller=new DashboardController();
		//controller.performTask();
    }*/
	/*@Bean
	public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
		MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
		obj.setTargetBeanName("jobone");
		obj.setTargetMethod("myTask");
		return obj;
	}
	//Job  is scheduled for 3+1 times with the interval of 30 seconds
	@Bean
	public SimpleTriggerFactoryBean simpleTriggerFactoryBean(){
		SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
		stFactory.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
		stFactory.setStartDelay(3000);
		stFactory.setRepeatInterval(30000);
		stFactory.setRepeatCount(10000000);
		return stFactory;
	}*/
	
}

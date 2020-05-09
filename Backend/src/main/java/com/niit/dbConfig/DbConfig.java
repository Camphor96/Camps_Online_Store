package com.niit.dbConfig;

import java.util.Properties;


import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.camp.CartItem;
import com.niit.camp.OrderDetails;
import com.niit.camp.Product;
import com.niit.camp.Supplier;
import com.niit.camp.UserDetails;
import com.niit.camp.category;
@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class DbConfig {
	
	@Bean (name="datasource")
	public DataSource getDataSource() {
	DriverManagerDataSource datasource= new DriverManagerDataSource();
	datasource.setDriverClassName("org.h2.Driver");
	datasource.setUsername("sa");
	datasource.setPassword("sa");
	datasource.setUrl("jdbc:h2:tcp://localhost/~/test");
	
	return datasource;
	
	}
	
	@Bean (name="sessionfactory")
	public SessionFactory getSessionFactory() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.show_sql", "true");
		LocalSessionFactoryBuilder n=new LocalSessionFactoryBuilder(getDataSource());
		n.addProperties(properties);
		n.addAnnotatedClass(category.class);
		n.addAnnotatedClass(Product.class);
		n.addAnnotatedClass(Supplier.class);
		n.addAnnotatedClass(UserDetails.class);
		n.addAnnotatedClass(CartItem.class);
		n.addAnnotatedClass(OrderDetails.class);
		SessionFactory sessionfactory = n.buildSessionFactory();
		return sessionfactory;
		
	}
	
	@Bean(name="transactionmanager")
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionfactory) {
		
		return new HibernateTransactionManager(sessionfactory);
		
	}
	
	
	
}

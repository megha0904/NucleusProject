package com.nucleus.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@Configuration
@ComponentScan("com.nucleus")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@EnableWebMvc
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver
                = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactoryBean(){
        LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
        bean.setDataSource(getDataSource());
        bean.setHibernateProperties(hibernateProperties());
        bean.setPackagesToScan(new String[]{"com.nucleus"});
        return bean;
    }

    Properties properties = new Properties();
    private Properties hibernateProperties() {
        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("current_session_context_class","thread");
        return properties;
    }

    @Bean
    public BasicDataSource getDataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");

//        ds.setUsername("c##username");
//        ds.setPassword("pwd");

         /*ds.setUrl("jdbc:oracle:thin:@localhost:1521:ORCLCDB");
         ds.setUsername("c##username");
         ds.setPassword("pwd");*/

//        ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
//        ds.setUsername("sys as sysdba");
//       ds.setPassword("gyanesh10");

        /* ds.setUrl("jdbc:oracle:thin:@localhost:1521:ORCLCDB");
         ds.setUsername("c##username");
<<<<<<< HEAD
         ds.setPassword("pwd");

>>>>>>> f9dc56f13ba721484ff770d703086753ee6550a0

=======
         ds.setPassword("pwd");*/



//        ds.setUrl("jdbc:oracle:thin:@localhost:1521/xepdb1");
//        ds.setUsername("MYUSERNAME");
//        ds.setPassword("MYPASSWORD");

//         ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
//         ds.setUsername("megha");
//         ds.setPassword("megha");

          /*   ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
=======
>>>>>>> b62af43ac6b34c9b9dbf921806e793f75f73301b

        /*   ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
             ds.setUsername("system");
             ds.setPassword("hemant12345");*/

       /* ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
        ds.setUsername("sys as sysdba");
        ds.setPassword("gkul0289");*/

        ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
        ds.setUsername("c##asaf");
        ds.setPassword("asaf");

        return ds;

    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactoryBean().getObject());
        return transactionManager;
    }

    // TODO: 15/12/20 to be used later when configuring for wildfly datasource instead of getDataSource()
//    @Bean
//    public DataSource dataSource() {
//        JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
//        return jndiDataSourceLookup.getDataSource("java:/OracleDS");
//    }

}
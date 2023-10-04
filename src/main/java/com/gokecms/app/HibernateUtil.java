package com.gokecms.app;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.gokecms.model.ApiToken;
import com.gokecms.model.Client;
import com.gokecms.model.ClientFile;
import com.gokecms.model.SystemUser;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
         if (sessionFactory == null) {
            try {
                 Configuration configuration = new Configuration();

                 Properties settings = new Properties();
                 
                 settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                 settings.put(Environment.URL, "jdbc:mysql://localhost:3306/goke_cms_db?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT");
                 settings.put(Environment.USER, "ebahn");
                 settings.put(Environment.PASS, "ebahn");
                 settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

                 settings.put(Environment.SHOW_SQL, "false");

                 settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                 settings.put(Environment.HBM2DDL_AUTO, "update");

                 configuration.setProperties(settings);
                 configuration.addAnnotatedClass(SystemUser.class);
                 configuration.addAnnotatedClass(Client.class);
                 configuration.addAnnotatedClass(ClientFile.class);
                 configuration.addAnnotatedClass(ApiToken.class);

                 ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                 .applySettings(configuration.getProperties()).build();
                 System.out.println("Hibernate Java Config serviceRegistry created");
                 sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                 return sessionFactory;

              } catch (Exception e) {
                 e.printStackTrace();
              }
          }
        return sessionFactory;
    }
}

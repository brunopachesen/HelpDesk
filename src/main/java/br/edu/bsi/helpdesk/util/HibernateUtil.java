package br.edu.bsi.helpdesk.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();

	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}

	private static SessionFactory criarFabricaDeSessoes() {
		try {
			Configuration configuracao = new Configuration().configure();

			ServiceRegistry registro = new StandardServiceRegistryBuilder()
					.applySettings(configuracao.getProperties()).build();

			SessionFactory fabrica = configuracao.buildSessionFactory(registro);

			return fabrica;
		} catch (Throwable ex) {
			System.err
					.println("A f�brica de sess�es n�o pode ser criada." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String driver = "jdbc:postgresql://localhost:5433/helpdesk";
            Connection con = DriverManager.getConnection(driver, "postgres", "root");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
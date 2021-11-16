package com.teiusko;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App 
{
    public static void main( String[] args )
    {

        Spaceship mimi = new Spaceship();
        mimi.setShipName("Xiao Mi One");

        Spaceship falconOne = new Spaceship();
        falconOne.setShipName("Falcon I");

        Alien alienOne = new Alien();
        alienOne.setColor("Orange");
        alienOne.setRace("Klicks");

        AlienName alienOneName = new AlienName();
        alienOneName.setFirstName("Jane");
        alienOneName.setMiddleName("Middle");
        alienOneName.setLastName("Smith");
        alienOne.setName(alienOneName);
        alienOne.setSpaceship(falconOne);


        Alien alienTwo = new Alien();
        alienTwo.setColor("Blue");
        alienTwo.setRace("Volties");
        AlienName alienTwoName = new AlienName();
        alienTwoName.setFirstName("John");
        alienTwoName.setMiddleName("Middle");
        alienTwoName.setLastName("Do");
        alienTwo.setName(alienTwoName);
        alienTwo.setSpaceship(mimi);


        HomePlanet mars = new HomePlanet();
        mars.setName("Mars");
//        Set<HomePlanet> homePlanetSet = new HashSet<HomePlanet>();
        mars.getAlien().add(alienOne);
        mars.getAlien().add(alienTwo);

        CountriesVisited unitedStates = new CountriesVisited();
        unitedStates.setCountryName("USA");
        CountriesVisited japan = new CountriesVisited();
        japan.setCountryName("Japan");

        unitedStates.getAlien().add(alienOne);
        unitedStates.getAlien().add(alienTwo);
        japan.getAlien().add(alienOne);

        alienOne.getCountriesVisited().add(unitedStates);
        alienOne.getCountriesVisited().add(japan);
        alienTwo.getCountriesVisited().add(unitedStates);

        Alien telusko = null;

        Configuration configuration = new Configuration();

        configuration.configure();
        configuration.addAnnotatedClass(Alien.class);
        configuration.addAnnotatedClass(Spaceship.class);
        configuration.addAnnotatedClass(HomePlanet.class);
        configuration.addAnnotatedClass(CountriesVisited.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        System.out.println("Hibernate serviceRegistry created");

        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(mars);
        session.save(falconOne);
        session.save(mimi);
        session.save(alienOne);
        session.save(alienTwo);
        session.save(unitedStates);
        session.save(japan);

        // Fetch data
        telusko = (Alien) session.get(Alien.class,Long.valueOf(1));
        System.out.println(telusko);

        Alien alien = session.load(Alien.class,Long.valueOf(1));
        // only fires if you use it in something else e.g. log
        System.out.println(alien);


        Collection<CountriesVisited> countriesVisitedList = telusko.getCountriesVisited();

        for(CountriesVisited cv : countriesVisitedList) {
            cv.getCountryName();
        }

        tx.commit();
        session.close();


        Session sessionTwo = sessionFactory.openSession();
        sessionTwo.beginTransaction();
        Query query = sessionTwo.createQuery("FROM Alien");
        List<Alien> aliens = query.list();

        for(Alien a : aliens) {
            System.out.println(a);
        }

        Query qOne = sessionTwo.createQuery("SELECT id, Name,Color FROM Alien");

        List<Object[]> aliensTwo = (List<Object[]>) qOne.list();

        for (Object[] a : aliensTwo) {
            System.out.println(a[0]);
        }

        String b = "Orange";
        Query querytwo = sessionTwo.createQuery("FROM Alien A WHERE A.Color=:a");
        querytwo.setParameter("a",b);
        Alien orangeAlien = (Alien) querytwo.uniqueResult();
        System.out.println(orangeAlien);

        Query queryThree = sessionTwo.createQuery("SELECT id, Name, Color from Alien A where A.id=1");
        Object[] alienIdOne = (Object[]) queryThree.uniqueResult();

        for (Object a : alienIdOne) {
            System.out.println(a);
        }

        sessionTwo.getTransaction().commit();
        sessionTwo.close();
    }
}

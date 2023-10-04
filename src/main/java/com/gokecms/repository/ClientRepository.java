package com.gokecms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gokecms.app.HibernateUtil;
import com.gokecms.model.Client;

public class ClientRepository {
	/**
     * Save new client
     * @param user
     */
    public int save( Client entity ) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.persist( entity );
            // commit transaction
            transaction.commit();
            
            System.out.println( entity.getId() );
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        
        return entity.getId();
    }

    /**
     * Update SystemUser
     * @param user
     */
    public void update( Client entity ) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.merge( entity );
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Delete SystemUser
     * @param id
     */
    public void delete(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a user object
            Client entity = session.get( Client.class, id );
            if ( entity != null ) {
                session.remove( entity );
                System.out.println("entity is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Get SystemUser By ID
     * @param id
     * @return
     */
    public Client get(int id) {

        Transaction transaction = null;
        Client entity = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            entity = session.get(Client.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * Get all SystemUsers
     * @return
     */
    @SuppressWarnings("unchecked")
    public List < Client > getAll() {

        Transaction transaction = null;
        List < Client > list = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            list = session.createQuery( "from Client", Client.class ).getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
			/*
			 * if (transaction != null) { transaction.rollback(); }
			 */
            e.printStackTrace();
        }
        return list;
    }
    
    /**
     * Find matching users by column
     * @return
     */
    @SuppressWarnings("unchecked")
    public List < Client > find( String column, String value ) {

        Transaction transaction = null;
        List < Client > list = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            
            String jpql = "from Client su where su." + column + " = :" + column;
            list = session.createQuery(jpql, Client.class)
                  .setParameter(column, value)
                  .getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
			/*
			 * if (transaction != null) { transaction.rollback(); }
			 */
            e.printStackTrace();
        }
        return list;
    }
}

package com.gokecms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gokecms.app.HibernateUtil;
import com.gokecms.model.ClientFile;

public class ClientFileRepository {
	/**
     * Save new client file
     * @param entity
     */
    public void save( ClientFile entity ) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.persist( entity );
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
     * Update client file
     * @param entity
     */
    public void update( ClientFile entity ) {
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
     * Delete client file
     * @param id
     */
    public void delete(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a user object
            ClientFile entity = session.get( ClientFile.class, id );
            if ( entity != null ) {
                session.remove( entity );
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
     * Get client file By ID
     * @param id
     * @return
     */
    public ClientFile get(int id) {

        Transaction transaction = null;
        ClientFile entity = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            entity = session.get(ClientFile.class, id);
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
     * Get all client file
     * @return
     */
    @SuppressWarnings("unchecked")
    public List < ClientFile > getAll() {

        Transaction transaction = null;
        List < ClientFile > list = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            list = session.createQuery( "from ClientFile", ClientFile.class ).getResultList();

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
     * Find matching client file by column
     * @return
     */
    @SuppressWarnings("unchecked")
    public List < ClientFile > find( String column, String value ) {

        Transaction transaction = null;
        List < ClientFile > list = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            
            String jpql = "from ClientFile su where su." + column + " = :" + column;
            list = session.createQuery(jpql, ClientFile.class)
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

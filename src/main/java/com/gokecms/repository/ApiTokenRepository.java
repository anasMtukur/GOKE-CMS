package com.gokecms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gokecms.app.HibernateUtil;
import com.gokecms.model.ApiToken;

public class ApiTokenRepository {

	/**
     * Save New
     * @param entity
     */
    public void save(ApiToken entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.persist(entity);
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
     * Update Item
     * @param entity
     */
    public void update(ApiToken entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.merge(entity);
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
     * Delete Item
     * @param id
     */
    public void delete(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a user object
            ApiToken entity = session.get(ApiToken.class, id);
            if (entity != null) {
                session.remove(entity);
                System.out.println("user is deleted");
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
     * Get Item By ID
     * @param id
     * @return
     */
    public ApiToken get(int id) {

        Transaction transaction = null;
        ApiToken entity = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            entity = session.get(ApiToken.class, id);
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
     * Get all Items
     * @return
     */
    @SuppressWarnings("unchecked")
    public List < ApiToken > getAll() {

        Transaction transaction = null;
        List < ApiToken > listOfItems = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            listOfItems = session.createQuery( "from ApiToken", ApiToken.class ).getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
			/*
			 * if (transaction != null) { transaction.rollback(); }
			 */
            e.printStackTrace();
        }
        return listOfItems;
    }
    
    /**
     * Find matching Items by column
     * @return
     */
    @SuppressWarnings("unchecked")
    public List < ApiToken > find( String column, String value ) {

        Transaction transaction = null;
        List < ApiToken > listOfItems = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            
            String jpql = "from ApiToken su where su." + column + " = :" + column;
            listOfItems = session.createQuery(jpql, ApiToken.class)
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
        return listOfItems;
    }
}

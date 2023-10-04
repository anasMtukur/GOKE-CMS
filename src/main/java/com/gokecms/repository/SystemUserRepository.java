package com.gokecms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gokecms.app.HibernateUtil;
import com.gokecms.model.SystemUser;

public class SystemUserRepository {

	/**
     * Save User
     * @param user
     */
    public void saveUser(SystemUser user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.persist(user);
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
     * Update SystemUser
     * @param user
     */
    public void update(SystemUser user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.merge(user);
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
            SystemUser user = session.get(SystemUser.class, id);
            if (user != null) {
                session.remove(user);
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
     * Get SystemUser By ID
     * @param id
     * @return
     */
    public SystemUser get(int id) {

        Transaction transaction = null;
        SystemUser user = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = session.get(SystemUser.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Get all SystemUsers
     * @return
     */
    @SuppressWarnings("unchecked")
    public List < SystemUser > getAll() {

        Transaction transaction = null;
        List < SystemUser > listOfSystemUser = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            listOfSystemUser = session.createQuery( "from SystemUser", SystemUser.class ).getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
			/*
			 * if (transaction != null) { transaction.rollback(); }
			 */
            e.printStackTrace();
        }
        return listOfSystemUser;
    }
    
    /**
     * Find matching users by column
     * @return
     */
    @SuppressWarnings("unchecked")
    public List < SystemUser > find( String column, String value ) {

        Transaction transaction = null;
        List < SystemUser > listOfSystemUser = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            
            String jpql = "from SystemUser su where su." + column + " = :" + column;
            listOfSystemUser = session.createQuery(jpql, SystemUser.class)
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
        return listOfSystemUser;
    }
}

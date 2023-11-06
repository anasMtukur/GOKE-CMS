package com.gokecms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gokecms.app.HibernateUtil;
import com.gokecms.model.DocumentType;

public class DocumentTypeRepository {
	/**
     * Save new
     * @param entity
     */
    public int save( DocumentType entity ) {
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
     * Update
     * @param entity
     */
    public void update( DocumentType entity ) {
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
     * Delete
     * @param id
     */
    public void delete(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a user object
            DocumentType entity = session.get( DocumentType.class, id );
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
     * Get By ID
     * @param id
     * @return DocumentType
     */
    public DocumentType get(int id) {

        Transaction transaction = null;
        DocumentType entity = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            entity = session.get(DocumentType.class, id);
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
     * Get all
     * @return
     */
    @SuppressWarnings("unchecked")
    public List < DocumentType > getAll() {

        Transaction transaction = null;
        List < DocumentType > list = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            list = session.createQuery( "from DocumentType", DocumentType.class ).getResultList();

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
    public List < DocumentType > find( String column, String value ) {

        Transaction transaction = null;
        List < DocumentType > list = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            
            String jpql = "from DocumentType su where su." + column + " = :" + column;
            list = session.createQuery(jpql, DocumentType.class)
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

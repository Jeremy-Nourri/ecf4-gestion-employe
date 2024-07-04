package com.example.ecf4gestionemployes.repository;

import com.example.ecf4gestionemployes.exception.PositionNotFoundException;
import com.example.ecf4gestionemployes.model.Position;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PositionRepository extends BaseRepository<Position> {

    public PositionRepository() {
        super();
    }

    @Override
    public Position add(Position entity) {
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public Position findById(Long id) {
        Transaction transaction = null;
        Position position = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            position = session.get(Position.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return position;
    }

    @Override
    public Position update(Position entity) {
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public boolean delete(Long id) {
        Transaction transaction = null;
        boolean result = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Position position = session.get(Position.class, id);
            session.remove(position);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Position> findAll() {
        Transaction transaction = null;
        List<Position> positions = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query<Position> query = session.createQuery("from Position", Position.class);
            positions = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return positions;
    }


}

package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.model.Alimento;
import com.hibernate.util.HibernateUtil;


/**
 * Clase DAO (Data Access Object) para la entidad Alimento. Permite realizar
 * operaciones de persistencia y recuperación de datos relacionados con la
 * entidad Alimento en la base de datos.
 */
public class AlimentoDAO {
	// Insertar
	
	/**
     * Inserta un objeto Alimento en la base de datos.
     *
     * @param a El objeto Alimento a insertar.
     */
	public void insertAlimento(Alimento a) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(a);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	
		// Actualizar
		/**
		 * Actualiza un objeto Alimento en la base de datos.
		 *
		 * @param a El objeto Alimento a actualizar.
		 */
		public void updateAlimento(Alimento a) {
			Transaction transaction = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();
				session.merge(a);
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
			}
		}
		
		
		// Borrar
		 /**
	     * Elimina un objeto Alimento de la base de datos.
	     *
	     * @param id El ID del Alimento a eliminar.
	     */
		public void deleteAlimento(int id) {
			Transaction transaction = null;
			Alimento a = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();
				a = session.get(Alimento.class, id);
				session.remove(a);
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
			}
		}
		
		
		
		// Selección simple
		/**
	     * Obtiene un objeto Alimento de la base de datos según su ID.
	     *
	     * @param id El ID del Alimento a obtener.
	     * @return El objeto Alimento correspondiente al ID especificado, o null si no se encuentra.
	     */
		public Alimento selectAlimentoById(int id) {
			Transaction transaction = null;
			Alimento a = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();
				a = session.get(Alimento.class, id);
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
			}
			return a;
		}
		
		/**
	     * Obtiene un objeto Alimento de la base de datos según su nombre.
	     *
	     * @param al El nombre del Alimento a obtener.
	     * @return El objeto Alimento correspondiente al nombre especificado, o null si no se encuentra.
	     */
		public Alimento selectAlimentoByNombre(String al) {
			Transaction transaction = null;
			Alimento a = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();
				Query<Alimento> query = session.createQuery("FROM Alimento WHERE nombre = :nomParam",Alimento.class);
				query.setParameter("nomParam", al);
				a=query.uniqueResult();
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
			}
			return a;
		}
		
		// Selección múltiple
		
		/**
	     * Obtiene todos los objetos Alimento de la base de datos.
	     *
	     * @return Una lista de objetos Alimento presentes en la base de datos.
	     */
		public List<Alimento> selectAllAlimento() {
			Transaction transaction = null;
			List<Alimento> alimentos = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();
				alimentos = session.createQuery("FROM Alimento", Alimento.class).getResultList();
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
			}
			return alimentos;
		}

}

package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserCar(String model, int series) {
      TypedQuery<User> query;
      try {
         query = sessionFactory.getCurrentSession()
                 .createQuery("FROM User user WHERE user.car.model = :Model AND user.car.series = :Series");
         query.setParameter("Model", model);
         query.setParameter("Series", series);
         return query.getSingleResult();
      } catch (NoResultException e) {
         System.out.println("No result");
      }
      return null;

   }


}

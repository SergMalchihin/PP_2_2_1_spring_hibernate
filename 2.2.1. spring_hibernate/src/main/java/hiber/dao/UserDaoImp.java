package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
   public void deleteAllUsers() {
      List<User> users = listUsers();
      for (User user : users) {
         sessionFactory.getCurrentSession().delete(user);
      }
   }

//https://docs.oracle.com/javaee/6/api/javax/persistence/TypedQuery.html
   @Override
   public User carOwner(String car_name, String car_series) {
      TypedQuery<Car> carOwnerQuery=sessionFactory.getCurrentSession()
              .createQuery("from Car where model = :car_name and series = :car_series")
              .setParameter("car_name", car_name)
              .setParameter("car_series", car_series);

      List<Car> ownerCarList = carOwnerQuery.getResultList();
      if (!ownerCarList.isEmpty()) {
         Car findCar = ownerCarList.get(0);
         List<User> ListUser = listUsers();
         User CarUser = ListUser.stream()
                 .filter(user -> user.getCar().equals(findCar))
                 .findAny()
                 .orElse(null);
         return CarUser;
      }

      return null;
   }

}

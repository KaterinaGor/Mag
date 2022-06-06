
package session;

import entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melnikov
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "WebShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    public User find(String login){
        try {
           return (User) em.createQuery("SELECT u FROM User u WHERE u.login = :login")
                   .setParameter("login", login)
                   .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public User findByLogin(String login) {
        try {
            return (User) em.createQuery("SELECT u FROM User u WHERE u.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();   
        } catch (Exception e) {
            return null;
        }
    }
    
}

package session;

import entity.User;
import entity.UserRoles;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melnikov
 */
@Stateless
public class UserRolesFacade extends AbstractFacade<UserRoles> {

    @PersistenceContext(unitName = "WebShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRolesFacade() {
        super(UserRoles.class);
    }
    public boolean isRole(String roleName, User user){
        try {
            List<String> userRoles = em.createQuery("SELECT ur.role.roleName FROM UserRoles ur WHERE ur.user = :user")
                    .setParameter("user", user)
                    .getResultList();
            if(userRoles.contains(roleName)){
                return true;
            }else{
                return false;
            }
                    
        } catch (Exception e) {
            return false;
        }
    }

    public String getTopRole(User user) {
        List<String> listRoles = em.createQuery("SELECT ur.role.roleName FROM UserRoles ur WHERE ur.user=:user")
                .setParameter("user", user)
                .getResultList();
        if(listRoles.contains("ADMINISTRATOR")){
            return "ADMINISTRATOR";
        }else if(listRoles.contains("MANAGER")){
            return "MANAGER";
        }else if(listRoles.contains("READER")){
            return "READER";
        }else {
            return null;
        }
    }
}

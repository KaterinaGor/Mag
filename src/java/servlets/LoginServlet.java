
package servlets;

import entity.Product;
import entity.Client;
import entity.Role;
import entity.User;
import entity.UserRoles;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.ProductFacade;
import session.ClientFacade;
import session.RoleFacade;
import session.UserFacade;
import session.UserRolesFacade;
import javax.servlet.http.HttpSession;
import tools.EncryptPassword;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "LoginServlet", loadOnStartup = 1, urlPatterns = {
    "/index.jsp",
    "/showLogin", 
    "/login", 
    "/logout",
    "/listBooks",
    "/showRegistration",
    "/registration",
    
})
public class LoginServlet extends HttpServlet {
    @EJB private UserFacade userFacade;
    @EJB private ClientFacade clientFacade;
    @EJB private RoleFacade roleFacade;
    @EJB private UserRolesFacade userRolesFacade;
    @EJB private ProductFacade productFacade;
    private EncryptPassword encryptPassword;
    
    
    @Override
    public void init() throws ServletException {
        super.init(); 
        List<User> users = userFacade.findAll();
        if(users.isEmpty()){
            User user = new User();
            user.setLogin("admin");
            encryptPassword = new EncryptPassword();
            String salt = encryptPassword.createSalt();
            user.setSalt(salt);
            String hashPassword = encryptPassword.createHash("12345", salt);
            user.setPassword(hashPassword);
            Client client = new Client();
            client.setFirstname("admin");
            client.setLastname("admin");
            client.setPhone("565456545");
            clientFacade.create(client);
            user.setClient(client);
            userFacade.create(user);
            Role role = new Role();
            role.setRoleName("ADMINISTRATOR");
            roleFacade.create(role);
            UserRoles userRoles = new UserRoles();
            userRoles.setUser(user);
            userRoles.setRole(role);
            userRolesFacade.create(userRoles);
            role = new Role();
            role.setRoleName("MANAGER");
            roleFacade.create(role);
            userRoles = new UserRoles();
            userRoles.setUser(user);
            userRoles.setRole(role);
            userRolesFacade.create(userRoles);
            role = new Role();
            role.setRoleName("READER");
            roleFacade.create(role);
            userRoles = new UserRoles();
            userRoles.setUser(user);
            userRoles.setRole(role);
            userRolesFacade.create(userRoles);
        }
        
    }

    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/index.jsp":
                List<Product> products = productFacade.findAll();
                request.setAttribute("products", products);
                
                request.getRequestDispatcher("/listProduct.jsp").forward(request, response);
                break;
            case "/showLogin":
                request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                //authentification
                User authUser = userFacade.find(login);
                if(authUser == null){
                    request.setAttribute("info", "Нет такого пользователя или неправильный пароль");
                    request.getRequestDispatcher("/showLogin").forward(request, response);
                    break;
                }
                //authorization
                encryptPassword = new EncryptPassword();
                String hashPassword = encryptPassword.createHash(password, authUser.getSalt());
                if(hashPassword == null){
                    request.setAttribute("info", "Произошла ошибка шифрования. Обратитесь к разработчику");
                    request.getRequestDispatcher("/showLogin").forward(request, response);
                    break;
                }
                if(!hashPassword.equals(authUser.getPassword())){
                    request.setAttribute("info", "Нет такого пользователя или неправильный пароль");
                    request.getRequestDispatcher("/showLogin").forward(request, response);
                    break;
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("authUser", authUser);
                session.setAttribute("role", userRolesFacade.getTopRole(authUser));
                String info = authUser.getClient().getFirstname()+", здравствуйте!";
                request.setAttribute("info", info);
                response.sendRedirect(request.getHeader("referer"));
                break;
            case "/logout":
                session = request.getSession(false);
                if(session != null){
                    session.invalidate();
                    request.setAttribute("info", "Вы успешно вышли");
                }
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listBooks":
                List<Product> listProducts = productFacade.findAll();
                request.setAttribute("books", listProducts);
                request.getRequestDispatcher("/listProduct.jsp").forward(request, response);
                break;
            case "/showRegistration":
                request.getRequestDispatcher("/showRegistration.jsp").forward(request, response);
                break;
            case "/registration":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                login = request.getParameter("login");
                String password1 = request.getParameter("password1");
                String password2 = request.getParameter("password2");
                if(!password1.equals(password2)){
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("phone", phone);
                    request.setAttribute("login", login);
                    request.setAttribute("info", "Пароли не совпадают!");
                    request.getRequestDispatcher("/showRegistration").forward(request, response);
                    break;
                }
                if("".equals(firstname) || "".equals(lastname)
                      ||  "".equals(phone) 
                        || "".equals(login)
                         ||  "".equals(password1) 
                           || "".equals(password2)){
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("phone", phone);
                    request.setAttribute("login", login);
                    request.setAttribute("info", "Заполните все поля!");
                    request.getRequestDispatcher("/showRegistration").forward(request, response);
                    break;
                }
                User newUser = userFacade.findByLogin(login);
                if(newUser != null){
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("phone", phone);
                    request.setAttribute("login", login);
                    request.setAttribute("info", "Такой пользователь уже зарегистрирован!");
                    request.getRequestDispatcher("/showRegistration").forward(request, response);
                    break; 
                }
                Client client = new Client();
                client.setFirstname(firstname);
                client.setLastname(lastname);
                client.setPhone(phone);
                clientFacade.create(client);
                newUser = new User();
                newUser.setLogin(login);
                encryptPassword = new EncryptPassword();
                newUser.setSalt(encryptPassword.createSalt());
                newUser.setPassword(encryptPassword.createHash(password1, newUser.getSalt()));
                newUser.setClient(client);
                userFacade.create(newUser);
                Role userRole = roleFacade.getRoleForName("READER");
                UserRoles ur = new UserRoles();
                ur.setRole(userRole);
                ur.setUser(newUser);
                userRolesFacade.create(ur);
                request.setAttribute("info", "Пользователь "+newUser.getLogin()+" зарегистрирован!");
                request.getRequestDispatcher("/showLogin").forward(request, response);
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

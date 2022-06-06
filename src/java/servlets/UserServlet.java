
package servlets;

import entity.Product;
import entity.History;
import entity.Client;
import entity.User;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ProductFacade;
import session.HistoryFacade;
import session.ClientFacade;
import session.UserFacade;
import session.UserRolesFacade;
import tools.EncryptPassword;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "UserServlet", urlPatterns = {
    "/editUser", 
    "/updateUser", 
    "/buyProduct",
    
   
    
})
public class UserServlet extends HttpServlet {
    
    @EJB private UserFacade userFacade;
    @EJB private ClientFacade clientFacade;
    @EJB private UserRolesFacade userRolesFacade;
    @EJB private ProductFacade productFacade;
    @EJB private HistoryFacade historyFacade;
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
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "У вас нет прав. Войдите с правами читателя");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        User authUser = (User) session.getAttribute("authUser");
        if(authUser == null){
            request.setAttribute("info", "У вас нет прав. Войдите с правами читателя");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        
        if(!userRolesFacade.isRole("READER", authUser)){
            request.setAttribute("info", "У вас нет прав. Войдите с правами читателя");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            case "/editUser":
                User user = userFacade.find(authUser.getId());
                request.setAttribute("user", user);
                request.getRequestDispatcher("/editUser.jsp").forward(request, response);
                break;
                
            case "/updateUser":
                String userId = request.getParameter("userId");
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                String money = request.getParameter("money");
                
                String password1 = request.getParameter("password1");
                String password2 = request.getParameter("password2");
                if(!password1.equals(password2)){
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("phone", phone);
                    request.setAttribute("money", money);
                    
                    request.setAttribute("info", "Пароли не совпадают!");
                    request.getRequestDispatcher("/showRegistration").forward(request, response);
                    break;
                }
                if("".equals(firstname) || "".equals(lastname)
                      ||  "".equals(phone)){
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("phone", phone);
                    request.setAttribute("money", money);
                    request.setAttribute("info", "Заполните все поля!");
                    request.getRequestDispatcher("/showRegistration").forward(request, response);
                    break;
                }
                user = userFacade.find(Long.parseLong(userId));
                Client client = clientFacade.find(user.getClient().getId());
                client.setFirstname(firstname);
                client.setLastname(lastname);
                client.setPhone(phone);
                client.setMoney(Integer.parseInt(money));
                clientFacade.edit(client);
                if(!"".equals(password1) && !"".equals(password2)){
                    EncryptPassword ep = new EncryptPassword();
                    password1 = ep.createHash(password2, user.getSalt());
                    user.setPassword(password1);
                }
                user.setClient(client);
                userFacade.edit(user);
                session.setAttribute("authUser", user);
                request.setAttribute("user", user);
                request.setAttribute("info", "Изменение данных успешно");
                request.getRequestDispatcher("/editUser.jsp").forward(request, response);
                break;
                
            case "/buyProduct":
                String productId = request.getParameter("productId");
                Product product = productFacade.find(Long.parseLong(productId));
                user = userFacade.find(authUser.getId());
                if(user.getClient().getMoney() == 0){
                    request.setAttribute("info", "Кошелёк пуст");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;
                }
                if(user.getClient().getMoney() < product.getPrice()){
                    request.setAttribute("info", "Для покупки не хватает денег");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;
                }
                client = clientFacade.find(user.getClient().getId());
                client.setMoney(client.getMoney() - product.getPrice());
                clientFacade.edit(client);
                History history = new History();
                history.setProduct(product);
                history.setClient(user.getClient());
                history.setDate(new GregorianCalendar().getTime());
                historyFacade.create(history);
                session.setAttribute("authUser", user);
                request.setAttribute("info", "Покупка совершена успешно");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
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

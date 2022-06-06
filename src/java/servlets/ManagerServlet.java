
package servlets;


import entity.Product;
import entity.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ProductFacade;
import session.UserRolesFacade;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "ManagerServlet", urlPatterns = {
    
    "/addProduct", 
    "/createProduct", 
    "/editProduct", 
    "/updateProduct"
    
})
@MultipartConfig
public class ManagerServlet extends HttpServlet {
    
    @EJB private ProductFacade productFacade;
    @EJB private UserRolesFacade userRolesFacade;
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
            request.setAttribute("info", "У вас нет прав. Войдите с правами менеджера");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        User authUser = (User) session.getAttribute("authUser");
        if(authUser == null){
            request.setAttribute("info", "У вас нет прав. Войдите с правами менеджера");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        
        if(!userRolesFacade.isRole("MANAGER", authUser)){
            request.setAttribute("info", "У вас нет прав. Войдите с правами менеджера");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            
            case "/addProduct":
                request.getRequestDispatcher("/showAddProduct.jsp").forward(request, response);
                break;
            
            case "/createProduct":
                String name = request.getParameter("name");
                String quantity = request.getParameter("quantity");
                String price = request.getParameter("price");
                Product product = new Product();
                product.setName(name);
                product.setQuantity(Integer.parseInt(quantity));
                product.setPrice(Integer.parseInt(price));
                productFacade.create(product);
                request.getRequestDispatcher("/showAddProduct.jsp").forward(request, response);
                break;
                
            case "/editProduct":
                String productId = request.getParameter("productId");
                product = productFacade.find(Long.parseLong(productId));
                request.setAttribute("product", product);
                request.getRequestDispatcher("/editProduct.jsp").forward(request, response);
                break;
            
            case "/updateProduct":
                String newProductId = request.getParameter("productId");
                String newName = request.getParameter("name");
                String newQuantity = request.getParameter("quantity");
                price = request.getParameter("price");
                if("".equals(newProductId) || newProductId == null
                     || "".equals(newName) || newName == null     
                     || "".equals(newQuantity) || newQuantity == null 
                     || "".equals(price)
                        ){
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/editProduct").forward(request, response);
                    break;
                }
                Product editProduct = productFacade.find(Long.parseLong(newProductId));
                editProduct.setName(newName);
                editProduct.setQuantity(Integer.parseInt(newQuantity));
                editProduct.setPrice(Integer.parseInt(price));
                productFacade.edit(editProduct);
                request.getRequestDispatcher("/listProduct").forward(request, response);
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

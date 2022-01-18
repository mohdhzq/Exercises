import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdatePassword
 */
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public UpdatePassword() {
		// TODO Auto-generated constructor stub
	}

	/**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("change_password.html");
		rd.forward(request, response);
	}

	/**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
		String error_message = null;

		/*
		 * get all form data using request.getParameter("parameter name");
		 */
		String user_name = (String) request.getParameter("user_name");
		String password_current = (String) request.getParameter("password_current");
		String password_new = (String) request.getParameter("password_new");
		String password_confirm = (String) request.getParameter("password_confirm");

		UserOperation operation = new UserOperation();

		if (!password_new.equals(password_confirm)) {
			error_message = "New Password and Confirm Password Does Not Match";
		}

		else {
			try {
        			String oldPassword = operation.getOldPassword(user_name);
				
				
				if(oldPassword == null) {
					error_message =  user_name+" not found in databse";
				}
				else if (!oldPassword.equals(password_current)) {
					error_message = "Your Password does not match with database";
				}
				else {
					operation.setNewPassword(user_name, password_confirm);
					String message = "Hello, " + user_name + ", your password has been updated";

					response.setContentType("text/html");
					PrintWriter out = response.getWriter();

					out.println("<html>");
					out.println("<head>");
					out.println("<title>Password Change Success</title>");
					out.println("</head>");
					out.println("<body bgcolor=\"white\">");
					out.println("<h1 style=\"color: green\">" + message + "<h1>");
					out.println("</body>");
					out.println("</html>");
				}

			} catch (ClassNotFoundException | SQLException e) {
                            if (error_message != null) {
                                response.setContentType("text/html");
                                PrintWriter out = response.getWriter();

                                out.println("<html>");
                                out.println("<head>");
                                out.println("<title>Error</title>");
                                out.println("</head>");
                                out.println("<body bgcolor=\"white\">");
                                out.println("<h1 style=\"color: red\">" + error_message + "<h1>");
                                out.println("<a href = 'http://localhost:8080/PasswordChange/UpdatePassword'> Try Again</a>");
                                out.println("</body>");
                                out.println("</html>");
                            }
			}
		}

		
	}

}
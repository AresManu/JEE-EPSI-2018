package fr.epsi.myEpsi.servlet;

import java.io.Console;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import fr.epsi.myEpsi.Constantes;
import fr.epsi.myEpsi.beans.Utilisateur;
import fr.epsi.myEpsi.listeners.StartupListener;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(StartupListener.class);
	private static final long serialVersionUID = 1L;
	private List<String> messages = new ArrayList<String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String pseudo = request.getParameter("pseudo");
		String pwd = request.getParameter("pwd");
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(pseudo);
		request.getSession().setAttribute(Constantes.PARAM_UTILISATEURS, utilisateur);
		//-------------------------------------ma cuisine----------------------------
		messages.clear();
		logger.error("Test de connexion CUISINE");
    	try {
    		Class.forName("org.hsqldb.jdbcDriver");
    		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
    		logger.error("Connexion ok à la cuisine");
        	//con.close();
    		ResultSet résultats = null;
            String requete = "SELECT * FROM UTILISATEURS WHERE ID = ? AND PASSWORD = ?";
            try {            	
            	PreparedStatement pt = con.prepareStatement(requete);
            	pt.setString(1, pseudo);
            	pt.setString(2, pwd);
            	résultats = pt.executeQuery();
            	
                while ( résultats.next() ) {
                String idUtilisateur = résultats.getString( "id" );
                String nomUtilisateur = résultats.getString( "name" );
                Integer adminUtilisateur = résultats.getInt( "isadministrator" );
                String pwdUtilisateur = résultats.getString( "password" );
                messages.add(" |Id: "+idUtilisateur+
                			 " |Nom: "+nomUtilisateur+
                			 " |Admin: "+adminUtilisateur+
                			 " |Mot de passe: "+pwdUtilisateur );
                }
                logger.error("REUSSITE de la requete");
                for  (int i = 0; i < messages.size(); i++) {
                	logger.error(messages.get(i));	
                }
            } catch (SQLException e) {
           	 logger.error("ECHEC de la requete");
            }   	
    	} catch (ClassNotFoundException | SQLException e){
    		logger.error("Connexion impossible à la cuisine" + e.getMessage());
    	}

    	
		if(pwd != "" && pseudo != "")	{			
			request.getRequestDispatcher("accueil.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("login.html").forward(request, response);
		}		
		//-------------------------------------ma cuisine FIN----------------------------
		

	}

}

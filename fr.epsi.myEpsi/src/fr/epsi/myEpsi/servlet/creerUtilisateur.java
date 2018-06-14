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
import fr.epsi.myEpsi.beans.Annonce;
import fr.epsi.myEpsi.beans.Utilisateur;
import fr.epsi.myEpsi.listeners.StartupListener;

/**
 * Servlet implementation class login
 */
@WebServlet("/creerUtilisateur")
public class creerUtilisateur extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(StartupListener.class);
	private static final long serialVersionUID = 1L;
	private int message = 0;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public creerUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		logger.error("Accès à la page de création d'utilisateur");
		request.getRequestDispatcher("creerutilisateur.html").forward(request, response);
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		logger.error("Ajout d'un utilisateur");	
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		
		
		//-------------------------------------ma cuisine----------------------------
		
		logger.error("Test de connexion à la bdd");
    	try {
    		Class.forName("org.hsqldb.jdbcDriver");
    		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
    		logger.error("Connexion ok à la bdd");
        	//con.close();
    		ResultSet résultats = null;
            String requete = "INSERT INTO UTILISATEURS (ID,PASSWORD,NAME) VALUES (?,?,?);";
            try {
            	logger.error("id : "+id);
            	
            	PreparedStatement pt = con.prepareStatement(requete);
            	pt.setObject(1,id, Types.VARCHAR);
            	pt.setObject(2,password, Types.VARCHAR); 
            	pt.setObject(3,nom,Types.VARCHAR); 
            	pt.executeUpdate();             	
                logger.error("REUSSITE de l'insert"); 
                con.commit();
                
            } catch (SQLException e) {
           	 logger.error("ECHEC de l'insert",e);
            }   	
    	} catch (ClassNotFoundException | SQLException e){
    		logger.error("Connexion impossible à la bdd" + e.getMessage());
    	}

    
		
	}

}


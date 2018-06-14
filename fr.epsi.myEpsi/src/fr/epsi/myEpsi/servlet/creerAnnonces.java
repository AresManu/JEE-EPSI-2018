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
@WebServlet("/creerAnnonces")
public class creerAnnonces extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(StartupListener.class);
	private static final long serialVersionUID = 1L;
	private int message = 0;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public creerAnnonces() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		logger.error("Accès à la page de création d'annonces");
		request.getRequestDispatcher("creerannonce.html").forward(request, response);
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		logger.error("Ajout d'une annonce");	
		String titre = request.getParameter("titre");
		String description = request.getParameter("description");
		int id=0;
		
		//-------------------------------------ma cuisine----------------------------
		message=0;
		logger.error("Test de connexion à la bdd");
    	try {
    		Class.forName("org.hsqldb.jdbcDriver");
    		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
    		logger.error("Connexion ok à la bdd");
        	//con.close();
    		String requete1 = "SELECT MAX(ID) FROM ANNONCES";
    		ResultSet résultats = null;
            String requete = "INSERT INTO ANNONCES (ID,TITLE, CONTENT) VALUES (?,?,?);";
            try {
            	logger.error("id : "+id);
            	PreparedStatement pt1 = con.prepareStatement(requete1);
            	résultats = pt1.executeQuery();
            	while (résultats.next() ) {
                    int idUtilisateur = résultats.getInt(1);          
                    message=idUtilisateur;
                    }
            	id=message;
            	logger.error("id trouvée: "+id);
            	PreparedStatement pt = con.prepareStatement(requete);
            	pt.setObject(1,id+1, Types.BIGINT);
            	pt.setObject(2,titre, Types.VARCHAR); 
            	pt.setObject(3,description,Types.VARCHAR); 
            	pt.executeUpdate();             	
                logger.error("REUSSITE de l'insert"); 
                
            } catch (SQLException e) {
           	 logger.error("ECHEC de l'insert",e);
            }   	
    	} catch (ClassNotFoundException | SQLException e){
    		logger.error("Connexion impossible à la bdd" + e.getMessage());
    	}

    
		
	}

}


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
import fr.epsi.myEpsi.listeners.StartupListener;

/**
 * Servlet implementation class login
 */
@WebServlet("/listeAnnonces")
public class listeAnnonces extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(StartupListener.class);
	private static final long serialVersionUID = 1L;
	private List<Annonce> messages = new ArrayList<Annonce>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listeAnnonces() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		logger.error("Accès à la page des annonces");
		try {
    		Class.forName("org.hsqldb.jdbcDriver");
    		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
    		logger.error("Connexion ok à la BDD");
        	//con.close();
    		ResultSet résultats = null;
    		messages.clear();
            String requete = "SELECT * FROM ANNONCES";
            try {            	
            	PreparedStatement pt = con.prepareStatement(requete);
            	résultats = pt.executeQuery(); 
            	
                while ( résultats.next() ) {
                	
                String idAnnonce = résultats.getString( "id" );
                String titreAnnonce = résultats.getString( "title" );
                String contentAnnonce = résultats.getString( "content" );
                //Integer useridAnnonce = résultats.getInt( "user_id" );
                //messages.add(" |Id: "+idAnnonce+
                //			 " |Titre: "+titreAnnonce+
                //			 " |Contenu: "+contentAnnonce+
                //			 " |utilisateur: "/**+useridAnnonce**/);
                messages.add(new Annonce(idAnnonce, titreAnnonce, contentAnnonce));
                
                }
                logger.error("REUSSITE de la requete");
                for  (int i = 0; i < messages.size(); i++) {
                	/**logger.error(messages.get(i));**/	
                }
            } catch (SQLException e) {
           	 logger.error("ECHEC de la requete");
            }   	
    	} catch (ClassNotFoundException | SQLException e){
    		logger.error("Connexion impossible à la BDD" + e.getMessage());
    	}	
			request.setAttribute("result",messages);
			request.getRequestDispatcher("listeAnnonces.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		logger.error("test psot");
		
	}

}


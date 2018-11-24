package modele.consulter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import classes.FicheFrais;
import classes.Visiteur;
import panel.Vue;
import classes.Visiteur;

public class ModeleConsulter {

	//attribut priv�
	private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement statement;
	
private static Connection connexion ;
	
	//m�thode publiques statiques connexion
	public static void connexionBD() {
		Connection connexion = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connexion = DriverManager.getConnection("jdbc:mysql://172.16.203.100/2018foulley", "tfoulley", "123456");
			connexion = DriverManager.getConnection("jdbc:mysql://localhost/gsbv2", "root", "");
			st = connexion.createStatement();
		} 
		catch (ClassNotFoundException erreur) {
			System.out.println("Driver non charg�!" + erreur);
		}
		catch (SQLException erreur){
			System.out.println("La connexion � la base de donn�es a �chou� ou Erreur SQL" + erreur);
		}
	}
	
	
	//deconnexion
	public static void deconnexionBD() {
		try {
			connexion.close();
			//System.out.println("Deconnexion r�ussi");
		} catch (SQLException erreur) {
			// TODO Auto-generated catch block
			erreur.printStackTrace();
		} 
	}
	
	
	//connexionSession
	public static boolean connexionSession(String mdp, char [] unMdp ,String login, Vue uneVue){
		boolean result = false;
		connexionBD();
		String sql;
		sql = "Select mdp From visiteur Where login ='" + login + " ' ";
		Statement st;
		ResultSet rs;
		String strMdp = "";
		for (int i = 0; i < mdp.length() ; i = i + 1){
			strMdp = strMdp + unMdp[i];
		}
		if(mdp == strMdp){
			result = true;
		}
		try {
			st = connexion.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//getLesFicheFraisValider � DEPLACER DANS UN AUTRE MODELE
	/*public static ArrayList<FicheFrais> getLesFichesFraisValider(){
		ArrayList<FicheFrais> lesFichesFraisValider = new ArrayList<FicheFrais>();
		connexionBD();
		// variables
		String mois;
		float montantValider;
		Date dateModif;
		
		try{
			st = connexion.createStatement();
			String req ="SELECT * FROM fichefrais";
			rs = st.executeQuery(req);
			
			while(rs.next()//vrai){
				mois= rs.getString("mois");
				montantValider=rs.getFloat("montantValide");
				dateModif=rs.getDate("dateModif");
				
				lesFichesFraisValider.add(new FicheFrais(mois, montantValider, dateModif));
				System.out.println("Code = " + num + ", Nom = " + nom + ", Pr�nom = " + prenom + ", Email = " + email + ", Commentaire = " + commentaire);
			}
			rs.close();
		}
		catch(SQLException erreur){
			System.out.println("Erreur dans l'execution de la requete " + erreur);
		}
		return lesFichesFraisValider;
		
	}*/
	
	
/* ******************************************************************************************************** */
	// R�cup�ration des fiche de frais � l'�tat valider 'VA'. Cette m�thode retourne une LISTE
/* ******************************************************************************************************** */	
	public static ArrayList<FicheFrais> getLesFichesFraisValider(){
		ArrayList<FicheFrais> lesFichesFraisValider = new ArrayList<FicheFrais>();
		//connexioin a la base de donn�es
		connexionBD();
		// variables
		String nom;
		String prenom;
		String mois;
		float montantValide;
		Date dateModif;
		int nbJustificatif;
		
		try{
			st = connexion.createStatement();
			String req ="SELECT nom, prenom, mois, montantValide, dateModif, nbJustificatifs "
					+"FROM visiteur V, fichefrais F, fraisforfait FF, etat E"
					+"WHERE V.id = F.idVisiteur AND E.id = F.idEtat"
					+"AND F.idEtat = 'VA'";
			statement = connexion.prepareStatement(req);
			rs = statement.executeQuery();
			
			while(rs.next()/*vrai*/){
				
				nom = rs.getString("nom");
				prenom = rs.getString("prenom");
				mois = rs.getString("mois");
				montantValide = rs.getFloat("montantValide");
				dateModif = rs.getDate("dateModif");
				nbJustificatif = rs.getInt("nbJustificatif");
				lesFichesFraisValider.add(new FicheFrais(nom, prenom, mois, montantValide, dateModif, nbJustificatif));
				//System.out.println("Code = " + num + ", Nom = " + nom + ", Pr�nom = " + prenom + ", Email = " + email + ", Commentaire = " + commentaire);
			}
			rs.close();
		}
		catch(SQLException erreur){
			//Requete fausse ou mal passer
			System.out.println("Erreur dans l'execution de la requete " + erreur);
		}
		return lesFichesFraisValider;
		
	}	
	
}
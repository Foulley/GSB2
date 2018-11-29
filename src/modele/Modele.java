package modele;

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

import java.security.MessageDigest;
import java.math.BigInteger;

public class Modele {

	//attribut privé
	private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement statement;
	
private static Connection connexion ;
	
	//méthode publiques statiques connexion
	public static void connexionBD() {
		Connection connexion = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://172.16.203.100/2018foulley", "tfoulley", "123456");
	//		connexion = DriverManager.getConnection("jdbc:mysql://localhost/bdd", "root", "");
			st = connexion.createStatement();
		} 
		catch (ClassNotFoundException erreur) {
			System.out.println("Driver non chargé!" + erreur);
		}
		catch (SQLException erreur){
			System.out.println("La connexion à la base de données a échoué ou Erreur SQL" + erreur);
		}
	}
	
	
	//deconnexion
	public static void deconnexionBD() {
		try {
			connexion.close();
			//System.out.println("Deconnexion réussi");
		} catch (SQLException erreur) {
			// TODO Auto-generated catch block
			erreur.printStackTrace();
		} 
	}
	
	
	//sha1 décrypter mdp
	public static String decrypterMdp (String mdp) {
		String sha1 = "";
		try {
			MessageDigest cryptage = MessageDigest.getInstance("SHA-1");
			cryptage.reset();
			cryptage.update(mdp.getBytes("utf8"));
			sha1 = String.format("%040x", new BigInteger(1, cryptage.digest()));
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return sha1;
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
		for (int i = 0; i < mdp.length(); i = i + 1){
			strMdp = strMdp + unMdp[i];
		}
		if(mdp == strMdp){
			decrypterMdp(strMdp);
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
	
}
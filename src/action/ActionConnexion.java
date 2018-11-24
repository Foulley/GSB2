package action;

import panel.Panel_Connecter;
import panel.Vue;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JLabel;

import controleurs.Menu;

public class ActionConnexion implements ActionListener{

	/* ATTRIBUTS PRIVEES */
	private panel.Vue vue;
	private JLabel lblErreur;
	/* CONSTRUCTEURS */
	public ActionConnexion(Vue uneVue, JLabel lblErreur){
		this.vue = uneVue;
		this.lblErreur = lblErreur;
	}
	
	/* ACTION */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		this.lblErreur.setText("Erreur Identifiant non valide");
		this.lblErreur.setForeground(Color.WHITE);
		this.lblErreur.setBackground(Color.RED);
		this.lblErreur.setOpaque(true);
		
		this.vue.remove(this.vue.getContentPane());
		
		this.vue.setContentPane(new Panel_Connecter(this.vue));
		
		this.vue.setJMenuBar(new Menu(this.vue));
		
		this.vue.revalidate();
		
	}

}

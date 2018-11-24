package action.consulter;

import MenuBar;
import PanelConsultation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import modele.consulter.ModeleConsulter;

import classes.FicheFrais;
import controleurs.Menu;
import panel.Panel_Connecter;
import panel.Vue;
import panel.consulter.Panel_Fiche_Valider;

public class ActionConsulterFicheValide implements ActionListener{

	/* ATTRIBUTS PRIVEES */
	private panel.Vue vue;
	private ArrayList<FicheFrais> lesFichesFraisValider;
	
	/* CONSTRUCTEURS */
	public ActionConsulterFicheValide(Vue uneVue, ArrayList<FicheFrais> lesFichesFraisValider){
		this.vue = uneVue;
		this.lesFichesFraisValider = new ArrayList<FicheFrais>();
	}

	
	public void actionPerformed(ActionEvent e) {
		//Recupere la liste au travers du modele 
		this.lesFichesFraisValider = ModeleConsulter.getLesFichesFraisValider();
		
		this.vue.remove(this.vue.getContentPane());
		this.vue.setContentPane(new Panel_Fiche_Valider(this.lesFichesFraisValider));
		this.vue.setJMenuBar(new MenuBar(this.vue));
		this.vue.revalidate();		
		
	}
}
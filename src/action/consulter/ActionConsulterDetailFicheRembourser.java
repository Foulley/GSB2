package action.consulter;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import panel.Vue;
import modele.consulter.ModeleConsulter;
import classes.FicheFrais;
import controleurs.Menu;

public class ActionConsulterDetailFicheRembourser implements ActionListener{

	/* ATTRIBUTS PRIVEES */
	private panel.Vue vue;
	private int ligne;
	
	/* CONSTRUCTEURS */
	public ActionConsulterDetailFicheRembourser(Vue uneVue, int ligne){
		this.vue = uneVue;
		this.ligne = ligne;
		//r�cup�ration indice tableau (cr�er un nv tableau pour r�cup�ration)
	}

	
	public void actionPerformed(ActionEvent e) {
		
//		Vue uneVue = new Vue();	
		System.out.println(this.ligne);
		
	}
}

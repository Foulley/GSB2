package action.validation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import panel.Vue;
import panel.recherche.Panel_Remboursement_Recherche;
import panel.recherche.Panel_Validation_Recherche;
import controleurs.Menu;

public class ActionRembourserLaFiche implements ActionListener{

	//Attributs
		//JLabel
	private JLabel lblMessage;
		//Vue
	private Vue vue;
	
	//Constructeur
	public ActionRembourserLaFiche(Vue vue ,JLabel lblMsg){
		this.lblMessage = lblMsg;
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Action btn valider
		this.lblMessage.setText("Remboursť");
		this.lblMessage.setForeground(Color.WHITE);
		this.lblMessage.setBackground(Color.BLUE);
		this.lblMessage.setOpaque(true);
		
		//Enlever le panel
		this.vue.remove(this.vue.getContentPane());
		
		//Ajouter de la recherche
		this.vue.setContentPane(new Panel_Remboursement_Recherche(this.vue, this.lblMessage));
		
		//revalidation
		this.vue.revalidate();
	}
}

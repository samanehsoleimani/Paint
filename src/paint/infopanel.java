package paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class infopanel extends JPanel {
    private JLabel Infos[];

    
    public infopanel() {
        super();
        setOpaque(true);
        setBackground(new Color(200, 233, 243));
        setPreferredSize(new Dimension(150, main.gHeight - 20));
        this.setLayout(new FlowLayout());
        
        Infos = new JLabel[10];
        for(int i=0;i<10;i++) {
        	Infos[i]=new JLabel();
        	Infos[i].setOpaque(true);
        	Infos[i].setFont(new Font("tahoma", 1, 12));
        	
        	Infos[i].setPreferredSize(new Dimension(120, 30));
        	Infos[i].setBackground( new Color(16, 121, 167));
        
        this.add(Infos[i]);
    }}
    
    
    public void setInfo(String s,int i) {
    	Infos[i].setText(s);
    }}
package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.*;

import comp401.sushi.*;
import sushigame.model.*;

public class PlateView extends JPanel {
	private JLabel cLabel;
	private JLabel sLabel;
	private JLabel chLabel;
	private JLabel aLabel;
	private JFrame frame;
	private JPanel panel;

	public PlateView(Belt b, Plate p, int position) {
		cLabel = new JLabel();
		sLabel = new JLabel();
		chLabel = new JLabel();
		aLabel = new JLabel();
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		cLabel.setText("Plate Color: " + p.getColor().toString());
		switch (p.getColor()) {
		case RED:
			cLabel.setOpaque(true);
			cLabel.setBackground(Color.RED);
			panel.setBackground(Color.RED);
			break;
		case GREEN:
			cLabel.setOpaque(true);
			cLabel.setBackground(Color.GREEN);
			panel.setBackground(Color.GREEN);
			break;
		case BLUE:
			cLabel.setOpaque(true);
			cLabel.setBackground(Color.BLUE);
			panel.setBackground(Color.BLUE);
			break;
		case GOLD:
			cLabel.setOpaque(true);
			cLabel.setBackground(Color.YELLOW);
			panel.setBackground(Color.YELLOW);
			break;
		}
		String placeHolder = "";
		if (p.getContents() instanceof Roll) {
			placeHolder = "Roll";
			
			panel.add(new JLabel("Ingredients: "));
			String[] ing = new String[p.getContents().getIngredients().length];
			IngredientPortion[] portions = p.getContents().getIngredients();
			for (int i = 0; i < ing.length; i++) {
				ing[i] = portions[i].getName() + ": " + (portions[i].getAmount())+ " oz";
				panel.add(new JLabel(ing[i]));
			}
		} else if (p.getContents() instanceof Sashimi) {
			placeHolder = "Sashimi";
			panel.add(new JLabel(p.getContents().getName()));
			
		} else if (p.getContents() instanceof Nigiri) {
			placeHolder = "Nigiri";
			panel.add(new JLabel(p.getContents().getName()));
		}
		sLabel.setText("Sushi type: " + placeHolder);

		sLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame = new JFrame();
				frame.setContentPane(panel);
				frame.add(cLabel);
				frame.add(sLabel);
				frame.add(aLabel);
				frame.add(chLabel);
				frame.pack();
				frame.setVisible(true);
			}
		});

		chLabel.setText("Chef: " + p.getChef().getName());
		aLabel.setText("Plate age: " + b.getAgeOfPlateAtPosition(position));
		add(sLabel);
		add(new JLabel (" Click on 'sushi type' for more info"));
	}
}

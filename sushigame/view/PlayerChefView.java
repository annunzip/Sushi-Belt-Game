package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import comp401.sushi.AvocadoPortion;
import comp401.sushi.CrabPortion;
import comp401.sushi.EelPortion;
import comp401.sushi.IngredientPortion;
import comp401.sushi.Nigiri;
import comp401.sushi.Nigiri.NigiriType;
import comp401.sushi.Plate;
import comp401.sushi.RedPlate;
import comp401.sushi.RicePortion;
import comp401.sushi.Roll;
import comp401.sushi.SalmonPortion;
import comp401.sushi.Sashimi;
import comp401.sushi.Sashimi.SashimiType;
import comp401.sushi.SeaweedPortion;
import comp401.sushi.ShrimpPortion;
import comp401.sushi.Sushi;
import comp401.sushi.TunaPortion;

import javax.swing.*;
public class PlayerChefView extends JPanel implements ActionListener {

	private List<ChefViewListener> listeners;
	private Sushi kmp_roll;
	private Sushi crab_sashimi;
	private Sushi eel_nigiri;
	private int belt_size;
	private JComboBox plateColor, fish;
	private JSlider platePick, gPrice, avocadoS, tunaS, salmonS, seaweedS, riceS, crabS, eelS, shrimpS;
	private ArrayList<IngredientPortion> rollIng;
	private IngredientPortion[] rollIng2;

	public PlayerChefView(int belt_size) {
		this.belt_size = belt_size;
		rollIng = new ArrayList<IngredientPortion>();
		listeners = new ArrayList<ChefViewListener>();
		JLabel top = new JLabel();
		top.setText("Plate Color:");
		add(top);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		plateColor = new JComboBox();
		plateColor.addItem("Red");
		plateColor.addItem("Blue");
		plateColor.addItem("Green");
		plateColor.addItem("Gold");
		plateColor.setMaximumSize(new Dimension(100,20));
		add(plateColor);

		JLabel plateNum = new JLabel();
		plateNum.setText("Belt position:");
		add(plateNum);

		platePick = new JSlider(JSlider.HORIZONTAL, 0, 19, 0);
		platePick.setMajorTickSpacing(1);
		platePick.setPaintTicks(true);
		platePick.setPaintLabels(true);
		platePick.setLabelTable(platePick.createStandardLabels(1));
		add(platePick, BorderLayout.CENTER);

		JLabel goldPrice = new JLabel();
		goldPrice.setText("Gold Plate Price: (All Values are Divided by 10)");
		add(goldPrice);
		gPrice = new JSlider(JSlider.HORIZONTAL, 50, 100, 50);
		gPrice.setMinorTickSpacing(1);
		gPrice.setMajorTickSpacing(10);
		gPrice.setPaintTicks(true);
		gPrice.setPaintLabels(true);
		gPrice.setLabelTable(gPrice.createStandardLabels(10));
		add(gPrice, BorderLayout.CENTER);

		JLabel fishType = new JLabel();
		fishType.setText("Type of Sushi: ");
		add(fishType);
		fish = new JComboBox();
		fish.addItem("Tuna");
		fish.addItem("Salmon");
		fish.addItem("Eel");
		fish.addItem("Crab");
		fish.addItem("Shrimp");
		fish.setMaximumSize(new Dimension(100,20));
		add(fish);

		JButton nigiri = new JButton("Make your nigiri");
		nigiri.setActionCommand("nigiri");
		nigiri.addActionListener(this);
		add(nigiri);

		JButton sashimi = new JButton("Make your sashimi");
		sashimi.setActionCommand("sashimi");
		sashimi.addActionListener(this);
		add(sashimi);

		JLabel rollTime = new JLabel();
		rollTime.setText("Custom Roll: (All portions on sliders divided by 10)");
		add(rollTime);

		JLabel avocadoP = new JLabel();
		avocadoP.setText("Avocado Portion: ");
		add(avocadoP);
		avocadoS = new JSlider(JSlider.HORIZONTAL, 0, 15, 0);
		avocadoS.setMinorTickSpacing(1);
		avocadoS.setMajorTickSpacing(5);
		avocadoS.setPaintTicks(true);
		avocadoS.setPaintLabels(true);
		avocadoS.setLabelTable(avocadoS.createStandardLabels(3));
		add(avocadoS, BorderLayout.CENTER);

		JLabel crabP = new JLabel();
		crabP.setText("Crab Portion:");
		add(crabP);
		crabS = new JSlider(JSlider.HORIZONTAL, 0, 15, 0);
		crabS.setMinorTickSpacing(1);
		crabS.setMajorTickSpacing(5);
		crabS.setPaintTicks(true);
		crabS.setPaintLabels(true);
		crabS.setLabelTable(crabS.createStandardLabels(3));
		add(crabS, BorderLayout.CENTER);

		JLabel eelP = new JLabel();
		eelP.setText("Eel Portion:");
		add(eelP);
		eelS = new JSlider(JSlider.HORIZONTAL, 0, 15, 0);
		eelS.setMinorTickSpacing(1);
		eelS.setMajorTickSpacing(5);
		eelS.setPaintTicks(true);
		eelS.setPaintLabels(true);
		eelS.setLabelTable(eelS.createStandardLabels(3));
		add(eelS, BorderLayout.CENTER);

		JLabel riceP = new JLabel();
		riceP.setText("Rice Portion:");
		add(riceP);
		riceS = new JSlider(JSlider.HORIZONTAL, 0, 15, 0);
		riceS.setMinorTickSpacing(1);
		riceS.setMajorTickSpacing(5);
		riceS.setPaintTicks(true);
		riceS.setPaintLabels(true);
		riceS.setLabelTable(riceS.createStandardLabels(3));
		add(riceS, BorderLayout.CENTER);

		JLabel salmonP = new JLabel();
		salmonP.setText("Salmon Portion:");
		add(salmonP);
		salmonS = new JSlider(JSlider.HORIZONTAL, 0, 15, 0);
		salmonS.setMinorTickSpacing(1);
		salmonS.setMajorTickSpacing(5);
		salmonS.setPaintTicks(true);
		salmonS.setPaintLabels(true);
		salmonS.setLabelTable(salmonS.createStandardLabels(3));
		add(salmonS, BorderLayout.CENTER);

		JLabel seaweedP = new JLabel();
		seaweedP.setText("Seaweed Portion:");
		add(seaweedP);
		seaweedS = new JSlider(JSlider.HORIZONTAL, 0, 15, 0);
		seaweedS.setMinorTickSpacing(1);
		seaweedS.setMajorTickSpacing(5);
		seaweedS.setPaintTicks(true);
		seaweedS.setPaintLabels(true);
		seaweedS.setLabelTable(seaweedS.createStandardLabels(3));
		add(seaweedS, BorderLayout.CENTER);

		JLabel shrimpP = new JLabel();
		shrimpP.setText("Shrimp Portion:");
		add(shrimpP);
		shrimpS = new JSlider(JSlider.HORIZONTAL, 0, 15, 0);
		shrimpS.setMinorTickSpacing(1);
		shrimpS.setMajorTickSpacing(5);
		shrimpS.setPaintTicks(true);
		shrimpS.setPaintLabels(true);
		shrimpS.setLabelTable(shrimpS.createStandardLabels(3));
		add(shrimpS, BorderLayout.CENTER);

		JLabel tunaP = new JLabel();
		tunaP.setText("Tuna Portion:");
		add(tunaP);
		tunaS = new JSlider(JSlider.HORIZONTAL, 0, 15, 0);
		tunaS.setMinorTickSpacing(1);
		tunaS.setMajorTickSpacing(5);
		tunaS.setPaintTicks(true);
		tunaS.setPaintLabels(true);
		tunaS.setLabelTable(tunaS.createStandardLabels(3));
		add(tunaS, BorderLayout.CENTER);

		JButton rollButton = new JButton("Make your roll");
		rollButton.setActionCommand("roll");
		rollButton.addActionListener(this);
		add(rollButton);





		/*JButton sashimi_button = new JButton("Make red plate of crab sashimi at position 3");
		sashimi_button.setActionCommand("red_crab_sashimi_at_3");
		sashimi_button.addActionListener(this);
		add(sashimi_button);

		JButton nigiri_button = new JButton("Make blue plate of eel nigiri at position 8");
		nigiri_button.setActionCommand("blue_eel_nigiri_at_8");
		nigiri_button.addActionListener(this);
		add(nigiri_button);

		JButton roll_button = new JButton("Make gold plate of KMP roll at position 5");
		roll_button.setActionCommand("gold_kmp_roll_at_5");
		roll_button.addActionListener(this);
		add(roll_button); 

		kmp_roll = new Roll("KMP Roll", new IngredientPortion[] {new EelPortion(1.0), new AvocadoPortion(0.5), new SeaweedPortion(0.2)});
		crab_sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
		eel_nigiri = new Nigiri(Nigiri.NigiriType.EEL); */
	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}

	@Override
	// Ensures that there is a case for sashimi, nigiri and rolls, and makes a plate dependent on plate color, and other options chosen in sliders and drop boxes above
	public void actionPerformed(ActionEvent e) {
		double avocado = avocadoS.getValue() / 10.0;
		double crab = crabS.getValue() / 10.0;
		double eel = eelS.getValue() / 10.0;
		double rice = riceS.getValue() / 10.0;
		double salmon = salmonS.getValue() / 10.0;
		double seaweed = seaweedS.getValue() / 10.0;
		double shrimp = shrimpS.getValue() / 10.0;
		double tuna = tunaS.getValue() / 10.0;

		if (avocado > 0) {
			rollIng.add(new AvocadoPortion(avocado));
		}
		if (crab > 0) {
			rollIng.add(new CrabPortion(crab));
		}
		if (eel > 0) {
			rollIng.add(new EelPortion(eel));
		}
		if (rice > 0) {
			rollIng.add(new RicePortion(rice));
		}
		if (salmon > 0) {
			rollIng.add(new SalmonPortion(salmon));
		}
		if (seaweed > 0) {
			rollIng.add(new SeaweedPortion(seaweed));
		}
		if (shrimp > 0) {
			rollIng.add(new ShrimpPortion(shrimp));
		}
		if (tuna > 0) {
			rollIng.add(new TunaPortion(tuna));
		}
		switch (e.getActionCommand()) {
		case "sashimi":
			switch(plateColor.getSelectedItem().toString()){
			case "Red":
				switch(fish.getSelectedItem().toString())
				{
				case "Tuna":
					makeRedPlateRequest(new Sashimi(SashimiType.TUNA), platePick.getValue());
					break;
				case "Salmon":
					makeRedPlateRequest(new Sashimi(SashimiType.SALMON), platePick.getValue());
					break;
				case "Eel":
					makeRedPlateRequest(new Sashimi(SashimiType.EEL), platePick.getValue());
					break;
				case "Crab":
					makeRedPlateRequest(new Sashimi(SashimiType.CRAB), platePick.getValue());
					break;
				case "Shrimp":
					makeRedPlateRequest(new Sashimi(SashimiType.SHRIMP), platePick.getValue());
					break;
				}
				break;
			case "Blue":
				switch(fish.getSelectedItem().toString())
				{
				case "Tuna":
					makeBluePlateRequest(new Sashimi(SashimiType.TUNA), platePick.getValue());
					break;
				case "Salmon":
					makeBluePlateRequest(new Sashimi(SashimiType.SALMON), platePick.getValue());
					break;
				case "Eel":
					makeBluePlateRequest(new Sashimi(SashimiType.EEL), platePick.getValue());
					break;
				case "Crab":
					makeBluePlateRequest(new Sashimi(SashimiType.CRAB), platePick.getValue());
					break;
				case "Shrimp":
					makeBluePlateRequest(new Sashimi(SashimiType.SHRIMP), platePick.getValue());
					break;
				}
				break;
			case "Green":
				switch(fish.getSelectedItem().toString())
				{
				case "Tuna":
					makeGreenPlateRequest(new Sashimi(SashimiType.TUNA), platePick.getValue());
					break;
				case "Salmon":
					makeGreenPlateRequest(new Sashimi(SashimiType.SALMON), platePick.getValue());
					break;
				case "Eel":
					makeGreenPlateRequest(new Sashimi(SashimiType.EEL), platePick.getValue());
					break;
				case "Crab":
					makeGreenPlateRequest(new Sashimi(SashimiType.CRAB), platePick.getValue());
					break;
				case "Shrimp":
					makeGreenPlateRequest(new Sashimi(SashimiType.SHRIMP), platePick.getValue());
					break;
				}
				break;
			case "Gold":
				switch(fish.getSelectedItem().toString())
				{
				case "Tuna":
					makeGoldPlateRequest(new Sashimi(SashimiType.TUNA), platePick.getValue(), gPrice.getValue());
					break;
				case "Salmon":
					makeGoldPlateRequest(new Sashimi(SashimiType.SALMON), platePick.getValue(), gPrice.getValue());
					break;
				case "Eel":
					makeGoldPlateRequest(new Sashimi(SashimiType.EEL), platePick.getValue(), gPrice.getValue());
					break;
				case "Crab":
					makeGoldPlateRequest(new Sashimi(SashimiType.CRAB), platePick.getValue(), gPrice.getValue());
					break;
				case "Shrimp":
					makeGoldPlateRequest(new Sashimi(SashimiType.SHRIMP), platePick.getValue(), gPrice.getValue());
					break;
				}
				break;
			}
			break;
		case "nigiri":
			switch(plateColor.getSelectedItem().toString()){
			case "Red":
				switch(fish.getSelectedItem().toString())
				{
				case "Tuna":
					makeRedPlateRequest(new Nigiri(NigiriType.TUNA), platePick.getValue());
					break;
				case "Salmon":
					makeRedPlateRequest(new Nigiri(NigiriType.SALMON), platePick.getValue());
					break;
				case "Eel":
					makeRedPlateRequest(new Nigiri(NigiriType.EEL), platePick.getValue());
					break;
				case "Crab":
					makeRedPlateRequest(new Nigiri(NigiriType.CRAB), platePick.getValue());
					break;
				case "Shrimp":
					makeRedPlateRequest(new Nigiri(NigiriType.SHRIMP), platePick.getValue());
					break;
				}
				break;
			case "Blue":
				switch(fish.getSelectedItem().toString())
				{
				case "Tuna":
					makeBluePlateRequest(new Nigiri(NigiriType.TUNA), platePick.getValue());
					break;
				case "Salmon":
					makeBluePlateRequest(new Nigiri(NigiriType.SALMON), platePick.getValue());
					break;
				case "Eel":
					makeBluePlateRequest(new Nigiri(NigiriType.EEL), platePick.getValue());
					break;
				case "Crab":
					makeBluePlateRequest(new Nigiri(NigiriType.CRAB), platePick.getValue());
					break;
				case "Shrimp":
					makeBluePlateRequest(new Nigiri(NigiriType.SHRIMP), platePick.getValue());
					break;
				}
				break;
			case "Green":
				switch(fish.getSelectedItem().toString())
				{
				case "Tuna":
					makeGreenPlateRequest(new Nigiri(NigiriType.TUNA), platePick.getValue());
					break;
				case "Salmon":
					makeGreenPlateRequest(new Nigiri(NigiriType.SALMON), platePick.getValue());
					break;
				case "Eel":
					makeGreenPlateRequest(new Nigiri(NigiriType.EEL), platePick.getValue());
					break;
				case "Crab":
					makeGreenPlateRequest(new Nigiri(NigiriType.CRAB), platePick.getValue());
					break;
				case "Shrimp":
					makeGreenPlateRequest(new Nigiri(NigiriType.SHRIMP), platePick.getValue());
					break;
				}
				break;
			case "Gold":
				switch(fish.getSelectedItem().toString())
				{
				case "Tuna":
					makeGoldPlateRequest(new Nigiri(NigiriType.TUNA), platePick.getValue(), gPrice.getValue());
					break;
				case "Salmon":
					makeGoldPlateRequest(new Nigiri(NigiriType.SALMON), platePick.getValue(), gPrice.getValue());
					break;
				case "Eel":
					makeGoldPlateRequest(new Nigiri(NigiriType.EEL), platePick.getValue(), gPrice.getValue());
					break;
				case "Crab":
					makeGoldPlateRequest(new Nigiri(NigiriType.CRAB), platePick.getValue(), gPrice.getValue());
					break;
				case "Shrimp":
					makeGoldPlateRequest(new Nigiri(NigiriType.SHRIMP), platePick.getValue(), gPrice.getValue());
					break;
				}
				break;
			}
			break;
		case "roll":
			if (rollIng.size() > 0)
			{
				rollIng2 = new IngredientPortion[rollIng.size()];
				rollIng.toArray(rollIng2);
			switch(plateColor.getSelectedItem().toString()){
			case "Red": 
				makeRedPlateRequest(new Roll("Random Roll", rollIng2), platePick.getValue());
				break;
			case "Blue":
				makeBluePlateRequest(new Roll("Random Roll", rollIng2), platePick.getValue());
				break;
			case "Green": 
				makeGreenPlateRequest(new Roll("Random Roll", rollIng2), platePick.getValue());
				break;
			case "Gold":
				makeGoldPlateRequest(new Roll("Random Roll", rollIng2), platePick.getValue(), gPrice.getValue());
				break;
			}
			break;
		}
		}

	}
}



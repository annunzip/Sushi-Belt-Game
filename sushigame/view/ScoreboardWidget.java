package sushigame.view;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.MenuListener;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver {

	private SushiGameModel game_model;
	private JLabel display;
	private JPanel panel;
	private JComboBox sort;

	public ScoreboardWidget(SushiGameModel gm) {
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);

		display = new JLabel();

		display.setVerticalAlignment(SwingConstants.TOP);
		setLayout(new BorderLayout());
		add(display, BorderLayout.NORTH);

		panel = new JPanel();
		panel.add(new JLabel("Display: "));
		sort = new JComboBox();
		sort.addItem("Balance");
		sort.addItem("Food consumed");
		sort.addItem("Food spoiled");
		sort.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String sb_html = "<html>";
				sb_html += "<h1>Scoreboard</h1>";

				// Create an array of all chefs and sort by balance.
				Chef[] opponent_chefs = game_model.getOpponentChefs();
				Chef[] chefs = new Chef[opponent_chefs.length + 1];
				chefs[0] = game_model.getPlayerChef();
				for (int i = 1; i < chefs.length; i++) {
					chefs[i] = opponent_chefs[i - 1];
				}
				if (sort.getSelectedItem().equals("Balance")) {
					Arrays.sort(chefs, new HighToLowBalanceComparator());
					for (Chef c : chefs) {
						sb_html += c.getName() + " ($" + Math.round(c.getBalance() * 100.0) / 100.0 + ") <br>";
					}
				} else if (sort.getSelectedItem().equals("Food consumed")) {
					Arrays.sort(chefs, new HighToLowConsumerComparator());
					for (Chef c : chefs) {
						sb_html += c.getName() + " ($" + Math.round(c.getConsume() * 100.0) / 100.0 + ") <br>";
					}
				} else if (sort.getSelectedItem().equals("Food spoiled")) {
					Arrays.sort(chefs, new LowToHighSpoilComparator());
					for (Chef c : chefs) {
						sb_html += c.getName() + " ($" + Math.round(c.getSpoil() * 100.0) / 100.0 + ") <br>";
					}
				}

				display.setText(sb_html);
			}
		});
		panel.add(sort);
		add(panel); 
		display.setText(makeScoreboardHTML());
	}

	private String makeScoreboardHTML() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs = game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length + 1];
		chefs[0] = game_model.getPlayerChef();
		for (int i = 1; i < chefs.length; i++) {
			chefs[i] = opponent_chefs[i - 1];
		}

		String choice = (String) sort.getSelectedItem();

		if (sort.getSelectedItem().equals("Balance")) {
			Arrays.sort(chefs, new HighToLowBalanceComparator());
			for (Chef c : chefs) {
				sb_html += c.getName() + " ($" + Math.round(c.getBalance() * 100.0) / 100.0 + ") <br>";
			}
		} else if (sort.getSelectedItem().equals("Food consumed")) {
			Arrays.sort(chefs, new HighToLowConsumerComparator());
			for (Chef c : chefs) {
				sb_html += c.getName() + " ($" + Math.round(c.getConsume() * 100.0) / 100.0 + ") <br>";
			}
		} else if (sort.getSelectedItem().equals("Food spoiled")) {
			Arrays.sort(chefs, new LowToHighSpoilComparator());
			for (Chef c : chefs) {
				sb_html += c.getName() + " ($" + Math.round(c.getSpoil() * 100.0) / 100.0 + ") <br>";
			}
		}

		return sb_html;
	}

	public void refresh() {
		display.setText(makeScoreboardHTML());
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}
	}

}

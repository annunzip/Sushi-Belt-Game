package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class LowToHighSpoilComparator implements Comparator<Chef>{

	@Override
	public int compare(Chef a, Chef b) {
		// TODO Auto-generated method stub
		return (int) (Math.round(a.getSpoil()) * 100) - (int) (Math.round(b.getSpoil()) * 100);
	}

}

package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class HighToLowConsumerComparator implements Comparator<Chef>{

	@Override
	public int compare(Chef a, Chef b) {
		// TODO Auto-generated method stub
		return (int) (Math.round(b.getConsume() * 100) - Math.round(a.getConsume() * 100));
	}

}

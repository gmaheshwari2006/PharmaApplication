package com.pharma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

public class TestNeighborhoods {

	public static void main(String[] args) {
		TestNeighborhoods test = new TestNeighborhoods();
		test.process();

	}

	private void process() {
		List<Integer> neighborhoods = new ArrayList<Integer>();
		neighborhoods.add(1);
		neighborhoods.add(2);
		neighborhoods.add(3);
		neighborhoods.add(4);
		neighborhoods.add(5);
		neighborhoods.add(6);
		neighborhoods.add(7);
		neighborhoods.add(8);
		neighborhoods.add(9);
		
		List<Integer> lockedNeighborhoods = new ArrayList<Integer>();
		lockedNeighborhoods.add(2);
		lockedNeighborhoods.add(3);
		lockedNeighborhoods.add(5);
		lockedNeighborhoods.add(7);
		lockedNeighborhoods.add(8);
		lockedNeighborhoods.add(9);
		
		Map<Integer, List<Integer>> neighGettingUnlocked = new HashMap<Integer, List<Integer>>();
		
		List<Integer> unlockingNeighs_1 = new ArrayList<Integer>();
		unlockingNeighs_1.add(3);
		unlockingNeighs_1.add(9);
		neighGettingUnlocked.put(2, unlockingNeighs_1);
		
		
		List<Integer> unlockingNeighs_2 = new ArrayList<Integer>();
		unlockingNeighs_2.add(8);
		neighGettingUnlocked.put(3, unlockingNeighs_2);
		
		List<Integer> unlockingNeighs_3 = new ArrayList<Integer>();
		unlockingNeighs_3.add(7);
		neighGettingUnlocked.put(4, unlockingNeighs_3);
		
		List<Integer> unlockingNeighs_4 = new ArrayList<Integer>();
		unlockingNeighs_4.add(2);
		unlockingNeighs_4.add(5);
		neighGettingUnlocked.put(6, unlockingNeighs_4);
		
		List<Integer> neighborhoodSequence = createNeighborhoodSequence(neighborhoods, lockedNeighborhoods, neighGettingUnlocked);  
		System.out.println("neighborhoodSequence: " + neighborhoodSequence);
		
	}

	private List<Integer> createNeighborhoodSequence(
			List<Integer> neighborhoods, List<Integer> lockedNeighborhoods,
			Map<Integer, List<Integer>> neighGettingUnlocked) {
		
		List<Integer> neighborhoodSequence = new ArrayList<Integer>(); 
		List<Integer> tempUnlockedNeighs = new ArrayList<Integer>(); 
		
		while(neighborhoodSequence.size() < neighborhoods.size()) {
			System.out.println("size: " + neighborhoodSequence.size());
			for(Integer neighId : neighborhoods) {
				boolean  inNeighSeq = checkInNeighborhoodSequence(neighId, neighborhoodSequence);
				boolean locked = checkLocked(neighId, lockedNeighborhoods);
				boolean tempUnlocked = checkTempUnlocked(neighId, tempUnlockedNeighs);
				
				if (!inNeighSeq && !locked) {
					neighborhoodSequence.add(neighId);
					updateAddInTempUnlokedNeigh(neighId, tempUnlockedNeighs, neighGettingUnlocked);
					continue;
				} else if (!inNeighSeq && locked && tempUnlocked) {
					neighborhoodSequence.add(neighId);
					updateRemoveFromTempUnlockedNeigh(neighId, tempUnlockedNeighs);
					updateAddInTempUnlokedNeigh(neighId, tempUnlockedNeighs, neighGettingUnlocked);
					continue;
				} /*else if (!inNeighSeq && locked && !tempUnlocked) {
					continue;
				}  */
			}
			
		}
		
		return neighborhoodSequence;
	}

	private void updateRemoveFromTempUnlockedNeigh(Integer neighId, List<Integer> tempUnlockedNeighs) {
		tempUnlockedNeighs.remove(neighId);
	}

	private void updateAddInTempUnlokedNeigh(Integer neighId, List<Integer> tempUnlockedNeighs, Map<Integer, List<Integer>> neighGettingUnlocked) {
		List<Integer> unlockedNeighs = neighGettingUnlocked.get(neighId);
		if (unlockedNeighs != null && !CollectionUtils.isEmpty(unlockedNeighs)) {
		tempUnlockedNeighs.addAll(unlockedNeighs);	
		}
	}

	private boolean checkTempUnlocked(Integer neighId, List<Integer> tempUnlockedNeighs) {
		return tempUnlockedNeighs.contains(neighId);
	}

	private boolean checkLocked(Integer neighId, List<Integer> lockedNeighborhoods) {
		return lockedNeighborhoods.contains(neighId);
	}

	private boolean checkInNeighborhoodSequence(Integer neighId, List<Integer> neighborhoodSequence) {
		return neighborhoodSequence.contains(neighId);
	}

}

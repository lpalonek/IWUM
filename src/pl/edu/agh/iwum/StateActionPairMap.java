package pl.edu.agh.iwum;

import java.util.HashMap;
import java.util.Map;

public class StateActionPairMap<T extends Object> {

	private Map<T, StateActionPair> map;

	public StateActionPairMap() {
		map = new HashMap<T, StateActionPair>();
	}

	public void add(T key, StateActionPair stateActionPair) {
		map.put(key, stateActionPair);
	}

	public StateActionPair get(T key) {
		return map.get(key);
	}

	public boolean contains(T key) {
		return map.containsKey(key);
	}

	public void remove(T key) {
		map.remove(key);
	}

	public void clear() {
		map.clear();
	}

}

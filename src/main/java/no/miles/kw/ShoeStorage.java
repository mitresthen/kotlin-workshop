package no.miles.kw;

import kotlin.Pair;
import no.miles.kw.db.Shoe;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoeStorage {


    public Map<Shoe, List<Pair<Integer, Integer>>> getShoeSizes(final List<Shoe> shoes){
        return shoes.stream().map(this::getSizes).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map.Entry<Shoe, List<Pair<Integer, Integer>>> getSizes(Shoe shoe) {
        ArrayList<Pair<Integer, Integer>> empty = new ArrayList<>();
        for(int i = 30; i < 45; i++){
            Pair<Integer, Integer> pair = new Pair<>(i, getRandomNumber());
            empty.add(pair);
        }
        return new AbstractMap.SimpleEntry(shoe, empty);
    }

    public int getRandomNumber() {
        int min = 0;
        int max = 5;
        return (int) ((Math.random() * (max - min)) + min);
    }

}

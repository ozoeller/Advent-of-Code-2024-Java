package dev.zoeller.aoc2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Areas {
    public static HashMap<Character, List<Area>> findAreas(List<String> lines) {
        HashMap<Character, List<Area>> map = new HashMap<>();
        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            for (int col = 0; col < line.length(); col++) {
                char c = line.charAt(col);
                if (!map.containsKey(c)) {
                    List<Area> areas = new ArrayList<>();
                    map.put(c, areas);
                    Area area = new Area(c, lines);
                    areas.add(area);
                    area.exploreNeighbours(row, col);
                } else {
                    boolean isPartOfExistingArea = false;
                    for (Area area : map.get(c)) {
                        if (area.contains(row, col)) {
                            isPartOfExistingArea = true;
                            break;
                        }
                    }
                    if (!isPartOfExistingArea) {
                        Area area = new Area(c, lines);
                        map.get(c).add(area);
                        area.exploreNeighbours(row, col);
                    }
                }
            }
        }
        return map;
    }
}

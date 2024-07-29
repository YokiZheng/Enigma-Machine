package enigmaRotorGenerate;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class EnigmaRotorGenerator {
    private EnigmaRotorGenerator() {
    }

    public static List<Integer> getReflectBoardList(int size) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(null);
        }

        int randomIndex;
        for (int i = 0; i < size; i++) {
            if (list.get(i) == null) {
                do {
                    randomIndex = new Random().nextInt(size);
                } while (randomIndex == i || list.get(randomIndex) != null);

                list.set(i, randomIndex);
                list.set(randomIndex, i);
            }
        }

        return list;
    }

    public static List<Integer> getRandomEnigmaRotorInsideLineThroughList(int size) {
        List<Integer> list = new LinkedList<>();
        int index = 0;
        while (index < size) {
            int nextRotorIndex = new Random().nextInt(size);
            if (nextRotorIndex != index) {
                if (!list.contains(nextRotorIndex)) {
                    list.add(nextRotorIndex);
                    index++;
                }
            }
        }
        return list;
    }

    public static List<Integer> getRandomEnigmaRotorInsideLineThroughList() {
        return getRandomEnigmaRotorInsideLineThroughList(28);
    }

    public static List<Character> getRotor1InputIndexList() {
        List<Character> rotor1InputIndexList = new LinkedList<>();
        for (int i = 97; i < 26 + 97; i++) {
            rotor1InputIndexList.add((char) i);
        }
        rotor1InputIndexList.add((char) 32);
        rotor1InputIndexList.add((char) 61);
        return rotor1InputIndexList;
    }

//    public static void main(String[] args) {
//        System.out.println(getRandomEnigmaRotorInsideLineThroughList(32));
//    }
}

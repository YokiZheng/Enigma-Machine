package Enigma0_0_4;

import enigmaRotorGenerate.EnigmaRotorGenerator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EnigmaMachine {
    private final Rotor rotor1;
    private final Rotor rotor2;
    private final Rotor rotor3;
    private final Rotor rotor1Original;
    private final Rotor rotor2Original;
    private final Rotor rotor3Original;
    private final ReflectBoard reflectBoard;

    private final List<Character> rotor1InputIndexList;

    public EnigmaMachine(int size) {
        this.rotor1InputIndexList = EnigmaRotorGenerator.getRotor1InputIndexList();
        List<Node> rotor1NodeListForIn = new LinkedList<>();
        List<Node> rotor1NodeListForOut = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            rotor1NodeListForOut.add(null);
        }
        List<Integer> integerRotor1NodeListForIn = EnigmaRotorGenerator.getRandomEnigmaRotorInsideLineThroughList(size);
        for (int i = 0; i < size; i++) {
            rotor1NodeListForIn.add(new Node(integerRotor1NodeListForIn.get(i)));
            rotor1NodeListForOut.set(integerRotor1NodeListForIn.get(i), new Node(i));
        }


        List<Node> rotor2NodeListForIn = new LinkedList<>();
        List<Node> rotor2NodeListForOut = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            rotor2NodeListForOut.add(null);
        }
        List<Integer> integerRotor2NodeListForIn = EnigmaRotorGenerator.getRandomEnigmaRotorInsideLineThroughList(size);
        for (int i = 0; i < size; i++) {
            rotor2NodeListForIn.add(new Node(integerRotor2NodeListForIn.get(i)));
            rotor2NodeListForOut.set(integerRotor2NodeListForIn.get(i), new Node(i));
        }


        List<Node> rotor3NodeListForIn = new LinkedList<>();
        List<Node> rotor3NodeListForOut = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            rotor3NodeListForOut.add(null);
        }
        List<Integer> integerRotor3NodeListForIn = EnigmaRotorGenerator.getRandomEnigmaRotorInsideLineThroughList(size);
        for (int i = 0; i < size; i++) {
            rotor3NodeListForIn.add(new Node(integerRotor3NodeListForIn.get(i)));
            rotor3NodeListForOut.set(integerRotor3NodeListForIn.get(i), new Node(i));
        }

        rotor1 = new Rotor(0, rotor1NodeListForIn, rotor1NodeListForOut);
        rotor2 = new Rotor(0, rotor2NodeListForIn, rotor2NodeListForOut);
        rotor3 = new Rotor(0, rotor3NodeListForIn, rotor3NodeListForOut);

        List<Integer> boardList = EnigmaRotorGenerator.getReflectBoardList(size);
//        List<Integer> boardList = new LinkedList<>(Arrays.asList(2, 3, 0, 1));
        List<Node> boardNodeList = new LinkedList<>();
        for (Integer integer : boardList) {
            boardNodeList.add(new Node(integer));
        }
        this.reflectBoard = new ReflectBoard(boardNodeList);

        this.rotor1Original = new Rotor(rotor1);
        this.rotor2Original = new Rotor(rotor2);
        this.rotor3Original = new Rotor(rotor3);

//        System.out.println("===================================");
//        System.out.print("ref-board: ");
//        this.reflectBoard.nodeList.forEach(e -> System.out.print(e.nextRotorIndex));
//        System.out.println();
//        System.out.println("rotor1: " + this.rotor1);
//        System.out.println("rotor2: " + this.rotor2);
//        System.out.println("rotor3: " + this.rotor3);
//        System.out.println("===================================");
    }

    public EnigmaMachine() {
        this(28);
    }

    public EnigmaMachine(List<Rotor> rotorList, List<Node> boardList) {
        this.rotor1InputIndexList = EnigmaRotorGenerator.getRotor1InputIndexList();
        this.rotor1 = new Rotor(rotorList.get(0));
        this.rotor2 = new Rotor(rotorList.get(1));
        this.rotor3 = new Rotor(rotorList.get(2));

        this.reflectBoard = new ReflectBoard(boardList);

        this.rotor1Original = new Rotor(rotor1);
        this.rotor2Original = new Rotor(rotor2);
        this.rotor3Original = new Rotor(rotor3);

//        System.out.println("===================================");
//        System.out.print("ref-board: ");
//        this.reflectBoard.nodeList.forEach(e -> System.out.print(e.nextRotorIndex));
//        System.out.println();
//        System.out.println("rotor1: " + this.rotor1);
//        System.out.println("rotor2: " + this.rotor2);
//        System.out.println("rotor3: " + this.rotor3);
//        System.out.println("===================================");
    }

    public String getOutput(String input) {
        char[] chars = input.toCharArray();
        StringBuilder result = new StringBuilder();
//        System.out.printf("r1 count=%d, r2 count=%d, r3 count=%d%n",rotor1.rotorCounter, rotor2.rotorCounter, rotor3.rotorCounter);
        for (char aChar : chars) {
            result.append(getOutput(aChar));
//            System.out.printf("r1 count=%d, r2 count=%d, r3 count=%d%n",rotor1.rotorCounter, rotor2.rotorCounter, rotor3.rotorCounter);
        }
        return result.toString();
    }

    public Character getOutput(Character input) {
        int rotor1InputIndex = rotor1InputIndexList.indexOf(input);
        int rotor2InputIndex = rotor1.nodeListForIn.get(rotor1InputIndex).nextRotorIndex;
        int rotor3InputIndex = rotor2.nodeListForIn.get(rotor2InputIndex).nextRotorIndex;

        int reflectInputIndex = rotor3.nodeListForIn.get(rotor3InputIndex).nextRotorIndex;
        int reflectOutputIndex = reflectBoard.nodeList.get(reflectInputIndex).nextRotorIndex;

        int rotor3OutputIndex = rotor3.nodeListForOut.get(reflectOutputIndex).nextRotorIndex;
        int rotor2OutputIndex = rotor2.nodeListForOut.get(rotor3OutputIndex).nextRotorIndex;
        int rotor1OutputIndex = rotor1.nodeListForOut.get(rotor2OutputIndex).nextRotorIndex;

//        System.out.printf("rotor1InputIndex=%d, rotor2InputIndex=%d, rotor3InputIndex=%d, reflectInputIndex=%d, reflectOutputIndex=%d, rotor3OutputIndex=%d, rotor2OutputIndex=%d, rotor1OutputIndex=%d%n", rotor1InputIndex, rotor2InputIndex, rotor3InputIndex, reflectInputIndex, reflectOutputIndex, rotor3OutputIndex, rotor2OutputIndex, rotor1OutputIndex);
        char op = rotor1InputIndexList.get(rotor1OutputIndex);
        rotateRotor(reflectBoard.nodeList.size());

        return op;
    }

    public List<Rotor> getRotors() {
        List<Rotor> rotorList = new LinkedList<>();
        rotorList.add(this.rotor1Original);
        rotorList.add(this.rotor2Original);
        rotorList.add(this.rotor3Original);
        return rotorList;
    }

    public List<Node> getReflectBoard() {
        List<Node> boardList = new LinkedList<>();
        for (Node node : this.reflectBoard.nodeList) {
            boardList.add(new Node(node.nextRotorIndex));
        }
        return boardList;
    }

    private void rotateRotor(int size) {
        rotor1.rotorCounter++;
        int rc2 = rotor2.rotorCounter;
        int rc3 = rotor3.rotorCounter;
        if (rotor1.rotorCounter % size == 0) {
            rotor2.rotorCounter++;
            if (rotor2.rotorCounter % size == 0) {
                rotor3.rotorCounter++;
            }
        }

        Rotor rotor = changeRotor(1);
        changeRotorInsideLineThrough(rotor);
//        System.out.println("r1: " + rotor1);

        if (rc2 != rotor2.rotorCounter) {
            rotor = changeRotor(2);
            changeRotorInsideLineThrough(rotor);
        }
//        System.out.println("r2: " + rotor2);

        if (rc3 != rotor3.rotorCounter) {
            rotor = changeRotor(3);
            changeRotorInsideLineThrough(rotor);
        }
//        System.out.println("r3: " + rotor3);

//        System.out.println();
    }

    private Rotor changeRotor(int rotorNo) {
        Rotor toBeChange = null;
        if (rotorNo == 1) {
            toBeChange = rotor1;
        }

        if (rotorNo == 2) {
            toBeChange = rotor2;
        }

        if (rotorNo == 3) {
            toBeChange = rotor3;
        }

        return toBeChange;
    }

    private void changeRotorInsideLineThrough(Rotor toBeChangeRotor) {
        List<Node> tempNodeInList = new ArrayList<>();
        for (int i = 0; i < toBeChangeRotor.nodeListForIn.size(); i++) {
            tempNodeInList.add(new Node(toBeChangeRotor.nodeListForIn.get(i).nextRotorIndex));
        }
        List<Node> tempNodeOutList = new ArrayList<>();
        for (int i = 0; i < toBeChangeRotor.nodeListForOut.size(); i++) {
            tempNodeOutList.add(new Node(toBeChangeRotor.nodeListForOut.get(i).nextRotorIndex));
        }

        for (int i = 0; i < toBeChangeRotor.nodeListForIn.size() - 1; i++) {
            int NRI = tempNodeInList.get(i).nextRotorIndex + 1;
            if (NRI >= toBeChangeRotor.nodeListForIn.size()) {
                NRI = 0;
            }
            toBeChangeRotor.nodeListForIn.set(i + 1, new Node(NRI));
        }
        int NRI = tempNodeInList.get(tempNodeInList.size() - 1).nextRotorIndex + 1;
        if (NRI >= toBeChangeRotor.nodeListForIn.size()) {
            NRI = 0;
        }
        toBeChangeRotor.nodeListForIn.set(0, new Node(NRI));


        for (int i = 0; i < toBeChangeRotor.nodeListForOut.size() - 1; i++) {
            int NRIO = tempNodeOutList.get(i).nextRotorIndex + 1;
            if (NRIO >= toBeChangeRotor.nodeListForOut.size()) {
                NRIO = 0;
            }
            toBeChangeRotor.nodeListForOut.set(i + 1, new Node(NRIO));
        }
        int NRIO = tempNodeOutList.get(tempNodeOutList.size() - 1).nextRotorIndex + 1;
        if (NRIO >= toBeChangeRotor.nodeListForOut.size()) {
            NRIO = 0;
        }
        toBeChangeRotor.nodeListForOut.set(0, new Node(NRIO));
    }

    private static class ReflectBoard {
        private final List<Node> nodeList;

        public ReflectBoard(List<Node> nodeList) {
            this.nodeList = nodeList;
        }
    }

    public static class Rotor {
        private int rotorCounter;
        private final List<Node> nodeListForIn;
        private final List<Node> nodeListForOut;

        public Rotor(int rotorCounter, List<Node> nodeListForIn, List<Node> nodeListForOut) {
            this.rotorCounter = rotorCounter;
            this.nodeListForIn = nodeListForIn;
            this.nodeListForOut = nodeListForOut;
        }

        public Rotor(Rotor rotor) {
            this.rotorCounter = rotor.rotorCounter;
            this.nodeListForIn = rotor.getNodeListForIn();
            this.nodeListForOut = rotor.getNodeListForOut();
        }

        public List<Node> getNodeListForIn() {
            List<Node> result = new LinkedList<>();
            for (Node node :
                    nodeListForIn) {
                result.add(new Node(node.nextRotorIndex));
            }
            return result;
        }

        public List<Node> getNodeListForOut() {
            List<Node> result = new LinkedList<>();
            for (Node node :
                    nodeListForOut) {
                result.add(new Node(node.nextRotorIndex));
            }
            return result;
        }

        @Override
        public String toString() {
            StringBuilder in = new StringBuilder();
            for (Node n :
                    nodeListForIn) {
                in.append(n.nextRotorIndex).append(" ");
            }

            StringBuilder out = new StringBuilder();
            for (Node n :
                    nodeListForOut) {
                out.append(n.nextRotorIndex).append(" ");
            }

            return "Rotor{" +
                    "rotorCounter=" + rotorCounter +
                    ", nodeListForIn=" + in +
                    ", nodeListForOut=" + out +
                    '}';
        }
    }

    public static class Node {
        public final int nextRotorIndex;

        Node(int nextRotorIndex) {
            this.nextRotorIndex = nextRotorIndex;
        }
    }
}

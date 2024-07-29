package main;

import Enigma0_0_4.EnigmaMachine;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EnigmaMachine enigmaMachine1 = new EnigmaMachine();
        String input1 = "hello world i am enigma machine";
        String ened = enigmaMachine1.getOutput(input1);
        System.out.println("密文：" + ened);

//        约定两台恩格码机的密钥，第一个为转轮的信息，第二个为反射板的信息
        List<EnigmaMachine.Rotor> rotorsInfo = enigmaMachine1.getRotors();
        List<EnigmaMachine.Node> reflectBoard = enigmaMachine1.getReflectBoard();

//        只要两台恩格码机的转轮和反射板设置相同，那么根据恩格码机的性质（加解密一体机），输入密文就能得到原文
        EnigmaMachine enigmaMachine = new EnigmaMachine(rotorsInfo, reflectBoard);
        String original = enigmaMachine.getOutput(ened);
        System.out.println("原文：" + original);
    }
}

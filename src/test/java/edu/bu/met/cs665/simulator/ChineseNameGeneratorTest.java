package edu.bu.met.cs665.simulator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChineseNameGeneratorTest {

    @Test
    public void getName() {
        ChineseNameGenerator chineseNameGenerator = new ChineseNameGenerator();
        System.out.println(chineseNameGenerator.getName());
    }
}
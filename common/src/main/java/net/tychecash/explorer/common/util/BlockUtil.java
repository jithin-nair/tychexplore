/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jithin
 */
public class BlockUtil {

    public static List<Integer> getAllBlockSamples(Integer sampleRate, Integer topBlock) {
        ArrayList<Integer> blockSamples = new ArrayList<>();
        int samplePos = topBlock / sampleRate;
        for (int i = 0; i <= topBlock; i++) {
            if (i % samplePos == 0) {
                blockSamples.add(i);
            }
        }
        return blockSamples;
    }

    public static String insertCharAt(String st, char ch, int index) throws NullPointerException, IndexOutOfBoundsException {
        String reward = new StringBuilder(st).reverse().toString();
        reward = reward.substring(0, index) + ch + reward.substring(index, reward.length());
        return new StringBuilder(reward).reverse().toString();
    }
}

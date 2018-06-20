package com.lxhdj.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Created by wangguijun1 on 2018/3/22.
 */
public class SearchTest {
    @Test
    public void testSearch_01() {
        int[] arr = {1, 2, 3, 4};
        int key = 0;
        int index = Search.binarySearch(arr, key);
        assertEquals(0, index);
    }

    @Test
    public void testSearch_02() {
        int[] arr = {1, 2, 3, 4};
        int key = 2;
        int index = Search.binarySearch(arr, key);
        assertEquals(1, index);
    }

    @Test
    public void testSearch_03() {
        int[] arr = {1, 3, 4, 5};
        int key = 2;
        int index = Search.binarySearch(arr, key);
        assertEquals(1, index);
    }

    @Test
    public void testSearch_04() {
        int[] arr = {1, 3, 4, 5};
        int key = 6;
        int index = Search.binarySearch(arr, key);
        assertEquals(4, index);
    }

    @Test
    public void testSearch_05() {
        int[] arr = {1, 3, 4, 5};
        int key = 1;
        int index = Search.binarySearch(arr, key);
        assertEquals(0, index);
    }

    @Test
    public void testSearch_06() {
        Integer[] arr = {1, 3, 4, 5};
        int key = 6;
        int index = Collections.binarySearch(Arrays.asList(arr), key);
        assertEquals(-5, index);
    }

}

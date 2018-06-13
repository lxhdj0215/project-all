package com.lxhdj.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangguijun1 on 2018/6/13.
 */
public class SortTest {

    @Test
    public void sortTest() {
        Sort sort = new Sort();
        List<String> list = new ArrayList<>();
        System.out.println(list);
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = 100 - i;
        }
        System.out.println(Arrays.toString(arr));
        // int[] arr = { 1, 2, 3, 4, 5, 6 };
        // arr = insertSort(arr);
//		bubbleSort(arr);
        sort.selectionSort(arr);
        System.out.println(Arrays.toString(arr));

    }
}

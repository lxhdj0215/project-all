package com.lxhdj.sort;

import org.junit.Test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by wangguijun1 on 2018/6/13.
 */
public class SortTest {


    @Test
    public void bubbleSortTest() {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10);
        }
        System.out.println("----------冒泡排序开始----------");
        System.out.println("排序前：" + Arrays.toString(arr));
        arr = Sort.bubbleSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
        System.out.println("----------冒泡排序结束----------");
    }

    @Test
    public void selectionSortTest() {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10);
        }
        System.out.println("----------选择排序开始----------");
        System.out.println("排序前：" + Arrays.toString(arr));
        arr = Sort.selectionSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
        System.out.println("----------选择排序结束----------");
    }

    @Test
    public void insertSortTest() {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10);
        }
        System.out.println("----------插入排序开始----------");
        System.out.println("排序前：" + Arrays.toString(arr));
        arr = Sort.insertSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
        System.out.println("----------插入排序结束----------");
    }
}

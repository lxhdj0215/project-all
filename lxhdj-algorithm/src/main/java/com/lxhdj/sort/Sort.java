package com.lxhdj.sort;

import java.util.Arrays;

public class Sort {

	public static void main(String[] args) {
		int[] arr = new int[100];
		for (int i = 0; i < 100; i++) {
			arr[i] = 100 - i;
		}
		System.out.println(Arrays.toString(arr));
		// int[] arr = { 1, 2, 3, 4, 5, 6 };
		// arr = insertSort(arr);
//		bubbleSort(arr);
		selectionSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	public static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
				if (min != i) {
					int temp = arr[i];
					arr[i] = arr[min];
					arr[min] = temp;
				}
			}
		}
	}

	public static void insertSort(int[] arr) {
		int key;

		for (int i = 1; i < arr.length; i++) {
			key = arr[i];
			int j = i;
			while (j > 0 && arr[j - 1] > key) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = key;
		}
	}

}

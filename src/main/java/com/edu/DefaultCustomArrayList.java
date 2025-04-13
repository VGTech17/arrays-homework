package com.edu;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class DefaultCustomArrayList<E> implements CustomArrayList<E> {

    private E[] arr;
    private int size;
    private int capacity;

    public DefaultCustomArrayList() {
        capacity = 10;
        arr = (E[]) new Object[capacity];
    }

    @Override
    public boolean add(E element) {
        if (size >= capacity) {
            int newArrCapacity = capacity * 2;
            arr = Arrays.copyOf(arr, newArrCapacity);
            capacity = newArrCapacity;
        }
        arr[size++] = element;
        return true;
    }

    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, arr[i])) {
                for (int j = i; j < size - 1; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[--size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index);
        }
        return arr[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null; // обнуляем старые ссылки
        }
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(arr[i], element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return arr[index++];
            }
        };
    }
}
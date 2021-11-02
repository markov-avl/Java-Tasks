package com.company;

import java.util.ArrayList;

public class Stack<T> {
    private final int maxSize;
    private final ArrayList<T> container;

    public Stack(int size) throws StackException {
        container = new ArrayList<>();
        if (size < 1) {
            throw new StackException("Stack size must be greater than 0");
        }
        maxSize = size;
    }

    public T pop() throws StackException {
        if (!container.isEmpty()) {
            T element = container.get(container.size() - 1);
            container.remove(container.size() - 1);
            return element;
        }
        throw new StackException("Stack is empty");
    }

    public void push(T element) throws StackException {
        if (container.size() < maxSize) {
            container.add(element);
            return;
        }
        throw new StackException("Stack is full");
    }

    public int getLength() {
        return container.size();
    }

    public String toString() {
        if (container.isEmpty()) {
            return "Stack is empty";
        }
        StringBuilder string = new StringBuilder("[" + container.get(0).toString());
        for (int i = 1; i < container.size(); ++i) {
            string.append(", ").append(container.get(i).toString());
        }
        return string.append("]").toString();
    }

    public int find(T element) throws StackException {
        int index = -1;
        int counter = 0;
        for (int i = 0; i < container.size(); ++i) {
            if (container.get(i).equals(element)) {
                ++counter;
                if (counter > 1) {
                    throw new StackException("This element occurs more than 1 time");
                }
                index = i;
            }
        }
        return index;
    }
}

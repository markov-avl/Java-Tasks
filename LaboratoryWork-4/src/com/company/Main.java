package com.company;

/**
 Разработать класс-дженерик для хранения данных.

 Данные должны храниться в поле-массиве, ограниченном максимальным размером (например, 10 элементов). Реализовать методы
 добавления данных и удаления данных (например, по принципу стека). Методы должны генерировать исключение при попытке
 добавить данное в уже заполненный контейнер и удалить из пустого контейнера. Для этого создать свои классы исключений.
 Кроме этого реализовать метод поиска элементов. Метод генерирует исключение, если контейнер содержит больше одного
 искомого элемента.
 */

public class Main {
    public static void main(String[] args) throws StackException {
        // Constructor testing
        try {
            Stack<String> stack = new Stack<>(-1);
        } catch (StackException e) {
            System.out.println(e.getMessage() + ": calling a constructor with negative size");
        }
        try {
            Stack<Integer> stack = new Stack<>(0);
        } catch (StackException e) {
            System.out.println(e.getMessage() + ": calling a constructor with zero size");
        }

        int size = 10;
        Stack<String> stack = new Stack<>(size);

        // Pop testing
        try {
            stack.pop();
        } catch (StackException e) {
            System.out.println(e.getMessage() + ": calling a pop method on an empty stack");
        }
        try {
            for (int i = 0; i < size; ++i) {
                stack.push(Integer.toString(i));
            }
            for (int i = 0; i < size + 1; ++i) {
                stack.pop();
            }
        } catch (StackException e) {
            System.out.println(e.getMessage() + ": calling too many pop methods");
        }

        // Push testing
        try {
            for (int i = 0; i < size + 1; ++i) {
                stack.push(Integer.toString(i * i));
            }
        } catch (StackException e) {
            System.out.println(e.getMessage() + ": calling too many push methods");
        }

        // Find testing
        System.out.println("Find(9) in " + stack + " = " + stack.find("9"));
        System.out.println("Find(1) in " + stack + " = " + stack.find("1"));
        System.out.println("Find(2) in " + stack + " = " + stack.find("2"));
        try {
            stack.pop();
            stack.push("9");
            stack.find("9");
        } catch (StackException e) {
            System.out.println(e.getMessage() + ": there are several 9 on the stack: " + stack);
        }
    }
}
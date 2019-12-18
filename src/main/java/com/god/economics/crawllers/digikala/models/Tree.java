package com.god.economics.crawllers.digikala.models;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
    private Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>();
        root.data = rootData;
        root.children = new ArrayList<Node<T>>();
    }

    public static class Node<T> {
        public T data;
        public Node<T> parent;
        public List<Node<T>> children;
    }


    public static void main(String[] args) {
        Tree<String> root = new Tree<>("root");
        root.root.children.add(new Node<>());
        System.out.println();


    }
}
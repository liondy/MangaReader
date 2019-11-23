package com.example.mangareader;

import java.util.Stack;

public class MainPresenter {

    FragmentListener fl;
    Stack<Integer> stack;
    ItemSelector itemSelector;

    public MainPresenter(MainActivity ma){
        this.fl = ma;
        this.stack = new Stack();
    }

    public void setStack(Stack stack){
        this.stack = stack;
    }

    public Stack getStack() {
        return stack;
    }
}

package chapter9;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayListStack<T> implements Stack<T> {

    private final ArrayList<T> data;

    public ArrayListStack() {
        data = new ArrayList<>();
    }

    @Override
    public void push(T item) {
        data.add(item);
    }

    @Override
    public T pop() throws Exception {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data.remove(data.size() - 1);
    }

    @Override
    public T top() throws Exception {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data.get(data.size() - 1);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}

package chapter9;

import java.util.ArrayList;

public class SortedArrayListPriorityQueue<T> implements PriorityQueue<T> {

    private static class Entry<T> implements Comparable<Entry<T>> {
        final int priority;
        final T data;

        Entry(int priority, T data) {
            this.priority = priority;
            this.data = data;
        }

        @Override
        public int compareTo(Entry<T> o) {
            return this.priority - o.priority;
        }
    }

    private final ArrayList<Entry<T>> list;

    public SortedArrayListPriorityQueue() {
        list = new ArrayList<>();
    }

    @Override
    public void enqueue(int priority, T data) {
        list.add(new Entry<>(priority, data));
        list.sort(null);
    }

    @Override
    public T dequeue() throws Exception {
        return list.remove(0).data;
    }

    @Override
    public T front() throws Exception {
        return list.get(0).data;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}



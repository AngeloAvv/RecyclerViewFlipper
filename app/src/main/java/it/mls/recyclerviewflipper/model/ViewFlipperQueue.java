package it.mls.recyclerviewflipper.model;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

/**
 * Created by Angelo Cassano on 04/06/2015.
 */
public class ViewFlipperQueue<T extends View> implements Deque<T> {

    private ArrayList<T> mItems;

    public ViewFlipperQueue() {
        this.mItems = new ArrayList<>();
    }
    
    public T middle() {
        int middle = mItems.size() / 2;

        return mItems.get(middle);
    }

    @Override
    public void addFirst(T item) {
        mItems.add(0, item);
    }

    @Override
    public void addLast(T item) {
        mItems.add(item);
    }

    @Override
    public boolean offerFirst(T item) {
        return false;
    }

    @Override
    public boolean offerLast(T item) {
        return false;
    }

    @Override
    public T removeFirst() {
        return mItems.remove(0);
    }

    @Override
    public T removeLast() {
        return mItems.remove(mItems.size() - 1);
    }

    @Override
    public T pollFirst() {
        return null;
    }

    @Override
    public T pollLast() {
        return null;
    }

    @Override
    public T getFirst() {
        return mItems.get(0);
    }

    @Override
    public T getLast() {
        return mItems.get(mItems.size() - 1);
    }

    @Override
    public T peekFirst() {
        return null;
    }

    @Override
    public T peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean add(T item) {
        return mItems.add(item);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return mItems.addAll(collection);
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(T item) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public void push(T item) {

    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return mItems.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return mItems.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return mItems.size();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NonNull
    @Override
    public <T> T[] toArray(T[] array) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return mItems.iterator();
    }

    @NonNull
    @Override
    public Iterator<T> descendingIterator() {
        return null;
    }
}

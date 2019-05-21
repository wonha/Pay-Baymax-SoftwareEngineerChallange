package com.wonha.paypay.challenge.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author Wonha Shin
 */
public class ImmutableStackIterator<T> implements Iterator<T> {

	private ImmutableStack<T> target;

	ImmutableStackIterator(ImmutableStack<T> target) {
		if (target == null) {
			throw new IllegalArgumentException("Can't make");
		}
		this.target = target;
	}

	@Override
	public boolean hasNext() {
		return !target.isEmpty();
	}

	@Override
	public T next() {
		LinkedList<Integer> a  = new LinkedList<>();
		a.listIterator();
		if (!hasNext()) {
			throw new NoSuchElementException("No more elements.");
		}
		T next = target.head;
		target = target.nested;
		return next;
	}
}

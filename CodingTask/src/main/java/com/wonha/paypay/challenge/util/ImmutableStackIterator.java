package com.wonha.paypay.challenge.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Wonha Shin
 */
public class ImmutableStackIterator<T> implements Iterator<T> {

	private ImmutableStack<T> target;

	ImmutableStackIterator(ImmutableStack<T> target) {
		this.target = target;
	}

	@Override
	public boolean hasNext() {
		return !target.isEmpty();
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException("No more elements.");
		}
		T next = target.getElement();
		target = target.getNested();
		return next;
	}
}

package com.wonha.paypay.challenge.util;

import java.util.Iterator;

/**
 * @author Wonha Shin
 */
public class ImmutableStack<T> implements Stack<T>, Iterable<T>{

	private static final ImmutableStack NIL = new ImmutableStack<>(null, null);

	final T element;
	final ImmutableStack<T> nested;

	private ImmutableStack(T element, ImmutableStack<T> nested) {
		this.element = element;
		this.nested = nested;
	}

	public static <T> ImmutableStack<T> getEmptyInstance() {
		return NIL;
	}

	@Override
	public ImmutableStack<T> push(T t) {
		return new ImmutableStack<>(t, this);
	}

	@Override
	public ImmutableStack<T> pop() {
		return nested;
	}

	@Override
	public T peek() {
		return element;
	}

	@Override
	public boolean isEmpty() {
		return NIL == this;
	}

	@Override
	public Iterator<T> iterator() {
		return new ImmutableStackIterator<>(this);
	}

	ImmutableStack<T> createReversed() {
		ImmutableStack<T> reversed = getEmptyInstance();
		for (T t : this) {
			reversed.push(t);
		}
		return reversed;
	}
}

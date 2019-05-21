package com.wonha.paypay.challenge.util;

import java.util.Iterator;

/**
 * @author Wonha Shin
 */
public class ImmutableStack<T> implements Stack<T>, Iterable<T>, Countable {

	private static final ImmutableStack NIL = new ImmutableStack<>();

	private final T element;
	private final ImmutableStack<T> nested;
	private final int size;

	public ImmutableStack() {
		this.element = null;
		this.nested = null;
		this.size = 0;
	}

	private ImmutableStack(T element, ImmutableStack<T> nested) {
		this.element = element;
		this.nested = nested;
		int nestedSize = nested != null ? nested.size() : 0;
		this.size = nestedSize + 1;
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

	@Override
	public int size() {
		return this.size;
	}

	ImmutableStack<T> createReversed() {
		ImmutableStack<T> reversed = getEmptyInstance();
		for (T t : this) {
			reversed = reversed.push(t);
		}
		return reversed;
	}

	T getElement() {
		return element;
	}

	ImmutableStack<T> getNested() {
		return nested;
	}
}

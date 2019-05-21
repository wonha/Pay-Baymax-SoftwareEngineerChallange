package com.wonha.paypay.challenge.util;

import java.util.Iterator;

/**
 * @author Wonha Shin
 */
public class ImmutableStack<T> implements Stack<T>, Iterable<T>{

	private final static ImmutableStack NIL = new ImmutableStack<>();

	final T head;
	final ImmutableStack<T> nested;

	private ImmutableStack() {
		this.head = null;
		this.nested = null;
	}

	public ImmutableStack(T head, ImmutableStack<T> nested) {
		if (head == null && nested == null) {
			throw new IllegalArgumentException("Use static method offered to use empty stack.");
		}
		this.head = head;
		this.nested = nested;
	}

	public static <T> ImmutableStack<T> getEmptyInstance() {
		return NIL;
	}

	@Override
	public boolean isEmpty() {
		return NIL == this;
	}

	@Override
	public T peek() {
		return head;
	}

	@Override
	public ImmutableStack<T> push(T t) {
		return new ImmutableStack<>(t, this);
	}

	@Override
	public ImmutableStack<T> pop() {
		return createReversed();
	}

	private ImmutableStack<T> createReversed() {
		ImmutableStack<T> reversed = getEmptyInstance();
		for (T t : this) {
			reversed.push(t);
		}
		return reversed;
	}

	@Override
	public Iterator<T> iterator() {
		return new ImmutableStackIterator<>(this);
	}
}

package com.wonha.paypay.challenge.util;

/**
 * @author Wonha Shin
 */
public class ImmutableQueue<T> implements Queue<T> {

	private static final ImmutableQueue NIL = new ImmutableQueue(null, null);

	private ImmutableStack<T> front;
	private ImmutableStack<T> back;

	private ImmutableQueue(ImmutableStack<T> front, ImmutableStack<T> back) {
		this.front = front;
		this.back = back;
	}

	public static <T> ImmutableQueue<T> getEmptyInstance() {
		return NIL;
	}

	@Override
	public Queue<T> enQueue(T t) {
		return new ImmutableQueue<>(front.push(t), back);
	}

	@Override
	public Queue<T> deQueue() {
		if (isEmpty()) {
			return NIL;
		} else if (back.isEmpty()) {
			return new ImmutableQueue<>(ImmutableStack.getEmptyInstance(), front.createReversed().nested);
		} else {
			return new ImmutableQueue<>(front, back.nested);
		}
	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		}

		if (back.isEmpty()) {
			back = front.createReversed();
			front = ImmutableStack.getEmptyInstance();
		}
		return back.pop().element;
	}

	@Override
	public boolean isEmpty() {
		return NIL == this;
	}
}

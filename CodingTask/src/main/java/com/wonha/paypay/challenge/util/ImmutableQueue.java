package com.wonha.paypay.challenge.util;

/**
 * @author Wonha Shin
 */
public class ImmutableQueue<T> implements Queue<T>, Countable {

	private static final ImmutableQueue NIL =
			new ImmutableQueue(ImmutableStack.getEmptyInstance(), ImmutableStack.getEmptyInstance());

	private ImmutableStack<T> front;
	private ImmutableStack<T> back;
	final int size;

	private ImmutableQueue(ImmutableStack<T> front, ImmutableStack<T> back) {
		this.front = front;
		this.back = back;
		int frontSize = front != null ? front.size() : 0;
		int backSize = back != null ? back.size() : 0;
		this.size = frontSize + backSize;
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
		if (isEmpty() || size() == 1) {
			return NIL;
		} else if (back.isEmpty()) {
			return new ImmutableQueue<>(ImmutableStack.getEmptyInstance(), front.newReversed().getNested());
		} else {
			return new ImmutableQueue<>(front, back.getNested());
		}
	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		}

		if (back.isEmpty()) {
			back = front.newReversed();
			front = ImmutableStack.getEmptyInstance();
		}
		return back.peek();
	}

	@Override
	public boolean isEmpty() {
		return NIL == this;
	}

	@Override
	public int size() {
		return this.size;
	}
}

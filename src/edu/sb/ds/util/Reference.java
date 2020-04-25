package edu.sb.ds.util;

import java.util.Objects;

/**
 * Reusable class referencing a single object, or {@code null}. It is useful for shared read/write access to objects across
 * multiple threads.
 * @param <T> the referenced value's type
 */
@Copyright(year = 2010, holders = "Sascha Baumeister")
public class Reference<T> {
	private T value;


	/**
	 * Initializes a new instance with a {@code null} value.
	 */
	public Reference () {
		this(null);
	}


	/**
	 * Initializes a new instance with the given value.
	 * @param value the value, or {@code null} for none
	 */
	public Reference (final T value) {
		this.value = value;
	}

	/**
	 * Returns the value.
	 * @return the referenced value
	 */
	public T get () {
		return this.value;
	}


	/**
	 * Sets the value.
	 * @param value the referenced value
	 */
	public void put (final T value) {
		this.value = value;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode () {
		return Objects.hashCode(this.value);
	}


	/**
	 * {@inheritDoc}
	 * @throws NullPointerException if the given argument is {@code null}
	 */
	@Override
	public boolean equals (final Object other) throws NullPointerException {
		if (!(other instanceof Reference)) return false;
		return Objects.equals(this.value, ((Reference<?>) other).value);
	}
}
package edu.sb.ds.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * This is a wrapper class enriching underlying string maps with event callback functionality. Instances notify their registered
 * event handlers of every change event within the underlying map (via {@link Map#put(Object, Object)},
 * {@link Map#remove(Object)}, or {@link Map#clear()} messages). This includes modifications caused by map views (
 * {@link Map#keySet()}, {@link Map#values()}, or {@link Map#entrySet()}) and their associated iterators. Note that event
 * notification doesn't happen if the underlying map (the one provided with the constructor) is modified directly. Also note
 * that this implementation supports only string keys because property change events only support string property names.
 * @param <E> the value type
 */
@Copyright(year = 2010, holders = "Sascha Baumeister")
public class CallbackMap<E> extends AbstractMap<String,E>implements Map<String,E> {

	/**
	 * Represents a change listener predicate (boolean-valued function) of three arguments.
	 * @param <E> the value type
	 * @see java.util.function.Predicate
	 */
	@FunctionalInterface
	static public interface ChangeListener<E> {

	    /**
	     * Evaluates this predicate on the given arguments.
	     * @param key the key
	     * @param oldValue the old value before the change
	     * @param oldValue the new value after the change
	     * @return {@code true} if the change may proceed, otherwise {@code false}
	     * @throws NullPointerException if the given key is {@code null}
	     */
	    boolean test (String key, E oldValue, E newValue) throws NullPointerException;
	}



	private final Map<String,E> delegateMap;
	private final CallbackEntrySet<E> entrySet;
	private final List<ChangeListener<E>> listeners;


	/**
	 * Initializes a new instance based on an empty hash map.
	 */
	public CallbackMap () {
		this(new HashMap<String,E>());
	}


	/**
	 * Initializes a new instance based on the delegate map. Note that no put events are spawned for the elements already existing
	 * within the delegate map.
	 * @param delegateMap the underlying map
	 * @see Map#putAll(Map)
	 */
	public CallbackMap (final Map<String,E> delegateMap) {
		this.delegateMap = delegateMap;
		this.entrySet = new CallbackEntrySet<>(this);
		this.listeners = new CopyOnWriteArrayList<>();
	}


	/**
	 * Returns the underlying delegate map. Note that event notifications don't take place when modifying the delegate map
	 * directly, which is the intended use-case for this method.
	 * @return the delegate map
	 */
	public Map<String,E> getDelegateMap () {
		return this.delegateMap;
	}


	/**
	 * Returns the registered change listeners.
	 * @return the change listeners
	 */
	public List<ChangeListener<E>> getListeners () {
		return this.listeners;
	}


	/**
	 * Notifies all listeners of an impeding change. If one of the listeners vetoes the change, this map will not perform it.
	 * @param key the key
	 * @param oldValue the old value
	 * @param newValue the new value
	 * @throws IllegalStateException if one of the listeners vetoes the change
	 */
	private void notifyChangeListeners (final String key, final E oldValue, final E newValue) throws IllegalStateException {
		for (final ChangeListener<E> listener : this.listeners) {
			if (!listener.test(key, oldValue, newValue)) throw new IllegalStateException(key);
		}
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Entry<String,E>> entrySet () {
		return this.entrySet;
	}


	/**
	 * {@inheritDoc}
	 * @throws ClassCastException {@inheritDoc}
	 * @throws NullPointerException {@inheritDoc}
	 */
	@Override
	public boolean containsKey (final Object key) {
		return this.delegateMap.containsKey(key);
	}


	/**
	 * {@inheritDoc}
	 * @throws ClassCastException {@inheritDoc}
	 * @throws NullPointerException {@inheritDoc}
	 */
	@Override
	public E get (final Object key) {
		return this.delegateMap.get(key);
	}


	/**
	 * {@inheritDoc}
	 * @throws UnsupportedOperationException {@inheritDoc}
	 * @throws NullPointerException {@inheritDoc}
	 * @throws ClassCastException {@inheritDoc}
	 * @throws IllegalArgumentException {@inheritDoc}
	 * @throws IllegalStateException if a listener vetoes the change
	 */
	@Override
	public E put (final String key, final E value) throws IllegalStateException {
		final E oldValue = this.delegateMap.get(key);
		this.notifyChangeListeners(key, oldValue, value);
		return this.delegateMap.put(key, value);
	}


	/**
	 * {@inheritDoc}
	 * @throws UnsupportedOperationException {@inheritDoc}
	 * @throws NullPointerException {@inheritDoc}
	 * @throws ClassCastException {@inheritDoc}
	 * @throws IllegalStateException if a listener vetoes the change
	 */
	@Override
	public E remove (final Object key) throws IllegalStateException {
		if (!this.containsKey(key)) return null;

		final Map.Entry<String,E> entry = new AbstractMap.SimpleEntry<>((String) key, this.delegateMap.get(key));
		this.notifyChangeListeners((String) key, entry.getValue(), null);

		this.entrySet.remove(entry);
		return entry.getValue();
	}



	/**
	 * Inner class defining an entry set view on a callback map.
	 */
	static private final class CallbackEntrySet<E> extends AbstractSet<Entry<String,E>> {
		private final CallbackMap<E> parent;

		public CallbackEntrySet (final CallbackMap<E> parent) {
			this.parent = Objects.requireNonNull(parent);
		}


		@Override
		public int size () {
			return this.parent.delegateMap.entrySet().size();
		}


		@Override
		public boolean contains (final Object object) {
			return this.parent.delegateMap.entrySet().contains(object);
		}


		@Override
		public boolean remove (final Object object) throws IllegalStateException {
			if (!this.contains(object)) return false;

			@SuppressWarnings("unchecked")
			final Map.Entry<String,E> entry = (Map.Entry<String,E>) object;
			if (this.contains(object)) this.parent.notifyChangeListeners(entry.getKey(), entry.getValue(), null);
			return this.parent.delegateMap.entrySet().remove(object);
		}


		@Override
		public Iterator<Map.Entry<String,E>> iterator () {
			return new CallbackEntrySetIterator<>(this);
		}



		/**
		 * Inner class defining an entry set view's callback iterator.
		 */
		static private final class CallbackEntrySetIterator<E> implements Iterator<Map.Entry<String,E>> {
			private final CallbackEntrySet<E> parent;
			private final Iterator<Map.Entry<String,E>> iterator;
			private Map.Entry<String,E> currentEntry;

			public CallbackEntrySetIterator (final CallbackEntrySet<E> parent) {
				this.parent = Objects.requireNonNull(parent);
				this.iterator = parent.parent.delegateMap.entrySet().iterator();
				this.currentEntry = null;
			}

			@Override
			public boolean hasNext () {
				return this.iterator.hasNext();
			}

			@Override
			public Map.Entry<String,E> next () {
				return this.currentEntry = this.iterator.next();
			}


			@Override
			public void remove () throws IllegalStateException {
				this.parent.parent.notifyChangeListeners(this.currentEntry.getKey(), this.currentEntry.getValue(), null);
				this.iterator.remove();
			}
		}
	}
}
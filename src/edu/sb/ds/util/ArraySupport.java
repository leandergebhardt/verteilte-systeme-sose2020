package edu.sb.ds.util;

import java.util.Objects;


/**
 * Facade for common array search operations.
 */
@Copyright(year = 2019, holders = "Sascha Baumeister")
public class ArraySupport {

	/**
	 * Prevents external instantiation.
	 */
	private ArraySupport () {}


	/**
	 * Returns the first index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the first index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int firstIndexOf (final boolean[] array, final boolean[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final boolean first = subArray[0];
		for (int index = offset, stop = array.length - subArray.length + 1; index < stop; ++index) {
			if (array[index] == first && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the first index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the first index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int firstIndexOf (final char[] array, final char[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final char first = subArray[0];
		for (int index = offset, stop = array.length - subArray.length + 1; index < stop; ++index) {
			if (array[index] == first && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the first index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the first index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int firstIndexOf (final byte[] array, final byte[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final byte first = subArray[0];
		for (int index = offset, stop = array.length - subArray.length + 1; index < stop; ++index) {
			if (array[index] == first && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the first index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the first index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int firstIndexOf (final short[] array, final short[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final short first = subArray[0];
		for (int index = offset, stop = array.length - subArray.length + 1; index < stop; ++index) {
			if (array[index] == first && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the first index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the first index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int firstIndexOf (final int[] array, final int[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final int first = subArray[0];
		for (int index = offset, stop = array.length - subArray.length + 1; index < stop; ++index) {
			if (array[index] == first && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the first index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the first index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int firstIndexOf (final long[] array, final long[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final long first = subArray[0];
		for (int index = offset, stop = array.length - subArray.length + 1; index < stop; ++index) {
			if (array[index] == first && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the first index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the first index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int firstIndexOf (final float[] array, final float[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final float first = subArray[0];
		for (int index = offset, stop = array.length - subArray.length + 1; index < stop; ++index) {
			if (array[index] == first && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the first index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the first index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int firstIndexOf (final double[] array, final double[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final double first = subArray[0];
		for (int index = offset, stop = array.length - subArray.length + 1; index < stop; ++index) {
			if (array[index] == first && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the first index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the first index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int firstIndexOf (final Object[] array, final Object[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final Object first = subArray[0];
		for (int index = offset, stop = array.length - subArray.length + 1; index < stop; ++index) {
			if (Objects.equals(array[index], first) && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the last index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the last index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int lastIndexOf (final boolean[] array, final boolean[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final boolean first = subArray[0];
		for (int index = offset; index >= 0; --index) {
			if (array[index] == first && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the last index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the last index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int lastIndexOf (final char[] array, final char[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final char first = subArray[0];
		for (int index = offset; index >= 0; --index) {
			if (array[index] == first && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the last index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the last index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int lastIndexOf (final byte[] array, final byte[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final byte first = subArray[0];
		for (int index = offset; index >= 0; --index) {
			if (array[index] == first && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the last index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the last index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int lastIndexOf (final short[] array, final short[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final short first = subArray[0];
		for (int index = offset; index >= 0; --index) {
			if (array[index] == first && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the last index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the last index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int lastIndexOf (final int[] array, final int[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final int first = subArray[0];
		for (int index = offset; index >= 0; --index) {
			if (array[index] == first && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the last index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the last index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int lastIndexOf (final long[] array, final long[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final long first = subArray[0];
		for (int index = offset; index >= 0; --index) {
			if (array[index] == first && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the last index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the last index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int lastIndexOf (final float[] array, final float[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final float first = subArray[0];
		for (int index = offset; index >= 0; --index) {
			if (array[index] == first && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the last index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the last index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int lastIndexOf (final double[] array, final double[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final double first = subArray[0];
		for (int index = offset; index >= 0; --index) {
			if (array[index] == first && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns the last index of the given sub-array found within the given array.
	 * @param array the array being searched
	 * @param subArray the array being searched for
	 * @param offset the offset to begin searching from
	 * @return the last index of the given sub-array within the given array, or {@code -1} for none
	 */
	static public int lastIndexOf (final Object[] array, final Object[] subArray, final int offset) throws NullPointerException, IllegalArgumentException {
		if (offset < 0 | offset > array.length) throw new IllegalArgumentException();
		if (offset == array.length) return -1;
		if (subArray.length == 0) return offset;

		final Object first = subArray[0];
		for (int index = offset; index >= 0; --index) {
			if (Objects.equals(array[index], first) && containsAt(array, subArray, index)) return index;
		}

		return -1;
	}


	/**
	 * Returns whether or not the given array contains the given sub-array at the specified position.
	 * @param array the array being searched
	 * @param position the array offset to begin searching from
	 * @param subArray the array being searched for
	 * @return {@code true} if the given array contains the given sub-array at the given position, {@code false} otherwise
	 */
	static private boolean containsAt (final boolean[] array, final boolean[] subArray, final int position) throws NullPointerException, IllegalArgumentException {
		for (int index = 0; index < subArray.length; ++index) {
			if (array[position + index] != subArray[index]) return false;
		}
		return true;
	}


	/**
	 * Returns whether or not the given array contains the given sub-array at the specified position.
	 * @param array the array being searched
	 * @param position the array offset to begin searching from
	 * @param subArray the array being searched for
	 * @return {@code true} if the given array contains the given sub-array at the given position, {@code false} otherwise
	 */
	static private boolean containsAt (final char[] array, final char[] subArray, final int position) throws NullPointerException, IllegalArgumentException {
		for (int index = 0; index < subArray.length; ++index) {
			if (array[position + index] != subArray[index]) return false;
		}
		return true;
	}


	/**
	 * Returns whether or not the given array contains the given sub-array at the specified position.
	 * @param array the array being searched
	 * @param position the array offset to begin searching from
	 * @param subArray the array being searched for
	 * @return {@code true} if the given array contains the given sub-array at the given position, {@code false} otherwise
	 */
	static private boolean containsAt (final byte[] array, final byte[] subArray, final int position) throws NullPointerException, IllegalArgumentException {
		for (int index = 0; index < subArray.length; ++index) {
			if (array[position + index] != subArray[index]) return false;
		}
		return true;
	}


	/**
	 * Returns whether or not the given array contains the given sub-array at the specified position.
	 * @param array the array being searched
	 * @param position the array offset to begin searching from
	 * @param subArray the array being searched for
	 * @return {@code true} if the given array contains the given sub-array at the given position, {@code false} otherwise
	 */
	static private boolean containsAt (final short[] array, final short[] subArray, final int position) throws NullPointerException, IllegalArgumentException {
		for (int index = 0; index < subArray.length; ++index) {
			if (array[position + index] != subArray[index]) return false;
		}
		return true;
	}


	/**
	 * Returns whether or not the given array contains the given sub-array at the specified position.
	 * @param array the array being searched
	 * @param position the array offset to begin searching from
	 * @param subArray the array being searched for
	 * @return {@code true} if the given array contains the given sub-array at the given position, {@code false} otherwise
	 */
	static private boolean containsAt (final int[] array, final int[] subArray, final int position) throws NullPointerException, IllegalArgumentException {
		for (int index = 0; index < subArray.length; ++index) {
			if (array[position + index] != subArray[index]) return false;
		}
		return true;
	}


	/**
	 * Returns whether or not the given array contains the given sub-array at the specified position.
	 * @param array the array being searched
	 * @param position the array offset to begin searching from
	 * @param subArray the array being searched for
	 * @return {@code true} if the given array contains the given sub-array at the given position, {@code false} otherwise
	 */
	static private boolean containsAt (final long[] array, final long[] subArray, final int position) throws NullPointerException, IllegalArgumentException {
		for (int index = 0; index < subArray.length; ++index) {
			if (array[position + index] != subArray[index]) return false;
		}
		return true;
	}


	/**
	 * Returns whether or not the given array contains the given sub-array at the specified position.
	 * @param array the array being searched
	 * @param position the array offset to begin searching from
	 * @param subArray the array being searched for
	 * @return {@code true} if the given array contains the given sub-array at the given position, {@code false} otherwise
	 */
	static private boolean containsAt (final float[] array, final float[] subArray, final int position) throws NullPointerException, IllegalArgumentException {
		for (int index = 0; index < subArray.length; ++index) {
			if (array[position + index] != subArray[index]) return false;
		}
		return true;
	}


	/**
	 * Returns whether or not the given array contains the given sub-array at the specified position.
	 * @param array the array being searched
	 * @param position the array offset to begin searching from
	 * @param subArray the array being searched for
	 * @return {@code true} if the given array contains the given sub-array at the given position, {@code false} otherwise
	 */
	static private boolean containsAt (final double[] array, final double[] subArray, final int position) throws NullPointerException, IllegalArgumentException {
		for (int index = 0; index < subArray.length; ++index) {
			if (array[position + index] != subArray[index]) return false;
		}
		return true;
	}


	/**
	 * Returns whether or not the given array contains the given sub-array at the specified position.
	 * @param array the array being searched
	 * @param position the array offset to begin searching from
	 * @param subArray the array being searched for
	 * @return {@code true} if the given array contains the given sub-array at the given position, {@code false} otherwise
	 */
	static private boolean containsAt (final Object[] array, final Object[] subArray, final int position) throws NullPointerException, IllegalArgumentException {
		for (int index = 0; index < subArray.length; ++index) {
			if (!Objects.equals(array[position + index], subArray[index])) return false;
		}
		return true;
	}

}

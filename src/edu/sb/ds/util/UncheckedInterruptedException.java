package edu.sb.ds.util;


/**
 * Unchecked wrapper exception type for class {@link InterruptedException}.
 */
@Copyright(year=2018, holders="Sascha Baumeister")
public class UncheckedInterruptedException extends RuntimeException {
	static private final long serialVersionUID = -2213445626461120133L;


	/**
	 * Initializes a new instance based on no cause.
	 */
	public UncheckedInterruptedException () {
		super();
	}


	/**
	 * Initializes a new instance based on the give cause.
	 * @param cause the cause
	 * @throws NullPointerException if the given argument is {@code null}
	 */
	public UncheckedInterruptedException (final InterruptedException cause) throws NullPointerException {
		super(cause.getMessage(), cause);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public InterruptedException getCause () {
		return (InterruptedException) super.getCause();
	}
}
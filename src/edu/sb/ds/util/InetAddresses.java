package edu.sb.ds.util;

import static java.lang.Integer.parseUnsignedInt;
import static java.net.InetSocketAddress.createUnresolved;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;


/**
 * This facade offers IP address and TCP/UDP socket address related factory operations.
 */
@Copyright(year = 2015, holders = "Sascha Baumeister")
public final class InetAddresses {

	/**
	 * Prevents external instantiation.
	 */
	private InetAddresses () {}


	/**
     * Returns the name of the local host retrieved from the system.
	 * If this fails, the local loopback "localhost" is returned instead.
	 * @return the name of the local host
	 */
	static public String localHostname () {
		try {
			return InetAddress.getLocalHost().getCanonicalHostName();
		} catch (final UnknownHostException exception) {
			return "localhost";
		}
	}


	/**
     * Returns the address of the local host. This is achieved by retrieving
     * the name of the host from the system, then resolving that name into
     * an {@code InetAddress}. If this fails, a local loopback address is
     * returned instead.
	 * @return the local address
	 */
	static public InetAddress localAddress () {
		try {
			return InetAddress.getLocalHost();
		} catch (final UnknownHostException exception) {
			return InetAddress.getLoopbackAddress();
		}
	}


	/**
	 * Returns a new socket address using a local address, and the given port.
	 * @param port the port
	 * @return the socket address created
     * @throws IllegalArgumentException if the argument does not represent a valid port
	 */
	static public InetSocketAddress localSocketAddress (final int port) throws IllegalArgumentException, SecurityException {
		return new InetSocketAddress(localAddress(), port);
	}


	/**
	 * Returns a new socket address parsed from the given text, as described in the
	 * following EBNF syntax:<pre>
	 *    socketAddress ::= [hostname | port] | [hostname], ':', [port]
	 *    hostname      ::= {(a-z | A-Z | 0-9 | '.' | ':')}
	 *    port          ::= (0-9), {(0-9)} within range [0,65535]</pre>
	 * Valid examples are ":", "80", ":80", "127.0.1.1:80", "::1:", "en.wikipedia.org".
	 * @param text the textual representation of a socket-address
	 * @throws NullPointerException if the given argument is {@code null}
	 * @throws IllegalArgumentException if the given argument does not represent a valid socket address
	 * @throws SecurityException if a security manager prohibits this operation
	 */
	static public InetSocketAddress socketAddress (final String text) throws NullPointerException, IllegalArgumentException, SecurityException {
		final int delimiterOffset = text.lastIndexOf(':');

		int port;
		String hostname;
		if (delimiterOffset == -1) {
			try {
				port = parseUnsignedInt(text);
				hostname = localHostname();
			} catch (final NumberFormatException exception) {
				port = 0;
				hostname = text.isEmpty() ? localHostname() : text;
			}
		} else {
			port = delimiterOffset == text.length() - 1 ? 0 : parseUnsignedInt(text.substring(delimiterOffset + 1));
			hostname = delimiterOffset == 0 ? localHostname() : text.substring(0, delimiterOffset);
		}

		return createUnresolved(hostname, port);
	}


	static public void main (String[] args) {
		System.out.println(socketAddress(""));
		System.out.println(socketAddress(":"));
		System.out.println(socketAddress("80"));
		System.out.println(socketAddress(":80"));
		System.out.println(socketAddress("en.wikipedia.org"));
		System.out.println(socketAddress("en.wikipedia.org:"));
		System.out.println(socketAddress("en.wikipedia.org:80"));
		System.out.println(socketAddress("127.0.0.1"));
		System.out.println(socketAddress("127.0.0.1:"));
		System.out.println(socketAddress("127.0.0.1:80"));
		System.out.println(socketAddress("::1"));
		System.out.println(socketAddress("::1:"));
		System.out.println(socketAddress("::1:80"));
	}
}
package edu.sb.ds.util;

import static java.lang.Math.max;
import static java.lang.Math.round;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * This type models HTTP cookies, as defined in RFC 6265 (HTTP State Management Mechanism).
 */
@Copyright(year=2010, holders="Sascha Baumeister")
public class HttpCookie {
	static private final DateFormat DATE_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");

	private final String name;
	private final String value;
	private Long expires;
	private String path;
	private String domain;
	private boolean encryptedChannelsOnly;
	private boolean channelInterfacesOnly;


	/**
	 * Initializes a new instance.
	 * @param name the name
	 * @param value the value
	 * @param expires the (optional) expiration timestamp in milliseconds since 1/1/1970, or {@code null} for none
	 * @param path the (optional) path, or {@code null} for none
	 * @param domain the (optional) domain, or {@code null} for none
	 * @param encryptedChannelsOnly whether or not this cookie has to be omitted when using non-encrypted channels
	 * @param channelInterfacesOnly whether or not this cookie has to be omitted when providing cookie access via non-network APIs
	 * @throws NullPointerException if the given name or value is {@code null}
	 */
	public HttpCookie (final String name, final String value, final Long expires, final String path, final String domain, final boolean encryptedChannelsOnly, final boolean channelInterfacesOnly) throws NullPointerException {
		if (name == null | value == null) throw new NullPointerException();

		this.name = name;
		this.value = value;
		this.expires = expires;
		this.path = path;
		this.domain = domain;
		this.encryptedChannelsOnly = encryptedChannelsOnly;
		this.channelInterfacesOnly = channelInterfacesOnly;
	}


	/**
	 * Returns the name.
	 * @return the name
	 */
	public String getName () {
		return this.name;
	}


	/**
	 * Returns the value.
	 * @return the value
	 */
	public String getValue () {
		return this.value;
	}


	/**
	 * Returns the expiration timestamp.
	 * @return the expiration timestamp in milliseconds since 1/1/1970, or {@code null} for none
	 */
	public Long getExpires () {
		return this.expires;
	}


	/**
	 * Sets the expiration timestamp.
	 * @param expires the expiration timestamp in milliseconds since 1/1/1970, or {@code null} for none
	 */
	public void setExpires (final Long expires) {
		this.expires = expires;
	}


	/**
	 * Returns the path.
	 * @return the path, or {@code null} for none
	 */
	public String getPath () {
		return this.path;
	}


	/**
	 * Sets the path.
	 * @param path the path, or {@code null} for none
	 */
	public void setPath (final String path) {
		this.path = path;
	}


	/**
	 * Returns the domain.
	 * @return the domain, or {@code null} for none
	 */
	public String getDomain () {
		return this.domain;
	}


	/**
	 * Sets the domain.
	 * @param domain the domain, or {@code null} for none
	 */
	public void setDomain (final String domain) {
		this.domain = domain;
	}


	/**
	 * Returns the encrypted channel restriction.
	 * @return whether or not this cookie has to be omitted when using non-encrypted channels
	 */
	public boolean getEncryptedChannelsOnly () {
		return this.encryptedChannelsOnly;
	}


	/**
	 * Sets the encrypted channel restriction.
	 * @param encryptedChannelsOnly whether or not this cookie has to be omitted when using non-encrypted channels
	 */
	public void setEncryptedChannelsOnly (final boolean encryptedChannelsOnly) {
		this.encryptedChannelsOnly = encryptedChannelsOnly;
	}


	/**
	 * Returns the channel interface restriction.
	 * @return whether or not this cookie has to be omitted when providing cookie access via non-network APIs
	 */
	public boolean getChannelInterfacesOnly () {
		return this.channelInterfacesOnly;
	}


	/**
	 * Sets the channel interface restriction.
	 * @param channelInterfacesOnly whether or not this cookie has to be omitted when providing cookie access via non-network APIs
	 */
	public void setChannelInterfacesOnly (final boolean channelInterfacesOnly) {
		this.channelInterfacesOnly = channelInterfacesOnly;
	}


	/**
	 * {@inheritDoc}
	 */
	public String toString () {
		return String.format("%s@(name=%s, value=%s, expires=%d, path=%s, domain=%s, encryptedChannelsOnly=%b, channelInterfacesOnly=%b)", this.getClass().getName(), this.name, this.value, this.expires, this.path, this.domain, this.encryptedChannelsOnly, this.channelInterfacesOnly);
	}


	/**
	 * Returns a new HTTP cookie representation marshaled from the given cookie instance.
	 * @param cookie the cookie instance
	 * @return the HTTP cookie representation created
	 * @throws NullPointerException if the given argument is {@code null}
	 */
	static public String marshal (final HttpCookie cookie) throws NullPointerException {
		final StringBuilder builder = new StringBuilder();
		builder.append(cookie.name.trim());
		builder.append('=');
		builder.append(cookie.value.trim());

		if (cookie.expires != null) builder.append("; Max-Age=" + round(0.001 * (cookie.expires - System.currentTimeMillis())));
		if (cookie.path != null) builder.append("; Path=" + cookie.path.trim());
		if (cookie.domain != null) builder.append("; Domain=" + cookie.domain);
		if (cookie.encryptedChannelsOnly) builder.append("; Secure");
		if (cookie.channelInterfacesOnly) builder.append("; HttpOnly");

		return builder.toString();
	}


	/**
	 * Returns a new cookie instance unmarshaled from the given HTTP cookie representation.
	 * @param httpCookie the HTTP cookie representation
	 * @return the cookie instance created
	 * @throws NullPointerException if the given argument is {@code null}
	 * @throws IllegalArgumentException if the cookie representation is not valid
	 */
	static public HttpCookie unmarshal (final String httpCookie) throws NullPointerException, IllegalArgumentException {
		final String[] fragments = httpCookie.split(";");
		if (fragments.length == 0) throw new IllegalArgumentException();
		int delimiterPosition;

		if ((delimiterPosition = fragments[0].indexOf('=')) == -1) throw new IllegalArgumentException();
		final String name = fragments[0].substring(0, delimiterPosition).trim();
		final String value = fragments[0].substring(delimiterPosition + 1).trim();

		boolean encryptedChannelsOnly = false, channelInterfacesOnly = false;
		String path = null, domain = null;
		Long expires = null;
		for (int index = 1; index < fragments.length; ++index) {
			final String fragment = fragments[index];

			if ((delimiterPosition = fragment.indexOf('=')) == -1) {
				switch (fragment.trim()) {
					case "Secure":
						encryptedChannelsOnly = true;
						break;
					case "HttpOnly":
						channelInterfacesOnly = true;
						break;
					default:
						// ignore attribute as specified in RFC 6265
						break;
				}
			} else {
				final String text = fragment.substring(delimiterPosition + 1).trim();
				switch (fragment.substring(0, delimiterPosition).trim()) {
					case "Max-Age":
						try {
							expires = System.currentTimeMillis() + 1000 * max(0, Long.parseLong(text));
						} catch (final NumberFormatException exception) {
							// ignore attribute as specified in RFC 6265
						}
						break;
					case "Expires":
						try {
							expires = DATE_FORMAT.parse(text).getTime();
						} catch (ParseException e) {
							// ignore attribute as specified in RFC 6265
						}
						break;
					case "Path":
						path = text.startsWith("/") ? text : "/";
						break;
					case "Domain":
						domain = (text.startsWith(".") ? text.substring(1) : text).toLowerCase();
						break;
					default:
						// ignore attribute as specified in RFC 6265
						break;
				}
			}
		}

		return new HttpCookie(name, value, expires, path, domain, encryptedChannelsOnly, channelInterfacesOnly);
	}
}
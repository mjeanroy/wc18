/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Mickael Jeanroy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.mjeanroy.wc18.it.json;

import com.google.common.base.MoreObjects;

import java.util.Objects;

class JsonEntry implements JsonValue {

	static JsonEntry entry(String key, Object value) {
		return new JsonEntry(key, value);
	}

	/**
	 * The JSON entry key.
	 */
	private final String key;

	/**
	 * The JSON value.
	 */
	private final Object value;

	/**
	 * Create the JSON entry.
	 *
	 * @param key Entry key.
	 * @param value Entry value.
	 */
	private JsonEntry(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Get {@link #key}
	 *
	 * @return {@link #key}
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Get {@link #value}
	 *
	 * @return {@link #value}
	 */
	public Object getValue() {
		return value;
	}

	@Override
	public String serialize() {
		return serializeKey() + ": " + serializeValue();
	}

	private String serializeKey() {
		return "\"" + key + "\"";
	}

	private String serializeValue() {
		if (value == null) {
			return "null";
		}

		if (value instanceof  JsonValue) {
			return ((JsonValue) value).serialize();
		}

		if (value instanceof String) {
			return "\"" + value + "\"";
		}

		return value.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (o instanceof JsonEntry) {
			JsonEntry e = (JsonEntry) o;
			return Objects.equals(key, e.key) && Objects.equals(value, e.value);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, value);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass())
			.add("key", key)
			.add("value", value)
			.toString();
	}
}

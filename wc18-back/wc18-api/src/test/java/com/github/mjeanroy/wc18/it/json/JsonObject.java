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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.github.mjeanroy.wc18.it.json.JsonEntry.entry;

/**
 * A JSON Object.
 */
public class JsonObject implements JsonValue {

	/**
	 * The object entries.
	 */
	private final List<JsonEntry> entries;

	/**
	 * Create the JSON object.
	 *
	 * @param entries Object entries.
	 */
	private JsonObject(List<JsonEntry> entries) {
		this.entries = new ArrayList<>(entries);
	}

	@Override
	public String serialize() {
		return "{" + entries.stream().map(JsonEntry::serialize).collect(Collectors.joining(",")) + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (o instanceof JsonObject) {
			JsonObject obj = (JsonObject) o;
			return Objects.equals(entries, obj.entries);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(entries);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass())
			.add("entries", entries)
			.toString();
	}

	/**
	 * Builder instance to create JSON object.
	 */
	public static class Builder {

		/**
		 * The list of JSON Object entries.
		 */
		private final List<JsonEntry> entries;

		/**
		 * Create the builder.
		 */
		Builder() {
			this.entries = new ArrayList<>();
		}

		/**
		 * Add new entry.
		 *
		 * @param key Entry key.
		 * @param value Entry value.
		 * @return The builder.
		 */
		public Builder add(String key, String value) {
			this.entries.add(entry(key, value));
			return this;
		}

		/**
		 * Add new entry.
		 *
		 * @param key Entry key.
		 * @param value Entry value.
		 * @return The builder.
		 */
		public Builder add(String key, int value) {
			this.entries.add(entry(key, value));
			return this;
		}

		/**
		 * Add new entry.
		 *
		 * @param key Entry key.
		 * @param object Entry value.
		 * @return The builder.
		 */
		public Builder add(String key, JsonObject object) {
			this.entries.add(entry(key, object));
			return this;
		}

		/**
		 * Build the JSON Object and add the new entry.
		 *
		 * @param key Entry key.
		 * @param object Entry value.
		 * @return The builder.
		 */
		public Builder add(String key, JsonObject.Builder object) {
			return add(key, object.build());
		}

		/**
		 * Serialize the current builded JSON object to a JSON string value.
		 *
		 * @return JSON String value.
		 */
		public String serialize() {
			return build().serialize();
		}

		/**
		 * Build the JSON Object.
		 *
		 * @return The JSON Object.
		 */
		public JsonObject build() {
			return new JsonObject(entries);
		}
	}
}

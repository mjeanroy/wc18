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

package com.github.mjeanroy.wc18.it.junit;

import com.github.mjeanroy.junit.servers.servers.EmbeddedServer;
import com.github.mjeanroy.junit.servers.servers.Hook;

import javax.servlet.ServletContext;
import java.util.TimeZone;

public class TimeZoneContextHook implements Hook {

	/**
	 * Get hook.
	 *
	 * @return Hook.
	 */
	public static TimeZoneContextHook get() {
		return new TimeZoneContextHook();
	}

	/**
	 * The timezone before running tests.
	 */
	private TimeZone tz;

	// Ensure non instantiation.
	private TimeZoneContextHook() {
	}

	@Override
	public void pre(EmbeddedServer server) {
		tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	@Override
	public void post(EmbeddedServer server) {
		TimeZone.setDefault(tz);
		tz = null;
	}

	@Override
	public void onStarted(EmbeddedServer server, ServletContext servletContext) {
	}
}

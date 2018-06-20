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

package com.github.mjeanroy.wc18.security.parsers;

import com.github.mjeanroy.wc18.security.encoders.TokenEncoder;
import com.github.mjeanroy.wc18.security.tests.builders.HttpServletRequestBuilder;
import com.github.mjeanroy.wc18.security.tests.builders.HttpServletResponseBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthHeaderTokenParserTest {

	private String headerName;
	private TokenEncoder tokenEncoder;
	private AuthHeaderTokenParser parser;

	@Before
	public void setUp() {
		headerName = "X-Auth-Token";
		tokenEncoder = mock(TokenEncoder.class);
		parser = new AuthHeaderTokenParser(headerName, tokenEncoder);

		initTokenEncoder();
	}

	private void initTokenEncoder() {
		when(tokenEncoder.encode(anyString())).thenAnswer(invocationOnMock ->
			"encoded:" + invocationOnMock.getArgument(0)
		);

		when(tokenEncoder.decode(anyString())).thenAnswer(invocationOnMock ->
			"decoded:" + invocationOnMock.getArgument(0)
		);
	}

	@Test
	public void it_should_parse_token() {
		String value = "test";
		HttpServletRequest rq = new HttpServletRequestBuilder()
			.withHeader(headerName, value)
			.build();

		String result = parser.parse(rq);

		assertThat(result).isEqualTo("decoded:" + value);
		verify(tokenEncoder).decode(value);
	}

	@Test
	public void it_should_parse_token_and_return_null_with_missing_header() {
		HttpServletRequest rq = new HttpServletRequestBuilder().build();
		String result = parser.parse(rq);
		assertThat(result).isNull();
	}

	@Test
	public void it_should() {
		HttpServletResponse rsp = new HttpServletResponseBuilder().build();
		String value = "test";
		boolean rememberMe = false;

		parser.put(rsp, value, rememberMe);

		assertThat(rsp.getHeader(headerName)).isEqualTo("encoded:" + value);
		verify(tokenEncoder).encode(value);
	}
}

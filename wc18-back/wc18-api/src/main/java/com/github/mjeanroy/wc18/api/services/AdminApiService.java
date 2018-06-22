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

package com.github.mjeanroy.wc18.api.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mjeanroy.wc18.api.dto.CommitDto;
import com.github.mjeanroy.wc18.api.exceptions.InvalidChangeLogException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.net.URL;

import static java.util.Arrays.asList;

@Service
public class AdminApiService {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(AdminApiService.class);

	private final ObjectMapper objectMapper;

	@Inject
	public AdminApiService(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	/**
	 * Read application changelog.
	 *
	 * @return List of commits in application changelog.
	 */
	public Iterable<CommitDto> readChangeLog() {
		try {
			URL resource = getClass().getClassLoader().getResource("changelog.json");
			return asList(objectMapper.readValue(resource, CommitDto[].class));
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new InvalidChangeLogException(ex);
		}
	}
}

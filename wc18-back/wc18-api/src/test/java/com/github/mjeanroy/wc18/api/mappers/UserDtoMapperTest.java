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

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.wc18.api.dto.UserDto;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.tests.builders.UserBuilder;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDtoMapperTest extends AbstractDtoMapperTest<User, UserDto, UserDtoMapper> {

	@Inject
	private UserDtoMapper userDtoMapper;

	@Override
	UserDtoMapper getMapper() {
		return userDtoMapper;
	}

	@Override
	User createInput() {
		return new UserBuilder()
			.withRandomId()
			.withLogin("John")
			.withPassword("azerty123")
			.build();
	}

	@Override
	void verifyOutput(User input, UserDto output) {
		assertThat(output.getId()).isEqualTo(input.getId());
		assertThat(output.getLogin()).isEqualTo(input.getLogin());
	}
}

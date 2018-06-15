/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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

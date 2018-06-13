/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.spring.mappers.Mapper;
import com.github.mjeanroy.wc18.api.dto.UserDto;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.tests.builders.UserBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDtoMapperTest extends AbstractDtoMapper<User, UserDto, UserDtoMapper> {

	@Override
	UserDtoMapper create(Mapper mapper) {
		LeagueDtoMapper leagueDtoMapper = new LeagueDtoMapper(mapper);
		return new UserDtoMapper(mapper, leagueDtoMapper);
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

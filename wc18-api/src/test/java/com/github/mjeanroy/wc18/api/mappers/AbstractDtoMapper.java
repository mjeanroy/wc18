/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.spring.mappers.Mapper;
import com.github.mjeanroy.spring.mappers.impl.spring.SpringMapper;
import com.github.mjeanroy.spring.mappers.objects.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractDtoMapper<T, U, MAPPER extends ObjectMapper<T, U>> {

	private MAPPER mapper;

	@Before
	public void initMockito() {
		mapper = create(new SpringMapper());
	}

	@Test
	public void it_should_map_input_to_output() {
		T input = createInput();
		U output = mapper.from(input);
		verifyOutput(input, output);
	}

	/**
	 * Get the mapper instance.
	 * @param mapper The internal object mapper implementation.
	 * @return The mapper.
	 */
	abstract MAPPER create(Mapper mapper);

	/**
	 * Create input value.
	 *
	 * @return Input value.
	 */
	abstract T createInput();

	/**
	 * Verify output value.
	 *
	 * @param input The mapped input.
	 * @param output The final output.
	 */
	abstract void verifyOutput(T input, U output);
}

/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.spring.mappers.configuration.EnableMapper;
import com.github.mjeanroy.spring.mappers.objects.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbstractDtoMapperTest.DtoMapperTestConfiguration.class)
public abstract class AbstractDtoMapperTest<T, U, MAPPER extends ObjectMapper<T, U>> {

	@Test
	public void it_should_map_input_to_output() {
		T input = createInput();
		U output = getMapper().from(input);
		verifyOutput(input, output);
	}

	/**
	 * Get the mapper instance.
	 *
	 * @return The mapper.
	 */
	abstract MAPPER getMapper();

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

	@Configuration
	@EnableMapper
	@ComponentScan("com.github.mjeanroy.wc18.api.mappers")
	static class DtoMapperTestConfiguration {
	}
}

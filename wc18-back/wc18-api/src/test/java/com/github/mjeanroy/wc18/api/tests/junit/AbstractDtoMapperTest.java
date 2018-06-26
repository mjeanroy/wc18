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

package com.github.mjeanroy.wc18.api.tests.junit;

import com.github.mjeanroy.spring.mappers.objects.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DtoMapperTestConfiguration.class)
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
	protected abstract MAPPER getMapper();

	/**
	 * Create input value.
	 *
	 * @return Input value.
	 */
	protected abstract T createInput();

	/**
	 * Verify output value.
	 *
	 * @param input The mapped input.
	 * @param output The final output.
	 */
	protected abstract void verifyOutput(T input, U output);

}

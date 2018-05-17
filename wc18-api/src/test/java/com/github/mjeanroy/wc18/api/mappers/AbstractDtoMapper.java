/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.spring.mappers.Mapper;
import com.github.mjeanroy.spring.mappers.impl.spring.SpringMapper;
import org.junit.Before;
import org.mockito.Spy;

import static org.mockito.MockitoAnnotations.initMocks;

public abstract class AbstractDtoMapper {

	/**
	 * The default mapper.
	 */
	@Spy
	private Mapper mapper = new SpringMapper();

	@Before
	public void initMockito() {
		initMocks(this);
	}

	/**
	 * Get {@link #mapper}
	 *
	 * @return {@link #mapper}
	 */
	Mapper getMapper() {
		return mapper;
	}
}

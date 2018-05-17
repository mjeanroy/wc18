/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import org.junit.Before;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Abstract template for service test suite.
 */
public abstract class AbstractServiceTest {

	@Before
	public void initMockito() {
		initMocks(this);
	}
}

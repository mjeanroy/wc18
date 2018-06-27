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

package com.github.mjeanroy.wc18.api.tests.builders;

import com.github.mjeanroy.wc18.api.dto.StageDto;
import com.github.mjeanroy.wc18.domain.models.Stage;

/**
 * Builder for {@link StageDto} instances.
 */
public class StageDtoBuilder {

	/**
	 * The stage id.
	 *
	 * @see StageDto#id
	 */
	private String id;

	/**
	 * The stage label.
	 *
	 * @see StageDto#label
	 */
	private String label;

	/**
	 * Set {@link #id} and {@link #label}
	 *
	 * @param stage New stage.
	 * @return The builder.
	 */
	public StageDtoBuilder with(Stage stage) {
		this.id = stage.name();
		this.label = stage.getLabel();
		return this;
	}

	/**
	 * Build final {@link StageDto} instance.
	 *
	 * @return The final instance.
	 */
	public StageDto build() {
		StageDto dto = new StageDto();
		dto.setId(id);
		dto.setLabel(label);
		return dto;
	}
}

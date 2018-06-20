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

import com.github.mjeanroy.wc18.api.dto.PasswordDto;

/**
 * Builder for {@link PasswordDto} instances.
 */
public class PasswordDtoBuilder {

	/**
	 * The form oldPassword.
	 * @see PasswordDto#oldPassword
	 */
	private String oldPassword;

	/**
	 * The form newPassword.
	 * @see PasswordDto#newPassword
	 */
	private String newPassword;

	/**
	 * Set {@link #oldPassword}
	 *
	 * @param oldPassword New {@link #oldPassword}
	 * @return The builder.
	 */
	public PasswordDtoBuilder withOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
		return this;
	}

	/**
	 * Set {@link #newPassword}
	 *
	 * @param newPassword New {@link #newPassword}
	 * @return The builder.
	 */
	public PasswordDtoBuilder withNewPassword(String newPassword) {
		this.newPassword = newPassword;
		return this;
	}

	/**
	 * Build final {@link PasswordDto} instance.
	 *
	 * @return The final instance.
	 */
	public PasswordDto build() {
		PasswordDto dto = new PasswordDto();
		dto.setOldPassword(oldPassword);
		dto.setNewPassword(newPassword);
		return dto;
	}
}

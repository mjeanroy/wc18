/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.encoders;

public interface TokenEncoder {

	/**
	 * Encode token value.
	 *
	 * @param decodedValue The decoded value.
	 * @return The encoded value.
	 */
	String encode(String decodedValue);

	/**
	 * Decode token value.
	 *
	 * @param encodedValue The encoded value.
	 * @return The decoded value.
	 */
	String decode(String encodedValue);
}

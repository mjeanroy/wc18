/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.wc18.api.dto.MatchDto;
import com.github.mjeanroy.wc18.domain.models.Match;

import java.util.Collections;

public class MatchDtoMapper {

	public Iterable<MatchDto> map(Iterable<Match> matches) {
		return Collections.emptyList();
	}
}

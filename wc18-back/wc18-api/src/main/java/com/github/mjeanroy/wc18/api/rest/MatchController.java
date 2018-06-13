/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.rest;

import com.github.mjeanroy.wc18.api.dto.MatchDto;
import com.github.mjeanroy.wc18.api.services.MatchApiService;
import com.github.mjeanroy.wc18.security.Security;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * The {@link MatchDto} Rest Controller.
 */
@RestController
@RequestMapping("/api/matches")
public class MatchController {

	private final MatchApiService matchApiService;

	@Inject
	public MatchController(MatchApiService matchApiService) {
		this.matchApiService = matchApiService;
	}

	@GetMapping
	public Iterable<MatchDto> findAll(@RequestParam(name = "locked", required = false) Boolean locked) {
		return matchApiService.findAll(locked);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Security(role = "ADMIN")
	public MatchDto create(@RequestBody @Valid MatchDto match) {
		return matchApiService.create(match);
	}

	@PutMapping("/{id}")
	@Security(role = "ADMIN")
	public MatchDto update(@PathVariable("id") String id, @RequestBody @Valid MatchDto match) {
		return matchApiService.update(id, match);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Security(role = "ADMIN")
	public void remove(@PathVariable("id") String id) {
		matchApiService.remove(id);
	}
}

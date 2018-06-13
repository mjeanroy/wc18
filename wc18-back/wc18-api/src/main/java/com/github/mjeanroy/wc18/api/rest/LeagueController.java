/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.rest;

import com.github.mjeanroy.wc18.api.dto.LeagueDto;
import com.github.mjeanroy.wc18.api.services.LeagueApiService;
import com.github.mjeanroy.wc18.security.Security;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * The {@link LeagueDto} Rest Controller.
 */
@RestController
@RequestMapping("/api/leagues")
public class LeagueController {

	private final LeagueApiService leagueApiService;

	@Inject
	public LeagueController(LeagueApiService leagueApiService) {
		this.leagueApiService = leagueApiService;
	}

	@GetMapping
	public Iterable<LeagueDto> findAll() {
		return leagueApiService.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Security(role = "ADMIN")
	public LeagueDto create(@RequestBody @Valid LeagueDto leagueDto) {
		return leagueApiService.create(leagueDto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Security(role = "ADMIN")
	public void create(@PathVariable("id") String id) {
		leagueApiService.remove(id);
	}
}

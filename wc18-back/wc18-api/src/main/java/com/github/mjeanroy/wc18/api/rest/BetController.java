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

package com.github.mjeanroy.wc18.api.rest;

import com.github.mjeanroy.wc18.api.dto.BetDto;
import com.github.mjeanroy.wc18.api.dto.MatchDto;
import com.github.mjeanroy.wc18.api.services.BetApiService;
import com.github.mjeanroy.wc18.security.Security;
import com.github.mjeanroy.wc18.security.models.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * The {@link MatchDto} Rest Controller.
 */
@RestController
public class BetController {

	private final BetApiService betApiService;

	@Inject
	public BetController(BetApiService betApiService) {
		this.betApiService = betApiService;
	}

	@GetMapping("/api/me/bets")
	@Security
	public Iterable<BetDto> findAll(
			@RequestParam(name = "locked", required = false) Boolean locked,
			Principal principal) {

		return betApiService.findAll(principal, locked);
	}

	@PostMapping("/api/me/bets")
	@Security
	public BetDto create(Principal principal, @RequestBody @Valid BetDto bet) {
		return betApiService.save(principal, bet);
	}

	@PutMapping("/api/me/bets/{id}")
	@Security
	public BetDto update(Principal principal, @RequestBody @Valid BetDto bet) {
		return betApiService.save(principal, bet);
	}

	@PostMapping("/api/users/{userId}/bets")
	@Security(role = "ADMIN")
	public BetDto update(@PathVariable("userId") String userId, @RequestBody @Valid BetDto bet) {
		return betApiService.save(userId, bet);
	}
}

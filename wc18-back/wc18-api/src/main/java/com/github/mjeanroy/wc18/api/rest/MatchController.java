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

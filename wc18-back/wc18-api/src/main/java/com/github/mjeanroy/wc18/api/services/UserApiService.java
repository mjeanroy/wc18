/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.wc18.api.dto.LeagueDto;
import com.github.mjeanroy.wc18.api.dto.LoginDto;
import com.github.mjeanroy.wc18.api.dto.PasswordDto;
import com.github.mjeanroy.wc18.api.dto.UserDto;
import com.github.mjeanroy.wc18.api.exceptions.PrincipalNotFoundException;
import com.github.mjeanroy.wc18.api.mappers.LeagueDtoMapper;
import com.github.mjeanroy.wc18.api.mappers.UserDtoMapper;
import com.github.mjeanroy.wc18.domain.models.League;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.services.LeagueService;
import com.github.mjeanroy.wc18.domain.services.UserService;
import com.github.mjeanroy.wc18.security.models.Principal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class UserApiService {

	private final UserService userService;
	private final UserDtoMapper userDtoMapper;
	private final LeagueService leagueService;
	private final LeagueDtoMapper leagueDtoMapper;

	@Inject
	public UserApiService(
			UserService userService,
			UserDtoMapper userDtoMapper,
			LeagueService leagueService,
			LeagueDtoMapper leagueDtoMapper) {

		this.userService = userService;
		this.userDtoMapper = userDtoMapper;
		this.leagueService = leagueService;
		this.leagueDtoMapper = leagueDtoMapper;
	}

	/**
	 * Get all users.
	 *
	 * @return Users.
	 */
	@Transactional(readOnly = true)
	public Iterable<UserDto> findAll() {
		Iterable<User> users = userService.findAll();
		return userDtoMapper.from(users);
	}

	/**
	 * Get all leagues of given user.
	 *
	 * @param principal User principal.
	 * @return The leagues.
	 */
	@Transactional(readOnly = true)
	public Iterable<LeagueDto> findLeagues(Principal principal) {
		User user = findUserByLogin(principal.getLogin()).orElseThrow(() ->
			new PrincipalNotFoundException(principal)
		);

		Iterable<League> leagues = leagueService.findByUser(user);
		return leagueDtoMapper.from(leagues);
	}

	/**
	 * Create new account with login/password.
	 *
	 * @param loginDto The account.
	 * @return The new created user.
	 */
	@Transactional
	public UserDto create(LoginDto loginDto) {
		User user = userService.create(loginDto.getLogin(), loginDto.getPassword());
		return userDtoMapper.from(user);
	}

	/**
	 * Delete user account.
	 *
	 * @param id The user identifier.
	 */
	@Transactional
	public void remove(String id) {
		userService.remove(id);
	}

	/**
	 * Find the principal user.
	 *
	 * @param principal The principal.
	 * @return The authenticated user.
	 */
	@Transactional(readOnly = true)
	public UserDto findOne(Principal principal) {
		return findOne(principal.getLogin()).orElseThrow(() ->
			new PrincipalNotFoundException(principal)
		);
	}

	/**
	 * Find user by its login.
	 *
	 * @param login The user login.
	 * @return The user.
	 */
	private Optional<UserDto> findOne(String login) {
		return findUserByLogin(login).map(userDtoMapper::from);
	}

	/**
	 * Find user by its login.
	 *
	 * @param login The user login.
	 * @return The user.
	 */
	private Optional<User> findUserByLogin(String login) {
		return userService.findByLogin(login);
	}

	/**
	 * Update user password.
	 *
	 * @param principal The principal user, i.e the authenticated user.
	 * @param passwords The old and new password.
	 */
	@Transactional
	public void updatePassword(Principal principal, PasswordDto passwords) {
		User user = userService.findByLoginAndPassword(principal.getLogin(), passwords.getOldPassword()).orElseThrow(() ->
			new PrincipalNotFoundException(principal)
		);

		userService.updatePassword(user, passwords.getNewPassword());
	}
}

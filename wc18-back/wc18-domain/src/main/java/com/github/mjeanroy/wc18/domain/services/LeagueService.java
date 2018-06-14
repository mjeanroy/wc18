/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.LeagueDao;
import com.github.mjeanroy.wc18.domain.dao.UserDao;
import com.github.mjeanroy.wc18.domain.exceptions.LeagueNotFoundException;
import com.github.mjeanroy.wc18.domain.models.League;
import com.github.mjeanroy.wc18.domain.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
public class LeagueService {

	private final LeagueDao leagueDao;
	private final UserDao userDao;

	@Inject
	public LeagueService(LeagueDao leagueDao, UserDao userDao) {
		this.leagueDao = leagueDao;
		this.userDao = userDao;
	}

	/**
	 * Get all existing leagues.
	 *
	 * @return Existing leagues.
	 */
	@Transactional(readOnly = true)
	public Iterable<League> findAll() {
		return leagueDao.findAll();
	}

	/**
	 * Create a new league and returns result.
	 *
	 * @param name The league name.
	 * @return The result.
	 */
	@Transactional
	public League create(String name) {
		League league = new League(name);
		return leagueDao.save(league);
	}

	/**
	 * Remove league.
	 *
	 * @param id The league identifier to remove.
	 */
	@Transactional
	public void remove(String id) {
		League league = findOneOrFail(id);
		leagueDao.delete(league);
	}

	/**
	 * Find league, or fail if it does not exist.
	 *
	 * @param id League identifier.
	 * @return The league.
	 */
	@Transactional(readOnly = true)
	public League findOneOrFail(String id) {
		return leagueDao.findOne(id).orElseThrow(() ->
			new LeagueNotFoundException(id)
		);
	}

	/**
	 * Find all users in given league.
	 *
	 * @param league The league.
	 * @return The Found users.
	 */
	@Transactional(readOnly = false)
	public void addUser(League league, User user) {
		league.addUser(user);
		leagueDao.save(league);
	}
}

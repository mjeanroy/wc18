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

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.LeagueDao;
import com.github.mjeanroy.wc18.domain.exceptions.LeagueNotFoundException;
import com.github.mjeanroy.wc18.domain.models.League;
import com.github.mjeanroy.wc18.domain.models.Rank;
import com.github.mjeanroy.wc18.domain.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Set;

@Service
public class LeagueService {

	private final LeagueDao leagueDao;
	private final RankService rankService;

	@Inject
	public LeagueService(LeagueDao leagueDao, RankService rankService) {
		this.leagueDao = leagueDao;
		this.rankService = rankService;
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
	 * Get ranks for given league.
	 *
	 * @return Ranks.
	 */
	@Transactional(readOnly = true)
	public Iterable<Rank> findRanks(League league) {
		Set<User> users = league.getUsers();
		return rankService.getRanks(users);
	}

	/**
	 * Get all existing leagues of given user.
	 *
	 * @param user The user.
	 * @return Existing leagues.
	 */
	@Transactional(readOnly = true)
	public Iterable<League> findByUser(User user) {
		return leagueDao.findByUser(user);
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

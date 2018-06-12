/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.TeamDao;
import com.github.mjeanroy.wc18.domain.exceptions.TeamNotFoundException;
import com.github.mjeanroy.wc18.domain.models.Team;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class TeamService {

	private final TeamDao teamDao;

	@Inject
	public TeamService(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

	/**
	 * Find all teams.
	 *
	 * @return All teams.
	 */
	@Transactional(readOnly = true)
	public Iterable<Team> findAll() {
		return teamDao.findAllOrderByName();
	}

	/**
	 * Find match and return result (empty result if not found).
	 *
	 * @param id Team identifier.
	 * @return The found team.
	 */
	@Transactional(readOnly = true)
	public Optional<Team> findOne(String id) {
		return teamDao.findOne(id);
	}

	/**
	 * Find team and return result or fail with {@link TeamNotFoundException}
	 * if team doest not exist.
	 *
	 * @param id Team identifier.
	 * @return The found team.
	 * @throws TeamNotFoundException If team does not exist.
	 */
	@Transactional(readOnly = true)
	public Team findOneOrFail(String id) {
		return teamDao.findOne(id).orElseThrow(() ->
			new TeamNotFoundException(id)
		);
	}
}

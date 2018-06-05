/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.TeamDao;
import com.github.mjeanroy.wc18.domain.models.Team;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

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
}

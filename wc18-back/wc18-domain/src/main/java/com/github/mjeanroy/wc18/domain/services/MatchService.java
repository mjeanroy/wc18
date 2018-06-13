/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.MatchDao;
import com.github.mjeanroy.wc18.domain.exceptions.MatchNotFoundException;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Match.Stage;
import com.github.mjeanroy.wc18.domain.models.Team;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.Optional;

@Service
public class MatchService {

	private final MatchDao matchDao;

	@Inject
	public MatchService(MatchDao matchDao) {
		this.matchDao = matchDao;
	}

	@Transactional(readOnly = true)
	public Optional<Match> findOne(String id) {
		return matchDao.findOne(id);
	}

	/**
	 * Find all matches.
	 *
	 * @return All matches.
	 */
	@Transactional(readOnly = true)
	public Iterable<Match> findAll(Boolean locked) {
		if (locked == null) {
			return matchDao.findAllOrderByDate();
		} else if (locked) {
			return matchDao.findByDateLessThanOrderByDate(new Date());
		} else {
			return matchDao.findByDateGreaterThanOrEqualOrderByDate(new Date());
		}
	}

	/**
	 * Create new match with given values.
	 *
	 * @param date The new match date.
	 * @param stage The new match stage.
	 * @param team1 First team.
	 * @param team2 Second team.
	 * @return The result.
	 */
	@Transactional
	public Match create(Date date, Stage stage, Team team1, Team team2) {
		Match match = new Match(date, stage, team1, team2);
		return save(match);
	}

	/**
	 * Update match with given values.
	 *
	 * @param id The match identifier.
	 * @param date The new match date.
	 * @param stage The new match stage.
	 * @param score1 First score.
	 * @param score2 Second score.
	 * @return The result.
	 */
	@Transactional
	public Match update(String id, Date date, Stage stage, Integer score1, Integer score2) {
		Match match = findOneOrFail(id);
		match.updateDate(date);
		match.updateStage(stage);
		match.updateScore(score1, score2);
		return save(match);
	}

	/**
	 * Remove match.
	 *
	 * @param id The match identifier.
	 */
	@Transactional
	public void remove(String id) {
		Match match = findOneOrFail(id);
		matchDao.delete(match);
	}

	/**
	 * Find match or fail with appropriate exception.
	 *
	 * @param id Match identifier.
	 * @return The match.
	 * @throws MatchNotFoundException If match does not exist.
	 */
	private Match findOneOrFail(String id) {
		return matchDao.findOne(id).orElseThrow(() ->
				new MatchNotFoundException(id)
		);
	}

	/**
	 * Save match in persistent storage.
	 *
	 * @param match The match to save.
	 * @return The result.
	 */
	private Match save(Match match) {
		return matchDao.save(match);
	}
}

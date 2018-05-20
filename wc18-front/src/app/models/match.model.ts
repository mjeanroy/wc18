/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Team } from './team.model';
import { Score } from './score.model';

export interface Match {
  id: string;
  date: Date;
  team1: Team;
  team2: Team;
  score: Score;
}

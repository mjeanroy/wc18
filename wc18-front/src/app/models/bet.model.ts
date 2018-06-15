/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Match } from './match.model';
import { Score } from './score.model';
import { User } from './user.model';

export interface Bet {
  id: string;
  date: Date;
  match: Match;
  score: Score;
  user: User;
  result: string;
  point: number;
}

/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { User } from './user.model';

export interface Rank {
  id: string;
  user: User;
  score: number;
  percentGood: number;
  percentPerfect: number;
}

/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { League } from './league.model';

export interface User {
  id: string;
  login: string;
  role: string;
  leagues: League[];
}

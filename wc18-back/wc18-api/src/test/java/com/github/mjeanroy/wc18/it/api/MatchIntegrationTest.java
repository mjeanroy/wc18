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

package com.github.mjeanroy.wc18.it.api;

import com.github.mjeanroy.junit.servers.client.HttpClient;
import com.github.mjeanroy.junit.servers.client.HttpResponse;
import com.github.mjeanroy.wc18.it.junit.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;

import static com.github.mjeanroy.restassert.assertj.api.JunitServersHttpAssertions.assertThat;
import static com.github.mjeanroy.restassert.assertj.api.JunitServersHttpAssertions.assertThatJson;
import static com.github.mjeanroy.wc18.it.json.JsonBuilders.jsonObject;
import static java.util.Collections.singleton;

class MatchIntegrationTest extends AbstractIntegrationTest {

	@Test
	void it_should_get_all_matches(HttpClient client) {
		HttpResponse response = client.prepareGet("/api/matches").execute();
		assertThat(response).isOk().isUtf8();
		assertThatJson(response).isEqualTo(getJsonFile("GET_api_matches.json"));
	}

	@Test
	void it_should_create_match(HttpClient client) {
		HttpResponse response = client.preparePost("/api/matches")
			.addHeader("Content-Type", "application/json")
			.addHeader("X-Auth-Token", getAdminToken(client))
			.setBody(jsonObject()
				.add("team1", jsonObject()
					.add("id", "5820fadd-ae19-48d5-b4e5-811b08f58b87")
					.add("name", "France"))
				.add("team2", jsonObject()
					.add("id", "e9c4e714-5b4b-4e2d-a896-9f043c295869")
					.add("name", "Brésil"))
				.add("date", "2018-07-15T18:00:00.000Z")
				.add("stage", jsonObject()
						.add("id", "FINAL")
						.add("label", "Finale"))
				.build()
				.serialize())
			.executeJson();

		assertThat(response).isCreated().isUtf8().isJson();
		assertThatJson(response).isEqualToIgnoring(getJsonFile("POST_api_matches.json"), singleton("id"));
	}

	@Test
	void it_should_update_match_score(HttpClient client) {
		String matchId = "4ff9c731-7eba-47bb-91fa-a0c04b2e394e";
		HttpResponse response = client.preparePut("/api/matches/" + matchId + "/score")
			.addHeader("Content-Type", "application/json")
			.addHeader("X-Auth-Token", getAdminToken(client))
			.setBody(jsonObject()
				.add("score1", 2)
				.add("score2", 1)
				.serialize())
			.executeJson();

		assertThat(response).isOk().isUtf8().isJson();
		assertThatJson(response).isEqualToIgnoring(getJsonFile("POST_api_matches_score.json"), singleton("id"));
	}
}

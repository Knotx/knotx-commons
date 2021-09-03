/*
 * Copyright (C) 2019 Knot.x Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.knotx.commons.json;

import static io.knotx.commons.json.JsonObjectUtil.getJsonObject;
import static io.knotx.commons.json.JsonObjectUtil.getObject;
import static io.knotx.commons.json.JsonObjectUtil.getString;
import static io.knotx.commons.json.JsonObjectUtil.putValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JsonObjectUtilTest {

  private static final String KEY = "root.nested.value";

  @Test
  @DisplayName("Expect nested object to be returned")
  void nestedLongReturned() {
    long value = 1000L;

    JsonObject object = objectWithValue(value);

    assertEquals(value, getObject(KEY, object));
  }

  @Test
  @DisplayName("Expect nested JsonObject to be returned")
  void nestedJsonObjectReturned() {
    JsonObject value = new JsonObject()
        .put("some-key", "some-value");

    JsonObject object = objectWithValue(value);

    assertEquals(value, getJsonObject(KEY, object));
  }

  @Test
  @DisplayName("Expect nested JsonObject to be returned")
  void nestedStringReturned() {
    String value = "some-string";

    JsonObject object = objectWithValue(value);

    assertEquals(value, getString(KEY, object));
  }

  @Test
  @DisplayName("Expect null when object does not exist")
  void objectDoesNotExist() {
    assertNull(getObject(KEY, new JsonObject()));
  }

  @Test
  @DisplayName("Expect empty string when string does not exist")
  void stringDoesNotExist() {
    assertEquals("", getString(KEY, new JsonObject()));
  }

  @Test
  @DisplayName("Expect null when JsonObject does not exist")
  void jsonObjectDoesNotExist() {
    assertNull(getJsonObject(KEY, new JsonObject()));
  }

  @Test
  @DisplayName("Expect nested value to be replaced")
  void nestedValueReplaced() {
    JsonObject object = objectWithValue(1000L);

    putValue(KEY, object, 2000L);

    assertEquals(2000L, object.getJsonObject("root").getJsonObject("nested").getValue("value"));
  }

  @Test
  @DisplayName("Expect nested JsonObjects to be created and value placed")
  void nestedStructureCreated() {
    JsonObject object = new JsonObject();

    putValue(KEY, object, 1000L);

    assertEquals(1000L, object.getJsonObject("root").getJsonObject("nested").getValue("value"));
  }

  private JsonObject objectWithValue(Object value) {
    return new JsonObject()
        .put("root", new JsonObject()
            .put("nested", new JsonObject()
                .put("value", value)));
  }

}

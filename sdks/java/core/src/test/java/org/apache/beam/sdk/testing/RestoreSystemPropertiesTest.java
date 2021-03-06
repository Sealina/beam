/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.beam.sdk.testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Tests for {@link RestoreSystemProperties}. */
@RunWith(JUnit4.class)
@SuppressWarnings({
  "nullness" // TODO(https://issues.apache.org/jira/browse/BEAM-10402)
})
public class RestoreSystemPropertiesTest {
  @Rule public TestRule restoreSystemProperties = new RestoreSystemProperties();

  /*
   * Since these tests can run out of order, both test A and B verify that they
   * could insert their property and that the other does not exist.
   */
  @Test
  public void testThatPropertyIsClearedA() {
    System.getProperties().put("TestA", "TestA");
    assertNotNull(System.getProperty("TestA"));
    assertNull(System.getProperty("TestB"));
  }

  @Test
  public void testThatPropertyIsClearedB() {
    System.getProperties().put("TestB", "TestB");
    assertNotNull(System.getProperty("TestB"));
    assertNull(System.getProperty("TestA"));
  }
}

/*
 * Copyright (C) 2016 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package okhttp3.internal.platform;

import java.lang.reflect.Method;
import org.junit.Test;

import static okhttp3.internal.platform.PlatformTest.getPlatform;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.assumeTrue;

public class Jdk9PlatformTest {
  @Test
  public void buildsWhenJdk9() {
    assumeTrue(getPlatform().equals("jdk9"));

    assertThat(Jdk9Platform.buildIfSupported()).isNotNull();
  }

  @Test
  public void findsAlpnMethods() {
    assumeTrue(getPlatform().equals("jdk9"));

    Jdk9Platform platform = Jdk9Platform.buildIfSupported();

    assertThat(platform.getProtocolMethod.getName()).isEqualTo("getApplicationProtocol");
    assertThat(platform.setProtocolMethod.getName()).isEqualTo("setApplicationProtocols");
  }

  @Test
  public void testToStringIsClassname() throws NoSuchMethodException {
    assumeTrue(getPlatform().equals("jdk9"));

    Method method = this.getClass().getMethod("toString");
    assertThat(new Jdk9Platform(method, method).toString()).isEqualTo("Jdk9Platform");
  }
}
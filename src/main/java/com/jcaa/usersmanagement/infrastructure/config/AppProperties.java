package com.jcaa.usersmanagement.infrastructure.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public final class AppProperties {

  private static final String PROPERTIES_FILE =
          "application.properties";

  private final Properties properties;

  public AppProperties() {

    this(
            AppProperties.class
                    .getClassLoader()
                    .getResourceAsStream(PROPERTIES_FILE));
  }

  // package private para tests
  AppProperties(final InputStream stream) {

    this.properties = doLoad(stream);
  }

  private static Properties doLoad(
          final InputStream stream) {

    // correccion regla 4 usar Objects.requireNonNull
    Objects.requireNonNull(
            stream,
            "File not found in classpath: " + PROPERTIES_FILE);

    // correccion regla 4 usar nombres descriptivos
    final Properties properties =
            new Properties();

    try (stream) {

      properties.load(stream);

    } catch (final IOException exception) {

      throw ConfigurationException
              .becauseLoadFailed(exception);
    }

    return properties;
  }

  public String get(final String key) {

    // correccion regla 4 usar nombres descriptivos
    final String value =
            properties.getProperty(key);

    // correccion regla 4 usar Objects.requireNonNull
    Objects.requireNonNull(
            value,
            "Property not found in "
                    + PROPERTIES_FILE
                    + ": "
                    + key);

    return value;
  }

  public int getInt(final String key) {

    return Integer.parseInt(get(key));
  }
}
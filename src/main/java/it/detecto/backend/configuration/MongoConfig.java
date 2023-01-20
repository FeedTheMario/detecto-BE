package it.detecto.backend.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "it.detecto.backend.DAO")
public class MongoConfig extends AbstractMongoClientConfiguration {

  private final List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();

  @Override
  protected String getDatabaseName() {
    return "test";
  }

  @Override
  public MongoClient mongoClient() {
    final ConnectionString connectionString = new ConnectionString("mongodb+srv://detecto:<password>@cluster0.rd1yhm8.mongodb.net/?retryWrites=true&w=majority");
    final MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
    return MongoClients.create(mongoClientSettings);
  }

  @Override
  public Collection<String> getMappingBasePackages() {
    return Collections.singleton("org.spring.mongo.demo");
  }
}

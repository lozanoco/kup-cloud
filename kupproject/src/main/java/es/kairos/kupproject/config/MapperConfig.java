package es.kairos.kupproject.config;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MapperConfig {

  @Bean
  @Autowired
  MapperFacade getMapperFacade(List<CustomConverter> customConverters) {
    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    customConverters.stream()
        .peek(converter -> log.info(String.format("Registering converter from class %s to class %s",
            converter.getAType().getName(), converter.getBType().getName())))
        .forEach(mapperFactory.getConverterFactory()::registerConverter);
    return mapperFactory.getMapperFacade();
  }

}

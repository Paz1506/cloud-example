package com.zaytsevp.modelservice.feign;

import com.jupiter.tools.spring.test.web.annotation.EnableEmbeddedWebServerTest;
import com.jupiter.tools.spring.test.web.extension.ribbon.RedirectRibbonToEmbeddedWebServer;
import com.zaytsevp.modelservice.model.Auto;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author Pavel Zaytsev
 */
@EnableEmbeddedWebServerTest
@RedirectRibbonToEmbeddedWebServer("auto-service")
public class AutoServiceFeignIT {

    @Autowired
    private AutoServiceFeign autoServiceFeign;

    private static final UUID ID = UUID.randomUUID();

    @Test
    @Disabled("После добавления OAuth interceptor'а")
    void getAll() throws Exception {
        // Actual
        List<Auto> actualList = autoServiceFeign.getAll();

        // Assertion
        Assertions.assertThat(actualList)
                  .extracting(Auto::getName, Auto::getFoundYear)
                  .containsExactlyInAnyOrder(Tuple.tuple("Тест", 1999));
    }

    @Test
    @Disabled("После добавления OAuth interceptor'а")
    void getById() throws Exception {
        Auto auto = autoServiceFeign.getById(ID);

        // Assertion
        Assertions.assertThat(auto).isNotNull();
        Assertions.assertThat(auto.getName()).isEqualTo("Тест");
        Assertions.assertThat(auto.getFoundYear()).isEqualTo(1999);

    }

    @TestConfiguration
    public static class AutoServiceFeignTestConfig {

        @RestController
        public static class AutoController {

            @GetMapping(value = "/autos/all", produces = MediaType.APPLICATION_JSON_VALUE)
            public List<Auto> getAll() {
                return Collections.singletonList(Auto.builder()
                                                     .name("Тест")
                                                     .foundYear(1999)
                                                     .build());
            }

            @GetMapping(value = "/autos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
            public Auto getById(@PathVariable(name = "id") UUID id) {

                Assertions.assertThat(id).isEqualTo(ID);

                return Auto.builder()
                           .name("Тест")
                           .foundYear(1999)
                           .build();
            }
        }
    }

}
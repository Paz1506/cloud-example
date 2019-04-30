package com.zaytsevp.autoservice.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.google.common.collect.Sets;
import com.zaytsevp.autoservice.api.dto.AutoDto;
import com.zaytsevp.autoservice.config.PostgresTestcontainersExtension;
import com.zaytsevp.autoservice.model.Auto;
import com.zaytsevp.autoservice.model.AutoType;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Интеграционные тесты контроллера
 *
 * @author Pavel Zaytsev
 */
@DBRider
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@ExtendWith(PostgresTestcontainersExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AutoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private final UUID id = UUID.fromString("00000000-0000-0000-0000-000000000000");

    @Test
    @DataSet(value = "/datasets/auto.json", cleanBefore = true, cleanAfter = true)
    void getAll() throws Exception {
        // Actual
        List<AutoDto> actualResult = mapper.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/autos/all"))
                                                             .andExpect(MockMvcResultMatchers.status()
                                                                                             .isOk())
                                                             .andDo(print())
                                                             .andReturn()
                                                             .getResponse().getContentAsByteArray(), new TypeReference<List<AutoDto>>() {});

        // Assertion
        Assertions.assertThat(actualResult)
                  .extracting(AutoDto::getId,
                              AutoDto::getName,
                              AutoDto::getFoundYear,
                              AutoDto::getTypes)
                  .containsExactlyInAnyOrder(Tuple.tuple(UUID.fromString("00000000-0000-0000-0000-000000000000"),
                                                         "parent1",
                                                         1980,
                                                         Sets.newHashSet(AutoType.LIGHT)),
                                             Tuple.tuple(UUID.fromString("00000000-0000-0000-0000-000000000001"),
                                                         "parent2",
                                                         1975,
                                                         Sets.newHashSet(AutoType.TRUCK)));
    }

    @Test
    @DataSet(value = "/datasets/auto.json", cleanBefore = true, cleanAfter = true)
    void getById() throws Exception {
        // Actual & Assertion
        Auto actualResult = mapper.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/autos/" + id))
                                                    .andExpect(MockMvcResultMatchers.status()
                                                                                    .isOk())
                                                    .andDo(print())
                                                    .andReturn()
                                                    .getResponse()
                                                    .getContentAsByteArray(), Auto.class);

        Assertions.assertThat(actualResult.getFoundYear()).isEqualTo(1980);
        Assertions.assertThat(actualResult.getName()).isEqualTo("parent1");
        Assertions.assertThat(actualResult.getTypes()).containsOnly(AutoType.LIGHT);
    }

    @Test
    @Disabled
    @DataSet(value = "/datasets/auto.json", cleanBefore = true, cleanAfter = true)
    @ExpectedDataSet(value = "/datasets/auto_create_random__expected.json")
    void createRandom() throws Exception {
        // Actual & Assertion
        mockMvc.perform(MockMvcRequestBuilders.post("/autos/createRandom"))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk())
               .andDo(print());
    }
}
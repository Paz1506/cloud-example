package com.zaytsevp.autoservice.api;

import com.zaytsevp.autoservice.api.dto.AutoDto;
import com.zaytsevp.autoservice.api.mapper.AutoMapper;
import com.zaytsevp.autoservice.model.Auto;
import com.zaytsevp.autoservice.service.AutoService;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Pavel Zaytsev
 */
public class AutoControllerTest {

    private AutoService autoService = mock(AutoService.class);

    private AutoMapper autoMapper = mock(AutoMapper.class);

    private AutoController autoController = new AutoController(autoService, autoMapper);

    @Test
    public void getAll() throws Exception {
        // Prepare
        Auto auto = mock(Auto.class);
        AutoDto autoDto = mock(AutoDto.class);

        List<Auto> autos = Lists.newArrayList(auto);
        when(autoService.getAll()).thenReturn(autos);
        when(autoMapper.toDto(any(Auto.class))).thenReturn(autoDto);

        // Actual
        List<AutoDto> actualResult = autoController.getAll();

        // Assertion
        Assertions.assertThat(actualResult).containsOnly(autoDto);

        verify(autoService).getAll();
        verify(autoMapper).toDto(auto);
        verifyNoMoreInteractions(autoService);
        verifyNoMoreInteractions(autoMapper);
    }

    @Test
    public void getById() throws Exception {
        // Prepare
        UUID id = UUID.randomUUID();
        Auto auto = mock(Auto.class);
        AutoDto autoDto = mock(AutoDto.class);

        when(autoService.getById(any(UUID.class))).thenReturn(auto);
        when(autoMapper.toDto(any(Auto.class))).thenReturn(autoDto);

        // Actual
        AutoDto actualResult = autoController.getById(id);

        // Assertion
        Assertions.assertThat(actualResult).isSameAs(autoDto);

        verify(autoService).getById(eq(id));
        verify(autoMapper).toDto(auto);
        verifyNoMoreInteractions(autoService);
        verifyNoMoreInteractions(autoMapper);
    }

    @Test
    public void createRandom() throws Exception {
        // Prepare
        Auto auto = mock(Auto.class);
        AutoDto autoDto = mock(AutoDto.class);

        when(autoService.createRandom()).thenReturn(auto);
        when(autoMapper.toDto(any(Auto.class))).thenReturn(autoDto);

        // Actual
        AutoDto actualResult = autoController.createRandom();

        // Assertion
        Assertions.assertThat(actualResult).isSameAs(autoDto);

        verify(autoService).createRandom();
        verify(autoMapper).toDto(auto);
        verifyNoMoreInteractions(autoService);
        verifyNoMoreInteractions(autoMapper);
    }
}
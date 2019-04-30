package com.zaytsevp.autoservice.service;

import com.zaytsevp.autoservice.model.Auto;
import com.zaytsevp.autoservice.model.AutoType;
import com.zaytsevp.autoservice.repository.AutoRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

/**
 * @author Pavel Zaytsev
 */
public class AutoServiceImplTest {

    private AutoRepository autoRepository = mock(AutoRepository.class);

    private AutoService autoService = new AutoServiceImpl(autoRepository);

    private Auto auto = mock(Auto.class);

    @Test
    public void getAll() throws Exception {
        // Prepare
        List<Auto> autos = Lists.newArrayList(auto);
        when(autoRepository.findAll()).thenReturn(autos);

        // Actual
        List<Auto> actualResult = autoService.getAll();

        // Assertion
        Assertions.assertThat(actualResult).isSameAs(autos);

        verify(autoRepository).findAll();
        verifyNoMoreInteractions(autoRepository);

    }

    @Test
    public void getById() throws Exception {
        // Prepare
        UUID id = UUID.randomUUID();
        when(autoRepository.findById(any(UUID.class))).thenReturn(Optional.of(auto));

        // Actual
        Auto actualResult = autoService.getById(id);

        // Assertion
        Assertions.assertThat(actualResult).isSameAs(auto);

        verify(autoRepository).findById(eq(id));
        verifyNoMoreInteractions(autoRepository);
    }

    @Test
    public void getByIdWithoutId() throws Exception {
        // Actual
        Executable exec = () -> autoService.getById(null);

        // Assertion
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, exec);
        verifyZeroInteractions(autoRepository);
    }

    @Test
    public void createRandom() throws Exception {
        // Prepare
        when(autoRepository.saveAndFlush(any(Auto.class))).thenReturn(auto);

        // Actual
        Auto actualResult = autoService.createRandom();

        ArgumentCaptor<Auto> argumentCaptor = ArgumentCaptor.forClass(Auto.class);

        // Assertion
        Assertions.assertThat(actualResult).isSameAs(auto);

        verify(autoRepository).saveAndFlush(argumentCaptor.capture());
        verifyNoMoreInteractions(autoRepository);

        Assertions.assertThat(argumentCaptor.getValue().getName()).startsWith("Random auto ");
        Assertions.assertThat(argumentCaptor.getValue().getFoundYear()).isBetween(0, 2000);
        Assertions.assertThat(argumentCaptor.getValue().getTypes()).containsExactly(AutoType.LIGHT);
    }

}
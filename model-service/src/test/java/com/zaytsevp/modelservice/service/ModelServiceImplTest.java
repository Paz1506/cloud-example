package com.zaytsevp.modelservice.service;

import com.google.common.collect.Lists;
import com.zaytsevp.modelservice.model.Model;
import com.zaytsevp.modelservice.repository.ModelRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

/**
 * @author Pavel Zaytsev
 */
public class ModelServiceImplTest {

    private ModelRepository modelRepository = mock(ModelRepository.class);

    private ModelService modelService = new ModelServiceImpl(modelRepository);

    private Model model = mock(Model.class);

    @Test
    public void getAll() throws Exception {
        // Arrange
        ArrayList<Model> models = Lists.newArrayList(model);
        when(modelRepository.findAll()).thenReturn(models);

        // Actual
        List<Model> actualResult = modelService.getAll();

        // Assertion
        Assertions.assertThat(actualResult).isSameAs(models);

        verify(modelRepository).findAll();
        verifyNoMoreInteractions(modelRepository);

    }

    @Test
    public void getById() throws Exception {
        // Prepare
        UUID id = UUID.randomUUID();
        when(modelRepository.findById(any(UUID.class))).thenReturn(Optional.of(model));

        // Actual
        Model actualResult = modelService.getById(id);

        // Assertion
        Assertions.assertThat(actualResult).isSameAs(model);

        verify(modelRepository).findById(eq(id));
        verifyNoMoreInteractions(modelRepository);

    }

    @Test
    public void getByIdWithoutId() throws Exception {
        // Actual
        Executable exec = () -> modelService.getById(null);

        // Assertion
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, exec);
        verifyZeroInteractions(modelRepository);
    }

    @Test
    public void getByIdWithNotFound() throws Exception {
        // Prepare
        UUID id = UUID.randomUUID();
        when(modelRepository.findById(any(UUID.class))).thenThrow(new EntityNotFoundException());

        // Actual
        Executable exec = () -> modelService.getById(id);

        // Assertion
        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, exec);

        verify(modelRepository).findById(eq(id));
        verifyNoMoreInteractions(modelRepository);
    }
}
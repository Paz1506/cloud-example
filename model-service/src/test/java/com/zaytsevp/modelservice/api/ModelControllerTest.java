package com.zaytsevp.modelservice.api;

import com.zaytsevp.modelservice.api.dto.ModelDto;
import com.zaytsevp.modelservice.api.dto.ModelProjectionDto;
import com.zaytsevp.modelservice.api.mapper.AutoModelMapper;
import com.zaytsevp.modelservice.feign.AutoServiceFeign;
import com.zaytsevp.modelservice.model.Auto;
import com.zaytsevp.modelservice.model.Model;
import com.zaytsevp.modelservice.model.ModelProjection;
import com.zaytsevp.modelservice.service.ModelService;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Pavel Zaytsev
 */
class ModelControllerTest {

    private ModelService modelService = mock(ModelService.class);

    private AutoServiceFeign autoServiceFeign = mock(AutoServiceFeign.class);

    private AutoModelMapper autoModelMapper = mock(AutoModelMapper.class);

    private ModelController modelController = new ModelController(modelService, autoServiceFeign, autoModelMapper);

    @Test
    void getAll() throws Exception {
        // Prepare
        Model model = mock(Model.class);
        ModelDto modelDto = mock(ModelDto.class);
        List<Model> models = Lists.newArrayList(model);

        when(modelService.getAll()).thenReturn(models);
        when(autoModelMapper.toDto(any(Model.class))).thenReturn(modelDto);

        // Actual
        List<ModelDto> actualResult = modelController.getAll();

        // Assertion
        Assertions.assertThat(actualResult).containsOnly(modelDto);
        verify(modelService).getAll();
        verify(autoModelMapper).toDto(model);
        verifyNoMoreInteractions(modelService);
        verifyNoMoreInteractions(autoModelMapper);
    }

    @Test
    void getById() throws Exception {
        // Prepare
        UUID id = UUID.randomUUID();
        Model model = mock(Model.class);
        ModelDto modelDto = mock(ModelDto.class);

        when(modelService.getById(any(UUID.class))).thenReturn(model);
        when(autoModelMapper.toDto(any(Model.class))).thenReturn(modelDto);

        // Actual
        ModelDto actualResult = modelController.getById(id);

        // Assertion
        Assertions.assertThat(actualResult).isEqualTo(modelDto);
        verify(modelService).getById(eq(id));
        verify(autoModelMapper).toDto(model);
        verifyNoMoreInteractions(modelService);
        verifyNoMoreInteractions(autoModelMapper);
    }

    @Test
    void getDtoById() throws Exception {
        // Prepare
        UUID id = UUID.randomUUID();
        Model model = mock(Model.class);
        Auto auto = mock(Auto.class);
        ModelProjectionDto modelProjectionDto = mock(ModelProjectionDto.class);

        when(model.getAutoId()).thenReturn(id);

        when(autoServiceFeign.getById(any(UUID.class))).thenReturn(auto);
        when(modelService.getById(any(UUID.class))).thenReturn(model);
        when(autoModelMapper.toProjectionDto(any(ModelProjection.class))).thenReturn(modelProjectionDto);

        // Actual
        ModelProjectionDto actualResult = modelController.getDtoById(id);

        ArgumentCaptor<ModelProjection> argumentCaptor = ArgumentCaptor.forClass(ModelProjection.class);

        // Assertion
        Assertions.assertThat(actualResult).isEqualTo(modelProjectionDto);

        verify(modelService).getById(eq(id));
        verify(autoServiceFeign).getById(eq(id));
        verify(autoModelMapper).toProjectionDto(argumentCaptor.capture());
        verifyNoMoreInteractions(modelService);
        verifyNoMoreInteractions(autoServiceFeign);
        verifyNoMoreInteractions(autoModelMapper);
    }

    @Test
    void getDtoAll() throws Exception {
        // Prepare
        UUID id = UUID.randomUUID();
        Model model = mock(Model.class);
        Auto auto = mock(Auto.class);
        ModelProjectionDto modelProjectionDto = mock(ModelProjectionDto.class);

        when(model.getAutoId()).thenReturn(id);

        when(autoServiceFeign.getById(any(UUID.class))).thenReturn(auto);
        when(modelService.getById(any(UUID.class))).thenReturn(model);
        when(autoModelMapper.toProjectionDto(any(ModelProjection.class))).thenReturn(modelProjectionDto);

        // Actual
        ModelProjectionDto actualResult = modelController.getDtoById(id);

        ArgumentCaptor<ModelProjection> argumentCaptor = ArgumentCaptor.forClass(ModelProjection.class);

        // Assertion
        Assertions.assertThat(actualResult).isEqualTo(modelProjectionDto);

        verify(modelService).getById(eq(id));
        verify(autoServiceFeign).getById(eq(id));
        verify(autoModelMapper).toProjectionDto(argumentCaptor.capture());
        verifyNoMoreInteractions(modelService);
        verifyNoMoreInteractions(autoServiceFeign);
        verifyNoMoreInteractions(autoModelMapper);
    }
}
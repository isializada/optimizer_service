package com.alizada.optimizer_service.controller;

import com.alizada.optimizer_service.cleaningPartner.request.StructureInfoRequest;
import com.alizada.optimizer_service.cleaningPartner.response.OptimalEmployeesPair;
import com.alizada.optimizer_service.cleaningPartner.service.CleaningCapacityOptimizer;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CleaningOptimizerControllerUnitTest {
    @Mock
    private CleaningCapacityOptimizer cleaningCapacityOptimizer;
    @InjectMocks
    private CleaningOptimizerController cleaningOptimizerController = new CleaningOptimizerController(cleaningCapacityOptimizer);
    private ArgumentCaptor<StructureInfoRequest> argumentsRequest = ArgumentCaptor.forClass(StructureInfoRequest.class);;

    @Test
    public void shouldSendRightDataToService(){
        MockitoAnnotations.initMocks(this);

        when(cleaningCapacityOptimizer.optimize(any())).thenReturn(Arrays.asList(mock(OptimalEmployeesPair.class)));

        StructureInfoRequest structureInfoRequest = new StructureInfoRequest()
                .setJunior(6)
                .setSenior(10)
                .setRooms(new ArrayList<>(Arrays.asList(35,17)));

        cleaningOptimizerController.findOptimalNumbers(structureInfoRequest);

        verify(cleaningCapacityOptimizer).optimize(argumentsRequest.capture());

        assertThat(argumentsRequest.getValue().getJunior()).isEqualTo(6);
        assertThat(argumentsRequest.getValue().getSenior()).isEqualTo(10);
        assertThat(argumentsRequest.getValue().getRooms().get(0)).isEqualTo(35);
        assertThat(argumentsRequest.getValue().getRooms().get(1)).isEqualTo(17);
    }
}

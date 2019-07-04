package com.alizada.optimizer_service.cleaningPartner.service;

import com.alizada.optimizer_service.cleaningPartner.request.StructureInfoRequest;
import com.alizada.optimizer_service.cleaningPartner.response.OptimalEmployeesPair;
import com.alizada.optimizer_service.exception.BadRequestException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CleaningCapacityOptimizerUnitTest {
    private CleaningCapacityOptimizer optimizer = new CleaningCapacityOptimizer();

    @Test
    public void shouldFindOptimalNumberOfEmployees(){
        StructureInfoRequest structureInfoRequest = new StructureInfoRequest()
                .setJunior(6)
                .setSenior(10)
                .setRooms(new ArrayList<>(Arrays.asList(35,21,17)));

        List<OptimalEmployeesPair> response = optimizer.optimize(structureInfoRequest);

        assertThat(response.get(0).getSenior()).isEqualTo(3);
        assertThat(response.get(0).getJunior()).isEqualTo(1);

        assertThat(response.get(1).getSenior()).isEqualTo(1);
        assertThat(response.get(1).getJunior()).isEqualTo(2);

        assertThat(response.get(2).getSenior()).isEqualTo(2);
        assertThat(response.get(2).getJunior()).isEqualTo(0);
    }

    @Test
    public void shouldThrowBadRequestForWrondRequestData(){
        StructureInfoRequest structureInfoRequest = new StructureInfoRequest()
                .setJunior(11)
                .setSenior(10)
                .setRooms(new ArrayList<>(Arrays.asList(25,-1)));

        assertThrows(BadRequestException.class, () -> optimizer.optimize(structureInfoRequest));
    }

}

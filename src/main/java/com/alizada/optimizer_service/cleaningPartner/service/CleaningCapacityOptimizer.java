package com.alizada.optimizer_service.cleaningPartner.service;

import com.alizada.optimizer_service.cleaningPartner.response.OptimalEmployeesPair;
import com.alizada.optimizer_service.cleaningPartner.request.StructureInfoRequest;
import com.alizada.optimizer_service.exception.BadRequestException;
import com.alizada.optimizer_service.optimizer.Optimizer;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*The service will validate request and find optimal numbers of employees for cleaning*/
@Service("cleaningCapacityOptimizer")
public class CleaningCapacityOptimizer implements Optimizer<List<OptimalEmployeesPair>, StructureInfoRequest> {
    @Override
    public List<OptimalEmployeesPair> optimize(StructureInfoRequest request) {
        validateRequest(request);

        final Integer seniorCapacity = request.getSenior();
        final Integer juniorCapacity = request.getJunior();
        final Integer doubleJuniorCapacity = 2*request.getJunior();

        List<OptimalEmployeesPair> optimalEmployeesPairList = new ArrayList<>();

        request.getRooms().forEach(roomCapacity -> {

            OptimalEmployeesPair optimalEmployeesPair = new OptimalEmployeesPair();
            Integer currentRoomCapacity = roomCapacity;

            //always needs to be at least one senior cleaner
            optimalEmployeesPair.addSenior(1);
            currentRoomCapacity -= seniorCapacity;

            while (currentRoomCapacity > 0) {
                if (currentRoomCapacity >= seniorCapacity && currentRoomCapacity > doubleJuniorCapacity) {
                    optimalEmployeesPair.addSenior(1);
                    currentRoomCapacity -= seniorCapacity;
                    continue;
                }

                if (currentRoomCapacity >= seniorCapacity && currentRoomCapacity <= doubleJuniorCapacity){
                    optimalEmployeesPair.addJunior(2);
                    currentRoomCapacity -= doubleJuniorCapacity;
                    continue;
                }

                if(currentRoomCapacity <= seniorCapacity && currentRoomCapacity > juniorCapacity){
                    optimalEmployeesPair.addSenior(1);
                    currentRoomCapacity -= seniorCapacity;
                    continue;
                }

                if(currentRoomCapacity <= seniorCapacity && currentRoomCapacity <= juniorCapacity){
                    optimalEmployeesPair.addJunior(1);
                    currentRoomCapacity -= juniorCapacity;
                }
            }

            optimalEmployeesPairList.add(optimalEmployeesPair);
        });

        return optimalEmployeesPairList;
    }

    private void validateRequest(StructureInfoRequest request) {
        Objects.requireNonNull(request.getJunior(), "Junior capacity is mandatory");
        Objects.requireNonNull(request.getSenior(), "Senior capacity is mandatory");
        Objects.requireNonNull(request.getRooms(), "Rooms field is mandatory");

        if(request.getRooms().stream().filter(room -> room > 0).collect(Collectors.toList()).size() != request.getRooms().size()){
            throw new BadRequestException("Rooms capacity have to be positive");
        }

        if(request.getRooms().isEmpty()){
            throw new BadRequestException("Rooms are empty");
        }

        if(request.getJunior() <= 0 || request.getSenior() <=0){
            throw new BadRequestException("Capacity of employee has to be positive");
        }

        if(request.getSenior() <= request.getJunior()){
            throw new BadRequestException("Senior capacity have to be higher than junior capacity");
        }

    }
}

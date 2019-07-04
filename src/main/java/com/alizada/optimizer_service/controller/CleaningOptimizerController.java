package com.alizada.optimizer_service.controller;

import com.alizada.optimizer_service.cleaningPartner.request.StructureInfoRequest;
import com.alizada.optimizer_service.cleaningPartner.response.OptimalEmployeesPair;
import com.alizada.optimizer_service.optimizer.Optimizer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cleaning")
public class CleaningOptimizerController {
    private Optimizer cleaningCapacityOptimizer;

    CleaningOptimizerController(Optimizer cleaningCapacityOptimizer) {
        this.cleaningCapacityOptimizer = cleaningCapacityOptimizer;
    }

    @PostMapping("/findOptimalNumbers")
    public List<OptimalEmployeesPair> findOptimalNumbers(@RequestBody StructureInfoRequest request){
        return (List<OptimalEmployeesPair>) cleaningCapacityOptimizer.optimize(request);
    }
}

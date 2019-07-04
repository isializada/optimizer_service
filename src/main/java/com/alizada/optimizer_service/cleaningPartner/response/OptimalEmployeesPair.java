package com.alizada.optimizer_service.cleaningPartner.response;

public class OptimalEmployeesPair {
    private Integer senior = 0;
    private Integer junior = 0;

    public void addSenior(Integer senior) {
        this.senior += senior;
    }

    public void addJunior(Integer junior) {
        this.junior += junior;
    }

    public Integer getSenior() {
        return senior;
    }

    public Integer getJunior() {
        return junior;
    }
}

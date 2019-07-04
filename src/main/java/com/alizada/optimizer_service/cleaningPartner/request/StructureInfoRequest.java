package com.alizada.optimizer_service.cleaningPartner.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;

public class StructureInfoRequest {
    @NotNull
    private ArrayList<Integer> rooms;
    @NotNull
    @Positive
    private Integer senior;
    @NotNull
    @Positive
    private Integer junior;
    @NotNull
    public ArrayList<Integer> getRooms() {
        return rooms;
    }

    public StructureInfoRequest setRooms(ArrayList<Integer> rooms) {
        this.rooms = rooms;
        return this;
    }

    public Integer getSenior() {
        return senior;
    }

    public StructureInfoRequest setSenior(Integer senior) {
        this.senior = senior;
        return this;
    }

    public Integer getJunior() {
        return junior;
    }

    public StructureInfoRequest setJunior(Integer junior) {
        this.junior = junior;
        return this;
    }
}

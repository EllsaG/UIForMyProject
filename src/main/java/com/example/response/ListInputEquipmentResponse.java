package com.example.response;

public class ListInputEquipmentResponse {
    private Long id;
    private Long fullInformationId;
    private Long startInformId;
    private Integer amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFullInformationId() {
        return fullInformationId;
    }

    public void setFullInformationId(Long fullInformationId) {
        this.fullInformationId = fullInformationId;
    }

    public Long getStartInformId() {
        return startInformId;
    }

    public void setStartInformId(Long startInformId) {
        this.startInformId = startInformId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

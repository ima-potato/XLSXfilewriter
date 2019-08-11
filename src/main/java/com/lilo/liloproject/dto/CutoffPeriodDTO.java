package com.lilo.liloproject.dto;

public class CutoffPeriodDTO {
    private Long id;
    private String cutoffPeriod;

    public CutoffPeriodDTO() {
    }

    public CutoffPeriodDTO(Long id, String cutoffPeriod) {
        this.id = id;
        this.cutoffPeriod = cutoffPeriod;
    }

    @Override
    public String toString() {
        return "CutoffPeriodDTO{" +
                "id=" + id +
                ", cutoffPeriod='" + cutoffPeriod + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCutoffPeriod() {
        return cutoffPeriod;
    }

    public void setCutoffPeriod(String cutoffPeriod) {
        this.cutoffPeriod = cutoffPeriod;
    }
}

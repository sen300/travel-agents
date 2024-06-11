package com.task.travel_service.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class TiketRequestBody {
    @JsonProperty(value = "id_penumpang")
    @NotBlank
    private Long idPenumpang;
    @JsonProperty(value = "id_travel")
    @NotBlank
    private Long idTravel;
    @JsonProperty(value = "jadwal")
    @NotBlank
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private String Jadwal;


    public Long getIdPenumpang() {
        return idPenumpang;
    }


    public Long getIdTravel() {
        return idTravel;
    }


    public String getJadwal() {
        return Jadwal;
    }
}

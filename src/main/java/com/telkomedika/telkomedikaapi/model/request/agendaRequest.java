package com.telkomedika.telkomedikaapi.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class agendaRequest {
    @NotNull

    private String judul;
    @NotNull
    private String deskripsi;
    @NotNull
    private String waktu;

}

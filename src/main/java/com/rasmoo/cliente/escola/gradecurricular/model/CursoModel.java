package com.rasmoo.cliente.escola.gradecurricular.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CursoModel {

    @NotBlank(message = "Nome deve ser preenchido")
    @Size(max = 30,min = 10)
    private String nome;
    @NotBlank(message = "Código deve ser preenchido")
    @Size(max = 10,min = 4)
    private String codCurso;
    private List<Long> materias;


}

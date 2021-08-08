package com.rasmoo.cliente.escola.gradecurricular.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Primary;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "materia")
@Data
@NoArgsConstructor
public class MateriaEntity implements Serializable {

    private static final long serialVersionUID = -922489302181456530L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "name")
    private String nome;

    @Column(name = "hrs")
    private int horas;

    @Column(name = "cod")
    private String codigo;

    @Column(name = "freq")
    private int frequencia;

}

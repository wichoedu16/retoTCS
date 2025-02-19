package com.werp.demo.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona {
    private Long clienteId;
    private String password;
    private Boolean estado;
}

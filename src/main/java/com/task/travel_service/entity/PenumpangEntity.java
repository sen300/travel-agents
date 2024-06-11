package com.task.travel_service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "penumpang")
public class PenumpangEntity {
    @Id
    @Column(name = "id_penumpang", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPenumpang;

    @NotBlank
    @Column(name = "nama", nullable = false)
    private String nama;

    @Size(min = 10, max = 13)
    @NotBlank
    @Pattern(regexp = "^[0-9]+$", message = "The value must be a positive integer")
    @Column(name = "no_telp", nullable = false)
    @JsonProperty(value = "no_telp")
    private String noTelp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PenumpangEntity that = (PenumpangEntity) o;
        return idPenumpang != null && Objects.equals(idPenumpang, that.idPenumpang);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

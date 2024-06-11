package com.task.travel_service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "travel")
public class TravelEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nama_travel", nullable = false)
    @JsonProperty(value = "nama_travel")
    private String namaTravel;

    @Size(min = 10, max = 13)
    @NotBlank
    @Pattern(regexp = "^[0-9]+$", message = "The value must be a positive integer")
    @Column(name = "no_telp", nullable = false)
    @JsonProperty(value = "no_telp")
    private String noTelp;

    @NotBlank
    @Column(name = "alamat", nullable = false)
    private String alamat;

    @NotBlank
    @Column(name = "no_polisi", nullable = false)
    @JsonProperty(value = "no_polisi")
    private String noPolisi;

    @NotBlank
    @Column(name = "jenis_bus", nullable = false)
    @JsonProperty(value = "jenis_bus")
    private String jenisBus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TravelEntity that = (TravelEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

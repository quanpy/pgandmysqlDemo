package org.example.pgnosqldemo.my;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * @author Ocean
 * @date 2024/4/12 15:33
 * @description ClassEntity
 */
@Data
@Entity
@Table(name = "clase")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clase_id_generator")
    @SequenceGenerator(name = "clase_id_generator", sequenceName = "clase_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    @Column(name = "creation_date")
    @CreationTimestamp
    private LocalDateTime creationDate;

}

package org.example.pgnosqldemo.pg;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

/**
 * @author Ocean
 * @date 2024/4/12 13:36
 * @description StudentEntity
 */

@Data
@Entity
@Table(name = "student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_generator")
    @SequenceGenerator(name = "student_id_generator", sequenceName = "student_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "creation_date")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Type(JsonType.class)
    @Column(name = "address", columnDefinition = "jsonb")
    private Address address;

    // Get、Set 省略
}
package org.example.pgnosqldemo.my;

import jakarta.persistence.*;
import lombok.*;
import org.example.pgnosqldemo.AbstractBaseTimeEntity;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

/**
 * @author Ocean
 * @date 2024/4/12 15:33
 * @description ClassEntity
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "clase")
public class ClassEntity extends AbstractBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clase_id_generator")
    @SequenceGenerator(name = "clase_id_generator", sequenceName = "clase_id_seq", allocationSize = 1)
    private Long id;

    private String name;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ClassEntity that = (ClassEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

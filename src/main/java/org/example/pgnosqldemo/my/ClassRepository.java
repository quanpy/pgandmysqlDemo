package org.example.pgnosqldemo.my;

import org.example.pgnosqldemo.pg.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ocean
 * @date 2024/4/12 13:46
 * @description StudentRepository
 */
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {


}
package org.example.pgnosqldemo.pg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Ocean
 * @date 2024/4/12 13:46
 * @description StudentRepository
 */
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    /**
    * @Author : Ocean
    * @createTime : 2024/4/12 14:31
    * @Description ：
     * JPQL 不支持 json 查询
    */
    @Query(value = "SELECT * FROM student WHERE address->>'postCode' = :postCode", nativeQuery = true)
    List<StudentEntity> findByAddressPostCode(@Param("postCode") String postCode);

}
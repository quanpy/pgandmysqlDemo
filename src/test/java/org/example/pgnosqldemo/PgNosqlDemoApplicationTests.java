package org.example.pgnosqldemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PgNosqlDemoApplicationTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void save() {
        Address address = new Address();
        address.setCity("shen yang");
        address.setPostCode("114200");
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setAddress(address);
        studentEntity.setAdmitYear("2024");
        StudentEntity save = studentRepository.save(studentEntity);
        System.out.println(save.toString());
    }

    @Test
    void queryId() {
        StudentEntity studentEntity = studentRepository.findById(1L).get();
        System.out.println(studentEntity);
    }

    @Test
    void queryJsonb() {
        List<StudentEntity> list = studentRepository.findByAddressPostCode("114200");
        System.out.println(list.toString());
    }

}

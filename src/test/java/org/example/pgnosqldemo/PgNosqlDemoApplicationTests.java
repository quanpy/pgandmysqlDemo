package org.example.pgnosqldemo;

import org.example.pgnosqldemo.pg.Address;
import org.example.pgnosqldemo.pg.StudentEntity;
import org.example.pgnosqldemo.pg.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        StudentEntity save = studentRepository.save(studentEntity);
        System.out.println(save.toString());
    }

    @Test
    void queryId() {
        StudentEntity studentEntity = studentRepository.findById(1L).get();
        System.out.println(studentEntity);
        LocalDateTime creationDate = studentEntity.getCreationDate();
        // 定义所需的日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 使用格式器将 Date 转换为字符串
        String format = creationDate.format(formatter);

        System.out.println("Formatted creation date and time: " + format);
    }

    @Test
    void queryJsonb() {
        List<StudentEntity> list = studentRepository.findByAddressPostCode("114200");
        System.out.println(list.toString());
    }

}

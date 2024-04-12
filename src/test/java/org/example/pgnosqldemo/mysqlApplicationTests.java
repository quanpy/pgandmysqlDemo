package org.example.pgnosqldemo;

import org.example.pgnosqldemo.my.ClassEntity;
import org.example.pgnosqldemo.my.ClassRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class mysqlApplicationTests {

    @Autowired
    private ClassRepository classRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void save() {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setName("1 ban");
        ClassEntity save = classRepository.save(classEntity);
        System.out.println(save.toString());
    }



}

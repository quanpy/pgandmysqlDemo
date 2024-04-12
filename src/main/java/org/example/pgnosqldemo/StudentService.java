package org.example.pgnosqldemo;

import jakarta.transaction.Transactional;
import org.example.pgnosqldemo.pg.Address;
import org.example.pgnosqldemo.pg.StudentEntity;
import org.example.pgnosqldemo.pg.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ocean
 * @date 2024/4/12 17:30
 * @description StudentService
 */
@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private void save() {
        Address address = new Address();
        address.setCity("shen yang");
        address.setPostCode("114200");
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setAddress(address);
        StudentEntity save = studentRepository.save(studentEntity);
        System.out.println(save.toString());
    }

}

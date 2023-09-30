package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    private final Map<String,Student> studentDb = new HashMap<>();
    private final Map<String,Teacher> teacherDb = new HashMap<>();
    private final Map<String,String> teacher_studentDb = new HashMap<>();

    public void addStudent(Student student) {
        studentDb.put(student.getName(),student);
    }
    public void addTeacher(Teacher teacher) {
        teacherDb.put(teacher.getName(),teacher);
    }
    public void addStudentTeacherPair(String student,String teacher) {
        if(studentDb.containsKey(student) && teacherDb.containsKey(teacher)) {
            teacher_studentDb.put(student,teacher);
        }
    }

    public Student getStudentByName(String name) {
        return studentDb.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherDb.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        List<String> list = new ArrayList<>();
        for(String key : teacher_studentDb.keySet()) {
            if(key.equals(teacher)) {
                list.add(teacher_studentDb.get(key));
            }
        }
        return list;
    }

    public List<String> getAllStudents() {
        List<String> list = new ArrayList<>();
        for(String key : studentDb.keySet()) {
            list.add(key);
        }
        return list;
    }
    public void deleteTeacherByName(String teacher) {
        String teacherToStudent = null;
        teacherToStudent = teacher_studentDb.get(teacher);
        teacher_studentDb.remove(teacher);
        studentDb.remove(teacherToStudent);
        teacherDb.remove(teacher);
    }
    public void deleteAllTeachers() {
        for (String key: teacherDb.keySet()) {
            teacherDb.remove(key);
            String student = teacher_studentDb.get(key);
            teacher_studentDb.remove(key);
            studentDb.remove(student);
        }
    }
}
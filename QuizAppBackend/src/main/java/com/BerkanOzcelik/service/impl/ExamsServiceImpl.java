package com.BerkanOzcelik.service.impl;

import com.BerkanOzcelik.dto.DtoExams;
import com.BerkanOzcelik.model.Departments;
import com.BerkanOzcelik.model.Exams;
import com.BerkanOzcelik.enums.ExamStatus;
import com.BerkanOzcelik.exception.BaseException;
import com.BerkanOzcelik.exception.ErrorMessage;
import com.BerkanOzcelik.exception.MessageType;
import com.BerkanOzcelik.repository.DepartmentsRepository;
import com.BerkanOzcelik.repository.ExamsRepository;
import com.BerkanOzcelik.service.IExamsService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ExamsServiceImpl implements IExamsService {

    @Autowired
    private ExamsRepository examsRepository;

    @Autowired
    private final DepartmentsRepository departmentsRepository;

    @Override
    public DtoExams addExam(DtoExams dtoExams) {
        Exams exam = new Exams();
        BeanUtils.copyProperties(dtoExams, exam, "departmentId"); // departmentId'yi kopyalamıyoruz

        Departments department = departmentsRepository.findById(dtoExams.getDepartmentId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Departman bulunamadı")));
        exam.setDepartment(department);

        exam.setCreateTime(new Date());

        Exams saved = examsRepository.save(exam);
        BeanUtils.copyProperties(saved, dtoExams, "department"); // department nesnesini kopyalamıyoruz
        dtoExams.setDepartmentId(saved.getDepartment().getId());
        return dtoExams;
    }

    @Override
    public DtoExams updateExam(Long examId, DtoExams dtoExams) {
        Optional<Exams> optExam = examsRepository.findById(examId);
        if (optExam.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Sınav bulunamadı"));
        }

        Exams exam = optExam.get();
        exam.setName(dtoExams.getName());
        exam.setCreatedBy(dtoExams.getCreatedBy());
        exam.setStartingTime(dtoExams.getStartingTime());
        exam.setEndingTime(dtoExams.getEndingTime());
        exam.setExamStatus(dtoExams.getExamStatus());

        Departments department = departmentsRepository.findById(dtoExams.getDepartmentId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Departman bulunamadı")));
        exam.setDepartment(department);

        exam.setUpdateTime(new Date());

        Exams updated = examsRepository.save(exam);
        BeanUtils.copyProperties(updated, dtoExams, "department");
        dtoExams.setDepartmentId(updated.getDepartment().getId());
        return dtoExams;
    }

    @Override
    public void deleteExam(Long examId) {
        if (!examsRepository.existsById(examId)) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Sınav bulunamadı"));
        }
        examsRepository.deleteById(examId);
    }

    @Override
    public List<DtoExams> listExams() {
        List<Exams> examsList = examsRepository.findAll();
        return examsList.stream().map(exam -> {
            DtoExams dto = new DtoExams();
            BeanUtils.copyProperties(exam, dto, "department");

            // Check if department is not null before accessing its id
            if (exam.getDepartment() != null) {
                dto.setDepartmentId(exam.getDepartment().getId());
            } else {
                // Set a default value or handle the case where department is null
                dto.setDepartmentId(null);  // Or set a default value if necessary
            }

            return dto;
        }).collect(Collectors.toList());
    }



    @Override
    public DtoExams updateExamStatus(Long examId, ExamStatus examStatus) {
        Optional<Exams> optExam = examsRepository.findById(examId);
        if (optExam.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Sınav bulunamadı"));
        }

        Exams exam = optExam.get();
        exam.setExamStatus(examStatus);
        exam.setUpdateTime(new Date());

        Exams updated = examsRepository.save(exam);
        DtoExams dtoExams = new DtoExams();
        BeanUtils.copyProperties(updated, dtoExams, "department");
        dtoExams.setDepartmentId(updated.getDepartment().getId());
        return dtoExams;
    }
}

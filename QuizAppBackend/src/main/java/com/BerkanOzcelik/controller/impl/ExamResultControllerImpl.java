    package com.BerkanOzcelik.controller.impl;

    import com.BerkanOzcelik.controller.IExamResultController;
    import com.BerkanOzcelik.controller.RestBaseController;
    import com.BerkanOzcelik.controller.RootEntity;
    import com.BerkanOzcelik.dto.DtoExamResults;
    import com.BerkanOzcelik.dto.DtoExamResultsIU;
    import com.BerkanOzcelik.model.ExamResults;
    import com.BerkanOzcelik.service.impl.ExamResultServiceImpl;
    import lombok.RequiredArgsConstructor;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/exam-results")
    @RequiredArgsConstructor
    public class ExamResultControllerImpl extends RestBaseController implements IExamResultController {

        private final ExamResultServiceImpl examResultService;

        @PostMapping("/save")
        @Override
        public ResponseEntity<RootEntity<ExamResults>> saveExamResult(@RequestBody DtoExamResultsIU input) {
            ExamResults saved = examResultService.saveExamResult(input);
            return ResponseEntity.ok(ok(saved));
        }

        @GetMapping("/all")
        @Override
        public ResponseEntity<RootEntity<Page<DtoExamResults>>> getAllExamResults(Pageable pageable) {
            Page<DtoExamResults> resultsPage = examResultService.getAllExamResults(pageable);
            return ResponseEntity.ok(ok(resultsPage));
        }


        @GetMapping("/exam/{examId}")
        @Override
        public ResponseEntity<RootEntity<List<DtoExamResults>>> getExamResultsByExamId(@PathVariable Long examId) {
            List<DtoExamResults> results = examResultService.getExamResultsByExamId(examId);
            return ResponseEntity.ok(ok(results));
        }
    }

package com.process.demo.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/questprocess")
public class QuestionnaireProcessController {

	@Autowired
	private QuestionnaireProcessRepo qpRepo;
	
	@GetMapping
	public QuestionnaireProcessList findAll() {
		return new QuestionnaireProcessList((Collection<QuestionnaireProcess>) qpRepo.findAll());
	}
	
	@GetMapping("/{id}")
	public QuestionnaireProcess findById(@PathVariable Long id){
		QuestionnaireProcess findqp = qpRepo.findOne(id);
		return findqp;
	}
	
	@PostMapping
	public ResponseEntity<QuestionnaireProcess> add(@RequestBody QuestionnaireProcess qp,
			UriComponentsBuilder uriComponentsBuilder) throws URISyntaxException {
		QuestionnaireProcess savedQuesProcess = qpRepo.save(qp);
//        statisticsServiceClient.sendMessage();
        UriComponents uriComponents = uriComponentsBuilder.path("/qp" + "/{id}")
                .buildAndExpand(savedQuesProcess.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(uriComponents.getPath()));
        return new ResponseEntity<>(savedQuesProcess, headers, HttpStatus.CREATED);
	}
	
    public static class QuestionnaireProcessList {
//        @JsonProperty("value")
        public List<QuestionnaireProcess> qp = new ArrayList<>();

        public QuestionnaireProcessList(Iterable<QuestionnaireProcess> qps) {
            qps.forEach(qp::add);
        }
    }
}

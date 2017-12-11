package com.process.demo.questionnaire;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
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
@RequestMapping("/quest")
public class QuestionnaireController {

	@Autowired
	private QuestionnaireRepo qRepo;
	@Autowired
	private StatisticsServiceClient statisticsServiceClient;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping
	public QuestionnaireList findAll() {
		return new QuestionnaireList((Collection<Questionnaire>) qRepo.findAll());
	}
	
	@GetMapping("/{id}")
	public Questionnaire findById(@PathVariable Long id){
		MDC.put("endpoint", "GET: " + "/quest" + "/" + id);
		logger.info("Get questionnaire with id: {}" , id);
		Questionnaire findq = this.qRepo.findOne(id);
		logger.trace("returning: {}", findq);
		return findq;
	}
	
	@PostMapping
	public ResponseEntity<Questionnaire> add(@RequestBody Questionnaire qp,
			UriComponentsBuilder uriComponentsBuilder) throws URISyntaxException {
		Questionnaire savedQuesProcess = qRepo.save(qp);
		String msg = "new questionnaire with id: " + savedQuesProcess.getId();
        statisticsServiceClient.sendMessage(msg);
        MDC.put("endpoint", "GET: " + "/quest/log" );
        logger.info("Send Message: {}", msg);
        logger.trace("returning: {}", savedQuesProcess);
        UriComponents uriComponents = uriComponentsBuilder.path("/qp" + "/{id}")
                .buildAndExpand(savedQuesProcess.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(uriComponents.getPath()));
        return new ResponseEntity<>(savedQuesProcess, headers, HttpStatus.CREATED);
	}
	
    public static class QuestionnaireList {
//      @JsonProperty("value")
      public List<Questionnaire> qp = new ArrayList<>();

      public QuestionnaireList(Iterable<Questionnaire> qps) {
          qps.forEach(qp::add);
      }
  }
}

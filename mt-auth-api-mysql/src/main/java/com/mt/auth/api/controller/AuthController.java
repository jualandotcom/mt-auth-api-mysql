package com.mt.auth.api.controller;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mt.auth.api.db.entity.TbAuth;
import com.mt.auth.api.db.repository.TbAuthRepository;
import com.mt.auth.api.model.AuthAddRequestModel;
import com.mt.auth.api.model.AuthAddResponseModel;
import com.mt.auth.api.model.AuthCheckRequestModel;
import com.mt.auth.api.model.AuthCheckResponseModel;
import com.mt.auth.api.model.AuthGenerateRequestModel;
import com.mt.auth.api.model.AuthGenerateResponseModel;
import com.mt.auth.api.model.AuthInvalidateResponseModel;
import com.mt.auth.api.util.MD5;
import com.mt.auth.api.util.Token;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

	private Logger log = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TbAuthRepository tbAuthRepository;
	
	@PostMapping("/add")
	@Transactional
	public HttpEntity<?> postAdd(@Valid @RequestBody AuthAddRequestModel requestModel) throws Exception {
		AuthAddResponseModel responseModel = new AuthAddResponseModel(requestModel);
		ResponseEntity<?> responseEntity = null;
		
		TbAuth exampleTbAuth = new TbAuth();
		exampleTbAuth.setTbaEmail(requestModel.getTbaEmail());
		
		if (tbAuthRepository.count(Example.of(exampleTbAuth)) > 0) {
			responseModel.setStatus("208");
			responseModel.setMessage("Email already exists");

			responseEntity = new ResponseEntity<>(responseModel, HttpStatus.ALREADY_REPORTED);
			log.info("postAdd responseEntity : " + objectMapper.writeValueAsString(responseEntity));

			return responseEntity;
		} else {
			TbAuth tbAuth = modelMapper.map(requestModel, TbAuth.class);
			tbAuth.setTbaCreateDate(new Date());
			tbAuth.setTbaCreateId(0);
			tbAuthRepository.save(tbAuth);
			
			responseModel.setStatus("200");
			responseModel.setMessage("Email added");

			responseEntity = new ResponseEntity<>(responseModel, HttpStatus.OK);
			log.info("postAdd responseEntity : " + objectMapper.writeValueAsString(responseEntity));

			return responseEntity;
		}
	}
	
	@PostMapping("/generate")
	@Transactional
	public HttpEntity<?> postGenerate(@Valid @RequestBody AuthGenerateRequestModel requestModel) throws Exception {
		requestModel.setTbaPassword(MD5.getInstance().get(requestModel.getTbaPassword()));
		
		AuthGenerateResponseModel responseModel = new AuthGenerateResponseModel(requestModel);
		ResponseEntity<?> responseEntity = null;
		
		TbAuth exampleTbAuth = new TbAuth();
		exampleTbAuth.setTbaEmail(requestModel.getTbaEmail());
		exampleTbAuth.setTbaPassword(requestModel.getTbaPassword());
		
		Optional<TbAuth> optTbAuth = tbAuthRepository.findOne(Example.of(exampleTbAuth));
		
		if (optTbAuth.isPresent()) {
			String token = Token.getInstance().generate(optTbAuth.get().getTbaEmail());
			
			Claims claims = Token.getInstance().claims(optTbAuth.get().getTbaEmail(), token);
			responseModel.setClaims(claims);
			
			responseModel.setToken(token);
			responseModel.setStatus("200");
			responseModel.setMessage("Auth generated");
			
			responseEntity = new ResponseEntity<>(responseModel, HttpStatus.OK);
			log.info("postGet responseEntity : " + objectMapper.writeValueAsString(responseEntity));

			return responseEntity;
		} else {
			responseModel.setStatus("401");
			responseModel.setError("Invalid login");
			
			responseEntity = new ResponseEntity<>(responseModel, HttpStatus.UNAUTHORIZED);
			log.info("postGet responseEntity : " + objectMapper.writeValueAsString(responseEntity));

			return responseEntity;
		}
	}
	
	@PostMapping("/check")
	@Transactional
	public HttpEntity<?> postCheck(@Valid @RequestBody AuthCheckRequestModel requestModel) throws Exception {
		AuthCheckResponseModel responseModel = new AuthCheckResponseModel(requestModel);
		ResponseEntity<?> responseEntity = null;
		
		try {
			Claims claims = Token.getInstance().claims(requestModel.getTbaEmail(), requestModel.getTbaToken());
			responseModel.setClaims(claims);
			
			responseModel.setStatus("200");
			responseModel.setMessage("Auth checked");
			
			responseEntity = new ResponseEntity<>(responseModel, HttpStatus.OK);
			log.info("postCheck responseEntity : " + objectMapper.writeValueAsString(responseEntity));
			
			return responseEntity;
		} catch (Exception e) {
			responseModel.setStatus("500");
			responseModel.setError(e.getMessage());
			
			responseEntity = new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
			log.info("postCheck responseEntity : " + objectMapper.writeValueAsString(responseEntity));
			
			return responseEntity;
		}
	}
	
	
	@GetMapping("/invalidate/{tbaEmail}")
	public HttpEntity<?> getProperties(@PathVariable String tbaEmail) throws JsonProcessingException {
		AuthInvalidateResponseModel responseModel = new AuthInvalidateResponseModel(null);
		ResponseEntity<?> responseEntity = null;
		
		try {
			TbAuth exampleTbAuth = new TbAuth();
			exampleTbAuth.setTbaEmail(tbaEmail);
			
			Optional<TbAuth> optTbAuth = tbAuthRepository.findOne(Example.of(exampleTbAuth));
			
			if (optTbAuth.isPresent()) {
				Token.getInstance().invalidate(tbaEmail);
				
				responseModel.setStatus("200");
				responseModel.setMessage("Auth invalidated");
				
				responseEntity = new ResponseEntity<>(responseModel, HttpStatus.OK);
				log.info("postInvalidate responseEntity : " + objectMapper.writeValueAsString(responseEntity));
				
				return responseEntity;				
			} else {
				responseModel.setStatus("404");
				responseModel.setMessage("Not found");

				responseEntity = new ResponseEntity<>(responseModel, HttpStatus.NOT_FOUND);
				log.info("postInvalidate responseEntity : " + objectMapper.writeValueAsString(responseEntity));
				
				return responseEntity;				
			}
		} catch (Exception e) {
			responseModel.setStatus("500");
			responseModel.setError(e.getMessage());
			
			responseEntity = new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
			log.info("postInvalidate responseEntity : " + objectMapper.writeValueAsString(responseEntity));
			
			return responseEntity;
		}
	}
	
	@GetMapping("/test")
	@Transactional
	public HttpEntity<?> postTest() {
//		UserConfirmationResponseModel responseModel = new UserConfirmationResponseModel(new UserConfirmationRequestModel());
//		
//		AuthCheckRequestModel authCheckRequestModel = new AuthCheckRequestModel();
//		authCheckRequestModel.setRequestDate("2019-03-21T16:56:50.706");
//		authCheckRequestModel.setRequestId("0123456789");
//		authCheckRequestModel.setTbaEmail("achmad.amri@gmail.com");
//		authCheckRequestModel.setTbaToken("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJtdC1hdXRoLWFwaSIsInN1YiI6InRva2VuIiwibmFtZSI6ImFjaG1hZC5hbXJpQGdtYWlsLmNvbSIsInNjb3BlIjoiYWRtaW5zIiwiaWF0IjoxNTU4MzM5Mzk2LCJleHAiOjE1NTg0MjU3OTZ9.TrcRAG78Bkxu7R9MAagqkb5jwrU22gDFXdaPNayBlBU");
//		
//		if (Auth.getInstance().check(authCheckRequestModel)) {
//			return new ResponseEntity<>(responseModel, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
		AuthAddRequestModel x = new AuthAddRequestModel();
		return new ResponseEntity<>(x, HttpStatus.OK);
	}
}

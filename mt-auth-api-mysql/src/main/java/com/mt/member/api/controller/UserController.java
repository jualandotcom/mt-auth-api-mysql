package com.mt.member.api.controller;

import java.util.Date;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mt.member.api.db.entity.TbUser;
import com.mt.member.api.db.repository.TbUserRepository;
import com.mt.member.api.model.user.UserAddRequestModel;
import com.mt.member.api.model.user.UserAddResponseModel;

@RestController
@RequestMapping("/user")
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TbUserRepository tbUserRepository;
	
	@PostMapping("/add")
	public HttpEntity<?> postAdd(@Valid @RequestBody UserAddRequestModel userAddRequestModel) throws Exception {
		UserAddResponseModel responseModel = new UserAddResponseModel(userAddRequestModel);
		ResponseEntity<?> responseEntity = null;
		
		TbUser example = new TbUser();
		example.setTbuEmail(userAddRequestModel.getTbuEmail());
		
		if (tbUserRepository.count(Example.of(example)) > 0) {
			responseModel.setStatus("208");
			responseModel.setError("Data already exists");

			responseEntity = new ResponseEntity<>(responseModel, HttpStatus.ALREADY_REPORTED);
			log.info("responseEntity : " + objectMapper.writeValueAsString(responseEntity));

			return responseEntity;
		} else {
			TbUser tbUser = modelMapper.map(userAddRequestModel, TbUser.class);
			tbUser.setTbuCreateDate(new Date());
			tbUser.setTbuCreateId(0);
			tbUserRepository.save(tbUser);
			
			responseModel.setTbUsers(tbUser);
			responseModel.setStatus("200");

			responseEntity = new ResponseEntity<>(responseModel, HttpStatus.OK);
			log.info("responseEntity : " + objectMapper.writeValueAsString(responseEntity));

			return responseEntity;
		}
	}
	
	@GetMapping("/test")
	public HttpEntity<?> test() throws Exception {
		UserAddRequestModel userAddRequestModel = new UserAddRequestModel();
		UserAddResponseModel responseModel = new UserAddResponseModel(userAddRequestModel);
		
		TbUser tbUser = tbUserRepository.findAll().get(0);
		
		responseModel.setTbUsers(tbUser);
		responseModel.setStatus("200");
		
		ResponseEntity<?> responseEntity = new ResponseEntity<>(responseModel, HttpStatus.OK);
		
		return responseEntity;
	}
	
	
//	@GetMapping("/mt-member-api-mysql/gettweet/screenname/{screenName}/{pageNumber}/{pageSize}/{sort}/{sortBy}")
//	public HttpEntity<?> getTweetUserByScreenName(@PathVariable String screenName, @PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String sort, @PathVariable String sortBy) throws JsonProcessingException {
//		log.debug("getTweetUserByScreenName : " + objectMapper.writeValueAsString(screenName));
//		
//		TbTwitterUser tbTwitterUser = tbTwitterUserRepository.findByTtuScreenName(screenName);
//		List<TbTwitterTweet> lstTbTwitterTweet = tbTwitterTweetRepository.findByTtuTid(tbTwitterUser.getTtuTid(), PageRequest.of(pageNumber, pageSize, sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
//		
//		return new ResponseEntity<>(lstTbTwitterTweet, HttpStatus.OK);
//	}
}

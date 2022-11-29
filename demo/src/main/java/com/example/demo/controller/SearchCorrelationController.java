package com.example.demo.controller;

import static com.example.demo.common.WebConst.CORRELATION_PRD_AUTO_URL;
import static com.example.demo.common.WebConst.CORRELATION_PRD_NORMAL_URL;
import static com.example.demo.common.WebConst.CORRELATION_TMS_AUTO_URL;
import static com.example.demo.common.WebConst.CORRELATION_TMS_NORMAL_URL;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.common.CorrelationResponse;
import com.example.demo.entity.common.DataAnaysisRequest;
import com.example.demo.service.SearchCorrelationPrdService;
import com.example.demo.service.SearchCorrelationTmsService;

@RestController
public class SearchCorrelationController {

	@Autowired
	private SearchCorrelationTmsService searchCorrelationTmsService;

	@Autowired
	private SearchCorrelationPrdService searchCorrelationPrdService;
	
	@RequestMapping(path = CORRELATION_TMS_NORMAL_URL, method = RequestMethod.POST)
	public CorrelationResponse NormalCorrelationTms(@RequestBody DataAnaysisRequest request) throws IOException {

		return searchCorrelationTmsService.normal(request);

	}	
	
	@RequestMapping(path = CORRELATION_PRD_NORMAL_URL, method = RequestMethod.POST)
	public CorrelationResponse NormalCorrelationPrd(@RequestBody DataAnaysisRequest request) throws IOException {

		return searchCorrelationPrdService.normal(request);

	}
	
	@RequestMapping(path = CORRELATION_TMS_AUTO_URL, method = RequestMethod.POST)
	public List<Object> AutoCorrelationTms(@RequestBody DataAnaysisRequest request) throws IOException {

		return searchCorrelationTmsService.auto(request);

	}	
	
	@RequestMapping(path = CORRELATION_PRD_AUTO_URL, method = RequestMethod.POST)
	public List<Object> AutoCorrelationPrd(@RequestBody DataAnaysisRequest request) throws IOException {

		return searchCorrelationPrdService.auto(request);

	}	


}
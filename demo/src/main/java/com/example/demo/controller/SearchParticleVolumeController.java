package com.example.demo.controller;

import static com.example.demo.common.WebConst.POWER_VOLUME_FIND_URL;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.common.DataAnaysisRequest;
import com.example.demo.entity.particlelogging.AllViewEntity;
import com.example.demo.service.SearchParticleVolumeService;

@RestController
public class SearchParticleVolumeController {

	@Autowired
	private SearchParticleVolumeService searchParticleVolumeService;
	
	@RequestMapping(path = POWER_VOLUME_FIND_URL, method = RequestMethod.POST)
	public List<AllViewEntity> ParticleVolumeFind(@RequestBody DataAnaysisRequest request) throws IOException {

		return searchParticleVolumeService.find(request);
		
	}

}
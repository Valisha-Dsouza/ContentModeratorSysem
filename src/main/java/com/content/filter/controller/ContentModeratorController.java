package com.content.filter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.content.filter.model.ApiResponse;
import com.content.filter.service.ContentModeratorService;

@RestController
@RequestMapping("/moderator")
public class ContentModeratorController {

	@Autowired
	@Qualifier("contentModeratorService")
	private ContentModeratorService contentModeratorService;

	@RequestMapping(value = "/invalidWords", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse findInvalidWords(@RequestParam(required = true) String text,
			@RequestParam(required = true) String langCode) {
		return new ApiResponse(contentModeratorService.findBadWords(text, langCode), HttpStatus.OK);
	}

}

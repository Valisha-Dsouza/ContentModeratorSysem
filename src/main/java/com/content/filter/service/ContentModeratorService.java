package com.content.filter.service;

import java.util.List;

public interface ContentModeratorService {

	public List<String> findBadWords(String text, String langCode);

	public boolean validate(String text, String langCode);

}

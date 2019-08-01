package com.content.filter.dao;

import java.util.List;
import java.util.Map;

public interface BadContentDao {
	public Map<String, List<String>> loadAllBadWords();

	public List<String> loadBadWordsForLang(String langCode);
}

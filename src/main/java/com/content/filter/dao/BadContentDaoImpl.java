package com.content.filter.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.content.filter.config.BadWordsLoader;

@Repository
public class BadContentDaoImpl implements BadContentDao {

	public Map<String, List<String>> loadAllBadWords() {
		return BadWordsLoader.getInstance().getLangWordMap();
	}

	public List<String> loadBadWordsForLang(String langCode) {
		return BadWordsLoader.getInstance().getWords(langCode);
	}

}

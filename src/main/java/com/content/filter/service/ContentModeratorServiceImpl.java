package com.content.filter.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tartarus.snowball.ext.porterStemmer;

import com.content.filter.dao.BadContentDao;

@Service("contentModeratorService")
public class ContentModeratorServiceImpl implements ContentModeratorService {

	@Autowired
	private BadContentDao badContentDao;

	public List<String> findBadWords(String text, String langCode) {
		ArrayList<String> foundWords = null;

		if (null != text && null != langCode) {
			List<String> badWords = badContentDao.loadBadWordsForLang(langCode);

			if (null != badWords && !badWords.isEmpty()) {
				String[] textSplit = text.trim().split("\\s");

				for (String str : textSplit) {
					if (null != str && badWords.contains(stemTerm(str).toLowerCase())) {
						if (null == foundWords) {
							foundWords = new ArrayList<String>();
						}

						foundWords.add(str);
					}
				}
			}
		}

		return foundWords;
	}

	public boolean validate(String text, String langCode) {
		boolean valid = true;

		if (null != text && null != langCode) {
			List<String> badWords = badContentDao.loadBadWordsForLang(langCode);

			if (null != badWords && !badWords.isEmpty()) {
				String[] textSplit = text.trim().split("\\s");

				for (String str : textSplit) {
					if (null != str && badWords.contains(stemTerm(str).toLowerCase())) {
						valid = false;
						break;
					}
				}
			}
		}

		return valid;
	}

	private String stemTerm(String word) {
		porterStemmer stemmer = new porterStemmer();
		stemmer.setCurrent(word);
		stemmer.stem();
		return stemmer.getCurrent();
	}

}

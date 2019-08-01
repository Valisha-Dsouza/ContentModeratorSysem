package com.content.filter.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class BadWordsLoader {

	private static BadWordsLoader theInstance;
	private HashMap<String, List<String>> langWordMap;

	private BadWordsLoader() {
		init();
	}

	public static BadWordsLoader getInstance() {
		if (null == theInstance) {
			synchronized (BadWordsLoader.class) {
				if (null == theInstance) {
					theInstance = new BadWordsLoader();
				}
			}
		}
		return theInstance;
	}

	private void init() {
		HashMap<String, List<String>> langWordMap = new HashMap<String, List<String>>();
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		try {
			inputStream = getClass().getResourceAsStream("/badWords.yml");
			inputStreamReader = new InputStreamReader(inputStream);
			Properties properties = new Properties();
			properties.load(inputStreamReader);
			for (Map.Entry<Object, Object> entry : properties.entrySet()) {
				String values = (String) entry.getValue();
				langWordMap.put(entry.getKey().toString(), Arrays.asList(values.split(",")));
			}
		} catch (Exception exception) {

			if (null != inputStreamReader) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			exception.printStackTrace();
		}

		this.langWordMap = langWordMap;
	}

	public HashMap<String, List<String>> getLangWordMap() {
		return langWordMap;
	}

	public List<String> getWords(String langCode) {
		return langWordMap.get(langCode);
	}

}

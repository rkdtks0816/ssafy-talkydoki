package com.ssafy.backend.domain.news.service;

import com.ssafy.backend.domain.news.dto.KeywordPostRequest;
import com.ssafy.backend.domain.news.exception.KeywordErrorCode;
import com.ssafy.backend.domain.news.exception.KeywordException;
import com.ssafy.backend.domain.news.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class KeywordServiceImpl implements KeywordService {
    private final KeywordRepository keywordRepository;

    @Override
    public void insertKeyword(KeywordPostRequest keywordPostRequest) {
        // 입력받은 일본어가 DB에 존재할 경우 : 예외 처리
        if (keywordRepository.existsByJapanese(keywordPostRequest.getJapanese())) {
            throw new KeywordException(KeywordErrorCode.EXIST_KEYWORD);
        }
        // DB에 존재하지 않는 경우 : 키워드 저장
        keywordRepository.save(keywordPostRequest.toEntity());
    }
}
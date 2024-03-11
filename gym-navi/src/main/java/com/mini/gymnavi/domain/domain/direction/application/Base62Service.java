package com.mini.gymnavi.domain.domain.direction.application;

import io.seruco.encoding.base62.Base62;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Base62Service {

    private static final Base62 base62Instance = Base62.createInstance();

    public String encodeDirectionId(Long directId) {
        log.info("id",directId);
        return new String(base62Instance.encode(String.valueOf(directId).getBytes()));
    }

    public Long decodeDirectionId(String directId) {
        String resultUrl = new String(base62Instance.decode(String.valueOf(directId).getBytes()));
        return Long.valueOf(resultUrl);
    }
}

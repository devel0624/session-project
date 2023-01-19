package com.nhnacademy.sessionproject.session;

import com.nhnacademy.sessionproject.dto.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public interface Session {

    void removeAttribute(String key);

    void updateAccessTime();

    String getSessionId();

    String getLastAccessedTime();

    String getCreationTime();

    void setAttribute(String field, Object value);

    Object getAttribute(String key);

    void invalidate();
}

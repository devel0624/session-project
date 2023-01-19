package com.nhnacademy.sessionproject.session;


import com.nhnacademy.sessionproject.entity.Data;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class DataBaseSession implements Session{

    private final DataBaseRepository dataBaseRepository;
    private final String sessionId;
    private final LocalDateTime creationTime;
    private LocalDateTime lastAccessTime;

    public DataBaseSession(DataBaseRepository dataBaseRepository) {
        this.dataBaseRepository = dataBaseRepository;
        this.sessionId = UUID.randomUUID().toString();
        this.creationTime = LocalDateTime.now();
        this.lastAccessTime = creationTime;
    }

    @Override
    public void setAttribute(String field, Object value) {
        this.dataBaseRepository.save(
            Data.builder()
                .key(this.sessionId)
                .field(field)
                .value(value.toString())
                .build()
        );
    }

    @Override
    public void removeAttribute(String field) {
        if(dataBaseRepository.existsByKeyAndField(this.sessionId, field)){
            this.dataBaseRepository.deleteByKeyAndField(this.sessionId,field);
        }
    }

    @Override
    public void updateAccessTime() {
        this.lastAccessTime = LocalDateTime.now();
    }

    @Override
    public String getSessionId() {
        return this.sessionId;
    }

    @Override
    public String getLastAccessedTime() {
        return this.lastAccessTime.toString();
    }

    @Override
    public String getCreationTime() {
        return this.creationTime.toString();
    }

    @Transactional(readOnly = true)
    @Override
    public Object getAttribute(String field) {
        Optional<Data> data = dataBaseRepository.findByKeyAndField(sessionId,field);

        return data.<Object>map(Data::getValue).orElse(null);
    }
    @Override
    public void invalidate() {
        this.dataBaseRepository.deleteAllByKey(sessionId);
    }
}

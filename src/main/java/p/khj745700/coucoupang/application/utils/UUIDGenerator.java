package p.khj745700.coucoupang.application.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGenerator {

    public String get() { return UUID.randomUUID().toString(); }
}

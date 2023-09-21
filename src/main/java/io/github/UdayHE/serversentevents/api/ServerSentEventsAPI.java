package io.github.UdayHE.serversentevents.api;


import io.github.UdayHE.serversentevents.service.SSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse")
public class ServerSentEventsAPI {

    @Autowired
    private SSEService service;

    @GetMapping(path = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> subscribe() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        service.addEmitter(emitter);
        service.sendEvents();
        return ResponseEntity.ok(emitter);
    }
}

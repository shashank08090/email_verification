package com.spring.verification.springbackendverification.controller;

import com.spring.verification.springbackendverification.model.Message;
import com.spring.verification.springbackendverification.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageRepository repository;

    // Create a new Message
    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        // Ensure ID is not set (let the database generate it)
        message.setId(null);
        Message savedMessage = repository.save(message);
        return ResponseEntity.ok(savedMessage);
    }

    // Read all Messages
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = repository.findAll();
        return ResponseEntity.ok(messages);
    }

    // Read a single Message by ID (sent in JSON body)
    @PostMapping("/get")
    public ResponseEntity<Message> getMessageById(@RequestBody IdRequest idRequest) {
        Optional<Message> message = repository.findById(idRequest.getId());
        return message.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a Message (ID and updated fields sent in JSON body)
    @PutMapping
    public ResponseEntity<Message> updateMessage(@RequestBody Message updatedMessage) {
        if (updatedMessage.getId() == null) {
            return ResponseEntity.badRequest().build(); // ID is required for update
        }
        Optional<Message> existingMessage = repository.findById(updatedMessage.getId());
        if (existingMessage.isPresent()) {
            Message message = existingMessage.get();
            // Update fields (e.g., content)
            message.setContent(updatedMessage.getContent()); // Adjust based on Message fields
            Message savedMessage = repository.save(message);
            return ResponseEntity.ok(savedMessage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Message by ID (sent in JSON body)
    @DeleteMapping
    public ResponseEntity<Void> deleteMessage(@RequestBody IdRequest idRequest) {
        if (idRequest.getId() == null) {
            return ResponseEntity.badRequest().build(); // ID is required
        }
        if (repository.existsById(idRequest.getId())) {
            repository.deleteById(idRequest.getId());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

// Helper class to receive ID in JSON body
class IdRequest {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
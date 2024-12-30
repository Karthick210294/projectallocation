package com.example.employeeallocation.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

// NotificationEvent.java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEvent {
    private String employeeId;
    private String type;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime timestamp;
    private Map<String, Object> additionalDetails;


    private NotificationEvent(Builder builder) {
        this.employeeId = builder.employeeId;
        this.type = builder.type;
        this.timestamp = builder.timestamp;
        this.additionalDetails = builder.additionalDetails;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String employeeId;
        private String type;
        private LocalDateTime timestamp;
        private Map<String, Object> additionalDetails;

        public Builder employeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder additionalDetails(Map<String, Object> additionalDetails) {
            this.additionalDetails = additionalDetails;
            return this;
        }

        public NotificationEvent build() {
            return new NotificationEvent(this);
        }
    }

}

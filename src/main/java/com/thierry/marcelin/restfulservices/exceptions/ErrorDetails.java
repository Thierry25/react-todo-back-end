package com.thierry.marcelin.restfulservices.exceptions;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime localDateTime, String description, String details) {
}

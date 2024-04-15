package com.stock.sp.apiserver.ticker.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/")
@Tag(name = "root", description = "Root")
public class AppController {
  @Operation(summary = "Hello, World")
  @GetMapping("/")
  public Map<String, Object> greeting() {
    return Collections.singletonMap("message", "Hello, World");
  }
}

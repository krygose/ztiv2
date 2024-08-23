package com.example.zti.service.user.problem;



import com.example.zti.common.problem.BadRequestProblem;

import java.util.Map;

public class EmailAddressNotFoundProblem extends BadRequestProblem {
  public EmailAddressNotFoundProblem() {
    super("Email Address Not Found", Map.of("translationKey", "EMAIL_ADDRESS_NOT_FOUND"));
  }
}
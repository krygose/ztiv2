package com.example.zti.service.user.problem;


import com.example.zti.common.problem.BadRequestProblem;

import java.util.Map;

public class InvalidEmailAddressProblem extends BadRequestProblem {
    public InvalidEmailAddressProblem() {
    super("Invalid Email Address", Map.of("translationKey", "INVALID_EMAIL_ADDRESS"));
    }
}
package com.kevin_leader.controllers;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kevin_leader.models.Employee;
import com.kevin_leader.services.LogInService;

import io.javalin.http.Handler;

public class LogInController {

    private static final Logger log = Logger.getLogger(LogInController.class);
    private LogInService liServ;
    private Gson gson;

    public LogInController(LogInService liServ) {
        this.liServ = liServ;
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    public Handler processLogInAttempt = (context) -> {
        log.info("Start Handler processLogInAttempt");

        Employee logInAttempt = gson.fromJson(context.body(), Employee.class);
        log.info(logInAttempt);
        
        Employee employee = liServ.checkForEmployee(logInAttempt);
        log.info(employee);
        
        if (employee != null) {
            context.result(gson.toJson(employee));
            context.status(200);
        } else {
            context.result("{}");
            context.status(404);
        }
    };

}

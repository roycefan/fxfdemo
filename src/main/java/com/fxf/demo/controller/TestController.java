package com.fxf.demo.controller;

import com.fxf.demo.service.TransactionRecordService;
import com.fxf.demo.service.dto.Result;
import com.fxf.demo.service.dto.TransactionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class TestController {

    @Autowired
    private TransactionRecordService transactionRecordService;

    @PostMapping(value = "/insert")
    @ResponseBody
    public Result insert(@RequestBody TransactionRecord transactionRecord) {

        try {
            transactionRecordService.submitTransaction(transactionRecord);
            return Result.ok("", "");
        } catch (Exception exception) {
            return Result.failed(exception.getMessage());
        }
    }


    @PostMapping(value = "/update")
    @ResponseBody
    public Result update(@RequestBody TransactionRecord transactionRecord) {
        try {
            transactionRecordService.submitTransaction(transactionRecord);
            return Result.ok("", "");
        } catch (Exception exception) {
            return Result.failed(exception.getMessage());
        }
    }


    @PostMapping(value = "/cancel")
    @ResponseBody
    public Result cancel(@RequestBody TransactionRecord transactionRecord) {
        try {
            transactionRecordService.submitTransaction(transactionRecord);
            return Result.ok("", "");
        } catch (Exception exception) {
            return Result.failed(exception.getMessage());
        }
    }


    @GetMapping(value = "/position")
    @ResponseBody
    public Result position() {
        try {
            return Result.ok(transactionRecordService.fetchAllPosition(), "");
        } catch (Exception exception) {
            return Result.failed(exception.getMessage());
        }
    }


    @RequestMapping("/")
    public String index(Model model, HttpServletResponse response) {
        model.addAttribute("name", "this is ok");
        return "index";
    }

}

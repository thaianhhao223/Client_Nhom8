package com.iuh.clientnhom8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping("/bills")
public class BillController {
    private Logger logger = Logger.getLogger(getClass().getName());
}

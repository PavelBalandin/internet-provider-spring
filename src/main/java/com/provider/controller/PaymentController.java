package com.provider.controller;

import com.provider.dto.PaymentDTO;
import com.provider.entity.Payment;
import com.provider.mapper.PaymentMapper;
import com.provider.security.JwtProvider;
import com.provider.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    private final JwtProvider jwtProvider;

    private final PaymentMapper paymentMapper;

    @Autowired
    public PaymentController(PaymentService paymentService, JwtProvider jwtProvider, PaymentMapper paymentMapper) {
        this.paymentService = paymentService;
        this.jwtProvider = jwtProvider;
        this.paymentMapper = paymentMapper;
    }

    @GetMapping("/user")
    public ResponseEntity<List<PaymentDTO>> getByUserId(@RequestHeader(name = "Authorization") String token) {
        log.trace("Getting payment list by user id");
        Long id = jwtProvider.getUserIdFromToken(token);
        return new ResponseEntity<>(paymentMapper.listEntityToDTOList(paymentService.getByUserId(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> create(@RequestBody PaymentDTO paymentDTO, @RequestHeader(name = "Authorization") String token) {
        log.trace("Creating payment");
        Payment payment = paymentMapper.DTOtoEntity(paymentDTO);
        payment.getUser().setId(jwtProvider.getUserIdFromToken(token));
        return new ResponseEntity<>(paymentMapper.entityToDTO(paymentService.create(payment)), HttpStatus.CREATED);
    }
}

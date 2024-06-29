package swp391.learning.application.service;

import swp391.learning.domain.dto.common.PaymentRes;
import swp391.learning.domain.dto.common.ResponseCommon;

import java.io.UnsupportedEncodingException;

public interface PaymentService {

    ResponseCommon<PaymentRes> addPayment(double amount) throws UnsupportedEncodingException;


}


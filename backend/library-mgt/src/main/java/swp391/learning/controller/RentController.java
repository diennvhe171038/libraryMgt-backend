package swp391.learning.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import swp391.learning.application.service.BookCopyService;
import swp391.learning.application.service.LoanService;
import swp391.learning.application.service.UserService;
import swp391.learning.domain.dto.common.ResponseCommon;
import swp391.learning.domain.dto.request.admin.book.BookCopyRequest;
import swp391.learning.domain.dto.request.user.loan.LoanStateRequest;
import swp391.learning.domain.dto.response.user.rent.RentRequest;
import swp391.learning.domain.dto.response.user.rent.RentResponse;
import swp391.learning.domain.entity.BookCopy;
import swp391.learning.domain.entity.Loan;
import swp391.learning.domain.entity.User;
import swp391.learning.domain.enums.EnumLoanStatus;
import swp391.learning.domain.enums.ResponseCode;
import swp391.learning.utils.CommonUtils;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rent")
public class RentController {

    private UserService userService;
    private BookCopyService bookCopyService;
    private LoanService loanService;

    @Autowired
    public RentController(UserService userService, BookCopyService bookCopyService, LoanService loanService) {
        this.userService = userService;
        this.bookCopyService = bookCopyService;
        this.loanService = loanService;
    }

    @GetMapping("/getRentsByUserId")
    public ResponseEntity<ResponseCommon<List<RentResponse>>> getRentsByUserId(
            @RequestParam(name = "userId") int userId) {
        List<RentResponse> list = loanService.getLoansByUserIdAndActive(userId);
        if (list != null) {
            return ResponseEntity.ok(new ResponseCommon<>(ResponseCode.SUCCESS.getCode(), "Success", list));
        } else {
            return ResponseEntity.badRequest()
                    .body(new ResponseCommon<>(ResponseCode.FAIL.getCode(), "Get payment failed", null));
        }
    }

    @GetMapping("/getAllRentWithPendingState")
    public ResponseEntity<ResponseCommon<List<RentResponse>>> getAllRentWithPendingState() {
        List<RentResponse> list = loanService.getLoansWithPendingState();
        if (list != null) {
            return ResponseEntity.ok(new ResponseCommon<>(ResponseCode.SUCCESS.getCode(), "Success", list));
        } else {
            return ResponseEntity.badRequest()
                    .body(new ResponseCommon<>(ResponseCode.FAIL.getCode(), "Get payment failed", null));
        }
    }

    @GetMapping("/getAllRentWithRejectState")
    public ResponseEntity<ResponseCommon<List<RentResponse>>> getAllRentWithRejectState() {
        List<RentResponse> list = loanService.getLoansWithRejectState();
        if (list != null) {
            return ResponseEntity.ok(new ResponseCommon<>(ResponseCode.SUCCESS.getCode(), "Success", list));
        } else {
            return ResponseEntity.badRequest()
                    .body(new ResponseCommon<>(ResponseCode.FAIL.getCode(), "Get payment failed", null));
        }
    }

    @GetMapping("/getAllRentWithReturnState")
    public ResponseEntity<ResponseCommon<List<RentResponse>>> getAllRentWithReturnState() {
        List<RentResponse> list = loanService.getLoansWithReturnState();
        if (list != null) {
            return ResponseEntity.ok(new ResponseCommon<>(ResponseCode.SUCCESS.getCode(), "Success", list));
        } else {
            return ResponseEntity.badRequest()
                    .body(new ResponseCommon<>(ResponseCode.FAIL.getCode(), "Get payment failed", null));
        }
    }

    @GetMapping("/getAllRentWithReturningState")
    public ResponseEntity<ResponseCommon<List<RentResponse>>> getAllRentWithReturningState() {
        List<RentResponse> list = loanService.getLoansWithReturningState();
        if (list != null) {
            return ResponseEntity.ok(new ResponseCommon<>(ResponseCode.SUCCESS.getCode(), "Success", list));
        } else {
            return ResponseEntity.badRequest()
                    .body(new ResponseCommon<>(ResponseCode.FAIL.getCode(), "Get payment failed", null));
        }
    }


    @PostMapping("/approve")
    public ResponseEntity<ResponseCommon<RentResponse>> approveLoan(@RequestBody LoanStateRequest loanStateRequest){
        Loan result = loanService.approveLoan(loanStateRequest.getLoanId(), loanStateRequest.getBookCopyId());
        if (result != null) {
            return ResponseEntity.ok(new ResponseCommon<>(ResponseCode.SUCCESS.getCode(), "Success", null));
        } else {
            return ResponseEntity.badRequest()
                    .body(new ResponseCommon<>(ResponseCode.FAIL.getCode(), "Get payment failed", null));
        }
    }

    @PostMapping("/return")
    public ResponseEntity<ResponseCommon<RentResponse>> returnLoan(@RequestBody LoanStateRequest loanStateRequest){
        Loan result = loanService.returnLoan(loanStateRequest.getLoanId(), loanStateRequest.getBookCopyId());
        if (result != null) {
            return ResponseEntity.ok(new ResponseCommon<>(ResponseCode.SUCCESS.getCode(), "Success", null));
        } else {
            return ResponseEntity.badRequest()
                    .body(new ResponseCommon<>(ResponseCode.FAIL.getCode(), "Get payment failed", null));
        }
    }

    @PostMapping("/returning")
    public ResponseEntity<ResponseCommon<RentResponse>> returningLoan(@RequestBody LoanStateRequest loanStateRequest){
        Loan result = loanService.returningLoan(loanStateRequest.getLoanId(), loanStateRequest.getBookCopyId());
        if (result != null) {
            return ResponseEntity.ok(new ResponseCommon<>(ResponseCode.SUCCESS.getCode(), "Success", null));
        } else {
            return ResponseEntity.badRequest()
                    .body(new ResponseCommon<>(ResponseCode.FAIL.getCode(), "Get payment failed", null));
        }
    }

    @PostMapping("/reject")
    public ResponseEntity<ResponseCommon<RentResponse>> rejectLoan(@RequestBody LoanStateRequest loanStateRequest){
        Loan result = loanService.rejectLoan(loanStateRequest.getLoanId(), loanStateRequest.getBookCopyId(), loanStateRequest.getNote());
        if (result != null) {
            return ResponseEntity.ok(new ResponseCommon<>(ResponseCode.SUCCESS.getCode(), "Success", null));
        } else {
            return ResponseEntity.badRequest()
                    .body(new ResponseCommon<>(ResponseCode.FAIL.getCode(), "Get payment failed", null));
        }
    }

    @Operation(summary = "rent a book")
    @PostMapping("/")
    public ResponseEntity<ResponseCommon<RentResponse>> rentBook(
            @RequestBody RentRequest rentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String userEmail = userDetails.getUsername();
        // get user by email
        User user = userService.getUserByEmail(userEmail);
        Integer member_subscription_id = user.getMemberSubscription() != null ? user.getMemberSubscription().getId()
                : null;
        if (member_subscription_id == null) {
            return ResponseEntity.badRequest().body(new ResponseCommon<RentResponse>(400, "Did not register a member"));
        }
        // Proceed with member_subscription_id knowing it's not null
        // LocalDateTime data = CommonUtils.convertStringToLocalDateTime(rentRequest.getReturnAt());
        // System.out.println(data);
        // create book copy
        BookCopyRequest bookCopyRequest = new BookCopyRequest(rentRequest.getId(),user.getId(),
        rentRequest.getBarcode(), EnumLoanStatus.PENDING.toString());
        BookCopy bCopy = bookCopyService.saveBookCopy(bookCopyRequest);
        // create loan
        Loan loan = new Loan(bCopy, user,
        CommonUtils.convertStringToLocalDateTime(rentRequest.getBorrowAt()),
        CommonUtils.convertStringToLocalDateTime(rentRequest.getReturnAt()),
        CommonUtils.convertStringToLocalDateTimeAfter7Days(rentRequest.getReturnAt()),
        EnumLoanStatus.PENDING, rentRequest.getDescription(),
        rentRequest.getPrice());
        loanService.addLoan(loan);
        return ResponseEntity.ok().body(new ResponseCommon<RentResponse>(200, "Request rent successfully"));
    }
}

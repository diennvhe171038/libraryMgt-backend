package swp391.learning.application.service;

import swp391.learning.domain.dto.response.user.rent.RentResponse;
import swp391.learning.domain.entity.Loan;

import java.util.List;

public interface LoanService {
    void addLoan(Loan loan);

    List<RentResponse> getLoansByUserIdAndActive(int userId);
    List<RentResponse> getLoansWithPendingState();
    List<RentResponse> getLoansWithRejectState();
    List<RentResponse> getLoansWithReturnState();
    Loan approveLoan(int loanId, int bookCopyId);
    Loan rejectLoan(int loanId, int bookCopyId, String note);

    Loan returningLoan(int loanId, int bookCopyId);
    Loan returnLoan(int loanId, int bookCopyId);

    List<RentResponse> getLoansWithReturningState();
    
}
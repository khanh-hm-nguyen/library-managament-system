package com.library.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {

    // Cost per day overdue (e.g., $0.50)
    private static final double FINE_PER_DAY = 0.50;

    /**
     * Calculates the total fine based on how many days late the item is.
     * @param expectedReturnDate When the book should have been returned
     * @param actualReturnDate When the book was actually returned
     * @return 0.0 if on time, otherwise the calculated fine.
     */
    public double calculateFine(LocalDate expectedReturnDate, LocalDate actualReturnDate) {
        // If returned before or on the due date, no fine.
        if (actualReturnDate.isBefore(expectedReturnDate) || actualReturnDate.isEqual(expectedReturnDate)) {
            return 0.0;
        }

        // Calculate days between
        long daysOverdue = ChronoUnit.DAYS.between(expectedReturnDate, actualReturnDate);

        // Math: days * cost
        return daysOverdue * FINE_PER_DAY;
    }
}
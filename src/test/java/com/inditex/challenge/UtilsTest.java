package com.inditex.challenge;


import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static com.inditex.challenge.utils.DateUtils.*;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilsTest {
    @Test
    void testParseDateString() throws ParseException {
        String dateString = "2024-04-13-00.00.00";

        Date expectedDate = stringToDate(dateString);
        assertNotNull(expectedDate);

        String parsedDateString = dateToString(expectedDate);
        assertEquals(dateString, parsedDateString);
    }

    @Test
    void testParseException() {
        String invalidDateString = "2020-06-14-00:00:00"; // Invalid date format

        assertThrows(ParseException.class, () -> stringToDate(invalidDateString));
    }
}

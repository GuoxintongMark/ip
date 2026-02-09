package lucy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import lucy.exception.LucyException;

public class DeadlineTest {

    @Test
    void constructor_validDateString_parsesToLocalDate() throws LucyException {
        Deadline d = new Deadline("return book", "2019-10-15");
        assertEquals(LocalDate.of(2019, 10, 15), d.getBy());
    }

    @Test
    void constructor_invalidDateString_throwsLucyException() {
        LucyException ex = assertThrows(LucyException.class, ()
                 -> new Deadline("return book", "15-10-2019"));
        assertTrue(ex.getMessage().contains("Invalid date format"));
        assertTrue(ex.getMessage().contains("yyyy-MM-dd"));
    }

    @Test
    void toString_formatsDateInEnglish() throws LucyException {
        Deadline d = new Deadline("return book", "2019-10-15");
        String s = d.toString();

        assertTrue(s.contains("return book"));
        assertTrue(s.contains("(by: Oct 15 2019)"));
    }

    @Test
    void toFileString_hasCorrectStorageFormat() throws LucyException {
        Deadline d = new Deadline("return book", "2019-10-15");
        String file = d.toFileString();

        assertTrue(file.startsWith("D | 0 | return book | 2019-10-15"));
    }
}

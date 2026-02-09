package lucy.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lucy.command.AddTodoCommand;
import lucy.command.Command;
import lucy.command.ExitCommand;
import lucy.exception.LucyException;

public class ParserTest {

    @Test
    void parse_byeCommand_returnsExitCommand() throws LucyException {
        Parser parser = new Parser();
        Command command = parser.parse("bye");

        assertTrue(command instanceof ExitCommand);
        assertTrue(command.isExit());
    }

    @Test
    void parse_todoCommandWithDescription_returnsAddTodoCommand() throws LucyException {
        Parser parser = new Parser();
        Command command = parser.parse("todo read book");

        assertTrue(command instanceof AddTodoCommand);
        assertFalse(command.isExit());
    }

    @Test
    void parse_todoWithoutDescription_throwsLucyException() {
        Parser parser = new Parser();

        assertThrows(LucyException.class, () -> parser.parse("todo"));
    }

    @Test
    void parse_unknownCommand_throwsLucyException() {
        Parser parser = new Parser();

        assertThrows(LucyException.class, () -> parser.parse("blahblah"));
    }
}

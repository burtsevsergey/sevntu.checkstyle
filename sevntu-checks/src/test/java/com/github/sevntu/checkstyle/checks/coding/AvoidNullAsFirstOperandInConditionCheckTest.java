package com.github.sevntu.checkstyle.checks.coding;

import com.github.sevntu.checkstyle.BaseCheckTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import org.junit.Test;

public class AvoidNullAsFirstOperandInConditionCheckTest extends BaseCheckTestSupport {

	@Test
    public void testAll() throws Exception
    {
        final DefaultConfiguration checkConfig = createCheckConfig(AvoidNullAsFirstOperandInConditionCheck.class);
        final String[] expected = {
        		createErrorMessage(27, "=="),
        		createErrorMessage(28, "!="),
        		createErrorMessage(33, "=="),
        		createErrorMessage(34, "!="),
        		createErrorMessage(39, "=="),
        		createErrorMessage(40, "!="),
        		createErrorMessage(48, "=="),
        		createErrorMessage(52, "=="),
        		createErrorMessage(53, "=="),
        		createErrorMessage(54, "=="),
        		createErrorMessage(55, "=="),
        		createErrorMessage(57, "=="),
        		createErrorMessage(58, "=="),
        		createErrorMessage(59, "=="),
        		createErrorMessage(65, "=="),
        		createErrorMessage(66, "=="),
        		createErrorMessage(67, "=="),

                createErrorMessage(78, "=="),
                createErrorMessage(82, "=="),
                createErrorMessage(92, "=="),
                createErrorMessage(93, "=="),
        };
        verify(checkConfig, getPath("InputAvoidNullAsFirstOperandInConditionCheck.java"), expected);
    }

    /**
     * Create error message.
     *
     * @param lineNumber Line number.
     * @param operand Operand
     * @return String message
     */
    private String createErrorMessage(int lineNumber, String operand) {
        return lineNumber + ": Null have to be second operand of '" + operand + "'.";
    }

}

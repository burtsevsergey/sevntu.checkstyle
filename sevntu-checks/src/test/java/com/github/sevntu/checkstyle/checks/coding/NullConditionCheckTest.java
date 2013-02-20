package com.github.sevntu.checkstyle.checks.coding;

import com.github.sevntu.checkstyle.BaseCheckTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import org.junit.Test;

import java.io.*;

public class NullConditionCheckTest extends BaseCheckTestSupport {

	@Test
    public void testAll() throws Exception
    {
        final DefaultConfiguration checkConfig = createCheckConfig(NullConditionCheck.class);
        final String[] expected = {
        		"26: " + createErrorMessage(26, 11, "=="),
        		"27: " + createErrorMessage(27, 11, "!="),
        		"32: " + createErrorMessage(32, 11, "=="),
        		"33: " + createErrorMessage(33, 11, "!="),
        		"38: " + createErrorMessage(38, 11, "=="),
        		"39: " + createErrorMessage(39, 11, "!="),
        		"51: " + createErrorMessage(51, 23, "=="),
        		"59: " + createErrorMessage(59, 29, "=="),
        		"60: " + createErrorMessage(60, 30, "=="),
        		"61: " + createErrorMessage(61, 12, "=="),
        		"62: " + createErrorMessage(62, 30, "=="),
        		"64: " + createErrorMessage(64, 35, "=="),
        		"65: " + createErrorMessage(65, 11, "=="),
        		"66: " + createErrorMessage(66, 11, "=="),
        		"66: " + createErrorMessage(66, 35, "=="),
        		"72: " + createErrorMessage(72, 4, "=="),
        		"73: " + createErrorMessage(73, 10, "=="),
        		"74: " + createErrorMessage(74, 14, "=="),

                "85: " + createErrorMessage(85, 14, "=="),
                "89: " + createErrorMessage(89, 16, "=="),
                "99: " + createErrorMessage(99, 12, "=="),
                "100: " + createErrorMessage(100, 33, "=="),
        };
        verify(checkConfig, getPath("coding" + File.separator
            + "InputNullConditionCheck.java"), expected);
    }

    /**
     * Create error message.
     *
     * @param lineNumber Line number.
     * @param columnNumber Column number
     * @param operand Operand
     * @return String message
     */
    private String createErrorMessage(int lineNumber, int columnNumber, String operand) {
        return lineNumber + ":" + columnNumber + ", null have to be second operand of '" + operand + "'.";
    }

}

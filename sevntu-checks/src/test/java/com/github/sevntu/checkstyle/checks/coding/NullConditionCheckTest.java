package com.github.sevntu.checkstyle.checks.coding;

import java.io.File;

import org.junit.Test;

import com.github.sevntu.checkstyle.BaseCheckTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;

public class NullConditionCheckTest extends BaseCheckTestSupport {
	
	@Test
    public void testAll() throws Exception
    {
        final DefaultConfiguration checkConfig = createCheckConfig(NullConditionCheck.class);
        final String[] expected = {
        		"26: NULL condition with == 26:11 need optimization.",
        		"27: NULL condition with != 27:11 need optimization.",
        		"32: NULL condition with == 32:11 need optimization.",
        		"33: NULL condition with != 33:11 need optimization.",
        		"38: NULL condition with == 38:11 need optimization.",
        		"39: NULL condition with != 39:11 need optimization.",
        		"51: NULL condition with == 51:23 need optimization.",
        		"59: NULL condition with == 59:29 need optimization.",
        		"60: NULL condition with == 60:30 need optimization.",
        		"61: NULL condition with == 61:12 need optimization.",
        		"62: NULL condition with == 62:30 need optimization.",
        		"64: NULL condition with == 64:35 need optimization.",
        		"65: NULL condition with == 65:11 need optimization.",
        		"66: NULL condition with == 66:11 need optimization.",
        		"66: NULL condition with == 66:35 need optimization.",
        		"72: NULL condition with == 72:4 need optimization.",
        		"73: NULL condition with == 73:10 need optimization.",
        		"74: NULL condition with == 74:14 need optimization.",
        		
        		"85: NULL condition with == 85:14 need optimization.",
        		"89: NULL condition with == 89:16 need optimization.",
        		"99: NULL condition with == 99:12 need optimization.",
        		"100: NULL condition with == 100:33 need optimization.",
        };
        verify(checkConfig, getPath("coding" + File.separator
            + "InputNullConditionCheck.java"), expected);
    }

}

////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2011  Oliver Burn
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////
package com.github.sevntu.checkstyle.checks.coding;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * Check to standardize null condition expressions.
 *
 * In C, where boolean types don't exist, it's useful to write
 * <code>if (null == variable)</code>
 * rather than
 * <code>if (variable == null)</code>
 * because if you forget one of the equal sign, you end up with
 * <code>if (variable = 5)</code>
 * which assigns null to variable and always evaluate to true.
 * But in Java, a boolean is a boolean. And with !=, there is no reason at all.
 *
 * @author Sergey Burtsev
 */
public class AvoidNullAsFirstOperandInConditionCheck extends Check {

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.EQUAL, TokenTypes.NOT_EQUAL};
    }

    @Override
    public void visitToken(DetailAST aDetailAST) {
        if (needOptimization(aDetailAST)) {
            log(aDetailAST.getLineNo(), "avoid.null.as.first.operand.in.condition",
                    aDetailAST.getText());
        }
    }

    /**
     * Return true if current expression part need optimization.
     *
     * @param aLogicNode Current logic operator node
     * @return Boolean variable
     */
    private boolean needOptimization(DetailAST aLogicNode) {
        final DetailAST[] children = getBothChildren(aLogicNode);
        final DetailAST firstOperand = children[0];
        final DetailAST secondOperand = children[1];
        return firstOperand.branchContains(TokenTypes.LITERAL_NULL)
                && !secondOperand.branchContains(TokenTypes.LITERAL_NULL);
    }

    /**
     * Return both operators children.
     *
     * @param aLogicNode Current logic operator node
     * @return Array with children
     */
    private DetailAST[] getBothChildren(DetailAST aLogicNode) {
        final DetailAST[] children = new DetailAST[2];
        int i = 0;
        for (DetailAST child = aLogicNode.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getType() != TokenTypes.LPAREN && child.getType() != TokenTypes.RPAREN) {
                children[i++] = child;
            }
        }

        return children;
    }
}
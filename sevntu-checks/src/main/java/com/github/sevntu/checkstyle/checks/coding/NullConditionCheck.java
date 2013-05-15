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
 * Safe (in C++ approach) IF conditions.
 * Example : "if (variable == null)" ==> "if (null == variable)"
 * In Java there is no possibility to make occasionally "=" instead of "==".
 * So we need to force developers to write "if (variable == null)".
 *
 * @author Sergey Burtsev
 */
public class NullConditionCheck extends Check {

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.EQUAL, TokenTypes.NOT_EQUAL};
    }

    @Override
    public void visitToken(DetailAST aDetailAST) {
        if (needOptimization(aDetailAST)) {
            log(aDetailAST.getLineNo(), "null.condition",
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
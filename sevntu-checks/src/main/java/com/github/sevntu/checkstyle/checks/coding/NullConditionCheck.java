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

public class NullConditionCheck extends Check {

    @Override
    public int[] getDefaultTokens() {
        return new int[] { TokenTypes.EQUAL };
    }

    @Override
    public void visitToken(DetailAST aDetailAST) {
        if (needOptimization(aDetailAST)) {
            log(aDetailAST.getLineNo(), "null.condition",
                    aDetailAST.getText(), aDetailAST.getLineNo(),
                    aDetailAST.getColumnNo());
        }
    }

    /**
     * Return true if current expression part need optimization
     * @param aLogicNode current logic operator node
     * @return boolean variable
     */
    private boolean needOptimization(DetailAST aLogicNode) {
        final DetailAST[] children = getBothChildren(aLogicNode);
        final DetailAST firstOperand = children[0];
        final DetailAST secondOperand = children[1];
        return !secondOperand.branchContains(TokenTypes.LITERAL_NULL)
                && firstOperand.branchContains(TokenTypes.LITERAL_NULL);
    }

    /**
     * Return both operators children
     * @param aLogicNode current logic operator node
     * @return array with children
     */
    private DetailAST[] getBothChildren(DetailAST aLogicNode) {
        final DetailAST[] children = new DetailAST[2];
        DetailAST child = aLogicNode.getFirstChild();
        for (int i = 0; child != null; ) {
            if (child.getType() != TokenTypes.LPAREN
                    && child.getType() != TokenTypes.RPAREN) {
                children[i++] = child;
            }

            child = child.getNextSibling();
        }

        return children;
    }
}

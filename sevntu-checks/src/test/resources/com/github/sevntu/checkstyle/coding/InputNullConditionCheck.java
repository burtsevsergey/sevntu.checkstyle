
public class InputNullConditionCheck {
	
	private Object classField;
	
	private boolean someFunction() {
		return false;
	}
	
	private Object returnNull() {
		return null;
	}
	
	/**
	 * Basic tests.
	 */
	public void ifCheck() {
		Object someVariable = new Object();
		Object nullVariable = null;
		
		if (null == null) {}
		
		// Compare variable
		if (someVariable == null) {}
		if (someVariable != null) {}
		if (null == someVariable) {} //!!
		if (null != someVariable) {} //!!
		
		// Compare function
		if (someFunction() == null) {}
		if (someFunction() != null) {}
		if (null == someFunction()) {} //!!
		if (null != someFunction()) {} //!!
		
		// Compare class field
		if (this.classField == null) {}
		if (classField != null) {}
		if (null == this.classField) {} //!!
		if (null != classField) {} //!!
		
		// Compare with null-variable
		if (someVariable == nullVariable) {}
		if (nullVariable == someVariable) {}
		
		// Compare with null-variable
		if (someVariable == returnNull()) {}
		if (returnNull() == someVariable) {}
		
		// Short IF tag
		someVariable = (nullVariable == null) ? true : false;
		someVariable = (null == nullVariable) ? true : false; //!!
		
		// Test for not-equals
		if (null || someVariable) {}
		if (null || someVariable == null) {}
		
		// Test more than one condition
		if (someFunction() && (someVariable == null)) {}
		if (someFunction() && null == someVariable) {} //!!
		if (someFunction() && (null == someVariable)) {} //!!
		if ((null == someVariable) && someFunction()) {} //!!
		if (someFunction() || (null == someVariable) && someFunction()) {} //!!
		
		if (someVariable == null && null == someVariable) {} //!!
		if (null == someVariable && someVariable == null) {} //!!
		if (null == someVariable && null == someVariable) {} //!! twice
		
		// Test for different code style 
		// (check line and position nuber in error message)
		if ((
			null
				== someVariable)
				&&null==someVariable
				|| (	null	==	someVariable)) {} //!!		
	}
	
	/**
	 * Almost all tests are checked in ifCheck() function,
	 * so there are only checked some tests
	 */
	public void whileCyclesCheck() {
		Object someVariable = new Object();
		
		while (someVariable == null) {}
		while (null == someVariable) {} //!!
		
		do {
			// Something...
		} while (null == someVariable); //!!
	}
	
	/**
	 * Almost all tests are checked in ifCheck() function,
	 * so there are only checked some tests
	 */
	public void forCyclesCheck() {
		Object someVariable = new Object();
		
		for (;null==someVariable;) {} //!!
		for (someVariable = null; null == someVariable; someVariable = null) {} //!!
	}

}
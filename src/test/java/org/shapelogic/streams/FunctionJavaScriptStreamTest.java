package org.shapelogic.streams;


/** Test of FunctionStream.
 * 
 * Requires Groovy to be installed. Need special installation.
 * 
 * @author Sami Badawi
 *
 */
public class FunctionJavaScriptStreamTest extends AbstractScriptingListStreamTests {

    @Override
	public void setUp() throws Exception {
		super.setUp();
		fibonacciNumbersAtStart = 2;
		fibonacciNumbersAfterOneIteration = 2;
		_language = "javascript";
		_filterFunctionExpressionEven = "function EvenNumbers_FUNCTION_(it) { return it*2 % 6 }";
		_filterFunctionExpressionThird = "function ThirdNumbers_FUNCTION_(it) { return it*8 }";
		_filterFunctionExpressionEvenNoPartName = "function XOrRule_naturalNumbersTo3_FUNCTION_(it) { return it*2 % 6 }";
	}

	ListStream<Integer> countingBaseStreamFactory(final int stopNumber) {
		ListStream<Integer> stream = StreamFactory.createListStream( "identity", 
				"function identity_FUNCTION_(a,it) { return it };", _language, new Integer(stopNumber));
		return stream;
	}
	
	/** Infinite lazy fibonnacci stream
	 * 
	 * @return
	 */
	ListStream<Integer> fibonacciBaseStreamFactory() {
		ListStream<Integer> stream = StreamFactory.createListStream("fibo",
				"function fibo_FUNCTION_(a,it) { return parseInt(fibo.get(it-2) ) + parseInt(fibo.get(it-1))}",_language,null,1,1);
		return stream;
	}
}

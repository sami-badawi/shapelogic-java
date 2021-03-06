package org.shapelogic.streams;


import junit.framework.TestCase;

/** Base class for other unit tests.
 * 
 * @author Sami Badawi
 *
 */
public abstract class AbstractListStreamTests extends TestCase {
	protected int fibonacciNumbersAtStart;
	protected int fibonacciNumbersAfterOneIteration = 1;
	protected boolean _disableTests = false;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	abstract ListStream<Integer> countingBaseStreamFactory(final int stopNumber);
	
	/** Infinite lazy fibonnacci stream
	 * 
	 * @return
	 */
	abstract ListStream<Integer> fibonacciBaseStreamFactory();	
	
	public void testCount() {
		if (_disableTests) return;
		ListStream<Integer> stream = countingBaseStreamFactory(2);
		assertNotNull(stream);
		assertNumberEquals(0,stream.next());
		assertNumberEquals(1,stream.next());
		assertNumberEquals(2,stream.next());
		assertTrue(!stream.hasNext());
	}
	
	public void testHasNext() {
		if (_disableTests) return;
		ListStream<Integer> stream = countingBaseStreamFactory(2);
		assertNotNull(stream);
		assertNumberEquals(0,stream.getList().size());
		assertNumberEquals(-1,stream.getIndex());
		assertNumberEquals(-1,stream.getIndex());
		assertTrue(stream.hasNext());
		assertNumberEquals(1,stream.getList().size());
		assertNumberEquals(-1,stream.getIndex());
		assertNumberEquals(0,stream.next());
		assertNumberEquals(1,stream.next());
		assertNumberEquals(2,stream.next());
		assertTrue(!stream.hasNext());
	}

	public void testGet() {
		if (_disableTests) return;
		ListStream<Integer> stream = countingBaseStreamFactory(2);
		assertNumberEquals(-1,stream.getIndex());
		assertNumberEquals(1,stream.get(1));
	}

	public void testFailedGet() {
		if (_disableTests) return;
		ListStream<Integer> stream = countingBaseStreamFactory(2);
		assertNumberEquals(-1,stream.getIndex());
		assertEquals(null,stream.get(10));
		assertNumberEquals(-1,stream.getIndex());
	}
	
	public void testSum() {
		if (_disableTests) return;
		ListStream<Integer> stream = countingBaseStreamFactory(2);
		int sum = 0;
		for (Number e: stream) {
			sum += e.intValue();
		}
		assertEquals(3,sum);
	}

	public void testSumOfIterable() {
		if (_disableTests) return;
		ListStream<Integer> stream = countingBaseStreamFactory(2);
		int sum = 0;
		Iterable<Integer> iterable = stream;
		for (Number e: iterable) {
			sum += e.intValue();
		}
		assertEquals(3,sum);
	}

	public void testGetCurrent() {
		if (_disableTests) return;
		ListStream<Integer> stream = countingBaseStreamFactory(10);
		assertEquals(-1,stream.getIndex());
		assertNumberEquals(5,stream.get(5));
	}
	
	public void testFibonacci() {
		if (_disableTests) return;
		ListStream<Integer> stream = fibonacciBaseStreamFactory();
		assertNotNull(stream);
		assertEquals(fibonacciNumbersAtStart,stream.getList().size());
		assertEquals(-1,stream.getIndex());
		assertEquals(-1,stream.getIndex());
		assertTrue(stream.hasNext());
//		assertEquals(fibonacciNumbersAfterOneIteration,stream.getList().size());
		assertEquals(-1,stream.getIndex());
		assertEquals(new Integer(1),stream.next());
		assertEquals(new Integer(1),stream.next());
		Object val2 = stream.next();
		assertNotNull(val2);
		assertNumberEquals(2,(Number)val2);
		assertNumberEquals(3,stream.next());
		assertNumberEquals(5,stream.next());
	}
	
	/** Maybe this should be moved to utility class */
	static public void assertNumberEquals(Number n1, Number n2 ) 
	{
		assertTrue(((Number)n2).doubleValue() == ((Number)n2).doubleValue());
	}  
}

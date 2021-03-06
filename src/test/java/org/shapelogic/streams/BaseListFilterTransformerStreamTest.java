package org.shapelogic.streams;

import java.util.Map.Entry;

import org.shapelogic.mathematics.NaturalNumberStream;

import junit.framework.TestCase;

/** Test BaseListFilterTransformerStream.<br />
 * 
 * BaseListFilterTransformerStreamTest and BaseListFilterTransformerStreamTest are the same.
 * 
 * @author Sami Badawi
 *
 */
public class BaseListFilterTransformerStreamTest extends TestCase {
	NumberedStream<Entry<Integer,Integer> > evenNumbers;
	
	@Override
	public void setUp() {
		NaturalNumberStream naturalNumbers = new NaturalNumberStream(5);
		evenNumbers = new BaseListFilterTransformerStream<Integer,Integer>(naturalNumbers){
			@Override
			public Integer transform(Integer input) {
				if (input % 2 == 0)
					return input;
				return null;
			}
		};
	} 
	
	public void testWithNext(){
		assertEquals(new Integer(0),evenNumbers.next().getKey());
		assertEquals(new Integer(2),evenNumbers.next().getKey());
		assertEquals(new Integer(4),evenNumbers.next().getKey());
		assertNull(evenNumbers.next());
	}

	public void testWithGet(){
		assertEquals(new Integer(0),evenNumbers.get(0).getKey());
		assertEquals(new Integer(2),evenNumbers.get(1).getKey());
		assertEquals(new Integer(4),evenNumbers.get(2).getKey());
//		assertNull(evenNumbers.get(3));
	}

}

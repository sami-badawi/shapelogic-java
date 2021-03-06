package org.shapelogic.streams;

import org.shapelogic.calculation.Calc1;

/** Make a stream that is using a Calc1 class to do a calculation.
 * <br />
 * In type does not seem to be used.<br />
 * 
 * @author Sami Badawi
 * 
 */
public class ListCalcStream1<In, E> extends BaseListStream1<In, E> {
	protected Calc1<In, E> _calc;
	
	public ListCalcStream1(Calc1<In, E> calc, NumberedStream<In> inputStream, int maxLast) {
		super(inputStream, maxLast);
		_calc = calc;
	}

	public ListCalcStream1(Calc1<In, E> calc, NumberedStream<In> inputStream) {
		this(calc,inputStream, inputStream.getMaxLast());
	}

	public ListCalcStream1() {
	}
	
	public ListCalcStream1(Calc1<In, E> calc, int maxLast) {
		this(calc,null,maxLast);
	}

	@Override
	public E invoke(In input) {
		return _calc.invoke(input);
	}
}

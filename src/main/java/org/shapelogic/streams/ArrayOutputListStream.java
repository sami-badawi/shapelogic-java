package org.shapelogic.streams;

import java.util.ArrayList;
import java.util.List;

import org.shapelogic.calculation.RecursiveContext;
import org.shapelogic.util.Constants;

/** ArrayOutputListStream takes a list of NumberStreams and creates a ListStream of double[].<br />
 *
 * This could be made more general, but start simple.<br />
 *
 * @author Sami Badawi
 *
 */
public class ArrayOutputListStream extends BaseListStreamList<Number,double[]> {
	
	/** Parallel to the NumberedStream. */
	protected List<String> _ohNames;
    protected boolean _setupRun = false;

	/** Use the ohName to also be the name of the input stream. <br />
	 * 
	 * @param ohNames
	 * @param maxLast
	 */
	public ArrayOutputListStream(List<String> ohNames, 
            RecursiveContext recursiveContext, String name, int maxLast) {
		super(null,maxLast);
		_ohNames = ohNames;
        _context = recursiveContext.getContext();
        _parentContext = recursiveContext.getParentContext();
		_inputStream = new ArrayList();
        if (name != null)
            _context.put(name, this);
        _name = name;
	}
	
	public ArrayOutputListStream(List<String> ohNames, RecursiveContext recursiveContext,int maxLast) {
        this (ohNames, recursiveContext, (String) null, maxLast);
    }

	public ArrayOutputListStream(List<String> ohNames, RecursiveContext recursiveContext) {
		this(ohNames, recursiveContext, Constants.LAST_UNKNOWN);
	}
	
	public ArrayOutputListStream(List<String> ohNames, RecursiveContext recursiveContext, List<NumberedStream<Number> > inputStream, int maxLast) {
		super(inputStream, maxLast);
		_ohNames = ohNames;
	}
	
	public ArrayOutputListStream(List<String> ohNames, RecursiveContext recursiveContext, List<NumberedStream<Number> > inputStream) {
		this(ohNames,recursiveContext,inputStream,Constants.LAST_UNKNOWN);
	}
	
	@Override
	public void setup() {
        _setupRun = true;
		for (String streamName: _ohNames) {
			NumberedStream numberedStream = StreamFactory.findNumberedStream(streamName, this);
			if (numberedStream != null)
				getInputStream().add(numberedStream);
			else
				throw new RuntimeException("No stream found for name: " + _ohNames);
		}
        _setupRun = true;
	}

	@Override
	public double[] invoke(List<Number> input) {
        double[] result = new double[input.size()];
		int i = 0;
		for (Number bool : input) {
			result[i] = bool.doubleValue();
            i++;
        }
		return result;
	}

	@Override
	public List<NumberedStream<Number> > getInputStream() {
        if (!_setupRun)
            setup();
		return _inputStream;
	}
}

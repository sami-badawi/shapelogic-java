package org.shapelogic.calculation;

/** This is a calculation that is done and set in the root context.
 * <br />
 * The value is immutable.
 * 
 * @author Sami Badawi
 *
 * @param <T>
 */
final public class NamedCalcFixed<T> implements CalcValue<T> {
	final private String _key;
	final private T _value;
	
	public NamedCalcFixed(String key, T value, RecursiveContext recursiveContext) {
		_key = key;
		_value = value;
		QueryCalc.getInstance().put(key, value, recursiveContext);
	}
	
	@Override
	public T getValue() {
		return _value;
	}
}
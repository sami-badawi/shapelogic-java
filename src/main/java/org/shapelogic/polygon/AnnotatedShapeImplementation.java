package org.shapelogic.polygon;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.shapelogic.logic.RootTask;

/** Instead putting all logic for AnnotatedShape in all classes implement this
 * Make them adapter for this class
 * 
 * I can make the map a lazy init map
 * 
 * @author Sami Badawi
 *
 */
public class AnnotatedShapeImplementation implements AnnotatedShape {

	protected Map<Object, Set<GeometricShape2D>> _map;

	public AnnotatedShapeImplementation() {
		_map = new HashMap<Object, Set<GeometricShape2D>>();
	}

	public AnnotatedShapeImplementation(AnnotatedShape annotatedShape) {
		_map = annotatedShape.getMap();
	}

	@Override
	public Map<Object, Set<GeometricShape2D>> getMap() {
		return _map;
	}
	
	@Override
	public void putAnnotation(GeometricShape2D shape, Object annotationKey) {
		Set<GeometricShape2D> set = _map.get(annotationKey);
		if (set == null) {
			set = new HashSet<GeometricShape2D>();
			_map.put(annotationKey, set);
		}
		set.add(shape);
	}

	@Override
	public void setup() {
		_map.clear();
	}

	@Override
	public AnnotatedShapeImplementation getAnnotatedShape() {
		return this;
	}

	@Override
	/** Get a set of all shape that has an annotation
	 * The annotation will usually be a enum.
	 * If nothing is return then see if input is a string and try to parse it 
	 * into an enum value
	 */
	public Set<GeometricShape2D> getShapesForAnnotation(Object annotation) {
		Set<GeometricShape2D> result = _map.get(annotation);
		if (result == null) {
			if (annotation instanceof String) {
				RootTask rootTask = RootTask.getInstance();
				Object obj = rootTask.findEnumValue((String)annotation);
				if (obj != null)
				result = _map.get(obj);
			}
		}
		if (result == null) {
			result = Collections.EMPTY_SET;
		}
		return result;
	}

	@Override
	public Set<Object> getAnnotationForShapes(GeometricShape2D shape) {
		HashSet<Object> result = new HashSet<Object>(); 
		for (Entry<Object, Set<GeometricShape2D>> entry: _map.entrySet()) {
			if (entry.getValue().contains(shape)) {
				result.add(entry.getKey());
			}
		}
		return result;
	}

	@Override
	public void putAllAnnotation(GeometricShape2D shape,
			Set annotationKeySet) {
		if (annotationKeySet == null)
			return;
		for (Object key: annotationKeySet)
			putAnnotation(shape, key);
	}
}

package org.shapelogic.filter;

import org.shapelogic.polygon.IPoint2D;

/** Filter points left of given part of the bounding box of a polygon
 * 
 * The constraint should be between 0 and 1.
 * 0 is the left point
 * 1 is the right point
 * 
 * @author Sami Badawi
 *
 */
public class PointLeftOfFilter extends PolygonSpatialPointFilter {

	@Override
	public boolean evaluate(Object obj) {
		IPoint2D point = (IPoint2D) obj;
		if (_limit == Double.NaN) 
			return true;
		else 
			return point.getX() < _limit;
	}

	@Override
	protected double coordinateChoser(IPoint2D diagonalVector) {
		return diagonalVector.getX();
	}
}

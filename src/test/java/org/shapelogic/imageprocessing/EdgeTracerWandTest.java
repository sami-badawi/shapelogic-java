package org.shapelogic.imageprocessing;

import org.shapelogic.imageutil.SLImage;

/** Test of EdgeTracerWand.<br />
 * 
 * @author Sami Badawi
 *
 */
public class EdgeTracerWandTest extends EdgeTracerTests {

	@Override
	public IEdgeTracer getInstance(SLImage image, int referenceColor,
			double maxDistance, boolean traceCloseToColor) {
		return new EdgeTracerWand(image,referenceColor,maxDistance,traceCloseToColor);
	}
	
	@Override
	public void setUp() {
		boxPerimeter = 21.65685424949238;
		iPerimeter =   56.828427124746185;
	}
}
